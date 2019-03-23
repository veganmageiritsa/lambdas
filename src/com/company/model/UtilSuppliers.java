package com.company.model;

import java.util.Date;
import java.util.function.Supplier;

public class UtilSuppliers {

    public static Supplier<Date> getSystemDate(){
        return Date::new;
    }

    public static Supplier<String> passwordGen(){
        String symbols="ABCDEFGHIJKLMNOPQRSTUVWXYZ#$@";
        Supplier<Integer> d=()->(int)(Math.random()*10);
        Supplier<Character> c=()->symbols.charAt((int)(Math.random()*29));
        String pwd="";
        for(int i =1;i<=8;i++)  {
            if(i%2==0)
                pwd=pwd + d.get();
            else
                pwd= pwd + c.get();
        }
        String finalPwd = pwd;
        return ()-> finalPwd;
    }
}
