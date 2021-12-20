package com.java.multithreading;

public class ThreadDemo extends Thread{

    private Thread thread;
    private String name;

    ThreadDemo(String name){
        this.name = name;
        System.out.println("Creating " + name);
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
