package com.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.domain.Student;
import com.service.StudentService;

import helper.DBHelper;

public class StudentServiceImpl implements StudentService {
  private PreparedStatement ps;
  private Statement st;
  private String query;
  private ResultSet rs;
  private Student student;

  @Override
  public void save(Student student) throws SQLException {
    query = "insert into student values(?,?,?,?,?,?);";
    ps = DBHelper.con.prepareStatement(query);
    ps.setInt(1, student.getRollNo());
    ps.setString(2, student.getName());
    ps.setFloat(3, student.getMarks());
    ps.setInt(4, student.getAge());
    ps.setString(5, student.getGender());
    ps.setString(6, student.getAddress());
    ps.executeUpdate();
  }

  @Override
  public void update(Student student) throws SQLException {
    query = "update student set name=?,marks=?,age=?,gender=?,address=? where rollNo=?;";
    ps = DBHelper.con.prepareStatement(query);
    ps.setInt(6, student.getRollNo());
    ps.setString(1, student.getName());
    ps.setFloat(2, student.getMarks());
    ps.setInt(3, student.getAge());
    ps.setString(4, student.getGender());
    ps.setString(5, student.getAddress());
    ps.executeUpdate();
  }

  @Override
  public Student first() throws SQLException {
    rs.first();
    return setStudent(rs);
  }

  @Override
  public Student last() throws SQLException {
    rs.last();
    return setStudent(rs);
  }

  @Override
  public Student next() throws SQLException {
    if (!rs.isLast()) {
      rs.next();
      return setStudent(rs);
    }
    return null;
  }

  @Override
  public Student prev() throws SQLException {
    if (!rs.isFirst()) {
      rs.previous();
      return setStudent(rs);
    }
    return null;
  }

  @Override
  public Student search(String rollNoTxt) throws SQLException {
    st = DBHelper.con.createStatement();
    query = "select * from student where rollNo=" + rollNoTxt;
    rs = st.executeQuery(query);
    if (rs.isBeforeFirst()) {
      rs.first();
      return setStudent(rs);
    } else
      return null;
  }

  @Override
  public Student delete(String rollNo) throws SQLException {
    query = "delete from student where rollNo=?;";
    ps = DBHelper.con.prepareStatement(query);
    ps.setInt(1, student.getRollNo());
    ps.executeUpdate();
    return first();
  }

  private Student setStudent(ResultSet rs) throws SQLException {
    student = new Student();
    student.setRollNo(rs.getInt("rollNo"));
    student.setName(rs.getString("name"));
    student.setMarks(rs.getFloat("marks"));
    student.setAge(rs.getInt("age"));
    student.setGender(rs.getString("gender"));
    student.setAddress(rs.getString("address"));
    return student;
  }

  @Override
  public void setResultSet() throws SQLException {
    st = DBHelper.con.createStatement();
    query = "select * from student";
    rs = st.executeQuery(query);
  }

}
