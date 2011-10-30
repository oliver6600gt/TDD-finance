package tddfinance.pricer;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.contract.Bond;
import tddfinance.contract.Cash;
import tddfinance.contract.Currency;
import tddfinance.curve.FlatCurve;
import tddfinance.pricer.CurvePricer;

public class CurvePricerTest {

	@Test
	public void priceTest() {
		Bond   bond       = new Bond( Currency.USD, 100.0, 0.5, new LocalDate(2000, 4, 1), Years.years(4) );
		
		Pricer bondPricerYTM3 = new CurvePricer( new FlatCurve(new LocalDate(2000, 4, 1), 0.3) );
		Pricer bondPricerYTM4 = new CurvePricer( new FlatCurve(new LocalDate(2000, 4, 1), 0.4) );
		Pricer bondPricerYTMSameAsCouponRate = new CurvePricer( new FlatCurve(new LocalDate(2000, 4, 1), 0.5) );

		try {
			assertEquals( 143.3248136, bondPricerYTM3.price( bond ), 1.0e-6 );
			assertEquals( 118.4922948, bondPricerYTM4.price( bond ), 1.0e-6 );
			assertEquals( 100.0, bondPricerYTMSameAsCouponRate.price( bond ), 1.0e-6 ); //if YTM = Coupon, then price = 100
		} catch (Exception e) {
			fail( "The above line should not have execption\n" + e.getMessage() );
		}
	}

	@Test
	public void priceInvalidContractTypeTest() {
		try {
			Pricer pricer = new CurvePricer( new FlatCurve(new LocalDate(2000, 4, 1), 0.1) );
			pricer.price(Cash.USD);
			fail( "This line should not be reached as the above line has to throw an exception" );
		} catch (Exception e) {
			String expectedExceptionSubString = "Contract of class " + Cash.class.toString() + " cannot be handled by CurvePricer";
			assertTrue(
				"The following exception message:\n" + e.getMessage() + "\nmust Contains the following string:\n" + expectedExceptionSubString,
				e.getMessage().contains(expectedExceptionSubString));		
		}
	}
}
