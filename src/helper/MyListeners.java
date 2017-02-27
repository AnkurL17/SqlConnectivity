package helper;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import com.domain.Student;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import com.ui.StudentUI;

public class MyListeners extends WindowAdapter implements ActionListener, KeyListener {
  private StudentUI studentUI;
  private StudentService studentService = new StudentServiceImpl();
  private Student student;
  private String originalRollNo;
  private String originalName;
  private String originalMarks;
  private String NumericRegex = "\\d+";
  private String FloatRegex = "\\d+\\.\\d+";
  private String CharacterRegx = "^[a-zA-Z\\s]+";

  public MyListeners(StudentUI studentUI) throws SQLException, InterruptedException {
    this.studentUI = studentUI;
    studentService.setResultSet();
    studentFunctions("first");

    originalRollNo = studentUI.rollNoTxt.getText().trim();
    originalName = studentUI.nameTxt.getText().trim();
    originalMarks = studentUI.markTxt.getText().trim();
  }

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

  private void studentFunctions(String choice) throws SQLException, InterruptedException {
    switch (choice.toLowerCase()) {
      case "save":
        System.out.println("Save");
        if (validateFields()) {
          studentService.save(createStudentObject());
          studentService.setResultSet();
          studentService.last();
          popup("User saved successfully");
        } else
          popup("RollNo,Name and Marks can't be left empty");
        break;
      case "delete":
        System.out.println("Delete");
        setToUI(studentService.delete(studentUI.rollNoTxt.getText()));
        studentService.first();
        popup("User deleted successfully");
        break;
      case "update":
        System.out.println("Update");
        if (validateFields()) {
          studentService.update(createStudentObject());
          studentService.setResultSet();
          popup("User updated successfully");
        } else
          popup("RollNo,Name and Marks can't be left empty");
        break;
      case "search":
        System.out.println("Search");
        searchify(studentUI.rollNoTxt.getText());
        break;
      case "first":
        System.out.println("First");
        setToUI(studentService.first());
        uiUpdates(false);
        break;
      case "last":
        System.out.println("Last");
        setToUI(studentService.last());
        uiUpdates(false);
        break;
      case "next":
        System.out.println("Next");
        student = studentService.next();
        if (student != null)
          setToUI(student);
        uiUpdates(false);
        break;
      case "prev":
        System.out.println("Prev");
        student = studentService.prev();
        if (student != null)
          setToUI(student);
        uiUpdates(false);
        break;
      default:
        System.out.println("Something went wrong");

    }
  }

  private boolean validateFields() {
    if (studentUI.rollNoTxt.getText().trim().isEmpty()
        || studentUI.nameTxt.getText().trim().isEmpty()
        || studentUI.markTxt.getText().trim().isEmpty())
      return false;
    return true;
  }

  private void popup(String message) throws InterruptedException {
    studentUI.messageDisplay.setText(message);
    Thread.sleep(1000);
    studentUI.messageDisplay.setText("");
  }

  private void searchify(String text) throws SQLException, InterruptedException {
    student = new Student();
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

  private Student createStudentObject() {
    student = new Student();
    student.setRollNo(Integer.parseInt(studentUI.rollNoTxt.getText()));
    student.setName(studentUI.nameTxt.getText());
    student.setMarks(Float.parseFloat(studentUI.markTxt.getText()));
    student.setAge(Integer.parseInt(studentUI.ageCh.getSelectedItem()));
    student.setGender(studentUI.genderGrp.getSelectedCheckbox().getLabel());
    student.setAddress(studentUI.addressTxtArea.getText());
    return student;
  }

  private void clearFields() {
    studentUI.nameTxt.setText("");
    studentUI.markTxt.setText("");
    studentUI.addressTxtArea.setText("");
    studentUI.ageCh.select("5");
    studentUI.maleRadio.setState(true);
  }

  void setToUI(Student student) {
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



  @Override
  public void windowClosing(WindowEvent e) {
    studentUI.dispose();
  }

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

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
  }

}
