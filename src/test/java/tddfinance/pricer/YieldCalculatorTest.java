package tddfinance.pricer;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.Months;
import org.junit.Test;

import tddfinance.contract.Bond;
import tddfinance.contract.Currency;
import tddfinance.curve.Curve;
import tddfinance.curve.DiscreteCurve;
import tddfinance.curve.FlatCurve;

public class YieldCalculatorTest {

	@Test
	public void futureValueTest() throws Exception {
		assertEquals( 105.0,    YieldCalculator.futureValue( 100, 0.05, Years.years(1) ), 1.0e-6 );	
		assertEquals( 110.25,   YieldCalculator.futureValue( 100, 0.05, Years.years(2) ), 1.0e-6 );	
		assertEquals( 105.0,    YieldCalculator.futureValue( 100, 0.05, Months.months(12) ), 1.0e-6 );	
		assertEquals( 105.0625, YieldCalculator.futureValue( 100, 0.05, Months.months(12), 2 ), 1.0e-6 );	
	}
	
	@Test
	public void annualRateOfReturnTest() throws Exception {
		LocalDate baseDate = new LocalDate(2001, 4, 1);
		Map<LocalDate, Double> curveValues = new TreeMap<LocalDate, Double>();
		curveValues.put(baseDate.plusYears(1), 0.038);
		curveValues.put(baseDate.plusYears(2), 0.041);
		curveValues.put(baseDate.plusYears(3), 0.0435);
		curveValues.put(baseDate.plusYears(4), 0.0481);
		curveValues.put(baseDate.plusYears(5), 0.0502);
				
		//zero-coupon -> reinvestment rate assuming you will get all the return from "re-"investment at maturity
		Curve zeroCouponRates = new DiscreteCurve( baseDate, curveValues ); 
		
		Bond  bond = new Bond(Currency.USD, 100, 0.04, baseDate, Years.years(5) );
		
		assertEquals(0.0493207, YieldCalculator.annualRateOfReturn(bond, zeroCouponRates, Years.years(5), Years.years(1)), 1.0e-6);	
	}

	@Test
	public void annualRateOfReturnYTMTest() throws Exception {
		LocalDate baseDate = new LocalDate(2001, 4, 1);
				
		//zero-coupon -> reinvestment rate assuming you will get all the return from "re-"investment at maturity
		Curve zeroCouponRates = new FlatCurve( baseDate, 0.02 ); 
		
		Bond  bond = new Bond(Currency.USD, 100, 0.04, baseDate, Years.years(5) );
		
		//If you re-invest intermidiate cashflows @YTM, YTM = annual rate of return 
		assertEquals(0.02, YieldCalculator.annualRateOfReturn(bond, zeroCouponRates, Years.years(5), Years.years(1)), 1.0e-6);	
	}
	
	@Test
	public void zeroCouponRateTest() throws Exception {
		assertEquals(0.014075, YieldCalculator.zeroCouponRate(98.612, Years.years(1)), 1.0e-6);
		assertEquals(0.030747, YieldCalculator.zeroCouponRate(94.123, Years.years(2)), 1.0e-6);
		assertEquals(0.035932, YieldCalculator.zeroCouponRate(89.951, Years.years(3)), 1.0e-6);
	}
}
