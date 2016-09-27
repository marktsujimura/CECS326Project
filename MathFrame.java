import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.util.*;

public class MathFrame extends JFrame{

 //variables and objects
 private final JPanel mainPanel = new JPanel();
 private final JPanel mathPanel = new JPanel(new GridBagLayout());
 private final JPanel mapPanel = new JPanel();
 private final JPanel buildingPanel = new JPanel();
 private final JLabel scoreLabel = new JLabel(""); //display score
 private final JLabel operationLabel = new JLabel(""); //display math operation
 private final JLabel timerLabel = new JLabel(""); //display timer
 private final JLabel messageLabel = new JLabel(""); //display Correct or Incorrect
 private final JLabel tempLabel1 = new JLabel("MAP");
 private final JLabel tempLabel2 = new JLabel("BUILDINGS AND UPGRADES");
 private final JButton mainButton = new JButton("START");
 private final Random r = new Random(); //to generate random numbers
 private final JTextField box = new JTextField(3); //box for user input
 private int score; //keep score
 private int a; //first operand
 private int b; //second operand
 private int correctAnswer; //correct answer
 private int userAnswer; //answer from user
 private final Timer t; //Timer
 private int counter; // counter for Timer
 private final JCheckBox additionCheckBox = new JCheckBox("ADD");
 private final JCheckBox subtractionCheckBox = new JCheckBox("SUB");
 private final JCheckBox multiplicationCheckBox = new JCheckBox("MULT");
 private final JCheckBox divisionCheckBox = new JCheckBox("DIV");
 private int randomOp; //store random number for operation
 private ArrayList<Integer> alist = new ArrayList<Integer>(); //arraylist to keep track of the operations selected
 private final GridBagConstraints c = new GridBagConstraints();

