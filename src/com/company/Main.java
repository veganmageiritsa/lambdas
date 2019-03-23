package com.company;

import com.company.model.Human;
import com.company.model.StringInterface;
import com.company.model.SumInterface;
import com.company.utilities.GeneralBuilder;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

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

        Collections.sort(humans, Comparator.comparing(Human::getAge));
        // sort by comparator
        humans.sort(
                Comparator.comparing(Human::getName));


        humans.sort(
                Comparator.comparing(Human::getName).thenComparing(Human::getAge)
        );


        //sort stream with comparator
        Comparator<Human> nameComparator = Comparator.comparing(Human::getName);

        List<Human> sortedHumans = humans.stream()
                .sorted(nameComparator)
                .collect(Collectors.toList());

        humans.forEach(System.out::println);

        Comparator<Human> ageComparator = Comparator.comparing(Human::getAge);
        List<Human> sortedAgeHumans = humans.stream()
                .sorted(nameComparator)
                .collect(Collectors.toList());

        humans.stream().forEach(System.out::println);


        SumInterface sumInterface = (a, b) -> System.out.println("The sum is : " + a + b);
        sumInterface.sum(10, 20);

        StringInterface stringInterface = (s) -> s.length();
        System.out.println(stringInterface.getLength("akfbsjbafbkj"));

//        Runnable runnable = () -> {
//            for (int i = 1; i <= 10; i++)
//                System.out.println(i);
//        };
//        new Thread(runnable).start();


        // Sorting Sets
        TreeSet<Human> humanTreeSet = new TreeSet<>(Comparator.comparing(Human::getAge));
        humanTreeSet.addAll(humans);

        humanTreeSet.removeIf(human -> human.getAge()<12);


        //Sets with streams
        TreeSet<Human> humanTreeSet1 = humanTreeSet.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Human::getAge))));


        // TreeMaps

        TreeMap<String,Human> humanTreeMap = new TreeMap<>();
        humanTreeMap.put(h1.getMobilePhone(),h1);
        humanTreeMap.put(h2.getMobilePhone(),h2);
        humanTreeMap.put(h3.getMobilePhone(),h3);

        Stream m=humanTreeSet.stream();
        Map<String, Human> results = humanTreeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Map<String, Human> byValue = humanTreeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Human::getMobilePhone)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


    }





}

