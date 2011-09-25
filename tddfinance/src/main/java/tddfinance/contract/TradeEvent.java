package tddfinance.contract;

import org.joda.time.LocalDate;

import tddfinance.trade.PositionEffect;

public class TradeEvent {
	private LocalDate      eventDate;
	private PositionEffect positionEffect;
	
	public static final TradeEvent NOEVENT = new TradeEvent(Contract.InfiniteMaturity, PositionEffect.ZERO);
	
	public TradeEvent(LocalDate eventDate, PositionEffect positionEffect){
		this.eventDate      = eventDate;
		this.positionEffect = positionEffect;
	}
	
	public LocalDate eventDate(){
		return eventDate;
	}

	public PositionEffect positionEffect() throws Exception {
		return positionEffect;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TradeEvent){
			TradeEvent theOther = (TradeEvent) obj;
			return this.eventDate.equals(theOther.eventDate) && this.positionEffect.equals(theOther.positionEffect);
		}
		else	
			return super.equals(obj);
	}
}
