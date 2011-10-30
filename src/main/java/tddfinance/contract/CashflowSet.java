package tddfinance.contract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tddfinance.numeral.AnnualizedPeriod;

import org.joda.time.LocalDate;
import org.joda.time.ReadablePeriod;
import org.joda.time.Years;

//FixMe: BDC, Calendar, 
//FixMe: adjusted (=maturityDate()), and unadjusted maturity date
//FixMe: Day Count Convention
//FixMe: compounding frequency??

public class CashflowSet extends AbstractBaseContract implements CashflowListInterface{
	private final ContractAdd self; //ContractAdd {Cashflow1, Cashflow2, ...}

//	private Currency       currency;
//	private double         quantity;
//	private double         couponRate;
//	private ReadablePeriod tenor;
//	private ReadablePeriod paymentPeriod;

	private CashflowSet(ContractAdd contract){
		self = contract;
	}
	
	/**
	 * A set of cashflows with an arbitrary period <br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param currency
	 * @param quantity
	 * @param couponRate 
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param tenor : The length of time between tenorStartDate and maturityDate()
	 * @param paymentPeriod : Arbitrary payment period, the payment frequency is calculated the inverse of this
	 */
	public CashflowSet(
		Currency       currency,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		ReadablePeriod tenor, 
		ReadablePeriod couponPeriod ) 
	{
		self = initializeCashflowSet(currency, quantity, couponRate, tenorStartDate, tenor, couponPeriod);
	}
	/**
	 * A set of cashflows with an arbitrary period <br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param cash : cash of the currency
	 * @param quantity
	 * @param couponRate 
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param tenor : The length of time between tenorStartDate and maturityDate()
	 * @param paymentPeriod : Arbitrary payment period, the payment frequency is calculated the inverse of this
	 */
	public CashflowSet(
		Cash           cash,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		ReadablePeriod tenor, 
		ReadablePeriod couponPeriod ) 
	{
		self = initializeCashflowSet(cash.currency(), quantity, couponRate, tenorStartDate, tenor, couponPeriod);
	}

	/**
	 * A set of cashflows with an arbitrary maturity date; this accepts a parameter of maturityDate instead of tenor <br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param currency
	 * @param quantity
	 * @param couponRate 
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param maturityDate : The maturity date; tenor = maturityDate - tenorStartDate
	 * @param paymentPeriod : Arbitrary payment period, the payment frequency is calculated the inverse of this
	 */
	public CashflowSet(
		Currency        currency,
		double          quantity, 
		double          couponRate, 
		LocalDate       tenorStartDate,
		LocalDate       maturityDate, 
		ReadablePeriod  couponPeriod ) 
	{
		ReadablePeriod tenor = AnnualizedPeriod.periodBetweenDays(tenorStartDate, maturityDate);
		self = initializeCashflowSet(currency, quantity, couponRate, tenorStartDate, tenor, couponPeriod);		
	}

	/**
	 * A set of cashflows with an arbitrary maturity date; this accepts a parameter of maturityDate instead of tenor <br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param cash : cash of the currency
	 * @param quantity
	 * @param couponRate 
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param maturityDate : The maturity date; tenor = maturityDate - tenorStartDate
	 * @param paymentPeriod : Arbitrary payment period, the payment frequency is calculated the inverse of this
	 */
	public CashflowSet(
		Cash            cash,
		double          quantity, 
		double          couponRate, 
		LocalDate       tenorStartDate,
		LocalDate       maturityDate, 
		ReadablePeriod  couponPeriod ) 
	{
		ReadablePeriod tenor = AnnualizedPeriod.periodBetweenDays(tenorStartDate, maturityDate);
		self = initializeCashflowSet(cash.currency(), quantity, couponRate, tenorStartDate, tenor, couponPeriod);		
	}
	
	/**
	 * A set of annual cashflows with an arbitrary tenor, but the tenor should be multiple of a year as the truncated couponPeriod means its annual coupon<br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param currency
	 * @param quantity
	 * @param couponRate
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param tenor : The length of time "in years" between tenorStartDate and maturityDate()
	 */	
	public CashflowSet(
		Currency       currency,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		Years          tenor ) 
	{
		self = initializeCashflowSet(currency, quantity, couponRate, tenorStartDate, tenor, Years.years(1));
	}

	/**
	 * A set of annual cashflows with an arbitrary tenor, but the tenor should be multiple of a year as the truncated couponPeriod means its annual coupon<br>
	 * Each cashlow amount = quantity x couponRate x day count factor
	 * @param cash : cash of the currency
	 * @param quantity
	 * @param couponRate
	 * @param tenorStartDate : NOT THE FIRST COUPON DATE!, (i.e.) the first coupon date = tenorStartDate + paymentPeriod 
	 * @param tenor : The length of time "in years" between tenorStartDate and maturityDate()
	 */	
	public CashflowSet(
		Cash           cash,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		Years          tenor ) 
	{
		self = initializeCashflowSet(cash.currency(), quantity, couponRate, tenorStartDate, tenor, Years.years(1));
	}

	private ContractAdd initializeCashflowSet(
		Currency       currency,
		double         quantity, 
		double         couponRate, 
		LocalDate      tenorStartDate,
		ReadablePeriod tenor, 
		ReadablePeriod couponPeriod )
	{
		LocalDate maturityDate    = tenorStartDate.plus(tenor);
		LocalDate firstCouponDate = tenorStartDate.plus(couponPeriod);
		double    couponAmount    = couponRate * quantity * new AnnualizedPeriod( couponPeriod ).getValue();

		List<Contract> couponList = new ArrayList<Contract>();
		LocalDate settlementDate  = firstCouponDate;
		
		//coupons except the last coupon & redemption
		while( !settlementDate.isAfter(maturityDate) ){
			couponList.add( new Cashflow(settlementDate, couponAmount, currency) );
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
			return new CashflowSet( (ContractAdd)nextContractSelf );  
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
