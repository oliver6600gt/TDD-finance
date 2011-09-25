package tddfinance.contract;

import org.joda.time.LocalDate;

import tddfinance.trade.PositionEffect;

/**
 * Represents unit amount of a given currency
 */
public class Cash implements Contract{
	private final Currency currency;
	
	static final public Cash USD = new Cash( Currency.USD );
	static final public Cash EUR = new Cash( Currency.EUR );
	static final public Cash JPY = new Cash( Currency.JPY );
	
	public Cash(Currency currency) {
		this.currency = currency;
	}
	
	public Cash(String currencyInString) throws Exception {
		for (Currency currency : Currency.values())
			if(currency.toString().equals(currencyInString))
			{
				this.currency = currency;
				return;
			}

		throw new Exception( currencyInString + " is not a valid Currency" );
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Cash ){
			Cash cashToCompare = (Cash) obj;
			return this.currency == cashToCompare.currency;			
		}
		else
			return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return currency.hashCode();
	}
	
	@Override
	public String toString() {
		return currency.toString();
	}
	
	public LocalDate maturityDate() {
		return  Contract.InfiniteMaturity;
	}

	public double scaleFactor() {
		return 1.0;
	}
	
	public Currency currency() {
		return currency;
	}
	
	public LocalDate nextEventDate() {
		return maturityDate();
	}

	public Contract nextContract() {
		return Contract.ZERO;
	}
	
	public PositionEffect nextSpunOffPositions(){
		return PositionEffect.ZERO;
	}
	
	public TradeEvent nextEvent() {
		return new TradeEvent(nextEventDate(), PositionEffect.ZERO);
	}
	
	public Contract unitContract() {
		return this;
	}

	public boolean isFungible() {
		return true;
	}
}
