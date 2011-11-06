package tddfinance.curve;

import org.joda.time.LocalDate;
import org.joda.time.ReadablePeriod;

public class FlatCurve implements Curve {
	private final LocalDate baseDate;
	private final double    value;
	
	public FlatCurve(LocalDate baseDate, double value) {
		this.baseDate = baseDate;
		this.value    = value;
	}

	public LocalDate baseDate() {
		return baseDate;
	}
	
	public double getValue(LocalDate date) throws Exception {
		return this.value;
	}
	
	public double forwardValue(ReadablePeriod forwardStartDate, ReadablePeriod forwardPeriod, int paymentFrequency) throws Exception {
		return this.value;
	}
	
	public Curve parralelShift(double offset) throws Exception {
		return new FlatCurve(baseDate(), this.value + offset);
	}
	
	public Curve horizontalShift(ReadablePeriod horizontalOffset) throws Exception {
		return new FlatCurve(baseDate().plus(horizontalOffset), this.value);
	}

	public Curve horizontalShiftNegative(ReadablePeriod horizontalOffset) throws Exception {
		return new FlatCurve(baseDate().minus(horizontalOffset), this.value);
	}

	public String toString() {
		Double valueInDouble = value;
		return new String( "FlatCurve with the following value: " + valueInDouble.toString() + "\n" ); 
	}
}
