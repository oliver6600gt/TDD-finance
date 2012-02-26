package tddfinance.contract;

import org.joda.time.LocalDate;

import tddfinance.day.Compounding;
import tddfinance.day.DayCountConvention;
import tddfinance.numeral.AccruedInterest;

/**
 *  Extended class of Cashflow, interest-baring cashflow.
 *  
 */
public class Coupon extends Cashflow{

	public Coupon(
		double             faceValue, 
		double             couponRate, 
		Currency           currency, 
		LocalDate          accrualStartDate,
		LocalDate          paymentDate, 
		DayCountConvention dayCountConvention, 
		Compounding        compoundingRule) {
	
		super( 
			//coupon amount: accrualEndDate = nextPaymentDate, as the coupon amount = accrual for a full period.
			new AccruedInterest(faceValue, couponRate, accrualStartDate, paymentDate, paymentDate, dayCountConvention, compoundingRule),
			paymentDate, 
			currency );
	}
	
	private AccruedInterest accruedIneterst(){
		//it is guaranteed that self.quantityNumeral() is an instance of AccruedInterest because of the constructor  
		return (AccruedInterest) quantityNumeral();
	}

	//--------------------------------------------------------//
	// Expose AccruedInterest properties
	//--------------------------------------------------------//

	public double faceValue(){
		return accruedIneterst().faceValue();
	}

	public double couponRate(){
		return accruedIneterst().couponRate();
	}
	
	public double couponAmount(){
		return accruedIneterst().getValue();
	}
	
	public LocalDate accrualStartDate(){
		return accruedIneterst().accrualStartDate();
	}
	
	public DayCountConvention dayCountConvention(){
		return accruedIneterst().dayCountConvention();
	}
	
	public Compounding compoundingRule(){
		return accruedIneterst().compoundingRule();
	}
}
