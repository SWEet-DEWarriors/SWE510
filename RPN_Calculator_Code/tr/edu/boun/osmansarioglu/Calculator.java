package tr.edu.boun.osmansarioglu;

import java.util.Stack;  

/**
* <h1>Calculator Class </h1>
* <p>
* The Calculator class has the code for making calculations.
* <p>
* It uses <b>Stack</b> class having <b>Double</b> numbers for collecting data for calculations
* <p>
* It has 3 different methods to make these operations:
* <p>
* <b>addValue(Object dblNo) : </b> Adds given object "double number" on top of Stack
* <p>
* <b>calculate(Object operator) : </b> Makes mathematical calculation according to given object "mathematical operator", 
* by popping top 2 numbers at Stack and putting the result on top of Stack
* <p>
* <b>result() : </b> Shows double number on top of Stack

* <p>
* <b>Note:</b> "Object" type is used to have flexibility at <i>"Engine"</i> class which sends parameters to <i>"Calculator"</i> class
*
* @author  Osman Selçuk Sarýoðlu
* @version 1.0
* @since   2020-12-19
*/

public class Calculator {

	// stk is Stack for keeping data for making calculations 
	
	private Stack<Double> stk;
	private int stkSize;
	
   /**
   * <h1>Constructor for Calculator </h1>
   * <p>
   * This method is used to initialize Stake for class.
   */	

	public Calculator() {
		stk = new Stack<Double>();
	}
	
	
   /**
   * <h1>addValue(Object dblNo) method for Calculator </h1>
   * <p>
   * This method is used to add values on top of Stack <b>stk</b>.
   * <p>
   * Since Stack contains Double objects, given Object <b>dblNo</b> is transformed to <b>Double</b> class first.
   * <p>
   * @param dblNo Method gets <i>dbl</i> <b> Object </b> as parameter. <b>dblNo</b> is sent as a parameter, and it's a Double object sent from
   * an <b>ArrayList</b> having different types of Objects at the same time: <i>Double</i> for numbers 
   * and <i>String</i> for operators. Therefore Object is transformed to <i>Double</i> while using at method.
   */	

	public void addValue(Object dblNo) {
		
		stk.push((Double) dblNo);
	}


   /**
   * <h1>calculate(Object operator) method for Calculator </h1>
   * <p>
   * This method is used to make <b>calculation</b> by using 2 values on top of Stack <b>stk</b>.
   * <p>
   * Since calculation sign is a String value, given parameter Object <b>operator</b> is transformed to <b>String</b> class while using.
   * <p>
   * @param operator Method gets <i>operator</i> <b> Object </b> as parameter. <b>operator</b> is sent as a parameter, and it's a String object sent from
   * an <b>ArrayList</b> having different types of Objects at the same time: <i>Double</i> for numbers 
   * and <i>String</i> for operators. Therefore Object is transformed to <i>String</i> while using at method.
   * @return <b>boolean</b> Method returns <i>true</i> or <i>false</i>.
   * <p>
   * <u><i>true:</i></u> There is enough value to make calculation, and calculation is done. 
   * <u><i> false:</i></u> There is not enough value to make calculation, and calculation cannot be done.    
   */		

	public boolean calculate(Object operator) {
		
		
		// stkOK is a temporary value to return whether calculation will be done or not
		boolean stkOK = true;
		
		// Calculation can be done only if Stack stk has enough numbers, i.e minimum 2 numbers
		
		if (stk.size() > 1) {
			
			// First 2 numbers are popped and assigned to double variables.
			// a^b- means a-b , there for first number popped "b" is assigned as secondNo, 
			// and second number "a" is assigned as firstNo 
			
			double secondNo = stk.pop();
			double firstNo = stk.pop();
		
			// switch condition control is applied to right calculation according to operator value
			// operation result is pushed at the top of Stack stk
			
			switch((String) operator) {
				case "+":
					stk.push(firstNo + secondNo);
					break;
				case "-":
					stk.push(firstNo - secondNo);
					break;
				case "*":
					stk.push(firstNo * secondNo);
					break;
				case "/":
					if (secondNo != 0.0) {
						stk.push(firstNo / secondNo);
					} else stkOK = false;
						
					break;
			}
		} 
		else {
			
			// if calculation is not done, method returns false. 
			
			stkOK = false;
		}
					
		return stkOK;
	}
	
	
    /**
   * <h1>result() method for Calculator </h1>
   * <p>
   * This method is used to send <b>result</b> by sending top value at Stack <b>stk</b>.
   * <p>
   * Value is formated according to <b>n,nnn,nnn.n</b> pattern.
   * <p> 
   * <b>","</b> stands for <i>thousand</i> separator, and
   * <b>"."</b> represents <i>decimal</i> separator.
   * <p>
   * @return <b>String</b> Method returns <i>value</i> as <i>String</i> in format mentioned above.    
   */	

	public String result() {
		
		// value at top of Stack stk is popped and stored in rawResult 
		String rawResult = Double.toString(stk.peek());
		
		// finalResult will be used in formatting process and used at return of method
		String finalResult ="";
		
		// signOfNumber will be used to understand value is negative or positive. 
		String signOfNumber = rawResult.substring(0, 1); 
		
		// if sign of number is negative, it's removed from string to make formatting correct. 
		// It will be added before sending final format
		if (signOfNumber.equals("-")) {
			rawResult = rawResult.substring(1);
		} else {
			signOfNumber = "";
		}
		
		// arrStr is used to split integer and decimal part of the number
		// two parts will be stored at integerPart and decimalPart Strings
		String[] arrStr;
		arrStr = rawResult.split("\\.");
		String integerPart = arrStr[0];
		String decimalPart = arrStr[1];
		
		// This is loop for formatting integerPart to add thousand separator ","
		// It checks size of integerPart string, and puts "," for every 3 digits by starting righthand side of String
		// It runs until number to format has at least 4 digits
		while (integerPart.length() > 3 ) {
				
			finalResult = "," + integerPart.substring(integerPart.length()-3) + finalResult;
			integerPart = integerPart.substring(0,integerPart.length()-3);
			
		}
		
		// Sign of the result is added to formated String to finalize the value and return it
		
		finalResult = signOfNumber + integerPart + finalResult + "." + decimalPart;
		
		return finalResult;
		
	}
	
	// This is getter method for getting Stack's current size

	public int getStkSize() {
		stkSize = stk.size();
		return stkSize;
	}


	
}
