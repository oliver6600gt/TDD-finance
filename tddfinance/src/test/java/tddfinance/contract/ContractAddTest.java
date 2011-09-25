package tddfinance.contract;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.trade.PositionEffect;


public class ContractAddTest {
	static final LocalDate today    = new LocalDate(2011,4,4);
	static final LocalDate tomorrow = new LocalDate(2011,4,5);

	@Test
	public void equalityTest() throws Exception {
		assertEqualsStrict(
			new ContractAdd(Cash.USD, Cash.EUR), 
			new ContractAdd(new Cash("USD"), new Cash("EUR")));
		assertEqualsStrict(
			new ContractAdd(Cash.USD, Cash.EUR, Cash.JPY),
			new ContractAdd(Cash.USD, new ContractAdd( Cash.EUR, Cash.JPY )));
		
		//associative law?
		//commutative law?
	}

	@Test
	public void InequalityTest() throws Exception {
		assertInEqualStrict(new ContractAdd(Cash.USD, Cash.EUR), new ContractAdd(Cash.EUR, Cash.USD));//Currently the order is important
		assertInEqualStrict(new ContractAdd(Cash.USD, Cash.EUR), new ContractAdd(Cash.USD, Cash.EUR, Cash.JPY));
	}

	@Test
	public void maturityTest() throws Exception {
		Contract c = new ContractAdd(new ContractGet(today,Cash.USD), new ContractGet(tomorrow,Cash.EUR));
		assertEquals(tomorrow, c.maturityDate());
	}
	
	@Test
	public void factorTest() throws Exception {
		assertEquals(1.0, new ContractAdd(new ContractScale(5, Cash.USD), new ContractScale(5,Cash.USD)).scaleFactor(), 1.0e-16);
	}
	
	@Test
	public void currencyTest() throws Exception {
		assertEquals(null, new ContractAdd(Cash.USD, Cash.USD).currency());		
	}
	
	@Test
	public void nextEventDateTest() throws Exception {
		Contract c = new ContractAdd( new ContractAdd(Cash.USD, new ContractGet(today, Cash.EUR)), new ContractGet(tomorrow, Cash.JPY) );
		assertEquals(today, c.nextEventDate());
	}
	
	@Test
	public void getListTest() throws Exception {
		LocalDate today = new LocalDate(2011, 4, 4);

		List<Contract> contracts = new ArrayList<Contract>();
		contracts.add( new Cashflow(today.plus(Years.years(1)), 100, Cash.USD) );
		contracts.add( new Cashflow(today.plus(Years.years(2)), 100, Cash.USD) );
		contracts.add( Cash.USD );
		contracts.add( Cash.EUR );

		ContractAdd cashflows = new ContractAdd( contracts.get(0), contracts.get(1), contracts.get(2), contracts.get(3) ); 
		
		assertEquals( cashflows.getList(), contracts );
	}

	@Test
	public void nextContractTest() throws Exception {

		ContractAdd oneCashflow    = new ContractAdd(
			new Cashflow(today.plus(Years.years(2)), 100, Cash.USD)
		);
		ContractAdd twoCashflows = new ContractAdd(
			new Cashflow(today.plus(Years.years(1)), 100, Cash.USD),
			oneCashflow
		);
		ContractAdd threeCashflows  = new ContractAdd(
			new Cashflow(today, 100, Cash.USD),
			twoCashflows
		);
		
		assertEquals( twoCashflows,  threeCashflows.nextContract() );
		assertEquals( oneCashflow,   twoCashflows.nextContract() );
		assertEquals( Contract.ZERO, oneCashflow.nextContract() );
	}

	@Test
	public void nextSpunOffPositionsTest() throws Exception {
		/**
		 * Case 1
		 */
		Contract threeCashflows = new ContractAdd(
			new Cashflow(today,                      100, Cash.USD),
			new Cashflow(today.plus(Years.years(1)), 50, Cash.USD),
			new Cashflow(today.plus(Years.years(2)), 60, Cash.USD)
		);
		assertEquals( new PositionEffect(Cash.USD, 100), threeCashflows.nextSpunOffPositions() );
		assertEquals( new PositionEffect(Cash.USD, 50),  threeCashflows.nextContract().nextSpunOffPositions() );
		assertEquals( new PositionEffect(Cash.USD, 60),  threeCashflows.nextContract().nextContract().nextSpunOffPositions() );
		assertEquals( PositionEffect.ZERO,               threeCashflows.nextContract().nextContract().nextContract().nextSpunOffPositions() );
	
		/**
		 * Case 2
		 */
		Contract cfUSD1Yr2Yr3Yr = new CashflowSet(Cash.USD, 100, 0.1, today, Years.years(3)); 
		Contract cfEUR10Yr12Yr  = new CashflowSet(Cash.EUR, 100, 0.2, today.plusYears(10), Years.years(2));
		Contract twoCashflowSets = new ContractAdd(
			cfUSD1Yr2Yr3Yr,
			new ContractGet(tomorrow, cfEUR10Yr12Yr) 
		);
		//from ContractGet
		assertEquals( PositionEffect.ZERO, twoCashflowSets.nextSpunOffPositions() );
		//from cfUSD1Yr2Yr3Yr
		Contract nextContract = twoCashflowSets.nextContract();
		assertEquals( new PositionEffect(Cash.USD, 10), nextContract.nextSpunOffPositions() );
		nextContract = nextContract.nextContract();
		assertEquals( new PositionEffect(Cash.USD, 10),	nextContract.nextSpunOffPositions() );
		nextContract = nextContract.nextContract();
		assertEquals( new PositionEffect(Cash.USD, 10),	nextContract.nextSpunOffPositions() );
		nextContract = nextContract.nextContract();
		assertEquals( new PositionEffect(Cash.EUR, 20),	nextContract.nextSpunOffPositions() );
		nextContract = nextContract.nextContract();
		assertEquals( new PositionEffect(Cash.EUR, 20),	nextContract.nextSpunOffPositions() );
	}

	@Test
	public void nextEventTest() throws Exception {
		LocalDate   today          = new LocalDate(2011,4,4);
		ContractAdd threeCashflows = new ContractAdd(
			new Cashflow(today,                      100, Cash.USD),
			new Cashflow(today.plus(Years.years(1)), 100, Cash.USD),
			new Cashflow(today.plus(Years.years(2)), 100, Cash.USD)
		);
		ContractAdd twoCashflows  = new ContractAdd(
			new Cashflow(today.plus(Years.years(1)), 100, Cash.USD),
			new Cashflow(today.plus(Years.years(2)), 100, Cash.USD)
		);

		//The next event is to 1) lose the contract ContractGet(Cash.USD) (-1), and 2) really get Cash.USD(+1)
		assertEquals(
			new PositionEffect(threeCashflows, -1, twoCashflows, 1, Cash.USD, 100 ), 
			threeCashflows.nextEvent().positionEffect());

		ContractAdd oneCashflow = new ContractAdd(
			new Cashflow(today.plus(Years.years(2)), 100, Cash.USD)
		);

		assertEquals(
			new PositionEffect(oneCashflow, -1, Cash.USD, 100 ), 
			oneCashflow.nextEvent().positionEffect());

		assertEquals(
			PositionEffect.ZERO, 
			new ContractAdd( Cash.USD ).nextEvent().positionEffect());
	}
}
