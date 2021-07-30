package com.javaTrainning.logicalProgram;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class calculateDays {

    public static void main(String arg[]) {
        Scanner c = new Scanner(System.in);
        System.out.println("Enter your birth date (yyyy-MM-dd): ");
        String dateOfBirth = c.nextLine();

        LocalDate todayDate = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(dateOfBirth);

        short noOfDaysOnEarth = (short) ChronoUnit.DAYS.between(birthDate, todayDate);

        System.out.println("You already spend: " + noOfDaysOnEarth + " days on planet earth");
    }
}
