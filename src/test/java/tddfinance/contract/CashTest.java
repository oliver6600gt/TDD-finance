package tddfinance.contract;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import org.junit.Test;

import tddfinance.trade.PositionEffect;


public class CashTest {
	@Test
	public void invalidConstructorTest() throws Exception {
		assertConstructorException(	"NHK is not a valid Currency", Cash.class, new String[]{ "NHK" } );
	}
	
	@Test
	public void equalityTest() throws Exception {
		assertEqualsStrict( new Cash( Currency.USD ), new Cash( Currency.USD ) );
		assertEqualsStrict( new Cash( Currency.USD ), Cash.USD );
		assertEqualsStrict( new Cash( Currency.USD ), new Cash( "USD" ) );
	}
	
	@Test
	public void inequalityTest() throws Exception {
		assertInEqualStrict( new Cash( "USD" ), new Cash( "EUR" ) );
	}

	@Test
	public void toStringTest() throws Exception {
		assertEquals("USD", Cash.USD.toString());
		assertEquals("EUR", Cash.EUR.toString());		
	}

	@Test
	public void maturityTest() throws Exception {
		assertEquals(Contract.InfiniteMaturity, Cash.USD.maturityDate());
	}
	
	@Test
	public void factorTest() throws Exception {
		assertEquals(1.0, Cash.USD.scaleFactor(), 1.0e-16);
	}

	@Test
	public void currencyTest() throws Exception {
		assertEquals(Currency.USD, Cash.USD.currency());		
	}
	
	@Test
	public void nextEventDateTest() throws Exception {
		assertEquals(Contract.InfiniteMaturity, Cash.USD.nextEventDate());				
	}
	
	@Test
	public void nextContract() throws Exception {
		assertEquals(Contract.ZERO, Cash.USD.nextContract());
	}

	@Test
	public void nextSpunOffPositions() throws Exception {
		assertEquals(PositionEffect.ZERO, Cash.USD.nextSpunOffPositions());	
	}

	@Test
	public void nextEventTest() throws Exception {
		assertEquals(TradeEvent.NOEVENT, Cash.USD.nextEvent());	
	}	
}
