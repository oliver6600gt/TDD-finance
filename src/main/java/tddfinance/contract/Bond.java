package tddfinance.contract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tddfinance.day.Compounding;

import org.joda.time.LocalDate;
import org.joda.time.ReadablePeriod;
import org.joda.time.Years;

//FixMe: BDC, Calendar, 
//FixMe: adjusted (=maturityDate()), and unadjusted maturity date
//FixMe: Day Count Convention
//FixMe: compounding frequency??

public class Bond extends AbstractBaseContract implements CashflowListInterface{
	private final ContractAdd self; //ContractAdd {Cashflow1, Cashflow2, ...}

//	private Currency       currency;
//	private double         quantity;
//	private double         couponRate;
//	private ReadablePeriod tenor;
//	private ReadablePeriod paymentPeriod;

	private Bond(ContractAdd contract){
		self = contract;
	}
	
	/**
	 * A set of cashflows with arbitrary compounding <br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param currency
	 * @param quantity
	 * @param couponRate 
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param tenor : The length of time between tenorStartDate and maturityDate()
	 * @param compoundingRule : determines compounding frequency/unit period
	 */
	public Bond(
		Currency       currency,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		ReadablePeriod tenor, 
		Compounding    compoundingRule ) 
	{
		self = initializeBond(currency, quantity, couponRate, tenorStartDate, tenor, compoundingRule );
	}

	/**
	 * A set of cashflows with annual compounding.
	 * The tenor should be multiple of a year as the truncated couponPeriod means its annual coupon. <br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param currency
	 * @param quantity
	 * @param couponRate
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param tenor : The length of time "in years" between tenorStartDate and maturityDate()
	 */	
	public Bond(
		Currency       currency,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		Years          tenor ) 
	{
		self = initializeBond(currency, quantity, couponRate, tenorStartDate, tenor, Compounding.ANNUAL);
	}

	private ContractAdd initializeBond(
		Currency       currency,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		ReadablePeriod tenor, 
		Compounding    compoundingRule )
	{
		ReadablePeriod couponPeriod    = compoundingRule.period();
		LocalDate      maturityDate    = tenorStartDate.plus(tenor);
		LocalDate      firstCouponDate = tenorStartDate.plus(couponPeriod);
		double         couponAmount    = couponRate * quantity * compoundingRule.fraction();
		
		List<Contract> couponList = new ArrayList<Contract>();
		LocalDate settlementDate  = firstCouponDate;
		
		//coupons except the last coupon & redemption
		while( !settlementDate.isAfter(maturityDate) ){
			if( settlementDate.equals(maturityDate) )
				couponList.add( new Cashflow(couponAmount + quantity, settlementDate, currency) ); //redemption
			else
				couponList.add( new Cashflow(couponAmount, settlementDate, currency) );

			settlementDate = settlementDate.plus( couponPeriod );
		}
		
		return new ContractAdd(couponList);		
	}
	
	protected Contract representation() {
		return self;
	};

	public Contract unitContract() {
		return this;
	}

	public Contract nextContract() {
		Contract nextContractSelf = self.nextContract();
		if( nextContractSelf.equals( new ContractZero() ) )
			return Contract.ZERO;
		else
			//This cast should be safe:
			//	self = ContractAdd, so self.nextContract() must be Contract.ZERO or ContractAdd by definition of nextContract()
			return new Bond( (ContractAdd)nextContractSelf );  
	}

	public boolean isFungible() {
		return false;
	}

	public List<Cashflow> cashflowList() {
		List<Cashflow> cashflows    = new LinkedList<Cashflow>();
		List<Contract> contractlist = ((ContractAdd)representation()).getList();
		
		for (Contract contract : contractlist) {
			cashflows.add((Cashflow) contract);
		}
		
		return cashflows;
	}
}
