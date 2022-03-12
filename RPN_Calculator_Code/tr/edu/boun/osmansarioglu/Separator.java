package tr.edu.boun.osmansarioglu;

import java.util.ArrayList;

/**
* <h1>Separator Class </h1>
* <p>
* The Separator class has the code for dividing given String input into proper pieces and storing them.
* <p>
* It uses <b>ArrayList</b> class having <b>Object</b> class for storing data after validating their format
* <p>
* It has 3 different methods to make these operations:
* <p>
* <b>separateMe(String[] valStr) : </b> Divides given <i>String</i> <b> valStr </b> into pieces, and keep them 
* at  <b>ArryList</b> of <i>Object</i> type after <b> validation </b> whether they are <i>numbers</i> or <i>operators</i>.
* "Object" type is used to have flexibility at <i>"Engine"</i> class 
* which sends parameters to <i>"Calculator"</i> class.
* <p>
* <b>validateMe(String checkMe) : </b> Makes <b> validation </b> of given String whether is a numerical expression in expected format.
* <p>
* <b>checkAllNumeric(String checkStr) : </b> Makes <b> validation </b> of given String whether there is <b>no non-numerical expression </b> 
*
* @author  Osman Selçuk Sarýoðlu
* @version 1.0
* @since   2020-12-19
*/

public class Separator {
	

	// This is Engine class to track error messages and working status of RPN calculator engine
	
	private Engine runningEngine;
	
	
	// ArrayList separatedArray is used to store validated numbers and operators

	public ArrayList<Object> separatedArray;
	
	//String array splitedStr is used keep interim calculations
	private String[] splitedStr;
	
	
   /**
   * <h1>Constructor for Separator </h1>
   * <p>
   * This is used a constructor for new Separators. It gets parameter as String <b>inStr</b> and 
   * Engine <b>engine</b> which is used to track <b>"Error state"</b> during all operations
   * <p>
   * @param inStr Method gets <i>inStr</i> <b>String</b> as parameter. <b>inStr</b> is  
   * representing input to be split and validated. 
   * @param runningEngine Method gets <i>runningEngine</i> <b>Engine</b> object as parameter. 
   * <b>runningEngine</b> is used to handle calculation state and error message handling.
   * <p>  
   */	
	
	public Separator(String inStr , Engine engine) {

		// Sets calculator engine with given parameter, so all calculator related classes can reach
		// same error state and types
		
		runningEngine = engine;
		
		//splitedStr splits String inStr and keep its characters
		
		splitedStr = inStr.split("");
		
		// separateMe method is called for this String array and output is kept at 
		// ArrayList separatedArray in Object format
		
		separatedArray = separateMe(splitedStr);
		
	}
	
	
   /**
   * <h1>separateMe(String[] valStr) method for Separator </h1>
   * <p>
   * This method is used to <b>transform</b> the given String array <b>valStr</b> by merging into meaningful format.
   * <p>
   * Output of method is stored in an <b>ArrayList</b> having different types of Objects at the same time: <i>Double</i> for numbers 
   * and <i>String</i> for operators. 
   * <p>
   * @param valStr Method gets <i>valStr</i> <b> String array </b> as parameter. <b>valStr</b> represents the set of characters
   * to be validated and merged in proper format: <i>Double</i> for numbers and <i>String</i> for operators. 
   * @return <b>ArrayList</b> Method returns an <b>ArrayList</b> having different types of Objects at the same time: <i>Double</i> for numbers 
   * and <i>String</i> for operators.  
   */	
	
