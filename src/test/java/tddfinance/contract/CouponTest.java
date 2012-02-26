package tddfinance.contract;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.assertEqualsStrict;
import static tddfinance.util.Assertion.assertInEqualStrict;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.junit.Test;

import tddfinance.day.Compounding;
import tddfinance.day.DayCount;
import tddfinance.numeral.AccruedInterest;

public class CouponTest {
	
	static final LocalDate today = new LocalDate(2010, 4, 1);
	
	@Test
	public void equalityTest() throws Exception {
		assertEqualsStrict( 
			new Coupon( 0.05, 100, Currency.USD, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ), 
			new Coupon( 0.05, 100, Currency.USD, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ) 
		);
	}

	@Test
	public void InequalityTest() throws Exception {
		assertInEqualStrict(
			new Coupon( 0.05, 100, Currency.USD, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ), 
			new Coupon( 0.04, 100, Currency.USD, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ) 
		);
		assertInEqualStrict(
			new Coupon( 0.05, 100, Currency.USD, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ), 
			new Coupon( 0.05, 100, Currency.EUR, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ) 
		);
		assertInEqualStrict(
			new Coupon( 0.05, 100, Currency.USD, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ), 
			new Cashflow(
				new AccruedInterest( 0.05, 100, today, today.plus(Months.SIX), today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ),
				today.plus(Months.SIX),
				Currency.USD )	
		);
	}
	

	@Test
	public void getterTest() throws Exception {
		Coupon c = new Coupon( 100, 0.05, Currency.USD, today, today.plus(Months.SIX), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ); 

		assertEquals(0.05, c.couponRate(),   1.0e-8);
		assertEquals(100,  c.faceValue(),    1.0e-8);
		assertEquals(2.5,  c.couponAmount(), 1.0e-8);
		assertEquals(Currency.USD,            c.currency());
		assertEquals(today,                   c.accrualStartDate());
		assertEquals(today.plus(Months.SIX),  c.settlementDate());
		assertEquals(DayCount.DC_30360US,     c.dayCountConvention());
		assertEquals(Compounding.SEMI_ANNUAL, c.compoundingRule());
	}

}
