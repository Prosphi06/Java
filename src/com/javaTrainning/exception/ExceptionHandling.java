package com.javaTrainning.exception;

import java.util.Scanner;

public class ExceptionHandling {

    public static void main(String[] args) {
        try{
            int num = Integer.parseInt("234");
                    System.out.println(num);
        }catch (NumberFormatException ex) {

            System.out.println("Number format occur");
        }

        try {
            String word = null;
            System.out.println("the value: " + word.length());
        }catch (NullPointerException ex){
            System.out.println("NullPointer Exception occur");
        }

        try {
            Scanner sc = new Scanner(System.in);
           System.out.println("Enter a word: ");
            String word = sc.nextLine();
            System.out.println(word.length());
            char c = word.charAt(0);
            c = word.charAt(5);
            System.out.println(c);
        }catch (StringIndexOutOfBoundsException ex){
            System.out.println("String Index Out Of Bounds Exception");
        }
    }

}
