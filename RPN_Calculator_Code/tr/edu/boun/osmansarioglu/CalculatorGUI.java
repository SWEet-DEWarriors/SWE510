package tr.edu.boun.osmansarioglu;

import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
* <h1>Graphical User Interface </h1>
* <p>
* The CalculatorGUI class implements an application that
* creates a GUI for RPN Calculator. This calculator creates an input string 
* for RPN Calculator and whenever a calculation identifier is clicked on, GUI
* sends latest string and calls result for calculation
* <p>
* <b>Note:</b> GUI has input and output displays. "Input display" shows latest string created,
* and when a calculation is triggered the result is shown at "Output Display"
*
* @author  Osman Selçuk Sarýoðlu
* @version 1.0
* @since   2020-12-19
*/

public class CalculatorGUI extends JFrame implements ActionListener  {
	
	// This is Engine class to track error messages and working status of RPN calculator engine
	
	private Engine runningEngine;
	
	// calcFrame is to create as an instance of JFrame for user to input numbers and operations
	
	JFrame calcFrame = new JFrame();
	
	// Buttons at layout of calculator frame
	
	JButton button0 = new JButton("0");
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");
	JButton buttonD = new JButton(".");
	JButton buttonTh = new JButton(",");
	JButton buttonE = new JButton("^");
	JButton buttonP = new JButton("+");
	JButton buttonM = new JButton("-");
	JButton buttonX = new JButton("x");
	JButton buttonDv = new JButton("/");
	JButton buttonS = new JButton("+/-");
	JButton buttonBS = new JButton("<--");
	JButton buttonC = new JButton("C");
	
	// Displays located at calculator frame
	// calcDisplayIn  : TextArea showing Input from user
	// calcDisplayOut : TextArea showing Output of calculator result
	
	JTextArea calcDisplayIn = new JTextArea();
	JTextArea calcDisplayOut = new JTextArea();	

	
   /**
   * <h1>Constructor for CalculatorGUI </h1>
   * <p>
   * This method is used to setup components of GUI.
   * Component layout has:
   * <p>
   * Number Buttons: <b> 0,1,2...9 </b>
   * <p>
   * Digit and Thousand Separators: <b> . and , </b>
   * <p>
   * Calculation Operators: <b> +,-,x,/ </b>
   * <p>
   * Input Operator: <b> ^ </b>
   * <p>
   * Sign Changer: <b> +/- </b>
   * <p>   
   * Correction Buttons: <b> Backspace (<--), Clear (C) </b>
   * <p>
   * GUI has size of <b> 335ppx </b> width, and <b>350ppx</b> height
   * @param Engine Constructor gets <b> engine </b> as parameter to manage <b> "Errors" </b>. 
   */	
	
	public CalculatorGUI(Engine engine) {
		
		// Sets calculator engine with given parameter, so all calculator related classes can reach
		// same error state and types
		
		runningEngine = engine;

		// Define locations and sizes of buttons and displays, and add action listeners
		
		button0.setBounds(70,250,50, 40);
		button0.addActionListener(this);
		
		button1.setBounds(10,200,50, 40);
		button1.addActionListener(this);
		
		button2.setBounds(70,200,50, 40);
		button2.addActionListener(this);
		
		button3.setBounds(130,200,50, 40);
		button3.addActionListener(this);
		
		button4.setBounds(10,150,50, 40);
		button4.addActionListener(this);
		
		button5.setBounds(70,150,50, 40);
		button5.addActionListener(this);
		
		button6.setBounds(130,150,50, 40);
		button6.addActionListener(this);
		
		button7.setBounds(10,100,50, 40);
		button7.addActionListener(this);
		
		button8.setBounds(70,100,50, 40);
		button8.addActionListener(this);
		
		button9.setBounds(130,100,50, 40);
		button9.addActionListener(this);
		
		buttonD.setBounds(10,250,50, 40);
		buttonD.addActionListener(this);
		
		buttonTh.setBounds(130,250,50, 40);
		buttonTh.addActionListener(this);
		
		buttonE.setBounds(260,200,50, 40);
		buttonE.addActionListener(this);
		
		buttonP.setBounds(200,100,50, 40);
		buttonP.addActionListener(this);
		
		buttonM.setBounds(260,100,50, 40);
		buttonM.addActionListener(this);
		
		buttonX.setBounds(200,150,50, 40);
		buttonX.addActionListener(this);
		
		buttonDv.setBounds(260,150,50, 40);
		buttonDv.addActionListener(this);
		
		buttonS.setBounds(200,200,50, 40);
		buttonS.addActionListener(this);

		buttonBS.setBounds(200,250,50, 40);
		buttonBS.addActionListener(this);
		
		buttonC.setBounds(260,250,50, 40);
		buttonC.addActionListener(this);
		
		calcDisplayIn.setBounds(10, 10, 300, 40);
		calcDisplayIn.setEditable(false);
		calcDisplayOut.setBounds(10, 50, 300, 40);
		calcDisplayOut.setEditable(false);
        
		//Adding buttons and displays in GUI frame  
		
		calcFrame.add(button0);
		calcFrame.add(button1);
		calcFrame.add(button2);
		calcFrame.add(button3); 
		calcFrame.add(button4);  
		calcFrame.add(button5); 
		calcFrame.add(button6);
		calcFrame.add(button7); 
		calcFrame.add(button8);  
		calcFrame.add(button9);
		calcFrame.add(buttonD);
		calcFrame.add(buttonTh); 
		calcFrame.add(buttonE);
		calcFrame.add(buttonP);
		calcFrame.add(buttonM); 
		calcFrame.add(buttonX); 
		calcFrame.add(buttonDv);
		calcFrame.add(buttonS);  
		calcFrame.add(buttonBS);
		calcFrame.add(buttonC);
		calcFrame.add(calcDisplayIn);
		calcFrame.add(calcDisplayOut); 
		
		// Setting up GUI Frame and making it visible
		
		calcFrame.setSize(335,350);
		calcFrame.setLayout(null);
		calcFrame.setVisible(true); 
		calcFrame.setResizable(false);
		calcFrame.setTitle("RPN Calculator by Osman Selçuk Sarýoðlu");
		
	}

	
   /**
   * <h1>Action Listener for CalculatorGUI </h1>
   * <p>   
   * This method is used listen click events and trigger action when
   * any buttons is clicked.
   * <p>
   * There are 2 main actions when a button is clicked.
   * <p>
   * 1. <b>Add value </b> of button at input display, and <b>update string</b> at display.
   * <p>
   * 2. <b>Trigger calculation</b> when an operation button is clicked and <b>display result </b>.
   * 
   * @param e This parameter is an ActionEvent object and listens click actions.
   * @return Nothing.
   */
		
