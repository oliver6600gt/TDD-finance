package tddfinance.curve;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;
import org.junit.Test;

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
}
