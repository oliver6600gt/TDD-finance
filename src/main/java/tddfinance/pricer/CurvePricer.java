package tddfinance.pricer;

import tddfinance.contract.Cashflow;
import tddfinance.contract.CashflowListInterface;
import tddfinance.contract.Contract;
import tddfinance.curve.Curve;
import tddfinance.day.Compounding;
import tddfinance.day.DayCount;
import tddfinance.day.DayCountConvention;

public class CurvePricer implements Pricer {

	private final Curve curve;
	
	public CurvePricer( Curve curve ) {
		this.curve = curve;
	}
	
	public double price(Contract contract) throws Exception {
		
		if(contract instanceof CashflowListInterface){

			CashflowListInterface cashflowList = (CashflowListInterface) contract;
			DayCountConvention    convention   = DayCount.DC_ACTUALACTUAL;
	
			double price = 0.0;
			
			for (Cashflow cashflow : cashflowList.cashflowList()) {
				double yield  = curve.getValue( cashflow.settlementDate() ); 
				Pricer pricer = new ZeroCouponPricer(curve.baseDate(), yield, Compounding.ANNUAL, convention);
				price += pricer.price(cashflow);
			}
			
			return price;
		}
		else{
			throw new Exception( "Contract of class " + contract.getClass().toString() + " cannot be handled by CurvePricer" );
		}
	}

	public static double price(Contract contract, Curve curve) throws Exception {
		Pricer pricer =  new CurvePricer(curve);
		return pricer.price(contract);
	}
}
