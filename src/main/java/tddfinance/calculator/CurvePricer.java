package tddfinance.calculator;

import tddfinance.contract.Cashflow;
import tddfinance.contract.CashflowListInterface;
import tddfinance.curve.Curve;
import tddfinance.day.Compounding;
import tddfinance.day.DayCountConvention;

public class CurvePricer {

	/**
	 * 
	 * @param cashflowList : cashflow list to be priced
	 * @param curve : zero-coupon curve where the cashflow list is priced against
	 * @param convention : day count convention of the cashflow list
	 * @param compounding : compounding rule for the cashflow list
	 * @return
	 * @throws Exception
	 */
	public static double price( 
		CashflowListInterface cashflowList, 
		Curve                 curve,
		DayCountConvention    convention,
		Compounding           compounding
	) throws Exception {

		double price = 0.0;
		for (Cashflow cashflow : cashflowList.cashflowList()) {
			double yield  = curve.getValue( cashflow.settlementDate() ); 
			ZeroCouponPricer pricer = new ZeroCouponPricer(curve.baseDate(), yield, compounding, convention);
			price += pricer.price(cashflow);
		}

		return price;
	}
}
