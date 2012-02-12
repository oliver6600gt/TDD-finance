package tddfinance.calculator;

import org.joda.time.LocalDate;

import tddfinance.contract.Cashflow;
import tddfinance.day.Compounding;
import tddfinance.day.DayCount;
import tddfinance.day.DayCountConvention;

public class FutureValue {

	/**
	 * 
	 * @param cashflow : cashflow to calculate the future value on
	 * @param yield : yield of investment from pricingDate to cashflow.SettlementDate()
	 * @param convention
	 * @param compounding
	 * @return
	 */
	public static double calculate(
		Cashflow           cashflow, 
		LocalDate          pricingDate, 
		double             yield, 
		DayCountConvention convention, 
		Compounding        compounding) {
		
		double t    = DayCount.fraction( convention, pricingDate, cashflow.settlementDate(), compounding );
		int    freq = compounding.frequency();
		return cashflow.quantity() * Math.pow( 1.0 + yield / freq, t * freq );
	}
}