 //frame constructor
 public MathFrame(){
  
  //add listener to button
  ActionListener listener = new ButtonListener();
  mainButton.addActionListener(listener);

  //add listener to TextField
  box.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e){
    userAnswer = Integer.parseInt(box.getText());
    t.stop();
    
    checkAnswer(correctAnswer, userAnswer);
   }
  });

  //add listener to Timer
  ActionListener tListener = new TimerListener();
  t = new Timer(1000,tListener);
  
  //add listener to Check boxes
  ItemListener checkListener = new CheckBoxListener();
  additionCheckBox.addItemListener(checkListener);
  subtractionCheckBox.addItemListener(checkListener);
  multiplicationCheckBox.addItemListener(checkListener);
  divisionCheckBox.addItemListener(checkListener);
  
  //set colors and fonts
  mainPanel.setBackground(Color.BLACK);
  mathPanel.setBackground(Color.BLACK);
  mapPanel.setBackground(Color.BLACK);
  buildingPanel.setBackground(Color.BLACK);
  additionCheckBox.setForeground(Color.GREEN);
  additionCheckBox.setBackground(Color.BLACK);
  additionCheckBox.setFont(new Font(additionCheckBox.getName(),Font.BOLD,15));
  subtractionCheckBox.setForeground(Color.GREEN);
  subtractionCheckBox.setBackground(Color.BLACK);
  subtractionCheckBox.setFont(new Font(subtractionCheckBox.getName(),Font.BOLD,15));
  multiplicationCheckBox.setForeground(Color.GREEN);
  multiplicationCheckBox.setBackground(Color.BLACK);
  multiplicationCheckBox.setFont(new Font(multiplicationCheckBox.getName(),Font.BOLD,15));
  divisionCheckBox.setForeground(Color.GREEN);
  divisionCheckBox.setBackground(Color.BLACK);
  divisionCheckBox.setFont(new Font(divisionCheckBox.getName(),Font.BOLD,15));
  timerLabel.setForeground(Color.GREEN);
  timerLabel.setFont(new Font(timerLabel.getName(),Font.BOLD,15));
  scoreLabel.setForeground(Color.GREEN);
  scoreLabel.setFont(new Font(scoreLabel.getName(),Font.BOLD,15));
  operationLabel.setForeground(Color.GREEN);
  operationLabel.setFont(new Font(operationLabel.getName(),Font.BOLD,15));
  messageLabel.setForeground(Color.GREEN);
  messageLabel.setFont(new Font(messageLabel.getName(),Font.BOLD,15));
  box.setForeground(Color.GREEN);
  box.setCaretColor(Color.GREEN);
  box.setBackground(Color.BLACK);
  box.setFont(new Font(box.getName(),Font.BOLD,15));
  mainButton.setBackground(Color.BLACK);
  mainButton.setForeground(Color.GREEN);
  mainButton.setFont(new Font(mainButton.getName(),Font.BOLD,15));
  tempLabel1.setForeground(Color.GREEN);
  tempLabel1.setFont(new Font(tempLabel1.getName(),Font.BOLD,20));
  tempLabel2.setForeground(Color.GREEN);
  tempLabel2.setFont(new Font(tempLabel1.getName(),Font.BOLD,20));
  
  //add components to MATH panel and arrange them
  c.fill = GridBagConstraints.HORIZONTAL;
  c.insets = new Insets(0,10,0,0);
  c.gridx = 0;
  c.gridy = 0;
  mathPanel.add(additionCheckBox,c);
  c.gridx = 1;
  c.gridy = 0;
  mathPanel.add(subtractionCheckBox,c);
  c.gridx = 0;
  c.gridy = 1;
  mathPanel.add(multiplicationCheckBox,c);
  c.gridx = 1;
  c.gridy = 1;
  mathPanel.add(divisionCheckBox,c);
  c.insets = new Insets(20,10,0,0);
  c.gridx = 0;
  c.gridy = 2;
  mathPanel.add(mainButton,c);
  c.gridx = 0;
  c.gridy = 3;
  mathPanel.add(operationLabel,c);
  c.gridx = 0;
  c.gridy = 4;
  c.insets = new Insets(20,10,0,0);
  c.fill = GridBagConstraints.HORIZONTAL;
  mathPanel.add(timerLabel,c);
  c.gridx = 1;
  c.gridy = 4;
  c.insets = new Insets(20,0,0,20);
  mathPanel.add(messageLabel,c);
  c.gridx = 0;
  c.gridy = 6;
  c.insets = new Insets(20,10,0,0);
  mathPanel.add(scoreLabel,c);
  
  //draw border lines
  mainPanel.setLayout(new BorderLayout());
  mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
  mathPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
  mapPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
  buildingPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
  
  //add MATH panel to MAIN panel
  mainPanel.add(mathPanel,BorderLayout.WEST);
  mainPanel.add(mapPanel,BorderLayout.CENTER);
  mainPanel.add(buildingPanel,BorderLayout.EAST);
  mapPanel.add(tempLabel1);
  buildingPanel.add(tempLabel2);
  
  //add MAIN panel to frame
  add(mainPanel);
 }

 //function to check if the answer from the user is correct
 public void checkAnswer(int x, int y){
  if((x == y) && (counter != 0)){
   score = score + 1; //if answer is correct increment score
   scoreLabel.setText("SCORE:  " + score);
   messageLabel.setText("CORRECT!");
  }else{
   messageLabel.setText("INCORRECT");
   scoreLabel.setText("SCORE:  " + score);
  }
 }

 //TimerListener class
 class TimerListener implements ActionListener{
  public void actionPerformed(ActionEvent event){
   counter = counter - 1;
   timerLabel.setText("TIME:  " + counter);
   if(counter == 0){
    t.stop(); //when counter hits 0, stop timer
    messageLabel.setText("TIME'S UP!");
    scoreLabel.setText("SCORE:  " + score);
   }
   t.setDelay(1000);
  }
 }

 //CheckBoxListener class
 class CheckBoxListener implements ItemListener{
  public void itemStateChanged(ItemEvent e){
   if(e.getStateChange() == ItemEvent.SELECTED){
    if(e.getSource() == additionCheckBox){
     alist.add(1);
    }else if(e.getSource() == subtractionCheckBox){
     alist.add(2);
    }else if(e.getSource() == multiplicationCheckBox){
     alist.add(3);
    }else if(e.getSource() == divisionCheckBox){
     alist.add(4);
    }
   }else{
    if(e.getSource() == additionCheckBox){
     alist.remove(alist.indexOf(1));
    }else if(e.getSource() == subtractionCheckBox){
     alist.remove(alist.indexOf(2));
    }else if(e.getSource() == multiplicationCheckBox){
     alist.remove(alist.indexOf(3));
    }else if(e.getSource() == divisionCheckBox){
     alist.remove(alist.indexOf(4));
    }
   }
  }
 }

 //ButtonListener class
 class ButtonListener implements ActionListener{
  public void actionPerformed(ActionEvent event){
   String command = event.getActionCommand();

   if((command.equals("START") || command.equals("NEXT")) && (!alist.isEmpty())){
    
    //display text box as soon as the user hits START
    c.gridx = 1;
    c.gridy = 3;
    c.fill = GridBagConstraints.NONE;
    c.insets = new Insets(20,0,0,20);
    mathPanel.add(box,c);
    
    box.requestFocusInWindow();
    timerLabel.setText("TIME:  5");
    counter = 5;
    t.start(); //start timer
    messageLabel.setText(""); //clear message label
    box.setText(""); //clear text box area
    
    randomOp = r.nextInt(4) + 1; //generate random number for operation
    while((!alist.contains(randomOp)) && (!alist.isEmpty())){ //keep generating random number until
     randomOp = r.nextInt(4) + 1;                         //the number of the operation is in the arraylist
    }
    
    displayOperation(randomOp);
    mainButton.setText("NEXT");
   }
  }
 }

 //function to display the math operations and calculate the correct answer
 public void displayOperation(int rand){
  
  a = r.nextInt(20) + 1; //generate random number between 1-20
  b = r.nextInt(20) + 1; 
  
  if(rand == 1){
   correctAnswer = a + b;
   operationLabel.setText(a + "  +  " + b + "   =  ");
  }else if(rand == 2){
   correctAnswer = a - b;
   operationLabel.setText(a + "  -  " + b + "   =  ");
  }else if(rand == 3){
   correctAnswer = a * b;
   operationLabel.setText(a + "  *  " + b + "   =  ");
  }else if(rand == 4){
   correctAnswer = a / b;
   operationLabel.setText(a + "  /  " + b + "   =  ");
  }
 }

 public static void main(String[] args){
  JFrame frame = new MathFrame();
  frame.setTitle("GAME");
  frame.setSize(1700,700);
  frame.setLocationRelativeTo(null);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
 }
}