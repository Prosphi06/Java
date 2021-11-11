package com.car.rental.management.service;

import com.car.rental.management.persistance.entity.Booking;
import com.car.rental.management.persistance.repo.BookingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingServiceTest {

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingRepository repo;

    @BeforeEach
    public void setUp() throws Exception{
        System.out.println("inside setup");
    }

    @AfterEach
    public void tearDown() throws Exception{
        System.out.println("inside tear down");
    }

    /**
     * Success test for creating booking details
     */
    @Test
    public void test_create_booking()throws Exception{
        Booking actual = new Booking();
        actual.setPickupDate("2020-03-31");
        actual.setDropOffDate("2021-03-31");
        actual.setDeposit(67000.00);
        actual.setPrice(550.00);
        Booking expected = bookingService.saveBooking(actual);
        assertThat(expected).isNotNull();
        assertThat(expected.getBookingId()).isGreaterThan(0);
    }

    /**
     * Failure test for creating booking with null body
     */
    @Test
    public void test_create_booking_null_body()throws Exception{
        Booking actual = new Booking();
        try {
            actual = null;
            bookingService.saveBooking(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Failure test for creating booking with missing fields
     */
    @Test
    public void test_create_booking_missing_field()throws Exception{
        Booking actual = new Booking();
        actual.setPickupDate("2020-03-31");
        actual.setDropOffDate("2020-03-31");

        try {
            bookingService.saveBooking(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Success test for retrieving booking details with id
     */
    @Test
    public void test_find_booking_byId()throws Exception{
        Booking actual = bookingService.getBookingById(1);
        Booking expected = bookingService.getBookingById(actual.getBookingId());
        assertEquals(actual, expected);
    }

    /**
     * Failure test for retrieving invalid booking
     */
    @Test
    public void test_find_invalid_booking()throws Exception{
        Booking actual = new Booking(6,"2020-06-01","2020-04-01",6000.00,500.00,null,null);
        try {
            bookingService.getBookingById(actual.getBookingId());
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Failure test for retrieving booking with zero id
     */
    @Test
    public void test_find_booking_with_zero_Id()throws Exception{
        Booking actual = new Booking(0,"2020-06-01","2020-04-01",6000.00,500.00,null,null);
        try {
            bookingService.getBookingById(actual.getBookingId());
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Success test for deleting booking details with id
     */
    @Test
    public void test_for_deleting_a_booking(){
        Booking actual = bookingService.getBookingById(4);
        bookingService.deleteBooking(actual.getBookingId());

        assertThat(repo.existsById(4)).isFalse();
    }

    /**
     * Failure test for deleting a non existing booking id
     */
    @Test
    public void test_for_deleting_a_non_existent_booking(){
       Booking actual = new Booking(6,"2020-06-01","2020-04-01",6000.00,500.00,null,null);
       try {
            Booking expected = bookingService.getBookingById(actual.getBookingId());
            bookingService.deleteBooking(expected.getBookingId());
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Success test for updating booking details with id
     */
    @Test
    public void test_for_updating_a_booking(){
        Booking actual = bookingService.getBookingById(3);
        actual.setDeposit(650.0);
        Booking expected = bookingService.saveBooking(actual);
        assertThat(expected.getDeposit()).isEqualTo(650.0);
    }

    /**
     * Failure test for updating a non existing booking id
     */
    @Test
    public void test_for_updating_a_non_existent_booking(){
        Booking actual = new Booking(6,"2020-06-01","2020-04-01",6000.00,500.00,null,null);

        try {
            bookingService.getBookingById(6);
            bookingService.updateBooking(6,actual);
            bookingService.saveBooking(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Success test for retrieving all bookings
     */
    @Test
    public void test_find_all_bookings()throws Exception{

        List<Booking> expected = bookingService.getAllBookings();
        assertThat(expected.size()).isGreaterThan(0);
    }

    /**
     * Failure test for retrieving null body
     */
    @Test
    public void test_find_all_bookings_null()throws Exception{
        try {
            List<Booking> expected = bookingService.getAllBookings();
            expected = null;
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }

    }


}
