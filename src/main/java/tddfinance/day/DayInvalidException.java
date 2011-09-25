package tddfinance.day;

public class DayInvalidException extends Exception {
	
	private static final long serialVersionUID = -5259440454784130436L;
	
	protected DayInvalidException() {};
	
	public DayInvalidException( int year, int month, int date ) {
		super( String.format( "Invalid Day!! year = %d, month = %d, date = %d", year, month, date ) );
	}
}
