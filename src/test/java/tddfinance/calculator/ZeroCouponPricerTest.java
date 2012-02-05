package tddfinance.calculator;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import tddfinance.calculator.ZeroCouponPricer;
import tddfinance.contract.Cashflow;
import tddfinance.contract.Currency;
import tddfinance.contract.ZeroCoupon;
import tddfinance.day.Compounding;
import tddfinance.day.DayCount;

public class ZeroCouponPricerTest {
	static final LocalDate today = new LocalDate(2010, 4, 1);

	@Test
	public void priceTest() throws Exception{
		assertEquals( 95.238095, ZeroCouponPricer.price( new ZeroCoupon(today.plusYears(1), 100, Currency.USD), today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.ANNUAL    ), 1.0e-6 );
		assertEquals( 100,       ZeroCouponPricer.price( new ZeroCoupon(today, 100, Currency.USD),              today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.ANNUAL    ), 1.0e-6 );
		assertEquals( 100,       ZeroCouponPricer.price( new ZeroCoupon(today, 100, Currency.USD),              today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.QUARTERLY ), 1.0e-6 );	

		assertEquals( 95.238095, ZeroCouponPricer.price( new Cashflow(today.plusYears(1), 100, Currency.USD), today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.ANNUAL    ), 1.0e-6 );
		assertEquals( 100,       ZeroCouponPricer.price( new Cashflow(today, 100, Currency.USD),              today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.ANNUAL    ), 1.0e-6 );
		assertEquals( 100,       ZeroCouponPricer.price( new Cashflow(today, 100, Currency.USD),              today, 0.05, DayCount.DC_ACTUAL_ACTUAL_ICMA, Compounding.QUARTERLY ), 1.0e-6 );	
	} 
}
