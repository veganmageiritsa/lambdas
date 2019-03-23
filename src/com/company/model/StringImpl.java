package com.company.model;

public class StringImpl implements StringInterface {
    @Override
    public int getLength(String s) {
        return s.length();
    }
}
