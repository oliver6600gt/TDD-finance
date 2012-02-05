package tddfinance.calculator;

import tddfinance.contract.Cashflow;
import tddfinance.contract.CashflowListInterface;
import tddfinance.contract.Contract;
import tddfinance.curve.Curve;
import tddfinance.day.Compounding;
import tddfinance.day.DayCount;
import tddfinance.day.DayCountConvention;

public class CurvePricer {

	public static double price(Contract contract, Curve curve) throws Exception {

		if(contract instanceof CashflowListInterface){

			CashflowListInterface cashflowList = (CashflowListInterface) contract;
			DayCountConvention    convention   = DayCount.DC_ACTUAL_ACTUAL_ICMA;
	
			double price = 0.0;
			
			for (Cashflow cashflow : cashflowList.cashflowList()) {
				double yield  = curve.getValue( cashflow.settlementDate() ); 
				ZeroCouponPricer pricer = new ZeroCouponPricer(curve.baseDate(), yield, Compounding.ANNUAL, convention);
				price += pricer.price(cashflow);
			}
			
			return price;
		}
		else{
			throw new Exception( "Contract of class " + contract.getClass().toString() + " cannot be handled by CurvePricer" );
		}

	}
}
