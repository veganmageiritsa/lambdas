package com.company.stream;

import com.company.model.Employee;
import com.company.utilities.GeneralBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamMain {
    public static void main(String[] args) {



        List<Employee> employees = populateEmployees();

        long count =employees.stream()
                .filter(employee -> employee.getSalary()>=1500.00)
                .count();
        System.out.println(count);

        Double totalSalaries = employees.stream()
                .map(emp -> emp.getSalary())
                .reduce(0.00,(total,salary) -> total+salary);

        Double totalSalaries2 = employees.stream()
                .map(emp -> emp.getSalary())
                .reduce(0.0 , Double::sum);

        employees.stream()
                .map(e->e.getLastName())
                .reduce("",String::concat);

        //do not use method reference when manipulating data
        // do not use if there is conflict between instance and static methods


       Double totalEvenSalariesDoubled = employees.stream()
                .filter(e->e.getSalary()%2==0)
                .map(e->e.getSalary()*2)
                .reduce(0.0,Double::sum);

        Double totalEvenSalariesDoubled2 = employees.stream()
                .filter(e->e.getSalary()%2==0)
                .mapToDouble(e->e.getSalary()*2)
                .sum();

        //sum is a specialized reduce operation
        // reduce brings values together, rather than move them like filter and map


        // dont do this sharing mutability if it runs in parallel stream is devils work
        List<Double> salaries = new ArrayList<>();
        employees.stream()
                .filter(e->e.getSalary()%2==0)
                .mapToDouble(e->e.getSalary()*2)
                .forEach(e->salaries.add(e));

        // do this
        ArrayList<Double> salariesCorrect = employees.stream()
                .filter(e -> e.getSalary() % 2 == 0)
                .mapToDouble(e -> e.getSalary() * 2)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        Map<Employee, Double> salaryMap = employees.stream()
                .collect(toMap(
                        employee -> employee,
                        employee -> employee.getSalary()
                        )
                );

        Optional<Double> smallerSalaryBiggerThan1500 = salariesCorrect.stream()
                .filter(StreamMain::isGtThanConstant) //two ways of reference
                .filter(x-> isGtThanConstant(x))
                .filter(x-> isGtThan(1500.00,x))
                .map(x -> 2 * x)
                .findFirst();

        System.out.println(smallerSalaryBiggerThan1500.get());

        Map<String, List<Employee>> genderMap = employees.stream()
                .collect(groupingBy(Employee::getGender));

        Map<String, List<Double>> genderSalaries = employees.stream()
                .collect(groupingBy(
                        Employee::getGender,
                        mapping(Employee::getSalary,
                                toList())));

    }

    private static boolean isGtThan(double i, Double x) {
        return x>=i;
    }

    private static boolean isGtThanConstant(double number) {
        return number>=1500;
    }


    private static List<Employee> populateEmployees() {
        Employee e1 = GeneralBuilder.Builder.build(Employee.class)
                .with(s -> s.setFirstName("Rick"))
                .with(s->s.setLastName("Beethovan"))
                .with(s -> s.setAge(23))
                .with(s->s.setGender("M"))
                .with(s->s.setId(1))
                .with(s->s.setSalary(1500.00))
                .get();

        Employee e2 = new Employee(2,13,"F","Martina","Hengis",1300.00);
        Employee e3 = new Employee(3,43,"M","Ricky","Martin",1500.00);
        Employee e4 = new Employee(4,26,"M","Jon","Lowman",1800.00);
        Employee e5 = new Employee(5,19,"F","Cristine","Maria",1600.00);
        Employee e6 = new Employee(6,15,"M","David","Feezor",1700.00);
        Employee e7 = new Employee(7,68,"F","Melissa","Roy",1200.00);
        Employee e8 = new Employee(8,79,"M","Alex","Gussin",1900.00);
        Employee e9 = new Employee(9,15,"F","Neetu","Singh",1400.00);
        Employee e10 = new Employee(10,45,"M","Naveen","Jain",1600.00);
        return (Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10));
    }

    private static int compute(int k,int n){
        return  Stream.iterate(k,e->e+1)
                .filter(e->e%2==0)
                .filter(e->Math.sqrt(e)>20)
                .mapToInt(e->e*2)
                .limit(n)
                .sum();
    }

    private static List<String> getEmployeesNames(List<Employee> employees){
        List<String> employeesNames = employees.stream()
                .filter(employee -> employee.getLastName() != null)
                .map(employee -> employee.getLastName().toUpperCase())
                .collect(Collectors.toList());
        return employeesNames;
    }
}
