package com.ui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.sql.SQLException;

import helper.DBHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentUI.
 */
public class StudentUI extends Frame {

  /** The Constant LABEL. */
  private static final String LABEL = "LABEL";

  /** The Constant TEXTFIELD. */
  private static final String TEXTFIELD = "TEXTFIELD";

  /** The Constant TEXTAREA. */
  private static final String TEXTAREA = "TEXTAREA";

  /** The Constant CHECKBOX. */
  private static final String CHECKBOX = "CHECKBOX";

  /** The Constant CHOICE. */
  private static final String CHOICE = "CHOICE";

  /** The Constant BUTTON. */
  private static final String BUTTON = "BUTTON";
  /** The roll no lbl. */
  private Label rollNoLbl;

  /** The name lbl. */
  private Label nameLbl;

  /** The mark lbl. */
  private Label markLbl;

  /** The age lbl. */
  private Label ageLbl;

  /** The gender lbl. */
  private Label genderLbl;

  /** The address lbl. */
  private Label addressLbl;

  /** The roll no txt. */
  public TextField rollNoTxt;

  /** The name txt. */
  public TextField nameTxt;

  /** The mark txt. */
  public TextField markTxt;

  /** The age ch. */
  public Choice ageCh;

  /** The gender grp. */
  public CheckboxGroup genderGrp;

  /** The male radio. */
  public Checkbox maleRadio;

  /** The female radio. */
  public Checkbox femaleRadio;

  /** The address txt area. */
  public TextArea addressTxtArea;

  /** The save btn. */
  public Button saveBtn;

  /** The update btn. */
  public Button updateBtn;

  /** The delete btn. */
  public Button deleteBtn;

  /** The search btn. */
  public Button searchBtn;

  /** The first btn. */
  public Button firstBtn;

  /** The last btn. */
  public Button lastBtn;

  /** The next btn. */
  public Button nextBtn;

  /** The prev btn. */
  public Button prevBtn;

  /** The message display. */
  public Label messageDisplay;

  /** The my listeners. */
  private MyListeners myListeners;

  /**
   * Instantiates a new student UI.
   *
   * @throws InstantiationException the instantiation exception
   * @throws SQLException the SQL exception
   * @throws ClassNotFoundException the class not found exception
   * @throws InterruptedException the interrupted exception
   */
  public StudentUI()
      throws InstantiationException, SQLException, ClassNotFoundException, InterruptedException {
    new DBHelper();
    setTitle("Student");
    setVisible(true);
    setBounds(100, 10, 700, 700);
    setResizable(false);
    setLayout(null);
    setBackground(Color.GRAY);

    myListeners = new MyListeners(this);

    messageDisplay =
        (Label) uiCreator(messageDisplay, LABEL, "", new int[] {100, 50, 300, 20}, false);;
    messageDisplay.setForeground(Color.white);
    rollNoLbl =
        (Label) uiCreator(rollNoLbl, LABEL, "Roll No", new int[] {100, 100, 100, 20}, false);
    nameLbl = (Label) uiCreator(nameLbl, LABEL, "Name", new int[] {100, 200, 100, 20}, false);
    markLbl = (Label) uiCreator(markLbl, LABEL, "Mark", new int[] {100, 300, 100, 20}, false);
    ageLbl = (Label) uiCreator(ageLbl, LABEL, "Age", new int[] {100, 400, 100, 20}, false);
    genderLbl = (Label) uiCreator(genderLbl, LABEL, "Gender", new int[] {100, 450, 100, 20}, false);
    addressLbl =
        (Label) uiCreator(addressLbl, LABEL, "Address", new int[] {100, 500, 100, 20}, false);


    rollNoTxt = (TextField) uiCreator(rollNoTxt, TEXTFIELD, "rollNoTxt",
        new int[] {300, 100, 100, 20}, true);
    nameTxt =
        (TextField) uiCreator(nameTxt, TEXTFIELD, "nameTxt", new int[] {300, 200, 100, 20}, true);
    markTxt =
        (TextField) uiCreator(markTxt, TEXTFIELD, "markTxt", new int[] {300, 300, 100, 20}, true);

    ageCh = (Choice) uiCreator(ageCh, CHOICE, "", new int[] {300, 400, 100, 20}, true);
    for (int age = 5; age <= 25; age++)
      ageCh.add(String.valueOf(age));

    genderGrp = new CheckboxGroup();
    maleRadio =
        (Checkbox) uiCreator(maleRadio, CHECKBOX, "Male", new int[] {300, 450, 50, 20}, false);
    femaleRadio =
        (Checkbox) uiCreator(femaleRadio, CHECKBOX, "Female", new int[] {450, 450, 60, 20}, false);

    addressTxtArea = (TextArea) uiCreator(addressTxtArea, TEXTAREA, "addressTxtArea",
        new int[] {300, 500, 310, 60}, true);


    saveBtn = (Button) uiCreator(saveBtn, BUTTON, "Save", new int[] {100, 600, 60, 20}, true);
    updateBtn = (Button) uiCreator(updateBtn, BUTTON, "Update", new int[] {250, 600, 60, 20}, true);
    deleteBtn = (Button) uiCreator(deleteBtn, BUTTON, "Delete", new int[] {400, 600, 60, 20}, true);
    searchBtn = (Button) uiCreator(searchBtn, BUTTON, "Search", new int[] {550, 600, 60, 20}, true);
    firstBtn = (Button) uiCreator(firstBtn, BUTTON, "First", new int[] {100, 650, 60, 20}, true);
    lastBtn = (Button) uiCreator(lastBtn, BUTTON, "Last", new int[] {250, 650, 60, 20}, true);
    nextBtn = (Button) uiCreator(nextBtn, BUTTON, "Next", new int[] {400, 650, 60, 20}, true);
    prevBtn = (Button) uiCreator(prevBtn, BUTTON, "Prev", new int[] {550, 650, 60, 20}, true);

    addWindowListener(myListeners);
    myListeners.studentFunctions("first");
  }


  /**
   * Ui creator.
   *
   * @param component the component
   * @param type the type
   * @param name the name
   * @param bounds the bounds
   * @param listen the listen
   * @return the component
   */

  public Component uiCreator(Component component, String type, String name, int bounds[],
      Boolean listen) {
    if ("LABEL".equals(type))
      component = new Label(name);
    else if ("TEXTFIELD".equals(type)) {
      TextField textField = new TextField();
      textField.setName(name);
      textField.addKeyListener(myListeners);
      component = textField;
    } else if ("TEXTAREA".equals(type)) {
      component = new TextArea(3, 100);
    } else if ("CHOICE".equals(type)) {
      component = new Choice();
    } else if ("CHECKBOX".equals(type)) {
      component = new Checkbox(name, genderGrp, true);
    } else if ("BUTTON".equals(type)) {
      Button button = new Button(name);
      button.addActionListener(myListeners);
      component = button;
    }
    component.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    add(component);
    return component;
  }

  /**
   * The main method.
   *
   * @param args the arguments
   */
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
