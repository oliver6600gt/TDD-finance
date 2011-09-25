package tddfinance.numeral;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;
import org.joda.time.Weeks;
import org.joda.time.Years;

public class AnnualizedPeriod{

	private double annualizedValue;
	
	public AnnualizedPeriod(ReadablePeriod period) throws IllegalArgumentException{
		if(period instanceof Years){
			Years years = (Years) period;
			annualizedValue = years.getYears();
		}
		else if(period instanceof Months){
			Months months = (Months) period;
			annualizedValue = months.getMonths() / 12.0;
		}
		else if(period instanceof Weeks){
			Weeks weeks = (Weeks) period;
			annualizedValue = weeks.getWeeks() / 52.0;
		} 
		else if(period instanceof Days){
			Days days = (Days) period;
			annualizedValue = days.getDays() / 365.0;			
		}
		else {
			throw new IllegalArgumentException("Cannot calculate the number of years from the type " + period.getClass().toString());
		}
	}
	

	public static ReadablePeriod periodBetweenDays(LocalDate start, LocalDate end) {
		if(start.getDayOfMonth() == end.getDayOfMonth()){
			
			if(start.getMonthOfYear() == end.getMonthOfYear())
				return Years.yearsBetween(start, end);
			else
				return Months.monthsBetween(start, end);
		}
		else
			return Days.daysBetween(start, end);
	}
	
	public double getValue(){
		return annualizedValue;
	}
}
