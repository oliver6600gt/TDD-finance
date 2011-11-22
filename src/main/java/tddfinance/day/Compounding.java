package tddfinance.day;

public enum Compounding {
	ANNUAL             ("Annual Compounding"),
	SEMI_ANNUAL        ("Semi-Annual Compounding"),
	EVERY_FOUR_MONTH   ("Every-Four-Month Compounding"),
	QUARTERLY          ("Quarterly Compounding"),
	BI_MONTHLY         ("Bi-Monthly Compounding"),
	MONTHLY            ("Monthly Compounding"),
	WEEKLY             ("Weekly Compounding"),
	DAILY              ("Daily Compounding");
	
	private final String description;
	
	private Compounding( String description ){
		this.description = description;
	}
	
	public String toString(){
		return description;
	}
}
