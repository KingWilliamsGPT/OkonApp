package com.example.touchbro;

public class Student {
    public int id;
    public String matricNo;
    public String name;
    public int age;
    public String department;
    public byte[] profilePic;

    public Student(int id, String matricNo, String name, int age, String department, byte[] profilePic) {
        this.id = id;
        this.matricNo = matricNo;
        this.name = name;
        this.age = age;
        this.department = department;
        this.profilePic = profilePic;
    }
}
