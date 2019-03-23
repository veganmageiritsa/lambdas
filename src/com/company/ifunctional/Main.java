package com.company.ifunctional;

import com.company.model.Employee;
import com.company.model.EmployeeFunctions;
import com.company.model.Human;
import com.company.model.UtilSuppliers;
import com.company.utilities.GeneralBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static com.company.model.EmployeeFilters.*;
import static com.company.model.EmployeeFunctions.getSalaryRange;
import static com.company.model.EmployeeFunctions.salaryRaise;

public class Main {

    public static void main(String[] args) {

        Predicate<Human> humanPredicate  = human -> human.getAge()>12;

        Human h1 = GeneralBuilder.Builder.build(Human.class)
                .with(s -> s.setName("George"))
                .with(s -> s.setAge(13))
                .with(s->s.setMobilePhone("6984352312"))
                .get();
        Human h2 = GeneralBuilder.Builder.build(Human.class)
                .with(s -> s.setName("Fred"))
                .with(s -> s.setAge(14))
                .with(s->s.setMobilePhone("6908739204"))
                .get();
        Human h3 = GeneralBuilder.Builder.build(Human.class)
                .with(s -> s.setName("Sarah"))
                .with(s -> s.setAge(15))
                .with(s->s.setMobilePhone("6903456342"))
                .get();

        List<Human> humans = new ArrayList<>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);

        humans.forEach(humanPredicate::test);
        humans.removeIf(humanPredicate);
        System.out.println(humans);

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

        List<Employee> employees = new ArrayList<>();
        employees.addAll(Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10));

        System.out.println( filterEmployees(employees, isAgeMoreThan(21)) );

        System.out.println( filterEmployees(employees, isFeMale()) );

        System.out.println( filterEmployees(employees, isSalaryInRange(1500,1800).and(isAgeMoreThan(33))) );


        System.out.println(filterEmployees(employees,lastNameStartsWithLetter('A')));
        employees.removeIf(isAgeMoreThan(12));

        employees.removeIf(null);


        //Function Interface
        employees.stream()
                .map(getSalaryRange::apply);
//
//        employees.stream()
//                .map(e-> salaryRaise().apply(e,0.2)));
        // Supplier Interface
           e1.setFirstName(UtilSuppliers.passwordGen().get());
    }
}
