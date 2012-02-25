package tddfinance.contract;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.trade.PositionEffect;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

public class ContractScaleTest {
	static final LocalDate today = new LocalDate(2011, 4, 4);

	@Test
	public void equalityTest() throws Exception {
		assertEqualsStrict(new ContractScale( 10.0 * 100.0, Cash.USD ), new ContractScale(1000.0, new Cash("USD")));
		assertEqualsStrict(new ContractScale( 100.0, Cash.USD ),        new ContractScale(100.0000000000000001, new Cash("USD")));
	}

	@Test
	public void inequalityTest() throws Exception {
		assertInEqualStrict(new ContractScale( 100, Cash.USD ), new ContractScale( 100, Cash.EUR ));
		assertInEqualStrict(new ContractScale( 100, Cash.USD ), new ContractScale( 100.00000001, Cash.USD ));
		assertInEqualStrict(new ContractScale( 100, Cash.USD ), new ContractScale(100, new ContractGet(new LocalDate(2011,4,4), Cash.USD)));
		
		//Counter-intuitive? See the comment of the ContracScale's equalsTo() method. 
		assertInEqualStrict(
			new ContractScale(10000.0, new ContractScale( 100.0,  Cash.USD )), 
			new ContractScale(1000.0,  new ContractScale( 1000.0, Cash.USD )) );
	}

	@Test
	public void maturityTest() throws Exception {
		assertEquals(new ContractScale(100, Cash.USD).maturityDate(), Cash.USD.maturityDate());
		assertEquals(new LocalDate(2011,4,5), new ContractScale(100, new ContractGet(new LocalDate(2011,4,5), Cash.USD)).maturityDate());
	}
	
	@Test
	public void factorTest() throws Exception {
		assertEquals(100, new ContractScale(100, Cash.USD).scaleFactor(), 1.0e-16);
		assertEquals(10,  new ContractScale(10,  new Cashflow(10, new LocalDate(), Currency.USD)).scaleFactor(), 1.0e-16);
	}
	
	@Test
	public void currencyTest() throws Exception {
		assertEquals(Currency.USD, new ContractScale(100, Cash.USD).currency());
	}
	
	@Test
	public void nextEventDateTest() throws Exception {
		assertEquals(today, new ContractScale(100, new ContractGet(today, Cash.USD)).nextEventDate());
	}
	
	@Test
	public void unitContractTest() throws Exception {
		//Scale of fungible
		assertEquals( 
			Cash.USD, 
			new ContractScale(456, Cash.USD).unitContract() );
		assertEquals( 
			new Cashflow(1, today, Currency.USD), 
			new ContractScale(20.28, new Cashflow(0.1, today, Currency.USD)).unitContract() );

		//Scale of non-fungible
		assertEquals( 
			new ContractGet( today, Cash.USD ), 
			new ContractScale(456, new ContractGet( today, Cash.USD )).unitContract() );
	}
	
	@Test
	public void nextContractTest() throws Exception {
		assertEquals( 
			Contract.ZERO, 
			new ContractScale(100, Cash.USD).nextContract() );

		assertEquals( 
			Contract.ZERO, 
			new ContractScale(100, new ContractGet(new LocalDate(), Cash.USD)).nextContract() );
		assertEquals(
			Contract.ZERO, 
			new ContractGet(new LocalDate(), new ContractScale(100, Cash.USD)).nextContract() );
		assertEquals(
			Contract.ZERO, 
			new ContractScale(100, new ContractGet(new LocalDate(), new ContractScale(100, Cash.USD))).nextContract() );

		Contract cashflows2Yrs = new CashflowSet(Currency.USD, 100, 0.1, today.plusYears(1), Years.years(2));
		Contract cashflows3Yrs = new CashflowSet(Currency.USD, 100, 0.1, today, Years.years(3));
		assertEquals(
			new ContractScale( 10, cashflows2Yrs ),
			new ContractScale( 10, cashflows3Yrs ).nextContract() );
		assertEquals(
			new ContractScale( 10, cashflows3Yrs ),
			new ContractScale( 10, new ContractGet( today, cashflows3Yrs ) ).nextContract() );
		assertEquals(
			new ContractScale( 10, cashflows3Yrs ),
			new ContractGet( today, new ContractScale( 10, cashflows3Yrs ) ).nextContract() );
	}
	
	@Test
	public void nextSpunOffPositions() throws Exception {
		assertEquals( 
			PositionEffect.ZERO, 
			new ContractScale(100, Cash.USD).nextSpunOffPositions() );

		assertEquals( 
			new PositionEffect(Cash.USD, 100),
			new ContractScale(100, new ContractGet(new LocalDate(), Cash.USD)).nextSpunOffPositions() );
		assertEquals(
			new PositionEffect(Cash.USD, 100), 
			new ContractGet(new LocalDate(), new ContractScale(100, Cash.USD)).nextSpunOffPositions() );
		assertEquals(
			new PositionEffect(Cash.USD, 10000), 
			new ContractScale(100, new ContractGet(new LocalDate(), new ContractScale(100, Cash.USD))).nextSpunOffPositions() );

		//Underlying contract is non-fungible
		Contract cashflows3Yrs = new CashflowSet(Currency.USD, 100, 0.1, today, Years.years(3));
		assertEquals(
			PositionEffect.ZERO,
			new ContractScale( 10, new ContractGet( today, cashflows3Yrs ) ).nextSpunOffPositions() );
		assertEquals(
			PositionEffect.ZERO,
			new ContractGet( today, new ContractScale( 10, cashflows3Yrs ) ).nextSpunOffPositions() );
	}

//	@Test
//	public void nextEventTest() throws Exception {
//		LocalDate today = new LocalDate(2011, 4, 4);
//		Contract cashflows3Yrs     = new CashflowSet(Currency.USD, 100, 0.1, today, Years.years(3));
//		Contract cashflows2YrsUnit = new CashflowSet(Currency.USD, 1,   0.1, today.plusYears(1), Years.years(2));
//		
//		assertEquals(
//			new TradeEvent(
//				today.plusYears(1), 
//				new PositionEffect(cashflows3Yrs, -50, cashflows2YrsUnit, 100*50, Cash.USD, 500) 
//			), 
//			new ContractScale(50, cashflows3Yrs ).nextEvent() );
//	}
}
