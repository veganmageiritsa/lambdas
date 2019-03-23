package com.company.model;

public class SumImpl implements SumInterface {
    @Override
    public void sum(int a, int b) {
        System.out.println("The sum is : " + a+b);
    }
}
