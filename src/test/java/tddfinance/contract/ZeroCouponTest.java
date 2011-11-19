package tddfinance.contract;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;

public class ZeroCouponTest {
	static final LocalDate today = new LocalDate(2010, 4, 1);
	
	@Test
	public void equalityTest() throws Exception {
		LocalDate settlementDate = today;
		assertEqualsStrict( new ZeroCoupon(settlementDate, 100, Cash.USD), new ZeroCoupon(settlementDate, 100, Cash.USD) );	
		assertEqualsStrict( new ZeroCoupon(settlementDate, 100, Cash.USD), new ZeroCoupon(settlementDate, 100, Currency.USD) );	
		assertEqualsStrict( new ZeroCoupon(settlementDate.plus(Years.years(1)), 100, Cash.USD), new ZeroCoupon(settlementDate.plus(Years.years(1)), 100, Cash.USD) );
	}

	@Test
	public void InequalityTest() throws Exception {
		LocalDate settlementDate = today;
		assertInEqualStrict( new ZeroCoupon(settlementDate, 100, Cash.USD), new ZeroCoupon(settlementDate.plus(Days.days(1)), 100, Cash.USD) );	
		assertInEqualStrict( new ZeroCoupon(settlementDate, 100, Cash.USD), new ZeroCoupon(settlementDate, 100, Cash.EUR) );	
		assertInEqualStrict( new ZeroCoupon(settlementDate, 100, Cash.USD), new ZeroCoupon(settlementDate, 100.00001, Currency.USD) );	
		assertInEqualStrict( new ZeroCoupon(settlementDate, 100, Cash.USD), new ContractScale(100, new ContractGet(settlementDate, Cash.USD)) );	
		assertInEqualStrict( new ZeroCoupon(settlementDate, 100, Cash.USD), new Cashflow(settlementDate, 100, Cash.USD) );	
	}

	@Test
	public void getterTest() throws Exception {
		LocalDate  settlementDate = today;
		double     quantity       = 100;
		ZeroCoupon zero           = new ZeroCoupon( settlementDate, quantity, Currency.USD );
		assertEquals(settlementDate,  zero.settlementDate());
		assertEquals(settlementDate,  zero.maturityDate());
		assertEquals(quantity,        zero.quantity(), 1.0e-6);
		assertEquals(Currency.USD,    zero.currency());
	}
	
	@Test
	public void factorTest() throws Exception {
		assertEquals(100, new ZeroCoupon(today, 100, Currency.USD).scaleFactor(), 1.0e-16);
	}
	
	@Test
	public void currencyTest() throws Exception {
		assertEquals(Currency.USD, new Cashflow(today, 100, Currency.USD).currency());		
	}
	
	@Test
	public void nextEventDateTest() throws Exception {
		LocalDate  settlementDate = today;
		ZeroCoupon zero           = new ZeroCoupon( settlementDate, 500, Currency.USD );
		
		assertEquals(settlementDate, zero.nextEventDate());
	}

	@Test
	public void nextContractTest() throws Exception {
		assertEquals(Contract.ZERO, new ZeroCoupon(today, 100, Currency.USD).nextContract());
	}
}
