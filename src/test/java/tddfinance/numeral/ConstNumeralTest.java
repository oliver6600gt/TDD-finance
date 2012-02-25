package tddfinance.numeral;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConstNumeralTest {
	
	@Test
	public void test() {
		assertEquals( 1.0, new ConstNumeral(1.0).getValue(), 1.0e-10 );
		assertEquals( 5.0, new ConstNumeral(5).getValue(),   1.0e-10 );
	}
	
}
