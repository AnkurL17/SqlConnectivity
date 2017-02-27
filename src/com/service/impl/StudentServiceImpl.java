package com.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.student.StudentModel;
import com.service.StudentService;

import helper.DBHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentServiceImpl.
 */
public class StudentServiceImpl implements StudentService {

  /** The ps. */
  private PreparedStatement ps;

  /** The st. */
  private Statement st;

  /** The query. */
  private String query;

  /** The rs. */
  private ResultSet rs;

  /** The student. */
  private StudentModel student;

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#save(com.domain.Student)
   */
  @Override
  public void save(StudentModel student) throws SQLException {
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

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#update(com.domain.Student)
   */
  @Override
  public void update(StudentModel student) throws SQLException {
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

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#first()
   */
  @Override
  public StudentModel first() throws SQLException {
    rs.first();
    return setStudent(rs);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#last()
   */
  @Override
  public StudentModel last() throws SQLException {
    rs.last();
    return setStudent(rs);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#next()
   */
  @Override
  public StudentModel next() throws SQLException {
    if (!rs.isLast()) {
      rs.next();
      return setStudent(rs);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#prev()
   */
  @Override
  public StudentModel prev() throws SQLException {
    if (!rs.isFirst()) {
      rs.previous();
      return setStudent(rs);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#search(java.lang.String)
   */
  @Override
  public StudentModel search(String rollNoTxt) throws SQLException {
    st = DBHelper.con.createStatement();
    query = "select * from student where rollNo=" + rollNoTxt;
    rs = st.executeQuery(query);
    if (rs.isBeforeFirst()) {
      rs.first();
      return setStudent(rs);
    } else
      return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#delete(java.lang.String)
   */
  @Override
  public StudentModel delete(String rollNo) throws SQLException {
    query = "delete from student where rollNo=?;";
    ps = DBHelper.con.prepareStatement(query);
    ps.setInt(1, student.getRollNo());
    ps.executeUpdate();
    return first();
  }

  /**
   * Sets the student.
   *
   * @param rs the rs
   * @return the student
   * @throws SQLException the SQL exception
   */
  private StudentModel setStudent(ResultSet rs) throws SQLException {
    student = new StudentModel();
    student.setRollNo(rs.getInt("rollNo"));
    student.setName(rs.getString("name"));
    student.setMarks(rs.getFloat("marks"));
    student.setAge(rs.getInt("age"));
    student.setGender(rs.getString("gender"));
    student.setAddress(rs.getString("address"));
    return student;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.service.StudentService#setResultSet()
   */
  @Override
  public void setResultSet() throws SQLException {
    st = DBHelper.con.createStatement();
    query = "select * from student";
    rs = st.executeQuery(query);
  }

}
