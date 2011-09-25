package tddfinance.contract;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.joda.time.LocalDate;

import tddfinance.trade.PositionEffect;

public class ContractAdd implements Contract {
	private final List<Contract> contracts;

	private void addContract(Contract contract){
		if(contract instanceof ContractAdd){
			ContractAdd contractInAdd = (ContractAdd) contract;
			this.contracts.addAll(contractInAdd.contracts);
		} 
		else {
			this.contracts.add(contract);
		}
	}
	
	public ContractAdd( Contract contract1, Contract contract2 ) {
		this.contracts = new LinkedList<Contract>();
		addContract(contract1);
		addContract(contract2);
	}
	
	public ContractAdd( Contract... contracts ) {
		this.contracts = new LinkedList<Contract>(Arrays.asList(contracts));
	}

	public ContractAdd( List<Contract> contracts ) {
		this.contracts = new LinkedList<Contract>(contracts);
	}

	public double scaleFactor() {
		return 1.0;
	}
	
	public Currency currency() {
		return null;
	}
	
	class MaturityComparator implements Comparator<Contract>{
		public int compare(Contract c1, Contract c2) {
			return c1.maturityDate().compareTo(c2.maturityDate());
		}
	}
	
	class NextEventDateComparator implements Comparator<Contract>{
		public int compare(Contract c1, Contract c2) {
			return c1.nextEventDate().compareTo(c2.nextEventDate());
		}
	}

	public LocalDate maturityDate() {
		return Collections.max(contracts, new MaturityComparator()).maturityDate();
	}
	
	public LocalDate nextEventDate() {
		return nextEventContractElement().nextEventDate();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ContractAdd){
			ContractAdd theOther = (ContractAdd) obj; 
			return this.contracts.equals(theOther.contracts);
		}
		else
			return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return contracts.hashCode();
	}

	@Override
	public String toString() {
		return contracts.toString();
	}
	
	public Contract unitContract() {
		return this;
	}

	public boolean isFungible() {
		return false;
	}
	
	/*
	 * returns a contract element which has the earliest nextEventDate()
	 */
	public Contract nextEventContractElement(){
		return Collections.min(contracts, new NextEventDateComparator());		
	}
	
	public Contract nextContract(){
		LinkedList<Contract> newContracts = new LinkedList<Contract>(contracts);

		LocalDate nextEventDate = nextEventDate();
		for (ListIterator<Contract> iterator = newContracts.listIterator(); iterator.hasNext();) {
			Contract contract = (Contract) iterator.next();
			
			if( contract.nextEventDate() == nextEventDate ){
				if( contract.nextContract().equals(Contract.ZERO) )
					iterator.remove();
				else
					iterator.set(contract.nextContract());
			}
		}
	
		if( newContracts.size() == 0 )
			return new ContractZero();
		else
			return new ContractAdd(newContracts);
	}

	public PositionEffect nextSpunOffPositions() throws Exception{
		return nextEventContractElement().nextSpunOffPositions();
	}


	public List<Contract> getList() {
		return new LinkedList<Contract>(contracts); //to keep the immutability, returning a copy of "contracts",
	}

	public TradeEvent nextEvent() throws Exception{
		PositionEffect replacement = new PositionEffect(nextContract(), 1).add( nextEventContractElement().nextSpunOffPositions() );
		
		if( replacement.equals( PositionEffect.ZERO) )
			return TradeEvent.NOEVENT;
		else
			return new TradeEvent(
				nextEventDate(), 
				new PositionEffect(this, -1 ).add( replacement ) );
	}
}
