package tddfinance.contract;

import org.joda.time.LocalDate;

import tddfinance.trade.PositionEffect;

public class ContractScale implements Contract {
	
	private static final double equalityThreshold = 1.0e-16; 
	
	private final double   thisScaleFactor;
	private final Contract subContract;
	
	public ContractScale( double scaleFactor, Contract contract ) {
		if(contract instanceof ContractScale){
			ContractScale scale  = (ContractScale) contract;
			this.subContract     = scale.subContract;
			this.thisScaleFactor = scaleFactor * contract.scaleFactor();			
		}
		else{
			this.thisScaleFactor = scaleFactor;
			this.subContract     = contract;	
		}
	}
	
	public ContractScale( Contract contract, double scaleFactor ) {
		if(contract instanceof ContractScale){
			ContractScale scale  = (ContractScale) contract;
			this.subContract     = scale.subContract;
			this.thisScaleFactor = scaleFactor * contract.scaleFactor();			
		}
		else{
			this.thisScaleFactor = scaleFactor;
			this.subContract     = contract;	
		}
	}

	public boolean equals(Object obj) {
		if( obj instanceof ContractScale ){
			ContractScale  theOther = (ContractScale) obj;
			
			double absoluteDiff = Math.abs( ( theOther.scaleFactor() - scaleFactor() ) / scaleFactor() );
			return absoluteDiff < ContractScale.equalityThreshold && subContract.equals( theOther.subContract );
		}
		else
			return super.equals(obj);
	}
	
	public int hashCode() {
		return new Double(scaleFactor()).hashCode() * 31 + subContract.hashCode();
	}
	
	public String toString() {
		return String.format("ContractScale(%.16f, %s)", thisScaleFactor, subContract);
	}

	public LocalDate maturityDate() {
		return subContract.maturityDate(); 
	}
	
	public double scaleFactor() {
		if( isFungible() )
			return thisScaleFactor * subContract.scaleFactor();
		else
			return 1;
	}
	
	public Contract unitContract() {
		if( isFungible() )
			return subContract.unitContract();
		else
			return this;
	}

	public boolean isFungible() {
		return subContract.isFungible();
	}

	public Currency currency() {
		return subContract.currency();
	}
	
	public LocalDate nextEventDate() {
		return subContract.nextEventDate();
	}

	public Contract nextContract() {
		Contract nextSubContract = subContract.nextContract();
		if( nextSubContract.equals( Contract.ZERO ) )
			return Contract.ZERO;
		else
			return new ContractScale( thisScaleFactor, nextSubContract );
	}

	public PositionEffect nextSpunOffPositions() throws Exception{
		//Currently nextSpunOffPositions will only have fungible positions, so this implementation is okay
		//However, if in the future nextSpunOffPositions can include non-fungibles
		//be careful not to have non-1 quantity in positions effect
		return subContract.nextSpunOffPositions().scale(thisScaleFactor);
	}
	
	public TradeEvent nextEvent() throws Exception{
		return new TradeEvent(
			nextEventDate(), 
			new PositionEffect(this, -1, nextContract(), 1).add( nextSpunOffPositions() ) );
	}
	
	public double rawScaleFactor(){
		return thisScaleFactor;
	}
}
