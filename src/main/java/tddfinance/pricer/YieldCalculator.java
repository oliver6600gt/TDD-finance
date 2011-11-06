package tddfinance.pricer;

import java.util.List;
import java.util.ListIterator;

import org.joda.time.ReadablePeriod;

import tddfinance.contract.Cashflow;
import tddfinance.contract.CashflowListInterface;
import tddfinance.curve.Curve;
import tddfinance.numeral.AnnualizedPeriod;

public class YieldCalculator {

	/**
	 * The future value of the quantity, when you invest it in the yield for the period with the annual compounding frequency  
	 */
	public static double futureValue(double quantity, double yield, ReadablePeriod period) {
		AnnualizedPeriod p = new AnnualizedPeriod(period);
		return quantity * Math.pow( 1.0 + yield, p.getValue() );
	}

	/**
	 * The future value of the quantity, when you invest it in the yield for the period with the compoundingFrequency  
	 */
	public static double futureValue(double quantity, double yield, ReadablePeriod period, int compoundingFrequency) {
		AnnualizedPeriod p = new AnnualizedPeriod(period);
		return quantity * Math.pow( 1.0 + yield / compoundingFrequency, compoundingFrequency * p.getValue() );
	}

	/**
	 * Return the annual rate of return of contract, assuming zeroCouponRateCurve remains unchanged over time
	 */
	public static double annualRateOfReturn ( CashflowListInterface contract, Curve zeroCouponRateCurve, ReadablePeriod periodToMaturity, ReadablePeriod paymentPeriod ) throws Exception {
		double futureValue = 0.0;
		double annualizedPeriodToMaturity = 0.0;
		
		List<Cashflow>         cashflowList = contract.cashflowList();
		ListIterator<Cashflow> lastIterator = cashflowList.listIterator( cashflowList.size() );
		
		//horizontal shift - assuming the zero curve remains unchanged
		Curve shiftZeroCurve  = zeroCouponRateCurve.horizontalShift( periodToMaturity );
		
		AnnualizedPeriod p = new AnnualizedPeriod(paymentPeriod);
		double eachAnnualizedPeroid = p.getValue(); 
		
		//Loop in reverse - needs to calculate the annualized period "To Maturity", so it is a bit difficult in the normal order
		for (ListIterator<Cashflow> iterator = lastIterator; iterator.hasPrevious();) {
			Cashflow cashflow = iterator.previous();

			if( cashflow.settlementDate().equals(contract.maturityDate()) ){
				futureValue += cashflow.quantity();
			}
			else{
				double yield = shiftZeroCurve.getValue( contract.maturityDate() ); 
				futureValue += cashflow.quantity() * Math.pow( 1 + yield, annualizedPeriodToMaturity );				
			}			
			
			//maybe annualized period should depend on the day count convention of the product? 
			annualizedPeriodToMaturity += eachAnnualizedPeroid;

			//horizontal shift - assuming the zero curve remains unchanged
			shiftZeroCurve = shiftZeroCurve.horizontalShiftNegative( paymentPeriod );
		}
		
		double price = new CurvePricer(zeroCouponRateCurve).price(contract);
		return Math.pow( futureValue / price, 1.0 / annualizedPeriodToMaturity ) - 1;
	}

	/**
	 *  Calculate the zero coupon rate of periodToMaturity from price
	 */
	public static double zeroCouponRate(double price, ReadablePeriod periodToMaturity, int compoundingFrequency) {
		AnnualizedPeriod p = new AnnualizedPeriod(periodToMaturity);
		return compoundingFrequency * ( Math.pow( 100/price, 1.0/(compoundingFrequency*p.getValue() ) ) - 1 );
	}

}
