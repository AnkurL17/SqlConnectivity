package com.service;

import java.sql.SQLException;

import com.domain.Student;

public interface StudentService {

  void save(Student student) throws SQLException;

  void update(Student student) throws SQLException;

  Student first() throws SQLException;

  Student last() throws SQLException;

  Student next() throws SQLException;

  Student prev() throws SQLException;

  Student search(String rollNo) throws SQLException;

  Student delete(String rollNo) throws SQLException;

  void setResultSet() throws SQLException;
}