	private ArrayList<Object> separateMe(String[] valStr) {
		
		// opArr is the Array List of Object. This is updated during the process, and final version
		// is returned as output of method
		
		ArrayList<Object> opArr = new ArrayList<Object>();
		
		// tempField is String variable that is used as a temporary expression during process
		// It's used to merge elements of valStr until a meaningful expression is created
		// i.e. a Number, or an Operator
		
		String tempField = "";
		
		
		// This is the loop to process all elements of valStr 
		
		for(String tempStr: valStr) {
			
			// if there is an "Error" identified during process, state of Engine is updated as false
			// In case runningEngine state is identified as non-working, the loop process stops
					
			if (runningEngine.worksOK == false ) break;

			// each characters send in valStr is evaluted one-by-one
			// Some characters have special meaning for operations. So, switch-case condition is used to
			// trigger special actions for the characters.
			
			switch(tempStr) {
			
			// tempField keeps collection of all characters until these operations
			
			// ^ - "Enter" : This means a number is entered, and characters before "^" should be stored as number
				case "^":
					// if tempfield is not empty and is not just the negative sign, it should be a number to be stored
					
					if (tempField.equals("") == false && tempField.equals("-") == false) {
						
						//Before adding the number, it should be validated whether it's in proper format
						// validateMe returns the correct number format by converting String into Double
						// or 0.0 and Error state on runningEngine
						
						opArr.add(validateMe(tempField));
					}
					
					// When a number is added to ArrayList, temporary string is started over by cleaning its content.
					
					tempField = "";

					break;

			// +-/*  "Math Operators" : These operators trigger 2 potential actions:
			//		1. If there is no "^" before it, it acts as an "Enter"
			//		2. It sents its operation sign to opArr.
			//
			// i.e "4^5+" is orginal input, there is no "^" before "+", therefore "5" should be first validated
			// 	   and entered to opArr and then "+" should be added 

				case "+","-","/","*":

					if (tempField.equals("") == false ) {
						opArr.add(validateMe(tempField));
					}
				
					opArr.add(tempStr);					
					tempField = "";
					break;
					
			// "c" - "Sign change" : This operators triggers change of sign on potential number
			//		If there is a negative sign "-" already at tempFiel, it removes it
			//		if there is not a negative sign, it puts "-" at the beginning of string.
	
				case "c":
					if ((tempField.equals("") == false) && (tempField.substring(0, 1).equals("-")) ) {
						// There is a negative "-" sign already, so "c" means remove it. i.e. 1cc = 1 	
						tempField = tempField.substring(1, tempField.length());				
					}else {
						// There is no negative "-" sign , so "c" means put at the beginning of it. i.e. 1c = -1 	
						tempField = "-" + tempField;
					}
					break;		
					
			// "No special character": This means this character is a part of a number, so it's merged on tempField
				
				default:
					tempField += tempStr;

					break;
			}
			
		}
		
		// In some cases users may finish expressions only with a "^" or "c", then this validate this last part
		// and add as a number if it's validated 
		
		if (tempField.equals("") == false ) opArr.add(validateMe(tempField));
		
		return opArr;
	}

	
   /**
   * <h1>validateMe(String checkMe) method for Separator </h1>
   * <p>
   * This method is used to <b>validate</b> the given String <b>checkMe</b> is a number in correct format.
   * <p>
   * Output of method is returned as <b>Double</b>. If the validated String has no proper format, i.e. non-numeric, wrong usage of decimal
   * or thousand separators ".," , runningEngine has working state as "false" and relevant error message is input to errorType.
   * <p>
   * @param checkMe Method gets <i>checkMe</i> <b> String</b> as parameter. <b>checkMe</b> represents the String to be validated whether it can be a number or not
   * to be validated and merged in proper format: <i>Double</i> for numbers and <i>String</i> for operators. 
   * @return <b>Double</b> Method returns a <b>Double</b> value by converting String into double format. If there is an error identified at format,
   * method return 0.0 and updates error handling fields
   */	
		
