/*	TestOfTime

	This program is a utility for exercising the methods in 
	the Time object class.

	It offers the user the following actions:
		Test Formatting Methods
		Test Time Difference
		Test Increment method
		Test Later method
		Test Copy method
		Test Equals method

	For any of the above tests, the user will enter one or two
	Times. If an entered time is invalid according to the object
	class mutator method, the user will have to re-enter. 

	Written by Richard Croft on 11 May 2013
*/

import javax.swing.*;

public class TestOfTime
{
	public static void main (String [ ] args )
	{
		int userChoice;

		Time testTime = new Time ( );

		String instructions = "Choose a test to perform, or click 'Quit'";

		String [ ] options = { "Formatting", "Difference", 
			"Increment", "Copy", "Equals", "Later", "Quit" };

		userChoice = JOptionPane.showOptionDialog(null, instructions,
			"Test Options", JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE, null, options, options[0] );

		while (!(options [userChoice].equals("Quit")) )	// process test choices
		{
			getTime (testTime, "time for test");

			if (options[userChoice].equals("Formatting") )
			{
				formatTest (testTime);
			}
			else if (options[userChoice].equals("Difference") )
			{
				differenceTest (testTime);
			}
			else if (options[userChoice].equals("Increment") )
			{
				incrementTest (testTime);
			}
			else if (options[userChoice].equals("Later") )
			{
				laterTest (testTime);
			}
			else if (options[userChoice].equals("Copy") )
			{
				copyTest (testTime);
			}
			else 
			{
				equalsTest(testTime);
			}

			userChoice = JOptionPane.showOptionDialog(null,instructions,
				"Test Options", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0] );
		}  // end while

		System.exit(0);
	}

	/*	formatTest
	
		This method is given a Time object and calls the three output
		formatting methods for Time. It displays the results in a dialog box.
	*/

	static void formatTest ( Time testTime )
	{

		String message = "toString:\t"+ testTime.toString() +
			"\nAM/PM time:\t"+ testTime.ampmTime() +
			"\nMilitary:\t" + testTime.milTime();	
		JOptionPane.showMessageDialog(null,message,"Results",
			JOptionPane.INFORMATION_MESSAGE);
	}

	/*	differenceTest
	
		This method is given a Time object and solicits entry of another time.
		It uses the difference method of the Time class to determine the 
		time difference between the two times, which it displays in an information box.
	*/

	static void differenceTest( Time begin )
	{
		Time end = new Time ( );
		getTime ( end, "second time" );

		Time timeDifference = begin.difference(end);

		JOptionPane.showMessageDialog(null,"Difference is  " + timeDifference.milTime(),
			"Time Difference", JOptionPane.INFORMATION_MESSAGE);
	}

	/*	incrementTest
	
		This method is given a Time object and uses the increment method to add
		one minute to the time. It displays the new time in a dialog box.
	*/

	static void incrementTest (Time start)
	{
		start.increment( ); // adds one minute

		JOptionPane.showMessageDialog (null, start.milTime( ), 
			"Increment Test", JOptionPane.INFORMATION_MESSAGE);
	}

	/*	laterTest
	
		This method is given a Time object. It solicits entry of another object and
		displays a message indicating whether the first time is later than 
		the second one.
	*/

	static void laterTest (Time first)
	{
		Time second = new Time ( );

		getTime (second, "time to compare with");

		String message = " is not later than ";

		if (first.later(second))
		{
			message = " is later than ";
		}

		JOptionPane.showMessageDialog (null, 
			first.ampmTime( ) + message + second.ampmTime( ), 
			"Later Test", JOptionPane.INFORMATION_MESSAGE);
	}

	/*	equalsTest
	
		This method is given a Time object. It solicits entry of another object and
		displays a message indicating whether the two times are equal 
		(same hour and minute values)
	*/

	static void equalsTest (Time first)
	{
		String result = " doesn't equal ";

		Time second = new Time ( );
	
		getTime (second, "time to compare");

		if (first.equals(second))
		{
			result = " equals ";
		}

		JOptionPane.showMessageDialog (null, first.milTime() + result + second.milTime(), 
			"Equals Test", JOptionPane.INFORMATION_MESSAGE);	
	} 

	/*	copyTest
	
		This method is given a Time object. It uses the Time class copy method to
		create a new Time object with the same values. It shows the two times and
		indicates whether they share the same address, which would indicate a
		failure of the copy method.
	*/

	static void copyTest (Time original)
	{
		String message = " not at same address as original ";
		Time dupe = original.copy();

		if (original == dupe)	// trouble--it's not a copy 
		{
			message = " at same address as original ";
		}
		message = "Copy " + dupe.ampmTime() + message + original.ampmTime();

		JOptionPane.showMessageDialog (null, message, 
			"Copy Test", JOptionPane.INFORMATION_MESSAGE);
	}	

	/*	getTime
	
		This method is given a Time object and a String descriptor. 
		It solicits entry of integer hour and minutes and attempts to
		set the time using those values. If the operation fails, this method
		gets new hour and minute values until a valid time
		is entered.
	*/

	static void getTime ( Time t, String descriptor )
	{
		int hour, min;

		hour = getInteger ( "hour for " + descriptor);
		min = getInteger ( "minute for "+ descriptor);

		String message = t.setTime ( hour, min);

		while (message != null)
		{
			JOptionPane.showMessageDialog(null,message);

			hour = getInteger ( "hour");
			min = getInteger ( "minute");

			message = t.setTime ( hour, min);
		}
	}

	/*	getInteger
	
		This method is given a String prompt of what integer to solicit. 
		It uses the prompt in an input dialog to solicit entry.
		If the entered value is not an integer, this method
		repeats input until a valid entry is achieved.

		Revised 02/22/19 to allow negative integers
	*/

	static int getInteger ( String prompt )
	{
		String inValue;

		boolean problems = true;

		inValue = JOptionPane.showInputDialog(null,
			 "Please enter the integer " + prompt );

		while ( problems )
		{
			problems = false;

			int i=0;

			if (inValue.charAt(i) == '-') // negative, skip 
			{
				i++;
			}

			for ( /* i already initialized */; i < inValue.length(); i++)
			{
				if (inValue.charAt(i) < '0' || inValue.charAt(i) > '9' )
				{
					problems = true;
				}
			}
			if (problems)
			{
				inValue = JOptionPane.showInputDialog(null,
			 	"You entered " + inValue + ". Please enter the integer "
				 + prompt );
			}

		}
		return Integer.parseInt(inValue);
	}

}
			

			
	
