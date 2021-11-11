package com.car.rental.management.service;

import com.car.rental.management.exception.ResourceNotFoundException;
import com.car.rental.management.persistance.entity.Booking;
import com.car.rental.management.persistance.repo.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    final BookingRepository repository;

    /**
     * This method a list all booking
     * @return bookings object
     */
    public List<Booking> getAllBookings(){
        return repository.findAll();
    }

    /**
     * This method create new booking
     * @param booking represents request for creating new car
     * @return saved record
     */
    public Booking saveBooking(Booking booking){
        return repository.save(booking);    }

    /**
     * This method fetch one booking
     * @param id represents specific booking to find
     * @return the booking
     * @return Exception if id is not found
     */
    public Booking getBookingById(int id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
    }

    /**
     * This method delete a booking
     * @param id represents booking to be deleted
     * @return status
     * @return Exception
     */
    public void deleteBooking(int id){
        Booking entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
        repository.deleteById(id);
    }

    /**
     * This method update existing booking
     * @param booking represents request for updating booking
     * @param id represent id for booking
     * @return status code
     * @return Exception
     */
    public void  updateBooking( int id, Booking booking){
        Booking entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for id: " +id));
        entity.setPickupDate(booking.getPickupDate());
        entity.setDropOffDate(booking.getDropOffDate());
        entity.setDeposit(booking.getDeposit());
        entity.setPrice(booking.getPrice());
        entity.setCar(booking.getCar());
        entity.setCustomer(booking.getCustomer());
        repository.save(entity);
    }
}
