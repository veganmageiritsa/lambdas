package com.company.model;

import java.util.function.BiFunction;
import java.util.function.Function;

public class EmployeeFunctions {

    public static Function<Employee,String> getSalaryRange= e->{
        if(e.getSalary()>=2000)
            return "Very Good Money";
        else if(e.getSalary()>=1500)
            return "Good Money";
        else if(e.getSalary()>=1000)
            return " Money";
        else
            return "No Money";

    };

    public static BiFunction<Employee, Double, Double> salaryRaise(){
        BiFunction<Employee,Double,Double> f = (e,t)->e.getSalary()*t/100;
        return f;
    }
    /*Function<Employee,Employee> salaryRaise=e-> {
        e.getSalary()=e.getSalary()+300;
        return e;
    };*/
}
