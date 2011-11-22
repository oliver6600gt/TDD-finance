package tddfinance.pricer;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import tddfinance.contract.Contract;
import tddfinance.contract.Currency;
import tddfinance.contract.ZeroCoupon;
import tddfinance.day.DayCount;

public class ZeroCouponPricerTest {
	static final LocalDate today = new LocalDate(2010, 4, 1);

	@Test
	public void priceTest() throws Exception{

		Contract zero = new ZeroCoupon(today.plusYears(1), 100, Currency.USD);
		assertEquals( 95.238095, ZeroCouponPricer.price( zero, today, 0.05, 1, DayCount.DC_ACTUALACTUAL ), 1.0e-6 );
		
		zero = new ZeroCoupon(today, 100, Currency.USD);
		assertEquals( 100, ZeroCouponPricer.price( zero, today, 0.05, 1, DayCount.DC_ACTUALACTUAL ), 1.0e-6 );
		
		zero = new ZeroCoupon(today, 100, Currency.USD);
		assertEquals( 100, ZeroCouponPricer.price( zero, today, 0.05, 4, DayCount.DC_ACTUALACTUAL ), 1.0e-6 );
	} 

}
