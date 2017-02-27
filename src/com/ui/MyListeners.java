package com.ui;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import com.model.student.StudentModel;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import com.ui.StudentUI;

// TODO: Auto-generated Javadoc
/**
 * The Class MyListeners.
 */
public class MyListeners extends WindowAdapter implements ActionListener, KeyListener {

  /** The student UI. */
  private StudentUI studentUI;

  /** The student service. */
  private StudentService studentService = new StudentServiceImpl();

  /** The student. */
  private StudentModel student;

  /** The original roll no. */
  private String originalRollNo;

  /** The original name. */
  private String originalName;

  /** The original marks. */
  private String originalMarks;

  /** The Numeric regex. */
  private String NumericRegex = "\\d+";

  /** The Float regex. */
  private String FloatRegex = "\\d+\\.\\d+";

  /** The Character regx. */
  private String CharacterRegx = "^[a-zA-Z\\s]+";

  /**
   * Instantiates a new my listeners.
   *
   * @param studentUI the student UI
   * @throws SQLException the SQL exception
   * @throws InterruptedException the interrupted exception
   */
  public MyListeners(StudentUI studentUI) throws SQLException, InterruptedException {
    this.studentUI = studentUI;
    studentService.setResultSet();

    // originalRollNo = studentUI.rollNoTxt.getText().trim();
    // originalName = studentUI.nameTxt.getText().trim();
    // originalMarks = studentUI.markTxt.getText().trim();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Button button = (Button) e.getSource();
    String buttonLabel = button.getLabel();
    try {
      studentFunctions(buttonLabel);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }

  }

  /**
   * Student functions.
   *
   * @param choice the choice
   * @throws SQLException the SQL exception
   * @throws InterruptedException the interrupted exception
   */
  public void studentFunctions(String choice) throws SQLException, InterruptedException {
    switch (choice.toLowerCase()) {
      case "save":
        if (validateFields()) {
          studentService.save(createStudentObject());
          studentService.setResultSet();
          studentService.last();
          popup("User saved successfully");
        } else
          popup("RollNo,Name and Marks can't be left empty");
        break;
      case "delete":
        setToUI(studentService.delete(studentUI.rollNoTxt.getText()));
        studentService.first();
        popup("User deleted successfully");
        break;
      case "update":
        if (validateFields()) {
          studentService.update(createStudentObject());
          studentService.setResultSet();
          popup("User updated successfully");
        } else
          popup("RollNo,Name and Marks can't be left empty");
        break;
      case "search":
        searchify(studentUI.rollNoTxt.getText());
        break;
      case "first":
        setToUI(studentService.first());
        uiUpdates(false);
        break;
      case "last":
        setToUI(studentService.last());
        uiUpdates(false);
        break;
      case "next":
        student = studentService.next();
        if (student != null)
          setToUI(student);
        uiUpdates(false);
        break;
      case "prev":
        student = studentService.prev();
        if (student != null)
          setToUI(student);
        uiUpdates(false);
        break;
    }
  }

  /**
   * Validate fields.
   *
   * @return true, if successful
   */
  private boolean validateFields() {
    if (studentUI.rollNoTxt.getText().trim().isEmpty()
        || studentUI.nameTxt.getText().trim().isEmpty()
        || studentUI.markTxt.getText().trim().isEmpty())
      return false;
    return true;
  }

  /**
   * Popup.
   *
   * @param message the message
   * @throws InterruptedException the interrupted exception
   */
  private void popup(String message) throws InterruptedException {
    studentUI.messageDisplay.setText(message);
    Thread.sleep(1000);
    studentUI.messageDisplay.setText("");
  }

  /**
   * Searchify.
   *
   * @param text the text
   * @throws SQLException the SQL exception
   * @throws InterruptedException the interrupted exception
   */
  private void searchify(String text) throws SQLException, InterruptedException {
    student = new StudentModel();
    student = studentService.search(text);
    if (student != null) {
      setToUI(student);
      uiUpdates(false);
    } else {
      clearFields();
      popup("Create new user !");
      studentService.setResultSet();
      uiUpdates(true);
    }
  }

  /**
   * Ui updates.
   *
   * @param flag the flag
   */
  private void uiUpdates(boolean flag) {
    if (!flag) {
      studentUI.saveBtn.setEnabled(false);
      studentUI.deleteBtn.setEnabled(true);
      studentUI.updateBtn.setEnabled(true);
    } else {
      studentUI.saveBtn.setEnabled(true);
      studentUI.deleteBtn.setEnabled(false);
      studentUI.updateBtn.setEnabled(false);
    }

  }

  /**
   * Creates the student object.
   *
   * @return the student
   */
  private StudentModel createStudentObject() {
    student = new StudentModel();
    student.setRollNo(Integer.parseInt(studentUI.rollNoTxt.getText()));
    student.setName(studentUI.nameTxt.getText());
    student.setMarks(Float.parseFloat(studentUI.markTxt.getText()));
    student.setAge(Integer.parseInt(studentUI.ageCh.getSelectedItem()));
    student.setGender(studentUI.genderGrp.getSelectedCheckbox().getLabel());
    student.setAddress(studentUI.addressTxtArea.getText());
    return student;
  }

  /**
   * Clear fields.
   */
  private void clearFields() {
    studentUI.nameTxt.setText("");
    studentUI.markTxt.setText("");
    studentUI.addressTxtArea.setText("");
    studentUI.ageCh.select("5");
    studentUI.maleRadio.setState(true);
  }

  /**
   * Sets the to UI.
   *
   * @param student the new to UI
   */
  void setToUI(StudentModel student) {
    studentUI.rollNoTxt.setText(String.valueOf(student.getRollNo()));
    studentUI.nameTxt.setText(student.getName());
    studentUI.markTxt.setText(String.valueOf(student.getMarks()));
    studentUI.ageCh.select(String.valueOf(student.getAge()));
    if (student.getGender().equalsIgnoreCase("male"))
      studentUI.maleRadio.setState(true);
    else
      studentUI.femaleRadio.setState(true);
    studentUI.addressTxtArea.setText(student.getAddress());
  }



  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
   */
  @Override
  public void windowClosing(WindowEvent e) {
    studentUI.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
   */
  @Override
  public void keyReleased(KeyEvent e) {
    TextField tf = (TextField) e.getSource();
    String tfValue = tf.getText().trim();
    String tfName = tf.getName();

    if (tfValue.isEmpty() && tfName.equals("rollNoTxt"))
      clearFields();
    else if (!tfValue.isEmpty()) {
      switch (tfName) {
        case "rollNoTxt":
          if (tfValue.matches(NumericRegex)) {
            originalRollNo = tfValue;
            try {
              searchify(tfValue);
            } catch (SQLException e1) {
              e1.printStackTrace();
            } catch (InterruptedException e1) {
              e1.printStackTrace();
            }
          } else {
            tf.setText(originalRollNo);
            tf.setCaretPosition((originalRollNo.length() + 1));
          }
          break;

        case "nameTxt":
          if (tfValue.matches(CharacterRegx)) {
            originalName = tfValue;
          } else {
            tf.setText(originalName);
            tf.setCaretPosition((originalName.length() + 1));
          }
          break;

        case "markTxt":
          if (tfValue.matches(FloatRegex)) {
            originalMarks = tfValue;
          } else {
            tf.setText(originalMarks);
            tf.setCaretPosition((originalMarks.length() + 1));
          }
          break;
      }

    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
   */
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
  }

}
