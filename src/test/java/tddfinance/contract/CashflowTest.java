package tddfinance.contract;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.trade.PositionEffect;

public class CashflowTest {
	static final LocalDate today = new LocalDate(2010, 4, 1);
	
	@Test
	public void equalityTest() throws Exception {
		LocalDate settlementDate = today;
		assertEqualsStrict( new Cashflow(settlementDate, 100, Currency.USD), new Cashflow(settlementDate, 100, Currency.USD) );	
		assertEqualsStrict( new Cashflow(settlementDate.plus(Years.years(1)), 100, Currency.USD), new Cashflow(settlementDate.plus(Years.years(1)), 100, Currency.USD) );
	}

	@Test
	public void InequalityTest() throws Exception {
		LocalDate settlementDate = today;
		assertInEqualStrict( new Cashflow(settlementDate, 100, Currency.USD), new Cashflow(settlementDate.plus(Days.days(1)), 100, Currency.USD) );	
		assertInEqualStrict( new Cashflow(settlementDate, 100, Currency.USD), new Cashflow(settlementDate, 100, Currency.EUR) );	
		assertInEqualStrict( new Cashflow(settlementDate, 100, Currency.USD), new Cashflow(settlementDate, 100.00001, Currency.USD) );	
		assertInEqualStrict( new Cashflow(settlementDate, 100, Currency.USD), new ContractScale(100, new ContractGet(settlementDate, Cash.USD)) );	
	}

	@Test
	public void getterTest() throws Exception {
		LocalDate settlementDate = today;
		double    quantity       = 100;
		Cashflow  cashflow       = new Cashflow( settlementDate, quantity, Currency.USD );
		assertEquals(settlementDate,  cashflow.settlementDate());
		assertEquals(settlementDate,  cashflow.maturityDate());
		assertEquals(quantity,        cashflow.quantity(), 1.0e-6);
		assertEquals(Currency.USD,    cashflow.currency());
	}
	
	@Test
	public void factorTest() throws Exception {
		assertEquals(100, new Cashflow(today, 100, Currency.USD).scaleFactor(), 1.0e-16);
	}
	
	@Test
	public void currencyTest() throws Exception {
		assertEquals(Currency.USD, new Cashflow(today, 100, Currency.USD).currency());		
	}
	
	@Test
	public void nextEventDateTest() throws Exception {
		LocalDate settlementDate = today;
		Cashflow  cashflow       = new Cashflow( settlementDate, 500, Currency.USD );
		
		assertEquals(settlementDate, cashflow.nextEventDate());
	}
	
	@Test
	public void nextEventTest() throws Exception {
		LocalDate settlementDate = today;
		double    quantity       = 100;
		Cashflow  cashflow       = new Cashflow( settlementDate, quantity, Currency.USD );

		TradeEvent nextEvent = cashflow.nextEvent();
		assertEquals(settlementDate, nextEvent.eventDate());

		//The next event is to 1) lose the contract ContractGet(Cash.USD) (-1), and 2) really get Cash.USD(+1)
		assertEquals(
			new PositionEffect(cashflow, -1, Cash.USD, quantity), 
			nextEvent.positionEffect());
	}

	@Test
	public void nextContractTest() throws Exception {
		assertEquals(Contract.ZERO, new Cashflow(today, 100, Currency.USD).nextContract());
	}
	
	@Test
	public void nextSpunOffPositions() throws Exception {
		assertEquals( 
			new PositionEffect(Cash.USD, 100), 
			new Cashflow( today, 100, Currency.USD ).nextSpunOffPositions() );

		assertEquals( 
			new PositionEffect(new Cashflow(today, 100, Currency.USD), 1), 
			new ContractGet(today.minusDays(1), new Cashflow(today, 100, Currency.USD)).nextSpunOffPositions() );

		assertEquals( 
			new PositionEffect(new Cashflow(today, 1, Currency.USD), 100), 
			new ContractGet(today.minusDays(1), new Cashflow(today, 100, Currency.USD)).nextSpunOffPositions() );
	}

//	@Test
//	public void toStringTest() throws Exception {
//		assertEquals( 
//			
//			new Cashflow( new LocalDate(2010, 4, 1), 100, Currency.USD ).toString() );
//		
//	}
}
