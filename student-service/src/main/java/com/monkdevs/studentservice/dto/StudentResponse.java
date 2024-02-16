package com.monkdevs.studentservice.dto;



public class StudentResponse
{
    private int id;
    private String name;
    private int age;
    private String gender;
    private School school;

    public StudentResponse()
    {
    }

    public StudentResponse(int id, String name, int age, String gender, School school) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
