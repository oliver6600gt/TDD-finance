package tddfinance.numeral;

import org.joda.time.LocalDate;

import tddfinance.day.Compounding;
import tddfinance.day.DayCount;
import tddfinance.day.DayCountConvention;

/**
 *  Accrued interest calculation class with lazy evaluation. 
 *  Lazy evaluation in a sense that accrual start/end dates can be subject to change (due to holiday calendar change, not yet implemented though :( )
 *  and also the coupon rate is subject to change
 */
public class AccruedInterest implements Numeral {

	private final Numeral            couponRate;    //being numeral for flexibility to handle a floating-rate coupon 
	private final LocalDate          accrualStartDate;
	private final LocalDate          accrualEndDate;
	private final LocalDate          nextCouponDate;
	private final Compounding        compounding;
	private final DayCountConvention dayCountConvention;
	
	
	public AccruedInterest( double fixedCouponRate, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextCouponDate, DayCountConvention convention, Compounding compounding ) {
		this.couponRate         = new ConstantNumeral( fixedCouponRate );
		this.accrualStartDate   = accrualStartDate;
		this.accrualEndDate     = accrualEndDate;
		this.nextCouponDate     = nextCouponDate;
		this.dayCountConvention = convention;
		this.compounding        = compounding;
	}
	
	public LocalDate accrualStartDate(){
		return this.accrualStartDate;
	}
	
	public LocalDate accrualEndDate() {
		return this.accrualEndDate;
	}

	public LocalDate nextCouponDate() {
		return this.nextCouponDate;
	}

	public double couponRate() {
		return this.couponRate.getValue();
	}

	public DayCountConvention dayCountConvention(){
		return this.dayCountConvention;
	}
	
	public Compounding compoundingRule(){
		return this.compounding;
	}
	
	public double getValue() {
		return DayCount.fraction(DayCount.DC_30360US, accrualStartDate, accrualEndDate, nextCouponDate, Compounding.QUARTERLY);
	}
}