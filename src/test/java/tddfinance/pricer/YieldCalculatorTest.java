package tddfinance.pricer;

import static org.junit.Assert.*;

//import java.util.Map;
//import java.util.TreeMap;
//
//import org.joda.time.LocalDate;
//import org.joda.time.Years;
//import org.junit.Test;
//
//import tddfinance.contract.Bond;
//import tddfinance.contract.Currency;
//import tddfinance.curve.Curve;
//import tddfinance.curve.DiscreteCurve;

public class YieldCalculatorTest {

	@Test
	public void futureValueTest() throws Exception {
		assertEquals( 105.0,  YieldCalculator.futureValue( 100, 0.05, 1 ), 1.0e-6 );	
		assertEquals( 110.25, YieldCalculator.futureValue( 100, 0.05, 2 ), 1.0e-6 );	
	}
	
//	@Test
//	public void annualRateOfReturnTest() throws Exception {
//		LocalDate baseDate = new LocalDate(2001, 4, 1);
//		Map<LocalDate, Double> curveValues = new TreeMap<LocalDate, Double>();
//		curveValues.put(baseDate.plusYears(1), 0.04);
//		curveValues.put(baseDate.plusYears(2), 0.045);
//		curveValues.put(baseDate.plusYears(3), 0.0475);
//		curveValues.put(baseDate.plusYears(4), 0.049);
//		curveValues.put(baseDate.plusYears(5), 0.05);
//				
//		//zero-coupon -> reinvestment rate assuming you will get all the return from "re-"investment at maturity
//		Curve zeroCouponRates = new DiscreteCurve( baseDate, curveValues ); 
//		
//		Bond  bond = new Bond(Currency.USD, 100, 5.0, baseDate, Years.years(5) );
//		
//		assertEquals(0.049686, YieldCaululator.annualRateOfReturn(bond, zeroCouponRates), 1.0e-6);	
//	}
}
