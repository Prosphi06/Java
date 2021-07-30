package com.javaTrainning.logicalProgram;

import java.util.Scanner;

public class reverse {

     public static void main(String[] args) {
         Scanner s = new Scanner(System.in);
         System.out.println("Enter a word: ");
         String word =  s.nextLine();
         String reversWord = "";

         for (int i = word.length() - 1; i >= 0; --i) {
             reversWord += word.charAt(i);
             System.out.println(reversWord);
         }



    }
}
