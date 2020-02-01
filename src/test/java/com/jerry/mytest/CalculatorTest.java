package com.jerry.mytest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;

public class CalculatorTest {
	private static Calculator calculator = new Calculator();
  
	
	@Test
	public void testAdd() {
	  calculator.add(2);
      calculator.add(3);
      AssertJUnit.assertEquals(5, calculator.getResult());
	}
  
	@Test
	public void testSubstract() {
	  calculator.add(5);
	  calculator.substract(3);
      AssertJUnit.assertEquals(2, calculator.getResult());
	}

	@Test
	public void testMultiply() {
	   calculator.add(3);
      calculator.multiply(2);
      AssertJUnit.assertEquals(6, calculator.getResult());
	}

	@Test
	public void testDivide() {
	  calculator.add(9);
      calculator.divide(3);
      AssertJUnit.assertEquals(3, calculator.getResult());
	}
	
	@BeforeMethod
	  public void beforeMethod() {
		  calculator.clear();
	  }

}


