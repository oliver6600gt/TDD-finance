package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DayCountActualActualISDA implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "Actual/Actual ISDA", "Actual/Actual", "Act/Act", "Actual/365", "Act/365" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCountActualActualISDA.knownAs );
	}
	
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate, //nextPaymentSettleDate: this is ignored for this class ( convention )
		int       paymentFrequency) {    //paymentFrequency:      this is also ignored for this class ( convention )

		return DayCountActualActualISDA.numberOfDaysInLeapYear(accrualStartDate, accrualEndDate) / 366.0
				+ DayCountActualActualISDA.numberOfDaysInNonLeapYear(accrualStartDate, accrualEndDate) / 365.0;
	}

	/**
	 * Returns the actual number of days in non-leap years between start and end 
	 * 
	 * @param start : start of the period
	 * @param end : end of the period
	 * @return number of days in int
	 */
	public static int numberOfDaysInNonLeapYear(LocalDate start, LocalDate end) {
		return Days.daysBetween(start, end).getDays() - DayCountActualActualISDA.numberOfDaysInLeapYear(start, end);
	}
	
	/**
	 * Returns the actual number of days in leap years between start and end 
	 * 
	 * @param start : start of the period
	 * @param end : end of the period
	 * @return number of days in int
	 */
	public static int numberOfDaysInLeapYear(LocalDate start, LocalDate end) {
		int       numberOfDays = 0;
		
		if( start.year().isLeap() ){
			if( start.getYear() == end.getYear() )
				numberOfDays += Days.daysBetween(start, end).getDays();
			else{
				LocalDate lastDayOfYear = new LocalDate( start.getYear() + 1, 1, 1 ).minus( Days.ONE );
				numberOfDays += Days.daysBetween(start, lastDayOfYear).getDays();
			}
		}	
		
		//In case "end" is in or after the next year to "start"
		LocalDate firstDayOfYear = new LocalDate( start.getYear() + 1, 1, 1 );
		while( !firstDayOfYear.isAfter(end) ){ //!isAfter() : "firstDayOfYear" is on or before "end"			
			if( firstDayOfYear.year().isLeap() ){
				if( firstDayOfYear.getYear() == end.getYear() )
					numberOfDays += Days.daysBetween(firstDayOfYear, end).getDays() + 1; //+1: Jan 1st is inclusive
				else
					numberOfDays += 366;
			}
			
			firstDayOfYear = new LocalDate( firstDayOfYear.getYear() + 1, 1, 1 ); //shift to next Year's first date
		}
			
		return numberOfDays;
	}
}