    public void actionPerformed(ActionEvent e) {
    	
    	// inDisplay and outDisplay are String classes to manage user interface visible data
    	
    	String inDisplay = ""; 
    	String outDisplay = ""; 
    	
    	// Whenever a button is clicked inputDisplay text is updated with relevant characters
       	
        if(e.getSource() == button0) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"0");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button1) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"1");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button2) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"2");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button3) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"3");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button4) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"4");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button5) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"5");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button6) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"6");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button7) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"7");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button8) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"8");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == button9) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"9");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == buttonD) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+".");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == buttonTh) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+",");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == buttonE) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"^");
        	calcDisplayOut.setText(outDisplay);
        } else if(e.getSource() == buttonP) {
 
        	
        	// Whenever a calculation button is clicked inputDisplay text is updated with relevant characters
        	// and calculation of "string" @ inputDisplay is get by triggering
        	// getStringForCalc method.
        	// This comment is valid for other calculation buttons as well.
        	
        	inDisplay = calcDisplayIn.getText();
        	outDisplay = runningEngine.getStringForCalc(inDisplay + "+" , runningEngine);
        	calcDisplayOut.setText(outDisplay);

        	// If there excessive input is made for calculation, getStringForCalc method creates an error message
        	// In case this error message is received, this doesn't update inputDisplay string.
        	// This comment is valid for other calculation buttons as well.	
        	
        	if (outDisplay.equals("Error: Can't calculate: Too many operator or Division to 0") == false) {
        		calcDisplayIn.setText(inDisplay + "+");
        	} 
        	
        } else if(e.getSource() == buttonM) {
        	
        	inDisplay = calcDisplayIn.getText();
        	outDisplay = runningEngine.getStringForCalc(inDisplay + "-" , runningEngine);
        	calcDisplayOut.setText(outDisplay);
        	
        	if (outDisplay.equals("Error: Can't calculate: Too many operator or Division to 0") == false) {
        		calcDisplayIn.setText(inDisplay + "-");
        	} 
        	
        } else if(e.getSource() == buttonX) {
        	
        	inDisplay = calcDisplayIn.getText();
        	outDisplay = runningEngine.getStringForCalc(inDisplay + "*" , runningEngine);
        	calcDisplayOut.setText(outDisplay);
        	
        	if (outDisplay.equals("Error: Can't calculate: Too many operator or Division to 0") == false) {
        		calcDisplayIn.setText(inDisplay + "*");
        	} 
        	
        } else if(e.getSource() == buttonDv) {
        	
        	inDisplay = calcDisplayIn.getText();
        	outDisplay = runningEngine.getStringForCalc(inDisplay + "/" , runningEngine);
        	calcDisplayOut.setText(outDisplay);
        	
        	if (outDisplay.equals("Error: Can't calculate: Too many operator or Division to 0") == false) {
        		calcDisplayIn.setText(inDisplay + "/");
        	} 
        	
        } else if(e.getSource() == buttonS) {
        	calcDisplayIn.setText(calcDisplayIn.getText()+"c");
        
        } else if(e.getSource() == buttonBS) {
 
        	inDisplay = calcDisplayIn.getText();
        	outDisplay = calcDisplayOut.getText();
     
        	// Whenever is a "Backspace" button is clicked, this deletes last string key at inputDisplay.
        	// There is only an exception for this when there is an error message "Error: Can't calculate: Too many operator or Division to 0" at outputDisplay as
        	// because, this code prevents to add last calculation operation when this error is triggered
        	// so Backspace button only clears outputDisplay
        	
        	if (outDisplay.equals("Error: Can't calculate: Too many operator or Division to 0")) {
        		outDisplay = runningEngine.getStringForCalc(inDisplay , runningEngine);
        		calcDisplayOut.setText(outDisplay);
        	} else if (inDisplay.length() > 0) {
        		inDisplay = inDisplay.substring(0, inDisplay.length()-1);
            	calcDisplayIn.setText(inDisplay);
            	calcDisplayOut.setText("");
        	}
        
        	// 'C'lear button deletes all values at input and output displays
        	
        } else if(e.getSource() == buttonC) {
        	calcDisplayIn.setText("");
        	calcDisplayOut.setText("");
        }
        
    } 


}
