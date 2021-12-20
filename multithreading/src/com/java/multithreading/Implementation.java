package com.java.multithreading;

public class Implementation implements Runnable{


    public static void main(String[] args){
//        Thread thread1 = new Thread("Threading-1");
//        Thread thread2 = new Thread("Threading-2");
//        thread1.start();
//        thread2.start();
//
//        System.out.println("Thread names are as follows:");
//        System.out.println(thread1.getName());
//        System.out.println(thread2.getName());
        Thread thread = new Thread();
        thread.start();
        try{
            thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.setPriority(1);
        int priority = thread.getPriority();
        System.out.println(priority);
        System.out.println("Thread Running");
    }

    public void run(){
    }

}
