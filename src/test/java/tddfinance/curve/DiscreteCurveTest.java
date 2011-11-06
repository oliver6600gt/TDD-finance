package tddfinance.curve;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.assertExceptionMessage;
import static tddfinance.util.Assertion.failUnexpectedToReachThis;

import java.util.Map;
import java.util.TreeMap;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.junit.Test;

import tddfinance.curve.Curve;
import tddfinance.curve.DiscreteCurve;

public class DiscreteCurveTest {
	
	static final LocalDate today       = new LocalDate(2009, 4, 1);
	static final Curve     sampleCurve = getSampleCurve(today);

	private static Curve getSampleCurve(LocalDate baseDate){
		Map<LocalDate, Double> curveValues = new TreeMap<LocalDate, Double>();
		curveValues.put(baseDate.plusYears(1), 0.04);
		curveValues.put(baseDate.plusYears(2), 0.045);
		curveValues.put(baseDate.plusYears(3), 0.0475);
		curveValues.put(baseDate.plusYears(4), 0.049);
		curveValues.put(baseDate.plusYears(5), 0.05);

		return new DiscreteCurve(baseDate, curveValues);
	}
	
	@Test
	public void getValueTest() throws Exception {
		assertEquals(0.040,  sampleCurve.getValue(new LocalDate(2010, 4, 1)), 1.0e-6);
		assertEquals(0.045,  sampleCurve.getValue(new LocalDate(2011, 4, 1)), 1.0e-6);
		assertEquals(0.0475, sampleCurve.getValue(new LocalDate(2012, 4, 1)), 1.0e-6);
		assertEquals(0.049,  sampleCurve.getValue(new LocalDate(2013, 4, 1)), 1.0e-6);
		assertEquals(0.05,   sampleCurve.getValue(new LocalDate(2014, 4, 1)), 1.0e-6);
	}

	@Test
	public void getValueInvalidTest() throws Exception {
		LocalDate Apr1st2015 = new LocalDate(2015, 4, 1);
		try { sampleCurve.getValue(Apr1st2015 ); failUnexpectedToReachThis(); } 
		catch (Exception e) { assertExceptionMessage( e, Apr1st2015.toString() + " is not a valid date for this curve" ); };
	}
	
	@Test
	public void horizontalShiftTest() throws Exception {
		Curve shiftedCurve = sampleCurve.horizontalShift(Years.years(2));
		assertEquals(0.040,  shiftedCurve.getValue(new LocalDate(2010 + 2, 4, 1)), 1.0e-6);
		assertEquals(0.045,  shiftedCurve.getValue(new LocalDate(2011 + 2, 4, 1)), 1.0e-6);
		assertEquals(0.0475, shiftedCurve.getValue(new LocalDate(2012 + 2, 4, 1)), 1.0e-6);
		assertEquals(0.049,  shiftedCurve.getValue(new LocalDate(2013 + 2, 4, 1)), 1.0e-6);
		assertEquals(0.05,   shiftedCurve.getValue(new LocalDate(2014 + 2, 4, 1)), 1.0e-6);
	}
	
	@Test
	public void horizontalShiftNegativeTest() throws Exception {
		Curve shiftedCurve = sampleCurve.horizontalShiftNegative(Years.years(2));
		assertEquals(0.040,  shiftedCurve.getValue(new LocalDate(2010 - 2, 4, 1)), 1.0e-6);
		assertEquals(0.045,  shiftedCurve.getValue(new LocalDate(2011 - 2, 4, 1)), 1.0e-6);
		assertEquals(0.0475, shiftedCurve.getValue(new LocalDate(2012 - 2, 4, 1)), 1.0e-6);
		assertEquals(0.049,  shiftedCurve.getValue(new LocalDate(2013 - 2, 4, 1)), 1.0e-6);
		assertEquals(0.05,   shiftedCurve.getValue(new LocalDate(2014 - 2, 4, 1)), 1.0e-6);
	}
	
	@Test
	public void forwartdRate() throws Exception {
		LocalDate today = new LocalDate(2010, 4, 1);
		
		Map<LocalDate, Double> curveValues = new TreeMap<LocalDate, Double>();
		curveValues.put(today.plusMonths(6),  0.05);   //6-month treasury strip rate
		curveValues.put(today.plusYears(1),   0.0525); //1-year treasury strip rate
		curveValues.put(today.plusMonths(18), 0.0575); //18-month treasury strip rate
			
		Curve zeroCouponYieldCurve = new DiscreteCurve(today, curveValues);

		assertEquals( 0.055003,	zeroCouponYieldCurve.forwardValue(Months.months(6), Months.months(6), 2), 1.0e-6);
		assertEquals( 0.061260,	zeroCouponYieldCurve.forwardValue(Months.months(6), Years.years(1), 2),   1.0e-6);
	}

