package com.company.model;

public class Human {
    private String name;
    private int age;
    private String mobilePhone;

    public Human() {
    }

    public Human(String name, int age, String mobilePhone) {
        this.name = name;
        this.age = age;
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
