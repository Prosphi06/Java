package com.car.rental.management.service;

import com.car.rental.management.exception.ResourceNotFoundException;
import com.car.rental.management.persistance.entity.Car;
import com.car.rental.management.persistance.repo.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    final CarRepository repo;

    /**
     * This method create new car
     * @param car represents request for creating new car
     * @return
     */
    public Car addCar(Car car){
        return repo.save(car);
    }

    /**
     * This method a list all cars
     * @return cars object
     */
    public List<Car> listCars(){
        return repo.findAll();
    }

    /**
     * This method fetch one car
     * @param id represents specific car to find
     * @return string message
     * @return Exception
     */
    public Car findOneCar(int id){
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car with id : " + id + " not found"));
//        Optional<Car> car = repo.findById(id);
//        if(!car.isPresent()) {
//            throw new ResourceNotFoundException("Car not found");
//        }
//        return car;

    }

    /**
     * This method update existing car
     * @param car represents request for updating car
     * @param id represent id for car
     * @return string message
     * @return Exception
     */
    public Car updateCar(int id, Car car){
        Car entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car with id : " + id + " not found"));
        entity.setBrand(car.getBrand());
        entity.setColor(car.getColor());
        entity.setModel(car.getModel());
        entity.setImage(car.getImage());
       // entity.setYear(car.getYear());
        return  repo.save(entity);
    }

    /**
     * This method delete a car
     * @param id represents car to be deleted
     * @return string message
     * @return Exception
     */
    public void deleteCar(int id){
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car with id : " + id + " not found"));
         repo.deleteById(id);
    }
}
