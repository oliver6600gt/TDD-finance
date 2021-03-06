package tddfinance.curve;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	DiscreteCurveTest.class, 
	FlatCurveTest.class,
	LinearInterpolatedCurveTest.class })
public class AllTests {
	public static void main(String[] args) {
		JUnitCore.main(AllTests.class.getName());
	}

}
