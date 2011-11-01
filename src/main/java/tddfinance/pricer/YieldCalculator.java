package tddfinance.pricer;

public class YieldCalculator {

	public static double futureValue(double quantity, double rate, int years) {
		// TODO Auto-generated method stub
		return quantity * Math.pow( 1.0 + rate, years );
	}

//	/**
//	 * Return the annual rate of return of contract, assuming zeroCouponRateCurve remains unchanged over time
//	 */
//	public static double annualRateOfReturn ( CashflowListInterface contract, Curve zeroCouponRateCurve ) throws Exception {
//		double futureValue = 0.0;
//		double annualizedPeriodToMaturity = 0.0;
//		LocalDate currentPeriodEndDate = contract.maturityDate();
//
//		List<Cashflow>         cashflowList = contract.cashflowList();
//		ListIterator<Cashflow> lastIterator = cashflowList.listIterator( cashflowList.size() );
//		
//		//Loop in reverse - needs to calculato the annualized period "To Maturity", so it is a bit difficult in the normal order
//		for (ListIterator<Cashflow> iterator = lastIterator; iterator.hasPrevious();) {
//			Cashflow cashflow = iterator.previous();
//			
//			//maybe annualized period should depend on the day count convention of the product? 
//			annualizedPeriodToMaturity += DayCount.fraction(DayCount.DC_ACTUALACTUAL, cashflow.settlementDate(), currentPeriodEndDate, currentPeriodEndDate);
//
//			//horizontal shift - assuming the zero curve remains unchanged
//			Days offsetDays = Days.daysBetween( contract.maturityDate(), cashflow.settlementDate() );
//			Curve shiftZeroCurve  = zeroCouponRateCurve.horizontalShift( offsetDays );
//			
//			double yield = shiftZeroCurve.getValue( cashflow.settlementDate() ); 
//			futureValue += cashflow.quantity() * Math.pow( 1 + yield, annualizedPeriodToMaturity );
//			
//			currentPeriodEndDate = cashflow.settlementDate();
//		}
//		
//		double price = new CurvePricer(zeroCouponRateCurve).price(contract);
//		return Math.pow( futureValue / price, annualizedPeriodToMaturity );
//	}

}
