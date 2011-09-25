package tddfinance.curve;

import org.joda.time.LocalDate;
import org.joda.time.ReadablePeriod;

public interface Curve {
	/**
	 * @param date
	 * @return return the value of the curve @date
	 * @throws Exception
	 */
	public double getValue(LocalDate date) throws Exception;

	public LocalDate baseDate();
	
	/**
	 * @param forwardStartDate
	 * @param forwardPeriodLength
	 * @param PaymentFrequency
 	 * @return return the value of the forward curve derived from this curve
	 * @throws Exception
	 */
	public double forwardValue(ReadablePeriod forwardStartDate, ReadablePeriod forwardPeriodLength, int PaymentFrequency) throws Exception;

	/**
	 * Create a new curve offset by a value, "offset" parameter
	 * @param  offset - the new curve will be offset by this value
	 * @return a new Curve offset by the given value
	 * @throws Exception
	 */
	public Curve parralelShift(double offset) throws Exception;
	
	/**
	 * Create a new curve horizontally-offset by a value, "horizontalOffset" parameter
	 * @param  horizontalOffset - value to offset the curve in a horizontal direction
	 * @return a new Curve horizontally offset by the given value
	 * @throws Exception
	 */
	public Curve horizontalShift(ReadablePeriod horizontalOffset) throws Exception;

}
