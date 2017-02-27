package com.service;

import java.sql.SQLException;

import com.model.student.StudentModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentService.
 */
public interface StudentService {

  /**
   * Save.
   *
   * @param save the student
   * @throws SQLException the SQL exception
   */
  void save(StudentModel student) throws SQLException;

  /**
   * Update.
   *
   * @param update the student
   * @throws SQLException the SQL exception
   */
  void update(StudentModel student) throws SQLException;

  /**
   * First.
   *
   * @return the first student
   * @throws SQLException the SQL exception
   */
  StudentModel first() throws SQLException;

  /**
   * Last.
   *
   * @return the last student
   * @throws SQLException the SQL exception
   */
  StudentModel last() throws SQLException;

  /**
   * Next.
   *
   * @return the next student
   * @throws SQLException the SQL exception
   */
  StudentModel next() throws SQLException;

  /**
   * Prev.
   *
   * @return the prev student
   * @throws SQLException the SQL exception
   */
  StudentModel prev() throws SQLException;

  /**
   * Search.
   *
   * @param rollNo the roll no
   * @return the student
   * @throws SQLException the SQL exception
   */
  StudentModel search(String rollNo) throws SQLException;

  /**
   * Delete.
   *
   * @param rollNo the roll no
   * @return the student
   * @throws SQLException the SQL exception
   */
  StudentModel delete(String rollNo) throws SQLException;

  /**
   * Sets the result set.
   *
   * @throws SQLException the SQL exception
   */
  void setResultSet() throws SQLException;
}
