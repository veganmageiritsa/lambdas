package com.company.model;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for(int i=1;i<=10;i++)
            System.out.println(i);
    }
}
