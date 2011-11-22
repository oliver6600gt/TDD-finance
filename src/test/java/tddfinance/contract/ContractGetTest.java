package tddfinance.contract;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.trade.PositionEffect;


public class ContractGetTest {
	private static LocalDate today = new LocalDate( 2011, 4, 5 ); 

	@Test
	public void equalityTest() throws Exception {
		assertEqualsStrict( 
			new ContractGet( new LocalDate( 2011, 4, 4 ), Cash.USD ), 
			new ContractGet( new LocalDate( 2011, 4, 4 ), Cash.USD ) );
	}

	@Test
	public void inequalityTest() throws Exception {
		assertInEqualStrict( new ContractGet( today, Cash.USD ), new ContractGet( today, Cash.EUR ) );
		assertInEqualStrict( new ContractGet( today, Cash.USD ), new ContractGet( today.plus(Days.days(1)), Cash.USD ) );
	}

	@Test
	public void maturityTest() throws Exception {
		ContractGet  c  = new ContractGet( today, Cash.USD );
		assertEquals( c.maturityDate(), today );
	}

	@Test
	public void factorTest() throws Exception {
		assertEquals(100, new ContractGet(today, new ContractScale(100, Cash.USD)).scaleFactor(), 1.0e-16);
		assertEquals(1,   new ContractGet(today, new CashflowSet(Currency.USD, 100, 0.1, today.plusDays(1), Years.years(3))).scaleFactor(), 1.0e-16);
	}
	
	@Test
	public void currencyTest() throws Exception {
		assertEquals(Currency.USD, new ContractGet(new LocalDate(2004,4,4), Cash.USD).currency());
	}

	@Test
	public void nextContractTest() throws Exception {
		//underlying contract is fungible
		assertEquals( Contract.ZERO, new ContractGet( today, Cash.USD ).nextContract() );

		//underlying contract is non-fungible
		Contract c = new CashflowSet(Currency.USD, 100, 0.1, today, Years.years(2));
		assertEquals(c, new ContractGet(today, c).nextContract());
	}
	
	@Test
	public void nextSpunOffPositionsTest() throws Exception {
		//underlying congtract is fungible
		assertEquals(new PositionEffect(Cash.USD , 1), new ContractGet(today, Cash.USD).nextSpunOffPositions());
		
		//underlying congtract is non-fungible
		Contract c = new CashflowSet(Currency.USD, 100, 0.1, today, Years.years(2));
		assertEquals(PositionEffect.ZERO, new ContractGet(today, c).nextSpunOffPositions());
	}

	@Test
	public void nextEventTest() throws Exception {
		ContractGet  c  = new ContractGet( today, Cash.USD );

		TradeEvent nextEvent = c.nextEvent();
		assertEquals(today, c.nextEventDate());
		assertEquals(today, nextEvent.eventDate());

		//The next event is to 1) lose the contract ContractGet(Cash.USD) (-1), and 2) really get Cash.USD(+1)
		assertEquals( new PositionEffect(c, -1, Cash.USD, 1), nextEvent.positionEffect());
	}
}
