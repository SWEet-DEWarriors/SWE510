package tr.edu.boun.osmansarioglu;

import static org.junit.Assert.*;

import org.junit.Test;


/**
* <h1>Test Suite for SWE510 Project - RPN Calculator </h1>
* <p>
* This test suite contains test scenarios for <b>Engine, Calculator, Separator</b> classes' methods .
* <p>
* <b>Engine tests: </b> testCalculatingFullExpression
* <p>
* <b>Calculator tests: </b> testCalculatorAddValue, testCalculatorResult, testCalculatorCalculateSum, 
* testCalculatorSubstract, testCalculatorMultiply, testCalculatorCalculorDivide
* <p>
* <b>Separator tests : </b> testSepartorSeparateMe, testSepartorValidateMe, testSepartorValidateMeErrorMsg_1, 
* testSepartorValidateMeErrorMsg_2, testSepartorValidateMeErrorMsg_3, testSepartorValidateMeErrorMsg_4
*
* @author  Osman Selçuk Sarýoðlu
* @version 1.0
* @since   2020-12-19
*/

public class TestSuite {


	// Engine will be tested class
	
	Engine testEngine = new Engine();
	
	
	/**
	* <h1>Testing Calculating a Full Expression</h1>
	* <p>
	* This test contains 19 different scenarios, and compare expected output of RPN calculator, and actual output.
	* <p>
	* If all scenarios give "True", test passes. Any failure at tests results as "Fail"
	* <p>
	* Test scenarios covers all cases mentioned at project paper, and includes cases expecting "Errors"
	* <p>
	*/
	
	
	@Test
	public void testCalculatingFullExpression() {
		String[] testInputs = { "2^3+",
								"2^3-",
								"2^3^4++",
								"2^3^4+-",
								"2^3^5+*",
								"2^3^5*+",
								"1^2+3+4+",
								"2c^3c-",								
								"+",
								"2+",
								"1^2-+",
								"2.1^3.05+",
								"1ccc^1*",
								"1.2c^1*",
								"12,345.67c^1+",
								"1.000,2^1*",
								"1.000.000^1*",
								"1,00,000.00^1*",
								"12osman^3^5**"};
		
		String[] expectedOutputs = { 			 "5.0",
												"-1.0",
												 "9.0",
												"-5.0",	
												"16.0",
											    "17.0",
												"10.0",
												 "1.0",
	"Error: Can't calculate: Too many operator or Division to 0",
	"Error: Can't calculate: Too many operator or Division to 0",
	"Error: Can't calculate: Too many operator or Division to 0",
												"5.15",
												"-1.0",
												"-1.2",
										  "-12,344.67",
			"Error: Wrong Usage of Thousand Seperator",
			 "Error: Wrong Usage of Decimal Seperator",
			"Error: Wrong Usage of Thousand Seperator",
							"Error: Non Numeric Value"};
		
		String[] actualOutputs = new String[19];

		int i=0;
		for(String tempStr: testInputs) {
			actualOutputs[i] = testEngine.getStringForCalc(tempStr, testEngine);
			assertEquals(expectedOutputs[i],actualOutputs[i]);
			i++;
		}
	}
	
	
	// Calculator will be tested class
	
	Calculator testCalculator = new Calculator();
	
	/**
	* <h1>Testing Calculator Gets Values</h1>
	* <p>
	* This test checks whether <b>Calculator</b> classes <b>addValue()</b> method works properly
	* <p>
	* If all scenarios give "True", test passes. Any failure at tests results as "Fail"
	* <p>
	* Test scenarios covers 2 cases to add a value to Stack of Calculator 
	* <p>
	*/
	
	
	@Test
	public void testCalculatorAddValue() {
		Double addNumber1 = 1.0;
		Double addNumber2 = 1.0;
		
		int countStackSize = 0;
	
		testCalculator.addValue(addNumber1);
		testCalculator.addValue(addNumber2);
		
		countStackSize += 2;
			
		assertEquals(countStackSize,testCalculator.getStkSize());
		
		
	}
	
	/**
	* <h1>Testing Calculator Gives Results</h1>
	* <p>
	* This test checks whether <b>Calculator</b> classes <b>result()</b> method works properly
	* <p>
	* This method shows the value at top of stack
	* <p>
	*/
	
