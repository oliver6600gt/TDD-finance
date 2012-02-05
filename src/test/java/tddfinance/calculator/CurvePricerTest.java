package tddfinance.calculator;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.calculator.CurvePricer;
import tddfinance.contract.Bond;
import tddfinance.contract.Cash;
import tddfinance.contract.Currency;
import tddfinance.curve.DiscreteCurve;
import tddfinance.curve.FlatCurve;

public class CurvePricerTest {

	@Test
	public void priceInvalidContractTypeTest() {
		try {
			CurvePricer.price( Cash.USD, new FlatCurve(new LocalDate(2000, 4, 1), 0.1) );
			fail( "This line should not be reached as the above line has to throw an exception" );
		} catch (Exception e) {
			String expectedExceptionSubString = "Contract of class " + Cash.class.toString() + " cannot be handled by CurvePricer";
			assertTrue(
				"The following exception message:\n" + e.getMessage() + "\nmust Contains the following string:\n" + expectedExceptionSubString,
				e.getMessage().contains(expectedExceptionSubString));		
		}
	}
	
	@Test
	public void priceTest() throws Exception {
		Bond bond = new Bond( Currency.USD, 100.0, 0.5, new LocalDate(2000, 4, 1), Years.years(4) );
		assertEquals( 143.3248136, CurvePricer.price( bond, new FlatCurve(new LocalDate(2000, 4, 1), 0.3) ), 1.0e-6 );
		assertEquals( 118.4922948, CurvePricer.price( bond, new FlatCurve(new LocalDate(2000, 4, 1), 0.4) ), 1.0e-6 );
		assertEquals( 100.0,       CurvePricer.price( bond, new FlatCurve(new LocalDate(2000, 4, 1), 0.5) ), 1.0e-6 ); //if YTM = Coupon, then price = 100
	}

	@Test
	public void DiscreteCurveTest() throws Exception {
		LocalDate baseDate = new LocalDate(2001, 4, 1);
		Bond      bond     = new Bond( Currency.USD, 100.0, 0.05, baseDate, Years.years(5) );

		Map<LocalDate, Double> curveValues = new TreeMap<LocalDate, Double>();
		curveValues.put(baseDate.plusYears(1), 0.03);
		curveValues.put(baseDate.plusYears(2), 0.0325);
		curveValues.put(baseDate.plusYears(3), 0.0355);
		curveValues.put(baseDate.plusYears(4), 0.0380);
		curveValues.put(baseDate.plusYears(5), 0.042);
		
		assertEquals( 103.8320757, CurvePricer.price( bond, new DiscreteCurve(baseDate, curveValues) ), 1.0e-6 );
	}
	
	@Test
	public void testPriceSensitivity() throws Exception {
		LocalDate baseDate  = new LocalDate(2001, 4, 1);
		Bond      bond      = new Bond( Currency.USD, 100, 0.1, baseDate, Years.years(20));
		double    basePrice = 100;
		
		assertEquals( basePrice + 45.879684, CurvePricer.price( bond, new FlatCurve(baseDate, 0.06) ),  1.0e-6 );
		assertEquals( basePrice + 9.128545,  CurvePricer.price( bond, new FlatCurve(baseDate, 0.09) ),  1.0e-6 );
		assertEquals( basePrice + 0.857199,  CurvePricer.price( bond, new FlatCurve(baseDate, 0.099) ), 1.0e-6 );
		assertEquals( basePrice,             CurvePricer.price( bond, new FlatCurve(baseDate, 0.1) ),   1.0e-6 );
		assertEquals( basePrice - 0.845578,  CurvePricer.price( bond, new FlatCurve(baseDate, 0.101) ), 1.0e-6 );
		assertEquals( basePrice - 7.963329,  CurvePricer.price( bond, new FlatCurve(baseDate, 0.11) ),  1.0e-6 );
		assertEquals( basePrice - 26.492523, CurvePricer.price( bond, new FlatCurve(baseDate, 0.14) ),  1.0e-6 );
	}
}
