package tddfinance.trade;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.junit.Test;
import tddfinance.contract.Cash;
import tddfinance.contract.Cashflow;
import tddfinance.contract.Contract;
import tddfinance.contract.ContractScale;
import tddfinance.contract.ContractZero;
import tddfinance.contract.Currency;

public class PositionEffectTest {
	
	@Test
	public void constructorTest() throws Exception {
		assertConstructorException(
			"Invalid parameters",
			PositionEffect.class,
			new Object[]{ new Cash[]{Cash.USD}, new double[]{1, 2}}, //Invalid: one contract, two quantities
			new Class[]{ Contract[].class, double[].class } 
		);

		assertConstructorException(
			"Invalid parameters",
			PositionEffect.class.getConstructor( new Class[]{ Contract[].class, double[].class } ),
			new Object[]{ new Cash[]{Cash.USD, Cash.EUR}, new double[]{1} } //Invalid: two contract, one quantities
		);
	}

	@Test
	public void equalityTest() throws Exception {
		assertEquals(
			new PositionEffect( new Cash[]{Cash.USD, Cash.EUR}, new double[]{100, 30} ),
			new PositionEffect( new Cash[]{Cash.USD, Cash.EUR}, new double[]{100, 30} )
		);

		assertEquals(	
			new PositionEffect( new Cash[]{Cash.EUR, Cash.USD}, new double[]{100, 100} ),
			new PositionEffect( new Cash[]{Cash.USD, Cash.EUR}, new double[]{100, 100} )
		);

		assertEquals(	
			new PositionEffect( new Cash[]{Cash.EUR}, new double[]{200} ),
			new PositionEffect( new Cash[]{Cash.EUR, Cash.EUR}, new double[]{100, 100} )
		);
		
		assertEquals(	
			new PositionEffect( new Cash[]{Cash.EUR, Cash.USD, Cash.JPY}, new double[]{200, 100, 300} ),
			new PositionEffect( Cash.EUR, 200, Cash.USD, 100, Cash.JPY, 300 )
		);

		assertEquals(	
			new PositionEffect( Cash.USD, 100, Cash.EUR, 200, Cash.JPY, 300 ),
			new PositionEffect( Cash.EUR, 200, Cash.USD, 100, Cash.JPY, 300 )
		);
	}

	@Test
	public void inequalityTest() throws Exception {
		assertInEqual(	
			new PositionEffect( new Cash[]{Cash.EUR}, new double[]{100} ),
			new PositionEffect( new Cash[]{Cash.USD}, new double[]{100} ) );

		assertInEqual(	
			new PositionEffect( new Cash[]{Cash.EUR}, new double[]{100}   ),
			new PositionEffect( new Cash[]{Cash.EUR}, new double[]{100.1} ) );

		assertInEqual( 
			new PositionEffect(Cash.EUR, 100), 
			new PositionEffect(Cash.EUR, 100.1) );
		
		assertInEqual( 
			new PositionEffect(Cash.EUR, 100, Cash.USD, 100), 
			new PositionEffect(Cash.EUR, 100, Cash.USD, 100.001) );
	}
	
	@Test
	public void addTest() throws Exception {
		assertEquals(
			new PositionEffect(Cash.USD, 200, Cash.EUR, 100),
			new PositionEffect(Cash.EUR, 100).add(new PositionEffect(Cash.USD, 200)) );
		
		assertEquals(
			new PositionEffect(Cash.EUR, 100, Cash.USD, 300, Cash.JPY, 100),
			new PositionEffect(Cash.USD, 100, Cash.EUR, 100).add(new PositionEffect(Cash.USD, 200, Cash.JPY, 100)) );
	}

	@Test
	public void scaleTest() throws Exception {
		assertEquals(
			new PositionEffect( Cash.USD, 20000, Cash.EUR, 10000 ),
			new PositionEffect( Cash.USD, 200,   Cash.EUR, 100 ).scale(100) );
	}
	
	@Test
	public void fungibleTest() throws Exception {
		assertEquals(
			new PositionEffect( Cash.EUR, 100 ),
			new PositionEffect( new ContractScale(100, Cash.EUR), 1 ));

		LocalDate today = new LocalDate(2011, 4, 4);
		assertEquals(
			new PositionEffect( new Cashflow(1, today, Currency.USD ),   100 ),
			new PositionEffect( new Cashflow(100, today, Currency.USD ), 1 ) );
	}
	
	@Test
	public void replacementTest() throws Exception {
		LocalDate today    = new LocalDate(2011, 4, 4);
		double    quantity = 100;
		
		assertEquals(
			new PositionEffect(
				new Cashflow(quantity, today.plus(Years.years(1)), Currency.USD), 1,
				new Cashflow(quantity, today.plus(Years.years(2)), Currency.USD), 1,
				new Cashflow(quantity, today.plus(Years.years(3)), Currency.USD), 1
			),
			new PositionEffect(
				new Cashflow(quantity, today.plus(Years.years(1)), Currency.USD), 1,
				new Cashflow(quantity, today.plus(Years.years(2)), Currency.USD), 1,
				new Cashflow(quantity, today.plus(Years.years(3)), Currency.USD), 1
			)
		);		
	}
	
	@Test
	public void contractZeroTest() throws Exception {
		assertEquals( PositionEffect.ZERO, new PositionEffect( new ContractZero(), 9800 ) );
		assertEquals(
			new PositionEffect( Cash.USD, 120 ),
			new PositionEffect( Cash.USD, 100, new ContractZero(), 95, Cash.USD, 20 ));

	}
}