	@Test
	public void testCalculatorResult() {
		Double addNumber1 = 1.0;
		Double addNumber2 = -2300.0;
	
		testCalculator.addValue(addNumber1);
		testCalculator.addValue(addNumber2);
		
		String expectedResult = "-2,300.0";
		
		assertEquals(expectedResult,testCalculator.result());
		
	}
	
	/**
	* <h1>Testing Calculator Calculation "Sum" </h1>
	* <p>
	* This test checks whether <b>Calculator</b> classes <b>calculate()</b> method 
	* works properly according to given operator
	* <p>
	* Scenarios checks whether "Sum" of 2 numbers are correct or not
	* <p>
	*/
	
	
	@Test
	public void testCalculatorCalculateSum() {
		
		Double addNumber1 = 1.0;
		Double addNumber2 = -2300.0;
	
		testCalculator.addValue(addNumber1);
		testCalculator.addValue(addNumber2);
		
		testCalculator.calculate("+");
		
		
		String expectedResult = "-2,299.0";
		
		assertEquals(expectedResult,testCalculator.result());
		assertFalse(testCalculator.calculate("+"));
	}
	
	
	/**
	* <h1>Testing Calculator Calculation "Substract" </h1>
	* <p>
	* This test checks whether <b>Calculator</b> classes <b>calculate()</b> method 
	* works properly according to given operator
	* <p>
	* Scenarios checks whether "Substraction" of 2 numbers are correct or not
	* <p>
	*/
	
	@Test
	public void testCalculatorCalculateSubstract() {
		
		Double addNumber1 = 1.0;
		Double addNumber2 = -2300.0;
	
		testCalculator.addValue(addNumber1);
		testCalculator.addValue(addNumber2);
		
		testCalculator.calculate("-");
		
		
		String expectedResult = "2,301.0";
		
		assertEquals(expectedResult,testCalculator.result());
		assertFalse(testCalculator.calculate("-"));
	}
	
	/**
	* <h1>Testing Calculator Calculation "Multiply" </h1>
	* <p>
	* This test checks whether <b>Calculator</b> classes <b>calculate()</b> method 
	* works properly according to given operator
	* <p>
	* Scenarios checks whether "Multiplication" of 2 numbers are correct or not
	* <p>
	*/
	
	@Test
	public void testCalculatorCalculateMultiply() {
		
		Double addNumber1 = 1.0;
		Double addNumber2 = -2300.0;
	
		testCalculator.addValue(addNumber1);
		testCalculator.addValue(addNumber2);
		
		testCalculator.calculate("*");
		
		
		String expectedResult = "-2,300.0";
		
		assertEquals(expectedResult,testCalculator.result());
		assertFalse(testCalculator.calculate("*"));
		
	}
	
	/**
	* <h1>Testing Calculator Calculation "Divide" </h1>
	* <p>
	* This test checks whether <b>Calculator</b> classes <b>calculate()</b> method 
	* works properly according to given operator
	* <p>
	* Scenarios checks whether "Division" of 2 numbers are correct or not
	* <p>
	*/

	@Test
	public void testCalculatorCalculateDivide() {
		
		Double addNumber1 = 2300.0;
		Double addNumber2 = -2300.0;
	
		testCalculator.addValue(addNumber1);
		testCalculator.addValue(addNumber2);
		
		testCalculator.calculate("/");
		
		
		String expectedResult = "-1.0";
		
		assertEquals(expectedResult,testCalculator.result());
		assertFalse(testCalculator.calculate("/"));
		
	}
	
	
	/**
	* <h1>Testing Separator's seperateMe() </h1>
	* <p>
	* This test checks whether <b>Separator</b> classes <b>separateMe()</b> method 
	* works properly. This method divide a given String into pieces, and keep them in an <b>ArrayList</b> with Object
	* container. ArrayList contains Double numbers, and String operators.
	* <p>
	* Scenarios checks whether <b>ArrayList</b> contains right value after split.
	* <p>
	*/
	
	
	// Separator will be tested class
	
	Separator testSeparator; 
	
	@Test
	public void testSepartorSeparateMe() {
		
		String testString = "2^3^4^5+++";
		testSeparator = new Separator(testString, testEngine);
		
		int numberOfItemsAtSeparatedArray = 7;
		Double fourthArrayContent = 5.0;
		
		assertEquals(numberOfItemsAtSeparatedArray,testSeparator.separatedArray.size());
		assertEquals(fourthArrayContent,testSeparator.separatedArray.get(3));
		
	}
	
