package tddfinance.calculator;

import org.joda.time.LocalDate;

import tddfinance.contract.Cashflow;
import tddfinance.contract.ZeroCoupon;
import tddfinance.day.Compounding;
import tddfinance.day.DayCount;
import tddfinance.day.DayCountConvention;

public class ZeroCouponPricer {
	
	/**
	 * Return the price of the (Zero Coupon) contract, or a single cashflow, given the yield to discount the cashflow
	 * 
	 * @param contract - zero coupon contract
	 * @param pricingDate - the date you are pricing the contract on
	 * @param zeriCouponRate - (implied) zero coupon rate on the settlement date of the redemption
	 * @param convention
	 * @param compoundingRule   
	 * @return
	 * @throws Exception
	 */
	public static double price( 
		ZeroCoupon         contract, 
		LocalDate          pricingDate, 
		double             zeriCouponRate, 
		DayCountConvention convention,
		Compounding        compoundingRule ){
		
		//day count fraction 
		double t    = DayCount.fraction( convention, pricingDate, contract.settlementDate(), compoundingRule );
		int    freq = compoundingRule.frequency();
		
		return contract.quantity() / Math.pow( 1.0 + zeriCouponRate / freq, t * freq );
	}
	
	/**
	 * Return the price of the (Zero Coupon) contract, or a single cashflow, given the yield to discount the cashflow
	 * 
	 * @param contract - zero coupon contract
	 * @param pricingDate - the date you are pricing the contract on
	 * @param zeriCouponRate - (implied) zero coupon rate on the settlement date of the redemption
	 * @param convention
	 * @param compoundingRule   
	 * @return
	 * @throws Exception
	 */
	public static double price( 
			Cashflow           contract, 
			LocalDate          pricingDate, 
			double             zeriCouponRate, 
			DayCountConvention convention,
			Compounding        compoundingRule ){
			
		//day count fraction 
		double t    = DayCount.fraction( convention, pricingDate, contract.settlementDate(), compoundingRule );
		int    freq = compoundingRule.frequency();
		
		return contract.quantity() / Math.pow( 1.0 + zeriCouponRate / freq, t * freq );
	}
}
