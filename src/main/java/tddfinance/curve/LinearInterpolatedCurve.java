package tddfinance.curve;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePeriod;

public class LinearInterpolatedCurve implements Curve {
	private LocalDate              baseDate;
	private Map<LocalDate, Double> curveValues;
	
	private LinearInterpolatedCurve(LocalDate baseDate) {
		this.baseDate    = baseDate;
		this.curveValues = new TreeMap<LocalDate, Double>();
	}
	
	public LinearInterpolatedCurve(LocalDate baseDate, Map<LocalDate, Double> curveValues) {
		this.baseDate    = baseDate;
		this.curveValues = new TreeMap<LocalDate, Double>();
		this.curveValues.putAll(curveValues);
	}
	
	public double getValue(LocalDate date) throws Exception {
		LocalDate lowerBound = null;
		LocalDate upperBound = null;
		
		for (Map.Entry<LocalDate, Double> entry : this.curveValues.entrySet()) {
			LocalDate dataPointDate = entry.getKey();
			
			if( date.equals(dataPointDate) )
				return entry.getValue();
			else if( date.isAfter(dataPointDate) ) 
				lowerBound      = dataPointDate;
			else if( upperBound == null && date.isBefore(dataPointDate) ) //checking upperBound == null, not to pick up too big upperBound 
				upperBound      = dataPointDate;
		}
		
		if( lowerBound == null || upperBound == null )
		{
			Object[] valueArray = this.curveValues.keySet().toArray();
			throw new Exception( date.toString() + " is outside of the value range of this curve, from " + valueArray[0].toString() + " to " + valueArray[ valueArray.length - 1 ].toString() );
		}
		
		double lowerBoundValue = this.curveValues.get(lowerBound);
		double upperBoundValue = this.curveValues.get(upperBound);
		double periodLength          = Days.daysBetween(lowerBound, upperBound).getDays();
		double lowerBoundValueWeight = periodLength - Days.daysBetween(lowerBound, date).getDays(); //the closer to the bound, the more the weight
		double upperBoundValueWeight = periodLength - Days.daysBetween(date, upperBound).getDays(); //the closer to the bound, the more the weight

		return ( lowerBoundValue * lowerBoundValueWeight + upperBoundValue * upperBoundValueWeight ) / periodLength;
	}

	public LocalDate baseDate() {
		return this.baseDate;
	}

	public double forwardValue(ReadablePeriod forwardStartsIn, ReadablePeriod forwardPeriodLength, int paymentFrequency) throws Exception {
		throw new Exception( "not implemented" );
//		LocalDate forwardStartDate = baseDate().plus(forwardStartsIn);
//		LocalDate forwardEndDate   = forwardStartDate.plus(forwardPeriodLength);
//
//		double yearsToForwardStartsIn     = new AnnualizedPeriod(forwardStartsIn).getValue();
//		double yearsOfForwardPeriodLength = new AnnualizedPeriod(forwardPeriodLength).getValue();
//		double yearsToForwardEndsIn       = yearsToForwardStartsIn + yearsOfForwardPeriodLength;
//	
//		return paymentFrequency * 
//		(
//				Math.pow(
//						(
//								Math.pow( 1.0 + getValue(forwardEndDate) / paymentFrequency, paymentFrequency * yearsToForwardEndsIn )
//								/ Math.pow( 1.0 + getValue(forwardStartDate) / paymentFrequency, paymentFrequency * yearsToForwardStartsIn )
//						),
//						1.0 / (paymentFrequency * yearsOfForwardPeriodLength)
//				) - 1.0									
//		); 
	}

	public Curve parralelShift(double offset) throws Exception {
		LinearInterpolatedCurve offsetCurve = new LinearInterpolatedCurve(baseDate());

		for (LocalDate date : this.curveValues.keySet()) 
			offsetCurve.curveValues.put(date, this.getValue(date)+offset);		

		return offsetCurve;
	}

	public Curve horizontalShift(ReadablePeriod horizontalOffset) throws Exception {
		LinearInterpolatedCurve offsetCurve = new LinearInterpolatedCurve(baseDate().plus(horizontalOffset));

		for (LocalDate date : this.curveValues.keySet()) 
			offsetCurve.curveValues.put(date.plus(horizontalOffset) , this.getValue(date));		

		return offsetCurve;
	}
	
	public Curve horizontalShiftNegative(ReadablePeriod horizontalOffset) throws Exception {
		LinearInterpolatedCurve offsetCurve = new LinearInterpolatedCurve(baseDate().plus(horizontalOffset));

		for (LocalDate date : this.curveValues.keySet()) 
			offsetCurve.curveValues.put(date.minus(horizontalOffset) , this.getValue(date));		

		return offsetCurve;
	}

}