	/**
	* <h1>Testing Separator's valideMe() </h1>
	* <p>
	* This test checks whether <b>Separator</b> classes <b>validateMe()</b> method 
	* works properly. This method validates a given String is numeric with expected format. It results Double
	* value of given String into <b>seperatedArray</b>.
	* <p>
	* Method returns "0.0" and triggers Engine.worksOK as "False" when format is not in suitable for converting a number.
	* It returns proper <b>"Error Message" </b> with updating Engine.errorType
	* <p>
	*/

	@Test
	public void testSepartorValidateMe() {
		
		String testString = "2.23^5.23.4^";
		testSeparator = new Separator(testString, testEngine);
		
		Double firstArrayContent = 2.23;
		assertEquals(firstArrayContent,testSeparator.separatedArray.get(0));
		
		Double secondArrayContent = 0.0;
		assertEquals(secondArrayContent,testSeparator.separatedArray.get(1));

	}
	
	/**
	* <h1>Testing Error Messages </h1>
	* <p>
	* This test checks whether <b>Separator</b> classes <b>validateMe()</b> method 
	* works properly. Method returns "0.0" and triggers Engine.worksOK as "False" when format is not in suitable for converting a number.
	* It returns proper <b>"Error Message" </b> with updating Engine.errorType
	* <p>
	* <i>This Error is triggered when someone enters more than 1 decimal separator </i>
	*/

	
	@Test
	public void testSepartorValidateMeErrorMsg_1() {
		
		String testString = "5.23.4^";
		testSeparator = new Separator(testString, testEngine);
		
		assertFalse(testEngine.worksOK);
		
		String expectedError = "Error: Wrong Usage of Decimal Seperator";
		assertEquals(expectedError,testEngine.errorType);

	}
	
	/**
	* <h1>Testing Error Messages </h1>
	* <p>
	* This test checks whether <b>Separator</b> classes <b>validateMe()</b> method 
	* works properly. Method returns "0.0" and triggers Engine.worksOK as "False" when format is not in suitable for converting a number.
	* It returns proper <b>"Error Message" </b> with updating Engine.errorType
	* <p>
	* <i>This Error is triggered when someone uses thousands separator in wrong place </i>
	*/


	@Test
	public void testSepartorValidateMeErrorMsg_2() {
		
		String testString = "5,23.4^";
		testSeparator = new Separator(testString, testEngine);
		
		assertFalse(testEngine.worksOK);
		
		String expectedError = "Error: Wrong Usage of Thousand Seperator";
		assertEquals(expectedError,testEngine.errorType);

	}
	
	/**
	* <h1>Testing Error Messages </h1>
	* <p>
	* This test checks whether <b>Separator</b> classes <b>validateMe()</b> method 
	* works properly. Method returns "0.0" and triggers Engine.worksOK as "False" when format is not in suitable for converting a number.
	* It returns proper <b>"Error Message" </b> with updating Engine.errorType
	* <p>
	* <i>This Error is triggered when someone uses thousands separator at decimal place </i>
	*/
	
	@Test
	public void testSepartorValidateMeErrorMsg_3() {
		
		String testString = "5,233.4,2^";
		testSeparator = new Separator(testString, testEngine);
		
		assertFalse(testEngine.worksOK);
		
		String expectedError = "Error: Wrong Usage of Thousand Seperator";
		assertEquals(expectedError,testEngine.errorType);

	}
	
	/**
	* <h1>Testing Error Messages </h1>
	* <p>
	* This test checks whether <b>Separator</b> classes <b>validateMe()</b> method 
	* works properly. Method returns "0.0" and triggers Engine.worksOK as "False" when format is not in suitable for converting a number.
	* It returns proper <b>"Error Message" </b> with updating Engine.errorType
	* <p>
	* <i>This Error is triggered when someone enters a non-numeric expression at console </i>
	*/
	
	@Test
	public void testSepartorValidateMeErrorMsg_4() {
		
		String testString = "123osMan^";
		testSeparator = new Separator(testString, testEngine);
		
		assertFalse(testEngine.worksOK);
		
		String expectedError = "Error: Non Numeric Value";
		assertEquals(expectedError,testEngine.errorType);

	}
	
}


