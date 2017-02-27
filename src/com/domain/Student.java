package com.domain;

public class Student {

  private int rollNo;
  private String name;
  private float marks;
  private int age;
  private String gender;
  private String address;
  // column name in db remains same

  public Student() {}

  public int getRollNo() {
    return rollNo;
  }

  public void setRollNo(int rollNo) {
    this.rollNo = rollNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getMarks() {
    return marks;
  }

  public void setMarks(float marks) {
    this.marks = marks;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


}
