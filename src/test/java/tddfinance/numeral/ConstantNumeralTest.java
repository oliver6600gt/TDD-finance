package tddfinance.numeral;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConstantNumeralTest {
	
	@Test
	public void test() {
		assertEquals( 1.0, new ConstantNumeral(1.0).getValue(), 1.0e-10 );
		assertEquals( 5.0, new ConstantNumeral(5).getValue(),   1.0e-10 );
	}
	
}
