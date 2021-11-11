package com.car.rental.management.rest;

import com.car.rental.management.persistance.entity.Booking;
import com.car.rental.management.persistance.entity.Car;
import com.car.rental.management.service.BookingService;
import com.car.rental.management.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin")
public class AdminController {

    final CarService carService;
    final BookingService bookingService;

    /**
     * End-point for retrieving all the cars
     * @return  list of cars
     */
    @GetMapping(value = "/car")
    public List<Car> listCars(){
        return carService.listCars();
    }

    /**
     * End-point for creating new car
     * @param car which represent car request
     * @return car object
     */
    @PostMapping(value = "/car")
    public ResponseEntity<?> createCar(@RequestBody Car car){
        return  ResponseEntity.ok(carService.addCar(car));
    }

    /**
     * End-point for updating one car
     * @param id referring to a specific car id
     * @param car which represent car request
     * @return car details request
     */
    @PutMapping(value = "/car/{id}")
    public Car updateCar(@PathVariable int id, @RequestBody Car car){
        return  carService.updateCar(id,car);
    }

    /**
     * End-point for deleting a car
     * @param id referring to a specific car id
     * @return car details request
     */
    @DeleteMapping(value = "/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id, Car car){
        carService.deleteCar(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
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
     * End-point for retrieving all the bookings
     * @return  list of bookings
     */
    @GetMapping("/booking")
    public List<Booking> getAllBooking() {return bookingService.getAllBookings();}

    /**
     * End-point for retrieving one booking
     * @param id referring to a specific booking id
     * @return booking details request
     */
    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getInsuranceById(@PathVariable int id){
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok().body(booking);
    }

    /**
     * End-point for updating one booking
     * @param id referring to a specific booking id
     * @param booking which represent booking request
     * @return booking details request
     */
    @PutMapping(value = "/booking/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable(value = "id") int id,
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
    public ResponseEntity<Booking> deleteBooking(@PathVariable(value = "id") int id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
