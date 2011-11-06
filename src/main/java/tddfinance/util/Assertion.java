package tddfinance.util;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Assertion {

	/**
	 * Expects the constructor of the class to thrown an exception, which includes the specified string.
	 * Otherwise, it fails
	 * 
	 * @param expectedExceptionSubString: the thrown exception from the constructor must include this message 
	 * @param classOfConstructor
	 * @param argumentTypes: the caller needs to specify this, otherwise it cannot handle cases where argument types are super classes but passed arguments are sub classes
	 * @param arguments: arguments to be passed to the constructor
	 */
	public static void assertConstructorException(
			String      expectedExceptionSubString, 
			Class<?>    classOfConstructor,
			Object[]    arguments, 
			Class<?>[]  argumentTypes )
	{
		try{
			Constructor<?> constructor = classOfConstructor.getConstructor(argumentTypes);
			assertConstructorException(expectedExceptionSubString, constructor, arguments );
		} 
		catch (NoSuchMethodException e){
			fail( "assertConstructorException: argumentTypes must be wrong.\n" +
				"1) Did you pass wrong 'argumentTypes'?\n" +
				"Or 2) did you omit the 'argumentTypes', but the types of 'arguments' mismatch with the constructor definition?\n"
			);
		}
	}

	/**
	 * Expects the constructor of the class to thrown an exception, which includes the specified string.
	 * Otherwise, it fails.
	 * 
	 * The argument types of the constructor must be exactly same as the types of "arguments".
	 * (i.e.) if your "arguments" is passing some child class instances, while the constructor definition
	 * says the argument types are super classes, then you need to instead call:
	 * 
	 * assertConstructorException(expectedExceptionSubString, classOfConstructor, arguments, "argumentTypes");
	 * 
	 * @param expectedExceptionSubMessage: the thrown exception from the constructor must include this message 
	 * @param classOfConstructor
	 * @param arguments: arguments to be passed to the constructor
	 */
	public static void assertConstructorException(
			String      expectedExceptionSubString, 
			Class<?>    classOfConstructor, 
			Object[]    arguments )
	{
		Class<?>[] argumentTypes = new Class<?>[arguments.length];
		for (int i = 0; i < arguments.length; i++)
			argumentTypes[i] = arguments[i].getClass();

		assertConstructorException(expectedExceptionSubString, classOfConstructor, arguments, argumentTypes );
	}

	/**
	 * Expects the constructor of the class to thrown an exception, which includes the specified string
	 * otherwise, it fails
	 * 
	 * @param expectedExceptionSubMessage: the thrown exception from the constructor must include this message 
	 * @param constructor
	 * @param arguments: arguments to be passed to the constructor
	 */
	public static void assertConstructorException(
			String         expectedExceptionSubString, 
			Constructor<?> constructor, 
			Object[]       arguments )
	{
		try {
			constructor.newInstance(arguments);
			fail( String.format( "Unexpected in assertConstructorException: new %s did not throw an exception", constructor ) ); 
		} 
		catch (InvocationTargetException e) {
			assertTrue(
				"The following exception message:\n" + e.getCause().getMessage() + "\nmust Contains the following string:\n" + expectedExceptionSubString,
				e.getCause().getMessage().contains(expectedExceptionSubString));		
		}
		catch (Exception e){
			fail( e.getMessage() );
		}
	}

	/**
	 * Put this wherever you want, and the program will fail with the following message.<br>
	 * "Unexpected to hit this line, as the previous statement should thrown an exception"
	 */
	public static void failUnexpectedToReachThis()
	{
		fail( "Unexpected to hit this line, as the previous statement should thrown an exception" ); 
	}
	
	/**
	 * Assert the Exception e to have the expected sub string. Typically you can use this as follows:
	 * <p> 
	 * try { stuff you expect to throw an exception; failUnexpected(); } <br> 
	 * catch (Exception e) { assertExceptionMessage( e, "the string you expect inside the exception" ); };
	 *	
	 * @param e
	 * @param expectedExceptionSubString
	 */
	public static void assertExceptionMessage( Exception e, String expectedExceptionSubString )
	{
		assertTrue(
			"The following exception message:\n" + e.getMessage() + "\nmust Contains the following string:\n" + expectedExceptionSubString,
			e.getMessage().contains(expectedExceptionSubString));		
	}

	/**
	 * Assert the Exception e to have the expected sub string, and with the expected type. Typically you can use this as follows:
	 * <p> 
	 * try { stuff you expect to throw an exception; failUnexpected(); } <br> 
	 * catch (Exception e) { assertExceptionMessage( e, "the string you expect inside the exception", ExpectedExceptionType.class ); };
	 *	
	 * @param e
	 * @param expectedExceptionSubString
	 * @param exceptionType
	 */
	public static void assertExceptionMessage( Exception e, String expectedExceptionSubString, Class<?> exceptionType )
	{
		assertTrue( "Exception e (" + e.getClass().toString() + ") is not an instance of " + exceptionType.toString(), exceptionType.isInstance(e) );
		assertTrue(
			"The following exception message:\n" + e.getMessage() + "\nmust Contains the following string:\n" + expectedExceptionSubString,
			e.getMessage().contains(expectedExceptionSubString));		
	}

	public static void assertInEqual( Object expected, Object actual ){
		assertTrue( 
			String.format("%s is expected to be inequal to %s", expected, actual), 
			!expected.equals(actual) );	
	}
	
	/**
	 * assert object equality AND hashcode() equality
	 */
	public static void assertEqualsStrict(Object expected, Object actual){
		assertEquals( expected, actual );
		assertEquals( "hashcode() is not equal: ", expected.hashCode(), actual.hashCode());
	}

	/**
	 * assert object inequality AND hashcode() inequality
	 */
	public static void assertInEqualStrict( Object expected, Object actual ){
		assertInEqual( expected, actual );
		assertTrue( 
			String.format("%s.hashcode() is expected to be inequal to %s.hashcode()", expected, actual), 
			!expected.equals(actual) );	
	}
}