	private Double validateMe(String checkMe) {

		// result is the variable to return with this method. Default is 0.0 (in case an error occurs it send default value)
		
		double result = 0.0;
		
		// First control is done very basically whether the string has any non-numeric characters or not
		// if there are any characters other than numbers and ".,-" this conditions runs to else statement
		
		if (checkAllNumeric(checkMe)) {
			
			// counter variable used in various parts of code
			
			int i = 0;
			
			// Boolean variable is used to keep error status due to thousands separators wrong usage
			
			boolean errorThousandSeparator = false;
			
			// signOfNumber will be used to understand value is negative or positive. 
			
			String signOfNumber = checkMe.substring(0, 1); 
			
			// if sign of number is negative, it's removed from string to make formatting correct. 
			// It will be added before sending final format if the number is negative
			
			if (signOfNumber.equals("-")) {
				checkMe = checkMe.substring(1);
			} else {
				signOfNumber = "";
			}
			
			
			// arrStr String array is used to keep values of integer and decimal Parts of number
			
			String[] arrStr;
			String integerPart = checkMe;
			String decimalPart = "";

			// this variable used in various parts of code to search specific characters in Strings
			
			char checkChar = '.';
			
			// This loop finds how many times "Decimal separator" "." is used in string
			// i keeps this number
			
			for (int nxtChar = 0; nxtChar < checkMe.length(); nxtChar++ ) {
				if (checkMe.charAt(nxtChar) == checkChar) {
					i++;
				}
			}
			
			// if string has "." more than once, this means the string is "not Valid" to be a number
			// Therefore else statement works, and relevant error handling is done
					
			if ( i < 2 ) {
			
				// location of decimal separator is found, and stored in int variable i
				i = checkMe.indexOf('.');
				
				// if there is a decimal separator in string, then string is divided into two pieces: 
				// 1) integerPart, and 2) decimalPart
				if(i > -1 ) {
					arrStr = checkMe.split("\\.");
					integerPart = arrStr[0];
					decimalPart = arrStr[1];
				}	
	
				//Now, checkChar is set as "," to start checking Thousands separator is used properly
				
				checkChar = ',';
				
				
				// if there is "," in decimalPart String, the code triggers error handling, and update errorType accordingly
				
				if (decimalPart.indexOf(checkChar) > -1 ) {
	
					runningEngine.worksOK = false;
					runningEngine.errorType = "Error: Wrong Usage of Thousand Seperator";
					
				} else {

					// if there is "," in integerPart String, then loop condition starts to check 
					// whether "," is in every 3-digits or not.
					
					if (integerPart.indexOf(checkChar) > -1 ) {
					
						// tmpCounter is counter for finding "," is used for-how many digits
						
						int tmpCounter = 0;
						
						// Searching starts from right side of String
						// counter is set to 0, for each time it reaches 3 digit, and recognizes a "," afterwards
						// if it does't recognize "," in each 3-digit cycle, then this means "," is not used properly
						// errorThousandSeparator is set to true, meaning there is an error on "Thousand separator" usage
						
						for (int nxtChar = integerPart.length() - 1; nxtChar >= 0 ; nxtChar-- ) {
							
							if (integerPart.charAt(nxtChar) == checkChar) {
								if (tmpCounter == 3) {
									tmpCounter = 0;
								} else {
									errorThousandSeparator = true;
									break;
								}
							} else tmpCounter++;
						}
						
						// if it's validated that "Thousand separators" are used properly, "," are removed from String
						// and result is updated by converting String into double number
						
						if (errorThousandSeparator == false ) {
							
							// removing "," from String
							
							i = 0;
							while (i != -1 ) {
								i = integerPart.indexOf(',');
								if(i > -1 ) {
									integerPart = integerPart.substring(0, i) + integerPart.substring(i+1);
								}	
							}
							
							// integerPart String is parsed to Double and assigned to result
							
							result = Double.parseDouble(integerPart);
							
							// if there is decimalPart, then decimalPart is parsed to double, and added to result
							
							if (decimalPart.equals("") == false) {
								
								// length of the decimalPart is used to identify # of digits after "."
								
								result += Double.parseDouble(decimalPart) / Math.pow(10, decimalPart.length());
							}
							
							// Finally, sign of the number is updated if it's negative
							
							if (signOfNumber.equals("-")) result *= -1.0;
							
						} else {
							
							// Error handling for wrong usage of Thousand Separator
							
							runningEngine.worksOK = false;
							runningEngine.errorType = "Error: Wrong Usage of Thousand Seperator";
							
						}
						
					} else {
						
						// if there is no "," at number, String is parsed to double number and assigned to result
						
						result = Double.parseDouble(integerPart);	
						
						// if there is decimalPart, then decimalPart is parsed to double, and added to result

						if (decimalPart.equals("") == false) {
							// length of the decimalPart is used to identify # of digits after "."
							
							result += Double.parseDouble(decimalPart) / Math.pow(10, decimalPart.length());
						}
						
						// Finally, sign of the number is updated if it's negative

						if (signOfNumber.equals("-")) result *= -1.0;
						
					}
					
				}
				
			} else {
				// Error handling for wrong usage of Decimal Separator
				
				runningEngine.worksOK = false;
				runningEngine.errorType = "Error: Wrong Usage of Decimal Seperator";
			}
			
		} else {
			
			// Error handling for non numeric inputs
			
			runningEngine.worksOK = false;
			runningEngine.errorType = "Error: Non Numeric Value";
		}
		
		return result;		
		
	}
	
   /**
   * <h1>checkAllNumeric(String checkStr) method for Separator </h1>
   * <p>
   * This method is used to <b>validate</b> the given String <b>checkStr</b> has only numbers or allowed signs ".,-".
   * <p>
   * Output of method is returned as <b>boolean</b>. If the validated String has proper format, i.e. numeric, 
   * method returns <i> true </i> and if there is any non-numeric value in it, it returns <i> false </i>
   * <p>
   * @param checkStr Method gets <i>checkStr</i> <b> String</b> as parameter. <b>checkStr</b> represents the String to be validated whether
   *  it has only allowed characters.
   * @return <b>boolean</b> Method returns <i>true or false</i> base on validation output.
   */	

	private boolean checkAllNumeric(String checkStr) {
		
		// This is set of allowed characters
		
		char[] checkNum = {'0','1','2','3','4','5','6','7','8','9','.',',','-'};
		
		// checkOK is variable to keep result of validation
		
		boolean checkOK = false;
		
		// counter used in loop
		int i;
		
		// This is the loop to remove allowed characters from input String checkStr
		
		for (char temp:checkNum) {
			i = 0;
			while (i != -1 ) {
				i = checkStr.indexOf(temp);
				if(i > -1 ) {
					checkStr = checkStr.substring(0, i) + checkStr.substring(i+1);
				}	
			}
		}
		
		// After removing all allowed characters, if there is still a value remaining at checkStr
		// this means validation failed.
		
		// if all characters are removed, and there is no value left at checkStr, this means
		// validation is successfull, and method returns true value
		
		if (checkStr.equals("")) checkOK = true;
		
		return checkOK;
	}
		

}
	

