package com.car.rental.management.service;

import com.car.rental.management.persistance.entity.Car;
import com.car.rental.management.persistance.repo.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarServiceTest {

    @Autowired
    CarService carService;
    @Autowired
     CarRepository repo;

    @BeforeEach
    public void setUp() throws Exception{
    System.out.println("inside setup");
    }

    @AfterEach
    public void tearDown() throws Exception{
        System.out.println("inside tear down");
    }

    /**
     * Success test for retrieving car details with id
     */
   @Test
    public void test_find_car_byId()throws Exception{
        Car actual = new Car(1,"Kia","Picanto","white",550, "Users/Sphiwe/Downloads/car1.png");
        Car expected = carService.findOneCar(actual.getCarId());
        assertEquals(actual, expected);
    }

    /**
     * Failure test for retrieving car with wrong id
     */
    @Test
    public void test_find_car_with_wrong_Id()throws Exception{
        Car actual = new Car(3,"Kia","Picanto","red",550, "Users/Sphiwe/Downloads/car1.png");
        try {
            carService.findOneCar(actual.getCarId());
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Failure test for retrieving car with zeero id
     */
    @Test
    public void test_find_car_with_zero_Id()throws Exception{
        Car actual = new Car(0,"Kia","Picanto","red",500, "Users/Sphiwe/Downloads/car.png");
        try {
            carService.findOneCar(0);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Success test for creating car details
     */
    @Test
    public void test_create_car()throws Exception{
       Car actual = new Car();
       // actual.setCarId(2);
       actual.setBrand("Kia");
       actual.setModel("Picanto");
       actual.setColor("white");
       actual.setPrice(550);
       actual.setImage("Users/Sphiwe/Downloads/car1.png");
       Car expected = carService.addCar(actual);
       assertThat(expected).isNotNull();
       assertThat(expected.getCarId()).isGreaterThan(0);
    }

    /**
     * Failure test for creating car with null body
     */
    @Test
    public void test_create_car_null_body()throws Exception{
        Car actual = new Car();
        try {
            actual = null;
           carService.addCar(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Failure test for creating car with missing fields
     */
    @Test
    public void test_create_car_missing_field()throws Exception{
        Car actual = new Car();
        //actual.setCarId(3);
        actual.setColor("white");
        actual.setPrice(550);
        actual.setImage("Users/Sphiwe/Downloads/car1.png");

        try {
           carService.addCar(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Success test for retrieving all cars
     */
    @Test
    public void test_find_all_cars()throws Exception{

        List<Car> expected = carService.listCars();
        assertThat(expected.size()).isGreaterThan(0);
    }

    /**
     * Failure test for retrieving null body
     */
    @Test
    public void test_find_all_cars_null()throws Exception{
        try {
            List<Car> expected = carService.listCars();
            expected = null;
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }

    }

    /**
     * Success test for deleting car details with id
     */
    @Test
    public void test_for_deleting_a_car(){
        Car actual = carService.findOneCar(5);
        carService.deleteCar(actual.getCarId());
        assertThat(repo.existsById(5)).isFalse();
    }


    /**
     * Failure test for deleting a non existing car id
     */
    @Test
    public void test_for_deleting_a_non_existent_car(){
        Car actual = new Car(6,"Kia","Picanto","white",500, "Users/Sphiwe/Downloads/car1.png");
        try {
            Car expected = carService.findOneCar(actual.getCarId());
            carService.deleteCar(expected.getCarId());
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Success test for updating car details with id
     */
    @Test
    public void test_for_updating_a_car(){
        Car actual = carService.findOneCar(2);
        actual.setPrice(600);
        Car expected = carService.addCar(actual);
        assertThat(expected.getPrice()).isEqualTo(600);
    }

    /**
     * Failure test for updating a non existing car id
     */
    @Test
    public void test_for_updating_a_non_existent_car(){
        Car actual = new Car(6,"Kia","Picanto","white",500, "Users/Sphiwe/Downloads/car1.png");
        try {
            carService.findOneCar(6);
            carService.updateCar(6,actual);
            carService.addCar(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }
}
