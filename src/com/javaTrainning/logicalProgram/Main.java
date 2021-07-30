package com.javaTrainning.logicalProgram;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter decimal number: ");
        double number =  sc.nextDouble();
        String conv = Double.toString(number);
        String[] separate = conv.split("\\.");
        int val1 = Integer.parseInt(separate[0]);
        int val2 = Integer.parseInt(separate[1]);
        System.out.println(val1);
        System.out.println(val2);

        if(val1 == val2){
            System.out.println("Identical");
        }
    }
}
