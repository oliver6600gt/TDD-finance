package tddfinance.pricer;

import org.joda.time.LocalDate;

import tddfinance.contract.Cashflow;
import tddfinance.contract.CashflowListInterface;
import tddfinance.contract.Contract;
import tddfinance.curve.Curve;
import tddfinance.day.DayCount;

public class CurvePricer implements Pricer {

	private final Curve curve;
	
	public CurvePricer( Curve curve ) {
		this.curve = curve;
	}
	
	public double price(Contract contract) throws Exception {
		
		if(contract instanceof CashflowListInterface){
			CashflowListInterface cashflowList  = (CashflowListInterface) contract;
	
			double price = 0.0;
			double annualizedPeriod = 0.0;
			LocalDate previousPeriodEndDate = curve.baseDate();
			
			for (Cashflow cashflow : cashflowList.cashflowList()) {
				
				//maybe annualized period should depend on the day count convention of the product? 
				annualizedPeriod += DayCount.fraction(DayCount.DC_ACTUALACTUAL, previousPeriodEndDate, cashflow.settlementDate(), cashflow.settlementDate());
				double yield = curve.getValue( cashflow.settlementDate() ); 
				
				double discountFactor = 1.0 / Math.pow( 1 + yield, annualizedPeriod ); 
				price += cashflow.quantity() * discountFactor;
				
				previousPeriodEndDate = cashflow.settlementDate();
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
