package tr.edu.boun.osmansarioglu;

// Scanner object is used for getting inputs from user at console

import java.util.Scanner;



/**
* <h1>Engine Class </h1>
* <p>
* The Engine class has the code for triggering calculations.
* <p>
* It uses <b>Calculator</b>, <b>CalculatorGUI</b>, <b>Separator</b>, classes for collecting data inputs, 
* and call methods for calculations.
* <p>
* It has 3 different methods to make these operations:
* <p>
* <b>getStringForCalc(String strInput, Engine runningEngine) : </b> Makes calculation for given "String" and returns output as "String"
* <p>
* <b>runAtConsole(Engine runningCalc) : </b> Enables inputs from user by using "System.in" and show results with "System.out"
* <p>
* <b>main(String[] args) : </b> Main method to run code. This triggers GUI enabling, console running waiting for inputs

* @author  Osman Selçuk Sarýoðlu
* @version 1.0
* @since   2020-12-19
*/

public class Engine {
	
	// errorType, and worksOK variables are used to keep "Error Messages" and 
	// status of code whether any error is received or not
	
	public static String errorType;
	public static boolean worksOK;

	
   /**
   * <h1>Constructor for Engine </h1>
   * <p>
   * This method is used to initialize Error state for class. State: working, Error: Blank 
   */		
	
	public Engine() {
		worksOK = true;
		errorType = "";
	}
	
	
   /**
   * <h1>String getStringForCalc(String strInput, Engine runningEngine) method for Engine </h1>
   * <p>
   * This method is used to make <b>calculation</b> by using given "String" <b>strInput</b> and 
   * returns output as "String". 
   * <p>
   * "Engine" <b>runningEngine</b> is used to track <b>"Error state"</b> during all operations
   * <p>
   * @param strInput Method gets <i>strInput</i> <b>String</b> as parameter. <b>strInput</b> is  
   * representing calculation input that is sent to calculator. 
   * @param runningEngine Method gets <i>runningEngine</i> <b>Engine</b> object as parameter. 
   * <b>runningEngine</b> is used to handle calculation state and error message handling.
   * @return <b>String</b> Method returns <i>result for calculation</i> as <i>String</i> so that
   * GUI display shows result or error code.
   * <p>  
   */	

    public static String getStringForCalc(String strInput, Engine runningEngine) {
    	
    	// this is a temporary String to get updates through the code and finally it's used
    	// to "return" as the result of method 
    	
    	String tempStr = "";
    	
    	
    	// sprtr is a Separator object which divides input String "strInput" into numbers and operators.
    	// sprtr keeps results in seperatedArray as an "ArrayList" of "Object" type
    	// "Object" type is used because, arraylist keeps both numbers in Double format, 
    	// and operators in String format
    	// sprtr used current Engine running for calculation as a paramater
    	
    	Separator sprtr = new Separator(strInput , runningEngine);
    	   	
    	// This condition checks whether Engine has "no Error" and still working
    	// Engine gets "Error" if wrong type of input string is given by user, and 
    	// relevant error is kept in errorType property of runningEngine
    	
    	if (worksOK) {
	    	
    		// If there not errors on strInput, the code creates a Calculator named as calc
    		
    		Calculator calc = new Calculator();
	    	
    		// For each elements of Separator object sprtr, this loop sends input to Calculator calc
    		
	    	for(Object tempObj: sprtr.separatedArray) {
	
	    		// if value at separatedArray is a String then, this means it's an "Operator"
	    		// Then a calculate method of Calculator calc is triggered
	    		// If calculate method is "False" this means there is not enough number at Stack of Calculator
	    		// therefore, state of Engine is updated, and errorType is added.
	    		
	    		if (tempObj instanceof String ) {
	    			if (calc.calculate(tempObj) == false) {
	    				worksOK = false;
	    				errorType = "Error: Can't calculate: Too many operator or Division to 0";
	    				break;
	    			}
	    		
	    			
	    			// if there is a number to be added, then addValue method of Calculator is called
	    			
	    		} else {

	    			worksOK = true;
	    			errorType = "";
	    			calc.addValue(tempObj);
	    		}
	
	    	}
	    	
    	// Calculators's result is returned as output of this method, if there is no error created during code
    	// If Engine stops working, relevant error type is sent as a return
	    // After return, error state and messages are initiated so the Engine continue to work for correct inputs
	    // from user
	    	
	    	if (worksOK) {

	    		return calc.result();
	    		
	    	} else {
	    		tempStr = errorType;
	    		worksOK = true;
				errorType = "";
				return tempStr;
	    	}
	    	
	   	} else {
				tempStr = errorType;
	    		worksOK = true;
				errorType = "";
		    	return tempStr;
	    	}

    }
    
	/**
	 * <h1>runAtConsole(Engine runningCalc) method for Engine </h1>
	 * <p>
	 * This method is used to make calculation by using <b>"Console "</b> enabling user to input
	 * as a "String" and gives <b>result </b> at "Console". 
	 * <p>
	 * "Engine" <b>runningEngine</b> is used to track <b>"Error state"</b> during all operations
	 * <p>
	 * @param runningCalc Method gets <i>runningEngine</i> <b>Engine</b> object as parameter. 
	 * <b>runningEngine</b> is used to handle calculation state and error message handling.
	 * <p>  
	 */
    
    public static void runAtConsole(Engine runningCalc) {
		
    	// takeInput Scanner object is used to get inputs from console by users. 
    	// "System.in" library is used.
    	
		Scanner takeInput = new Scanner(System.in);
		
    	// Sends a message to console, to get input from user
		
		System.out.print("Please enter your input: ");
		String myInput = takeInput.next();
		
		// Input from user and result of calculation is shown at console.
		// user's input is sent to method getStringForCalc and result is displayed to user.
		
		System.out.println("Input String is : " + myInput);	
		System.out.print("Result: " + getStringForCalc(myInput, runningCalc));
    }
    

	/**
	 * <h1>main(String[] args) method for Engine </h1>
	 * <p>
	 * This method is main method to run <b>Engine</b> class. 
	 * <p>
	 * New <i>Engine</i> class <b> newCalculator </b> is initiated.
	 * <p>
	 * New <i>CalculatorGUI</i> class <b> rPNCalculator </b> is initiated.
	 * <p>
	 * <i>Method</i> of <b> runAtConsole </b> is run to enable user to give inputs, and get results.
	 * @param args standard input for a main method.  
	 */
    
	public static void main(String[] args) {
		
		// an Engine and a CalculatorGUI objects are created to run calculations 
		
		Engine newCalculator = new Engine();
		CalculatorGUI rPNCalculator = new CalculatorGUI(newCalculator);
		runAtConsole(newCalculator);

	}

}
