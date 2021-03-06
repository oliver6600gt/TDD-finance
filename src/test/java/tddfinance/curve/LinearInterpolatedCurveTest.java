package tddfinance.curve;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.junit.Test;

import static tddfinance.util.Assertion.*;

public class LinearInterpolatedCurveTest {

	static final LocalDate today       = new LocalDate(2009, 4, 1);
	static final Curve     sampleCurve = getSampleCurve(today);

	private static Curve getSampleCurve(LocalDate baseDate){
		Map<LocalDate, Double> curveValues = new TreeMap<LocalDate, Double>();
		curveValues.put(baseDate.plusYears(1), 0.03);
		curveValues.put(baseDate.plusYears(2), 0.035);
		curveValues.put(baseDate.plusYears(3), 0.0375);
		curveValues.put(baseDate.plusYears(4), 0.039);
		curveValues.put(baseDate.plusYears(5), 0.04);

		return new LinearInterpolatedCurve(baseDate, curveValues);
	}
	
	@Test
	public void getValueTest() throws Exception {
		assertEquals(0.030,   sampleCurve.getValue(new LocalDate(2010, 4,  1)), 1.0e-6);
		assertEquals(0.0325,  sampleCurve.getValue(new LocalDate(2010, 10, 1)), 1.0e-5);
		assertEquals(0.035,   sampleCurve.getValue(new LocalDate(2011, 4,  1)), 1.0e-6);
		assertEquals(0.03625, sampleCurve.getValue(new LocalDate(2011, 10, 1)), 1.0e-5);
		assertEquals(0.0375,  sampleCurve.getValue(new LocalDate(2012, 4,  1)), 1.0e-6);
		assertEquals(0.037625,sampleCurve.getValue(new LocalDate(2012, 5,  1)), 1.0e-5);
		assertEquals(0.039,   sampleCurve.getValue(new LocalDate(2013, 4,  1)), 1.0e-6);
		assertEquals(0.04,    sampleCurve.getValue(new LocalDate(2014, 4,  1)), 1.0e-6);
	}
	
	@Test
	public void getValueExceptionTest() throws Exception {
		try { sampleCurve.getValue( new LocalDate(2008, 4,  1) ); failUnexpectedToReachThis(); } 
		catch (Exception e) { assertExceptionMessage( e, "is outside of the value range of this curve" ); };
	}
	
	@Test
	public void horizontalShiftTest() throws Exception {
		Curve shiftedCurve = sampleCurve.horizontalShift(Years.years(2));
		assertEquals(0.030,   shiftedCurve.getValue(new LocalDate(2010 + 2, 4,  1)), 1.0e-6);
		assertEquals(0.0325,  shiftedCurve.getValue(new LocalDate(2010 + 2, 10, 1)), 1.0e-5);
		assertEquals(0.035,   shiftedCurve.getValue(new LocalDate(2011 + 2, 4,  1)), 1.0e-6);
		assertEquals(0.03625, shiftedCurve.getValue(new LocalDate(2011 + 2, 10, 1)), 1.0e-5);
		assertEquals(0.0375,  shiftedCurve.getValue(new LocalDate(2012 + 2, 4,  1)), 1.0e-6);
		assertEquals(0.037625,shiftedCurve.getValue(new LocalDate(2012 + 2, 5,  1)), 1.0e-5);
		assertEquals(0.039,   shiftedCurve.getValue(new LocalDate(2013 + 2, 4,  1)), 1.0e-6);
		assertEquals(0.04,    shiftedCurve.getValue(new LocalDate(2014 + 2, 4,  1)), 1.0e-6);
	}
	
	@Test
	public void horizontalShiftNegativeTest() throws Exception {
		Curve shiftedCurve = sampleCurve.horizontalShiftNegative(Years.years(2));
		assertEquals(0.030,   shiftedCurve.getValue(new LocalDate(2010 - 2, 4,  1)), 1.0e-6);
		assertEquals(0.0325,  shiftedCurve.getValue(new LocalDate(2010 - 2, 10, 1)), 1.0e-5);
		assertEquals(0.035,   shiftedCurve.getValue(new LocalDate(2011 - 2, 4,  1)), 1.0e-6);
		assertEquals(0.03625, shiftedCurve.getValue(new LocalDate(2011 - 2, 10, 1)), 1.0e-5);
		assertEquals(0.0375,  shiftedCurve.getValue(new LocalDate(2012 - 2, 4,  1)), 1.0e-6);
		assertEquals(0.037625,shiftedCurve.getValue(new LocalDate(2012 - 2, 5,  1)), 1.0e-5);
		assertEquals(0.039,   shiftedCurve.getValue(new LocalDate(2013 - 2, 4,  1)), 1.0e-6);
		assertEquals(0.04,    shiftedCurve.getValue(new LocalDate(2014 - 2, 4,  1)), 1.0e-6);
	}
	
	@Test
	public void forwartdRate() throws Exception {
		try { sampleCurve.forwardValue(Months.months(6), Months.months(6), 2); failUnexpectedToReachThis(); } 
		catch (Exception e) { assertExceptionMessage( e, "not implemented" ); };
	}
	
}
