package com.java.multithreading;

public class Threading implements Runnable{

    Thread thread;
    private String name;

    Threading(String name){
        this.name = name;
    }

    public void run(){
        System.out.println("Thread running " + name);
        for(int i = 0; i < 3; i++){
            System.out.println(i);
            System.out.println(name);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
        }
    }

    public void start(){
        System.out.println("Thread started");
        if(thread == null){
            thread = new Thread(this,name);
            thread.start();
        }
    }
}
