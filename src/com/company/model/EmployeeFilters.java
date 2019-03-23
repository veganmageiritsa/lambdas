package com.company.model;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeFilters {

    private  EmployeeFilters() {
    }

    public static Predicate<Employee> isMale(){
        return p ->  p.getGender().equalsIgnoreCase("M");
    }

    public static Predicate<Employee> isFeMale(){
        return p ->  p.getGender().equalsIgnoreCase("F");
    }


    public static Predicate<Employee> isMaleWithAgeMoreThan( int age) {
        return p -> p.getAge() > age && p.getGender().equalsIgnoreCase("M");
    }

    public static Predicate<Employee> isFemaleWithAgeMoreThan(int age) {
        return p -> p.getAge() > age && p.getGender().equalsIgnoreCase("F");
    }

    public static Predicate<Employee> isAgeMoreThan(Integer age) {
        return p -> p.getAge() > age;
    }

    public static Predicate<Employee> isSalaryInRange(Integer low, Integer high) {
        return p -> p.getSalary() > low && p.getSalary()<high;
    }

    public static Predicate<Employee> lastNameStartsWithLetter(Character c){
        return p->p.getLastName().charAt(0)==c;
    }


    public static BiConsumer<Employee,Double>giveSalaryRaise(){
        return (e,d)->e.getSalary()=e.getSalary()*d/100;
    }

    public static List<Employee> filterEmployees (List<Employee> employees,
                                                  Predicate<Employee> predicate)
    {
        return employees.stream()
                .filter( predicate )
                .collect(Collectors.<Employee>toList());
    }
}
