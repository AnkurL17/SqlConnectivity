package com.ui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.sql.SQLException;

import helper.DBHelper;
import helper.MyListeners;

public class StudentUI extends Frame {
  private Label rollNoLbl = new Label("Roll No");
  private Label nameLbl = new Label("Name");
  private Label markLbl = new Label("Mark");
  private Label ageLbl = new Label("Age");
  private Label genderLbl = new Label("Gender");
  private Label addressLbl = new Label("Address");

  public TextField rollNoTxt = new TextField();
  public TextField nameTxt = new TextField();
  public TextField markTxt = new TextField();
  public Choice ageCh = new Choice();
  public CheckboxGroup genderGrp = new CheckboxGroup();
  public Checkbox maleRadio = new Checkbox("Male", genderGrp, true);
  public Checkbox femaleRadio = new Checkbox("Female", genderGrp, false);
  public TextArea addressTxtArea = new TextArea(3, 100);

  public Button saveBtn = new Button("Save");
  public Button updateBtn = new Button("Update");
  public Button deleteBtn = new Button("Delete");
  public Button searchBtn = new Button("Search");
  public Button firstBtn = new Button("First");
  public Button lastBtn = new Button("Last");
  public Button nextBtn = new Button("Next");
  public Button prevBtn = new Button("Prev");

  public Label messageDisplay = new Label("");
  private MyListeners myListeners;

  public StudentUI()
      throws InstantiationException, SQLException, ClassNotFoundException, InterruptedException {
    new DBHelper();
    myListeners = new MyListeners(this);
    setTitle("Student");
    setVisible(true);
    setBounds(100, 10, 700, 700);
    setResizable(false);
    setLayout(null);
    setBackground(Color.GRAY);

    add(rollNoLbl);
    add(nameLbl);
    add(markLbl);
    add(ageLbl);
    add(genderLbl);
    add(addressLbl);

    rollNoTxt.setName("rollNoTxt");
    nameTxt.setName("nameTxt");
    markTxt.setName("markTxt");

    add(messageDisplay);
    add(rollNoTxt);
    add(nameTxt);
    add(markTxt);
    add(ageCh);
    add(maleRadio);
    add(femaleRadio);
    add(addressTxtArea);

    add(saveBtn);
    add(updateBtn);
    add(deleteBtn);
    add(searchBtn);
    add(firstBtn);
    add(lastBtn);
    add(prevBtn);
    add(nextBtn);


    for (int age = 5; age <= 25; age++)
      ageCh.add(String.valueOf(age));


    messageDisplay.setBounds(100, 50, 300, 20);
    messageDisplay.setForeground(Color.white);

    rollNoLbl.setBounds(100, 100, 100, 20);
    nameLbl.setBounds(100, 200, 100, 20);
    markLbl.setBounds(100, 300, 100, 20);
    ageLbl.setBounds(100, 400, 100, 20);
    genderLbl.setBounds(100, 450, 100, 20);
    addressLbl.setBounds(100, 500, 100, 20);


    rollNoTxt.setBounds(300, 100, 100, 20);
    nameTxt.setBounds(300, 200, 100, 20);
    markTxt.setBounds(300, 300, 100, 20);
    ageCh.setBounds(300, 400, 100, 20);
    maleRadio.setBounds(300, 450, 50, 20);
    femaleRadio.setBounds(450, 450, 60, 20);
    addressTxtArea.setBounds(300, 500, 310, 60);

    saveBtn.setBounds(100, 600, 60, 20);
    updateBtn.setBounds(250, 600, 60, 20);
    deleteBtn.setBounds(400, 600, 60, 20);
    searchBtn.setBounds(550, 600, 60, 20);
    firstBtn.setBounds(100, 650, 60, 20);
    lastBtn.setBounds(250, 650, 60, 20);
    nextBtn.setBounds(400, 650, 60, 20);
    prevBtn.setBounds(550, 650, 60, 20);

    addWindowListener(myListeners);
    saveBtn.addActionListener(myListeners);
    updateBtn.addActionListener(myListeners);
    deleteBtn.addActionListener(myListeners);
    searchBtn.addActionListener(myListeners);
    firstBtn.addActionListener(myListeners);
    lastBtn.addActionListener(myListeners);
    nextBtn.addActionListener(myListeners);
    prevBtn.addActionListener(myListeners);

    rollNoTxt.addKeyListener(myListeners);
    nameTxt.addKeyListener(myListeners);
    markTxt.addKeyListener(myListeners);
  }

  public static void main(String[] args) {
    try {
      new StudentUI();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
