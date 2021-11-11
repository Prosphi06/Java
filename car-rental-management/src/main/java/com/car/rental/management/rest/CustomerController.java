package com.car.rental.management.rest;

import com.car.rental.management.persistance.entity.Booking;
import com.car.rental.management.persistance.entity.Car;
import com.car.rental.management.persistance.entity.Customer;
import com.car.rental.management.service.BookingService;
import com.car.rental.management.service.CarService;
import com.car.rental.management.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    public final CustomerService service;
    public final CarService carService;
    public final BookingService bookingService;

    /**
     * End-point for creating new customer
     * @param customer which represent customer request
     * @return customer object
     */
    @PostMapping(value = "/add")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(service.createCustomer(customer));
    }

    /**
     * End-point for retrieving all the cars
     * @return  list of cars
     */
    @GetMapping(value = "/car")
    public List<Car> listCars(){
        return carService.listCars();
    }

    /**
     * End-point for retrieving one car
     * @param id referring to a specific car id
     * @return car details request
     */
    @GetMapping(value = "/car/{id}")
    public Car getOne(@PathVariable int id){
        return carService.findOneCar(id);
    }

    /**
     * End-point for creating new booking
     * @param booking which represent booking request
     * @return booking object
     */
    @PostMapping(value = "/booking")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking){
        return ResponseEntity.ok().body(bookingService.saveBooking(booking));
    }

    /**
     * End-point for updating one booking
     * @param id referring to a specific booking id
     * @param booking which represent booking request
     * @return booking details request
     */
    @PutMapping(value = "/booking/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int id,
                                                  @RequestBody Booking booking ) {
        bookingService.updateBooking(id, booking);
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

    /**
     * End-point for deleting a booking
     * @param id referring to a specific booking id
     * @return booking details request
     */
    @DeleteMapping(value = "/booking/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
