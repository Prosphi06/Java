package com.javaTrainning.OOP;

public class Vehicle {
    private String color;
    private String engine;
    private String wheels;
    private String steering;

    public Vehicle(){

    }
    public Vehicle(String color, String engine, String wheels, String steering) {
        this.color = color;
        this.engine = engine;
        this.wheels = wheels;
        this.steering = steering;
    }

    public String getEngine(){
        if(engineIn()){
            System.out.println("I have an engine");
        }else
            System.out.println("I don't have an engine");
    return engine;
    }

    public boolean engineIn() {
        int size = 3;
        switch (size) {
            case 1:
                System.out.println("Small");
                break;
            case 2:
                System.out.println("Medium");
                break;
            case 3:
                System.out.println("Large");
                break;
            default:
                System.out.println("No engine");
                break;
        }
        return true;
    }
}
