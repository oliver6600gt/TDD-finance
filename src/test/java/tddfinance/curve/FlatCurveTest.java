package tddfinance.curve;

import static org.junit.Assert.*;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.junit.Test;

public class FlatCurveTest {
	static final LocalDate today = new LocalDate(2009, 4, 1);

	@Test
	public void getValueTest() throws Exception {
		Curve curve = new FlatCurve(today, 0.042);
		assertEquals(0.042, curve.getValue(new LocalDate(2010, 4, 1)), 1.0e-6);
		assertEquals(0.042, curve.getValue(new LocalDate(2011, 4, 1)), 1.0e-6);
		assertEquals(0.042, curve.getValue(new LocalDate(2012, 4, 1)), 1.0e-6);	
	}
	
	@Test
	public void horizontalShiftTest() throws Exception {
		Curve curve        = new FlatCurve(today, 0.042);
		Curve shiftedCurve = curve.horizontalShift(Years.years(6));
		assertEquals(0.042, shiftedCurve.getValue(new LocalDate(2010 + 6, 4, 1)), 1.0e-6);
		assertEquals(0.042, shiftedCurve.getValue(new LocalDate(2011 + 6, 4, 1)), 1.0e-6);
		assertEquals(0.042, shiftedCurve.getValue(new LocalDate(2012 + 6, 4, 1)), 1.0e-6);	
	}
	
	@Test
	public void horizontalShiftNegativeTest() throws Exception {
		Curve curve        = new FlatCurve(today, 0.042);
		Curve shiftedCurve = curve.horizontalShiftNegative(Years.years(6));
		assertEquals(0.042, shiftedCurve.getValue(new LocalDate(2010 - 6, 4, 1)), 1.0e-6);
		assertEquals(0.042, shiftedCurve.getValue(new LocalDate(2011 - 6, 4, 1)), 1.0e-6);
		assertEquals(0.042, shiftedCurve.getValue(new LocalDate(2012 - 6, 4, 1)), 1.0e-6);	
	}

	@Test
	public void forwardCurveTest() throws Exception {
		Curve curve = new FlatCurve(today, 0.042);
		assertEquals(0.042, curve.forwardValue(Years.years(1), Months.months(6), 1), 1.0e-6);
		assertEquals(0.042, curve.forwardValue(Years.years(1), Months.months(6), 2), 1.0e-6);
		assertEquals(0.042, curve.forwardValue(Months.months(6), Years.years(1), 4), 1.0e-6);
	}
}
