package com.java.multithreading;

public class Main {

//    public void run(){
//        System.out.println("My thread is in running state");
//    }
    public static void main(String[] args) {
//	    Main obj = new Main();
//	    obj.start();
        ThreadDemo threading = new ThreadDemo("thread");
        threading.start();
        ThreadDemo threading1 = new ThreadDemo("thread-1");
        threading1.start();
    }
}
