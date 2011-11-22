package tddfinance.contract;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.day.Compounding;
import tddfinance.trade.PositionEffect;


public class CashflowSetTest {
	
	@Test
	public void equalityTest() throws Exception {
		assertEqualsStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(4) ),
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(4) ) );
		assertEqualsStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ),
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ) );
		assertEqualsStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(3) ),
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(3), Compounding.ANNUAL ) );
	}

	@Test
	public void InequalityTest() throws Exception {
		assertInEqualStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(4) ),
			new CashflowSet( Currency.EUR, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(4) ) );
		assertInEqualStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ),
			new CashflowSet( Currency.USD, 200.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ) );
		assertInEqualStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ),
			new CashflowSet( Currency.USD, 100.0, 0.04, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ) );
		assertInEqualStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ),
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2001,5,6), Years.years(1), Compounding.QUARTERLY ) );
		assertInEqualStrict(
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.QUARTERLY ),
			new CashflowSet( Currency.USD, 100.0, 0.08, new LocalDate(2000,5,6), Years.years(1), Compounding.SEMI_ANNUAL ) );
	}
	
	@Test
	public void CashFlowListTest() throws Exception {
		double quantity     = 1000000;
		double couponRate   = 0.08;
		double couponAmount = quantity * couponRate / 2; //semi-annualy: devided by 2 

		CashflowSet cashflowSet = new CashflowSet( Currency.USD, quantity, couponRate, new LocalDate(2000,5,6), Years.years(4), Compounding.SEMI_ANNUAL );

		//tenorStartDate = 2000-5-6, semi-annual => first coupon date = 2000-11-6
		List<Contract> expectedCashflows = new ArrayList<Contract>();
		expectedCashflows.add( new Cashflow(new LocalDate(2000,11,6), couponAmount, Currency.USD) );
		expectedCashflows.add( new Cashflow(new LocalDate(2001,5,6),  couponAmount, Currency.USD) );
		expectedCashflows.add( new Cashflow(new LocalDate(2001,11,6), couponAmount, Currency.USD) );
		expectedCashflows.add( new Cashflow(new LocalDate(2002,5,6),  couponAmount, Currency.USD) );
		expectedCashflows.add( new Cashflow(new LocalDate(2002,11,6), couponAmount, Currency.USD) );
		expectedCashflows.add( new Cashflow(new LocalDate(2003,5,6),  couponAmount, Currency.USD) );
		expectedCashflows.add( new Cashflow(new LocalDate(2003,11,6), couponAmount, Currency.USD) );
		expectedCashflows.add( new Cashflow(new LocalDate(2004,5,6),  couponAmount, Currency.USD) );

		assertEquals(expectedCashflows, cashflowSet.cashflowList());
	}
	
	@Test
	public void maturityDateTest() throws Exception {
		assertEquals(
			new LocalDate(2004,5,6),
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6), Years.years(4), Compounding.SEMI_ANNUAL ).maturityDate());
		assertEquals(
			new LocalDate(2001,11,6),
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6), Months.months(18), Compounding.SEMI_ANNUAL ).maturityDate() );
	}

	@Test
	public void nextEventDateTest() throws Exception {
		assertEquals(
			new LocalDate(2000,11,6),
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6), Years.years(4), Compounding.SEMI_ANNUAL ).nextEventDate() );

		assertEquals(
			new LocalDate(2001,5,6),
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6), Years.years(4) ).nextEventDate() );
	}
	
	@Test
	public void nextContractTest() throws Exception {
		assertEquals(
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,11,6), Months.months(3*12 + 6), Compounding.SEMI_ANNUAL ),
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6),  Years.years(4),          Compounding.SEMI_ANNUAL ).nextContract() );
		assertEquals(
			Contract.ZERO,
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6), Years.years(1), Compounding.ANNUAL ).nextContract() );
	}
	
	@Test
	public void nextSpunOffPositionsTest() throws Exception {
		assertEquals(
			new PositionEffect( Cash.USD, 10 ),
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6), Years.years(4), Compounding.ANNUAL).nextSpunOffPositions() );
		assertEquals(
			new PositionEffect( Cash.USD, 5 ),
			new CashflowSet( Currency.USD, 100, 0.1, new LocalDate(2000,5,6), Years.years(4), Compounding.SEMI_ANNUAL ).nextSpunOffPositions() );
	}
}
