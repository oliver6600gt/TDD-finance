package tddfinance.contract;

import org.joda.time.LocalDate;

/**
 * Cashflow with specified settlement date, currency, and quantity 
 */
public class Cashflow extends AbstractBaseContract implements Contract {
	private final ContractScale self;

	public Cashflow(LocalDate settlementDate, double quantity, Currency currency) {
		self = new ContractScale(quantity, new ContractGet(settlementDate, new Cash(currency)));
	}

	@Override
	protected Contract representation() {
		return self;
	}

	public Contract unitContract() {
		return new Cashflow( settlementDate(), 1, currency() );
	}

	public Contract nextContract() {
		return Contract.ZERO;
	}

	public boolean isFungible() {
		return true;
	}

	@Override
	public String toString() {
		return String.format( "Cashflow: %s %.2f on %s", currency(), quantity(), settlementDate() );
	}
	
	public double quantity(){    
		return scaleFactor();	
	};
	
	public LocalDate settlementDate(){ 
		return representation().maturityDate();	
	}
}