	@Test
	public void parYieldCurve() throws Exception {
		LocalDate today = new LocalDate(2010, 4, 1);
		
		Map<LocalDate, Double> zeroCouponRates = new TreeMap<LocalDate, Double>();
		zeroCouponRates.put(today.plusYears(1),  0.07);
		zeroCouponRates.put(today.plusYears(2),  0.068);
		zeroCouponRates.put(today.plusYears(3),  0.0662);
		zeroCouponRates.put(today.plusYears(4),  0.0646);
		zeroCouponRates.put(today.plusYears(5),  0.0633);
		zeroCouponRates.put(today.plusYears(6),  0.0625);
		zeroCouponRates.put(today.plusYears(7),  0.062);
		zeroCouponRates.put(today.plusYears(8),  0.0616);
		zeroCouponRates.put(today.plusYears(9),  0.06125);
		zeroCouponRates.put(today.plusYears(10), 0.061);
			
		DiscreteCurve zeroCouponYieldCurve = new DiscreteCurve(today, zeroCouponRates);

		assertEquals(0.07,    zeroCouponYieldCurve.parYield(Years.years(1)),  1.0e-4);
		assertEquals(0.06807, zeroCouponYieldCurve.parYield(Years.years(2)),  1.0e-4);
		assertEquals(0.06636, zeroCouponYieldCurve.parYield(Years.years(3)),  1.0e-4);
		assertEquals(0.06487, zeroCouponYieldCurve.parYield(Years.years(4)),  1.0e-4);
		assertEquals(0.06367, zeroCouponYieldCurve.parYield(Years.years(5)),  1.0e-4);
		assertEquals(0.06293, zeroCouponYieldCurve.parYield(Years.years(6)),  1.0e-4);
		assertEquals(0.06246, zeroCouponYieldCurve.parYield(Years.years(7)),  1.0e-4);
		assertEquals(0.06209, zeroCouponYieldCurve.parYield(Years.years(8)),  1.0e-4);
		assertEquals(0.06177, zeroCouponYieldCurve.parYield(Years.years(9)),  1.0e-4);
		assertEquals(0.06154, zeroCouponYieldCurve.parYield(Years.years(10)), 1.0e-4);
	}
	
	@Test
	public void forwartdRate2() throws Exception {
		LocalDate today = new LocalDate(2010, 4, 1);
		
		Map<LocalDate, Double> zeroCouponRates = new TreeMap<LocalDate, Double>();
		zeroCouponRates.put(today.plusYears(1),  0.07);
		zeroCouponRates.put(today.plusYears(2),  0.068);
		zeroCouponRates.put(today.plusYears(3),  0.0662);
		zeroCouponRates.put(today.plusYears(4),  0.0646);
		zeroCouponRates.put(today.plusYears(5),  0.0633);
		zeroCouponRates.put(today.plusYears(6),  0.0625);
		zeroCouponRates.put(today.plusYears(7),  0.062);
		zeroCouponRates.put(today.plusYears(8),  0.0616);
		zeroCouponRates.put(today.plusYears(9),  0.06125);
		zeroCouponRates.put(today.plusYears(10), 0.061);
			
		DiscreteCurve zeroCouponYieldCurve = new DiscreteCurve(today, zeroCouponRates);

		assertEquals(0.066,   zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(1), 1), 1.0e-4 );
		assertEquals(0.06431, zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(2), 1), 1.0e-4 );
		assertEquals(0.06281, zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(3), 1), 1.0e-4 );
		assertEquals(0.06163, zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(4), 1), 1.0e-4 );
		assertEquals(0.06101, zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(5), 1), 1.0e-4 );
		assertEquals(0.06067, zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(6), 1), 1.0e-4 );
		assertEquals(0.06041, zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(7), 1), 1.0e-4 );
		assertEquals(0.06016, zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(8), 1), 1.0e-4 );
		assertEquals(0.06,    zeroCouponYieldCurve.forwardValue(Years.years(1), Years.years(9), 1), 1.0e-4 );
	}
}
