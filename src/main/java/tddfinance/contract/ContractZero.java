package tddfinance.contract;

import org.joda.time.LocalDate;
import tddfinance.trade.PositionEffect;

public class ContractZero implements Contract {

	public ContractZero() {
	}
	
	public LocalDate maturityDate() {
		return Contract.InfiniteMaturity;
	}

	public double scaleFactor() {
		return 1;
	}

	public Contract unitContract() {
		return this;
	}

	public Currency currency() {
		return null;
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
	
	public boolean equals(Object obj) {
		if(obj instanceof ContractZero )
			return true;
		else
			return false;
	}
	
	public int hashCode() {
		return 1 ;
	}

	public TradeEvent nextEvent() throws Exception {
		return TradeEvent.NOEVENT;
	}
	
	public boolean isFungible() {
		return false;
	}
}
