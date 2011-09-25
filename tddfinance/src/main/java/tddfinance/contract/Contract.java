package tddfinance.contract;

import org.joda.time.LocalDate;

import tddfinance.trade.PositionEffect;

public interface Contract {
	//horrible implementation...
	//although the assumption that the chlonology is Gregorian can be fair enough??
	public static final LocalDate InfiniteMaturity = new LocalDate( new LocalDate().year().getMaximumValue(), 12, 31 );
	public static final Contract  ZERO = new ContractZero();
	
	/**
	 * @return The date of contract's maturity where you will lose all the rights and obligations from the contract
	 */
	public LocalDate maturityDate();
	
	/**
	 * @return by default 1.0, but for example ContractScale returns the scale factor
	 */
	public double scaleFactor();

	public Contract unitContract();

	//maybe it shouldn't be a method of Contract...? 
	//for example ContractAdd does not have a single representation currency naturally
	//or Maybe we should implement MultipleCurrency class which extends currency?
	public Currency currency();
	
	public LocalDate nextEventDate();
	
	/**
	 * The "non-fungible" contract in the next event.<br> 
	 * Assumption is that any contract does not have two non-fungible contracts in the next event. 
	 * <p>
	 * For example, if you have a ContractGet(tomorrow, Cash), the next contract is Contract.ZERO as Cash is fungible.
	 * If you have a ContractGet(tomorrow, c) where c is non-fungible, you will get c. 
	 * <p>
	 * @return the next non-fungible contract 
	 */
	public Contract nextContract();

	/**
	 * The PositionEffect of fungible contracts in the next event
	 * <p>
	 * For example, a bond will spin off coupon as nextSpunOffPositions() 
	 * @return PositionEffect of the fungible contracts in the next event 
	 * @throws Exception
	 */
	public PositionEffect nextSpunOffPositions() throws Exception;

	/**
	 * 
	 * @return the scheduled trade event which happens on nextEventDate()
	 * By default, the position effect of the event should be calculated as follows:
	 * <p>
	 * new PositionEffect( this, -1, nextContract(), +1 ) + nextSpunOffPositions()<br>
	 * (i.e.) convert "the contract of this type" from this to nextContract(), and spin off positions with other-type contracts if there is any
	 * @throws Exception
	 */
	public TradeEvent nextEvent() throws Exception;
	
	boolean isFungible();
}
