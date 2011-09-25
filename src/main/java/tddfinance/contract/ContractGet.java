package tddfinance.contract;

import org.joda.time.LocalDate;
import tddfinance.trade.PositionEffect;

/**
 * Acquire the underlying contract at the maturity
 */
public class ContractGet implements Contract {
	private final Contract  contractToGet;
	private final LocalDate dateToGetContract;
	
	/**
	 * probably the dateToGetContract parameter can be later refactored to be a "region" as in MlFi presentation
	 */
	public ContractGet(LocalDate dateToGetContract, Contract contractToGet) {
		this.dateToGetContract = dateToGetContract;
		this.contractToGet     = contractToGet;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof ContractGet ){
			ContractGet theOther = (ContractGet)obj;
			return this.dateToGetContract.equals(theOther.dateToGetContract)&& 
				   this.contractToGet.equals(theOther.contractToGet);	
		}
		else 
			return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return dateToGetContract.hashCode() + 31 * contractToGet.hashCode();
	}
	
	@Override
	public String toString() {
		return "ContractGet(" + dateToGetContract.toString() + ", " + contractToGet.toString() + ")";
	}

	public LocalDate maturityDate(){
		return dateToGetContract;
	}
	
	public double scaleFactor() {
		if( isFungible() )
			return contractToGet.scaleFactor();
		else
			return 1;
	}
	
	public Contract unitContract() {
		if( isFungible() )
			return new ContractGet( dateToGetContract, contractToGet.unitContract() );
		else
			return this;
	}

	public boolean isFungible() {
		return contractToGet.isFungible();
	}
	
	public Currency currency() {
		return contractToGet.currency();
	}

	public LocalDate nextEventDate(){
		return dateToGetContract;
	}
	
	public Contract nextContract() {
		if( contractToGet.isFungible() )
			return Contract.ZERO;
		else 
			return contractToGet;
	}
	
	public PositionEffect nextSpunOffPositions() throws Exception{
		if( contractToGet.isFungible() )
			return new PositionEffect( contractToGet, 1 );
		else 
			return PositionEffect.ZERO;
	};

	public PositionEffect negateMyself() throws Exception {
		return new PositionEffect( new Contract[]{this}, new double[]{-1} );
	}
	
	public TradeEvent nextEvent() throws Exception{
		return new TradeEvent(
			nextEventDate(), 
			new PositionEffect(this, -1, nextContract(), 1).add( nextSpunOffPositions() ) );
	}

}
