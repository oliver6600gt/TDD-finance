package tddfinance.contract;

import org.joda.time.LocalDate;

public class ZeroCoupon extends AbstractBaseContract implements Contract {
	private final Cashflow self;
	
	public ZeroCoupon(LocalDate settlementDate, double quantity, Currency currency) {
		self = new Cashflow(settlementDate, quantity, currency);
	}

	@Override
	protected Contract representation() {
		return self;
	}

	public Contract unitContract() {
		return new ZeroCoupon( settlementDate(), 1, currency() );
	}

	public Contract nextContract() {
		return Contract.ZERO;
	}

	public boolean isFungible() {
		return true;
	}

	@Override
	public String toString() {
		return String.format( "ZeroCoupon: %s %.2f on %s", currency(), quantity(), settlementDate() );
	}
	
	public double quantity(){    
		return scaleFactor();	
	};
	
	public LocalDate settlementDate(){ 
		return representation().maturityDate();	
	}
	
	public Cashflow cashflow(){
		return (Cashflow) representation();
	}

}
