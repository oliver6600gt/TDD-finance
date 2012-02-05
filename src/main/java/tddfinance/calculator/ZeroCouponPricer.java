package tddfinance.calculator;

import org.joda.time.LocalDate;

import tddfinance.contract.Cashflow;
import tddfinance.contract.Contract;
import tddfinance.contract.ZeroCoupon;
import tddfinance.day.Compounding;
import tddfinance.day.DayCount;
import tddfinance.day.DayCountConvention;

public class ZeroCouponPricer {
	private final LocalDate          pricingDate;
	private final double             yield;
	private final Compounding        compoundingRule;
	private final DayCountConvention convention;

	/**
	 * Return the price of the (Zero Coupon) contract, or a single cashflow, given the yield to discount the cashflow
	 * 
	 * @param contract - zero coupon contract
	 * @param pricingDate - the date you are pricing the contract on
	 * @param yield - yield on the settlement date of the redemption
	 * @param compoundingFrequency - you'll need to provide the compounding frequency as the prices will be different for the same zero coupon, if you use different frequencies  
	 */
	static double price( 
		Contract           contract, 
		LocalDate          pricingDate, 
		double             yield, 
		Compounding        compoundingRule, 
		DayCountConvention convention 
	) throws Exception{
		
		Cashflow cashflow;
		
		if( contract instanceof ZeroCoupon )
			cashflow = ( (ZeroCoupon) contract ).cashflow();
		else if( contract instanceof Cashflow )
			cashflow = ( Cashflow ) contract;
		else
			throw new Exception( "Contract of class " + contract.getClass().toString() + " cannot be handled by ZeroCouponPricer" );
			
		//day count fraction 
		double t = DayCount.fraction(convention, pricingDate, cashflow.settlementDate(), compoundingRule );
		return cashflow.quantity() / Math.pow( 1.0 + yield, t );
	}

	public ZeroCouponPricer(LocalDate pricingDate, double yield, Compounding compoundingRule, DayCountConvention convention) {
		this.pricingDate = pricingDate;
		this.yield = yield;
		this.compoundingRule = compoundingRule;
		this.convention = convention;
	}
	
	public double price(Contract contract) throws Exception {
		return ZeroCouponPricer.price(contract, pricingDate, yield, compoundingRule, convention );
	}

}
