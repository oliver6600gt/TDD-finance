package tddfinance.calculator;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.contract.Cashflow;
import tddfinance.contract.Currency;
import tddfinance.day.Compounding;
import tddfinance.day.DayCount;

public class FutureValueTest {

	@Test
	public void testName() throws Exception {
		LocalDate today = new LocalDate(2001, 4, 1);
		
		assertEquals( 105.0,    FutureValue.calculate( new Cashflow(100, today.plus(Years.ONE), Currency.USD),     today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.ANNUAL ), 1.0e-6 );	
		assertEquals( 110.25,   FutureValue.calculate( new Cashflow(100, today.plus(Years.TWO), Currency.USD),     today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.ANNUAL ), 1.0e-6 );	
		assertEquals( 105.0,    FutureValue.calculate( new Cashflow(100, today.plus(Months.TWELVE), Currency.USD), today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.ANNUAL ), 1.0e-6 );	
		assertEquals( 105.0625, FutureValue.calculate( new Cashflow(100, today.plus(Months.TWELVE), Currency.USD), today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.SEMI_ANNUAL ), 1.0e-6 );	
	}
}
