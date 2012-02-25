package tddfinance.contract;

import org.joda.time.LocalDate;

import tddfinance.numeral.ConstNumeral;
import tddfinance.numeral.Numeral;
import tddfinance.trade.PositionEffect;

public class ContractScale implements Contract {
	
	private static final double equalityThreshold = 1.0e-16; 
	
	private final Numeral  scaleFactor;
	private final Contract contractToScale;
	
	/**
	 *  It does not flatten the inner contract even if contract is ContractScale
	 */
	public ContractScale( double scaleFactor, Contract contract ) {
		this.scaleFactor     = new ConstNumeral( scaleFactor );
		this.contractToScale = contract;	
	}

	/**
	 *  It does not flatten the inner contract even if contract is ContractScale
	 */
	public ContractScale( Numeral scaleFactor, Contract contract ) {
		this.scaleFactor     = scaleFactor;
		this.contractToScale = contract;	
	}

	/**
	 *  ContractScale( 100, c ) != ContractScale( 10, ContractScale( 10, c ) ). Counter-intuitive? 
	 *  <p>
	 *  However, the constructor does not "flatten" the inner ContractScale.
	 *  And it distinguishes two contracts like ContractScale( 100, c ) and ContractScale( 10, ContractScale( Price Of X, c ) ) where the price of X happens to be 10.  
	 */
	public boolean equals(Object obj) {
		if( obj instanceof ContractScale ){
			ContractScale  theOther = (ContractScale) obj;
			
			double absoluteDiff = Math.abs( ( theOther.scaleFactor() - scaleFactor() ) / scaleFactor() );
			return absoluteDiff < ContractScale.equalityThreshold && contractToScale.equals( theOther.contractToScale );
		}
		else
			return false;
	}
	
	public int hashCode() {
		return new Double(scaleFactor()).hashCode() * 31 + contractToScale.hashCode();
	}
	
	public String toString() {
		return String.format("ContractScale(%s, %s)", scaleFactor, contractToScale);
	}

	public LocalDate maturityDate() {
		return contractToScale.maturityDate(); 
	}
	
	public double scaleFactor() {
		return scaleFactor.getValue();
	}
	
	public Contract unitContract() {
		if( isFungible() )
			return contractToScale.unitContract();
		else
			return this;
	}

	public boolean isFungible() {
		return contractToScale.isFungible();
	}

	public Currency currency() {
		return contractToScale.currency();
	}
	
	public LocalDate nextEventDate() {
		return contractToScale.nextEventDate();
	}

	public Contract nextContract() {
		Contract nextSubContract = contractToScale.nextContract();
		if( nextSubContract.equals( Contract.ZERO ) )
			return Contract.ZERO;
		else
			return new ContractScale( scaleFactor, nextSubContract );
	}

	public PositionEffect nextSpunOffPositions() throws Exception{
		//Currently nextSpunOffPositions will only have fungible positions, so this implementation is okay
		//However, if in the future nextSpunOffPositions can include non-fungibles
		//be careful not to have non-1 quantity in positions effect
		return contractToScale.nextSpunOffPositions().scale(scaleFactor.getValue());
	}
	
	public TradeEvent nextEvent() throws Exception{
		return new TradeEvent(
			nextEventDate(), 
			new PositionEffect(this, -1, nextContract(), 1).add( nextSpunOffPositions() ) );
	}
	
	public double rawScaleFactor(){
		return scaleFactor.getValue();
	}
}
