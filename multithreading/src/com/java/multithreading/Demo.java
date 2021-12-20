package com.java.multithreading;

public class Demo {
    public static void main(String[] args){
        Threading threading = new Threading("thread");
        threading.start();
        Threading threading1 = new Threading("thread-1");
        threading1.start();

    }
}
