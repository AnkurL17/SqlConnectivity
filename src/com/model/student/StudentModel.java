package com.model.student;

// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 */
public class StudentModel {

  /** The roll no. */
  private int rollNo;

  /** The name. */
  private String name;

  /** The marks. */
  private float marks;

  /** The age. */
  private int age;

  /** The gender. */
  private String gender;

  /** The address. */
  private String address;
  // column name in db remains same

  /**
   * Instantiates a new student.
   */
  public StudentModel() {}

  /**
   * Gets the roll no.
   *
   * @return the roll no
   */
  public int getRollNo() {
    return rollNo;
  }

  /**
   * Sets the roll no.
   *
   * @param rollNo the new roll no
   */
  public void setRollNo(int rollNo) {
    this.rollNo = rollNo;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the marks.
   *
   * @return the marks
   */
  public float getMarks() {
    return marks;
  }

  /**
   * Sets the marks.
   *
   * @param marks the new marks
   */
  public void setMarks(float marks) {
    this.marks = marks;
  }

  /**
   * Gets the age.
   *
   * @return the age
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age.
   *
   * @param age the new age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the gender.
   *
   * @return the gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * Sets the gender.
   *
   * @param gender the new gender
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Gets the address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the address.
   *
   * @param address the new address
   */
  public void setAddress(String address) {
    this.address = address;
  }


}
