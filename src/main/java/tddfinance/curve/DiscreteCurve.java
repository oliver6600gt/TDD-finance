package tddfinance.curve;

import java.util.Map;
import java.util.TreeMap;

import tddfinance.numeral.AnnualizedPeriod;

import org.joda.time.LocalDate;
import org.joda.time.ReadablePeriod;
import org.joda.time.Years;

public class DiscreteCurve implements Curve {
	private LocalDate              baseDate;
	private Map<LocalDate, Double> curveValues;
	
	private DiscreteCurve(LocalDate baseDate) {
		this.baseDate    = baseDate;
		this.curveValues = new TreeMap<LocalDate, Double>();
	}
	
	public DiscreteCurve(LocalDate baseDate, Map<LocalDate, Double> curveValues) {
		this.baseDate    = baseDate;
		this.curveValues = new TreeMap<LocalDate, Double>();
		this.curveValues.putAll(curveValues);
	}

	public Curve parralelShift(double offset) throws Exception{
		DiscreteCurve offsetCurve = new DiscreteCurve(baseDate());

		for (LocalDate date : this.curveValues.keySet()) 
			offsetCurve.curveValues.put(date, this.getValue(date)+offset);		

		return offsetCurve;
	}

	public LocalDate baseDate() {
		return baseDate;
	}
	
	public double getValue(LocalDate date) throws Exception {
		try {
			return this.curveValues.get(date);
		} 
		catch (NullPointerException e) {
			throw new Exception(
						date.toString() 
						+ " is not a valid date for this curve - following is this curve values\n" 
						+ this.curveValues.toString()
					);
		}
		//The other types of Exception is propagated
	}
	
	public double forwardValue(ReadablePeriod forwardStartsIn, ReadablePeriod forwardPeriodLength, int paymentFrequency) throws Exception {
		LocalDate forwardStartDate = baseDate().plus(forwardStartsIn);
		LocalDate forwardEndDate   = forwardStartDate.plus(forwardPeriodLength);

		double yearsToForwardStartsIn     = new AnnualizedPeriod(forwardStartsIn).getValue();
		double yearsOfForwardPeriodLength = new AnnualizedPeriod(forwardPeriodLength).getValue();
		double yearsToForwardEndsIn       = yearsToForwardStartsIn + yearsOfForwardPeriodLength;
	
		return paymentFrequency * 
		(
				Math.pow(
						(
								Math.pow( 1.0 + getValue(forwardEndDate) / paymentFrequency, paymentFrequency * yearsToForwardEndsIn )
								/ Math.pow( 1.0 + getValue(forwardStartDate) / paymentFrequency, paymentFrequency * yearsToForwardStartsIn )
						),
						1.0 / (paymentFrequency * yearsOfForwardPeriodLength)
				) - 1.0									
		); 
	}
	
	//FixMe what about multi-annual bond? get some more parameters??
	public double parYield(ReadablePeriod tenorToMaturity) throws Exception{

		ReadablePeriod couponPeriod            = Years.years(1);//Months.months(12 / couponFrequency);
		ReadablePeriod periodToFirstCouponDate = couponPeriod;  //temporary assumption for easiness
		
		double yearsToFirstCouponDate = new AnnualizedPeriod(periodToFirstCouponDate).getValue();
		double yearsOfCouponPeriod    = new AnnualizedPeriod(couponPeriod).getValue();
		
		LocalDate maturityDate = this.baseDate().plus(tenorToMaturity);

		//1: Calculate the denominator
		double denominator            = 0.0;
		int    couponPeriodIndex      = 1;
		for( 
			LocalDate couponDate = this.baseDate.plus(periodToFirstCouponDate); //firstCouponDate
			!couponDate.isAfter(maturityDate); 
			couponDate = couponDate.plus(couponPeriod), ++couponPeriodIndex
		){
			//Sadly, there is not "ReadablePeriod + ReadablePeriod" operation, and cannot calculate AnnualizedPeriod from two LocalDate,
			//so this is the best way so fart to calculate the annualized period to a coupon date
			double yearsToCouponDate = yearsToFirstCouponDate + (couponPeriodIndex - 1)* yearsOfCouponPeriod;
			denominator += 1.0 / Math.pow(1.0 + this.getValue(couponDate), yearsToCouponDate);
		}
			
		//2: Calculate the numerator
		double yearsToMaturity = new AnnualizedPeriod(tenorToMaturity).getValue();
		double numerator       = 1.0 - 1.0 / Math.pow(1.0 + this.getValue(maturityDate), yearsToMaturity);
		
		return numerator / denominator; 
	}

	public Curve horizontalShift(ReadablePeriod horizontalOffset) throws Exception {
		DiscreteCurve offsetCurve = new DiscreteCurve(baseDate().plus(horizontalOffset));

		for (LocalDate date : this.curveValues.keySet()) 
			offsetCurve.curveValues.put(date.plus(horizontalOffset) , this.getValue(date));		

		return offsetCurve;
	}
	
	@Override
	public String toString() {
		return new String( "DiscreteCurve with the following values\n" + curveValues.toString() );
	}
}
