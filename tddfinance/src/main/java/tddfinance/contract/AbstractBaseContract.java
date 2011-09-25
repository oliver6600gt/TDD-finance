package tddfinance.contract;

import org.joda.time.LocalDate;

import tddfinance.trade.PositionEffect;

public abstract class AbstractBaseContract implements Contract {

	/**
	 * The internal representation of the contract.
	 * A primitive Contract type (Cash, ContractAdd, ContractScale,...) should return itself (=this).
	 * 
	 * If you set up a new Contract as a composite of primitives, or other existing contracts, 
	 * then you return the composite so that you can leverage the composite design patter to avoid re-defining various methods (maturityDate(), etc) 
	 *  
	 * @return Composite of Contract(s) to represent itself 
	 */
	protected abstract Contract representation();
	
	@Override
	public int hashCode() {
		return getClass().hashCode()*31 + representation().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(getClass().isInstance(obj)){
			AbstractBaseContract theOther = (AbstractBaseContract) obj;
			return representation().equals( theOther.representation() );
		}
		else
			return super.equals(obj);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + representation().toString();
	}
	
	public LocalDate maturityDate() {
		return representation().maturityDate();
	}
	
	public double scaleFactor(){
		return representation().scaleFactor();
	}
	
	public Currency currency() {
		return representation().currency();
	}
	
	public LocalDate nextEventDate() {
		return representation().nextEventDate();
	}
	
	public PositionEffect nextSpunOffPositions() throws Exception{
		return representation().nextSpunOffPositions();
	};
	
	public TradeEvent nextEvent() throws Exception{
		return new TradeEvent(
			nextEventDate(), 
			new PositionEffect(this, -1, nextContract(), 1).add( nextSpunOffPositions() ) );
	}
	
}
