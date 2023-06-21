/* P5Bittner

	Some description.

	Programmer: Keith Bittner
	Date:		March 5, 2019
*/

import java.util.Scanner;
import java.io.*;

public class P5Bittner
{
	public static void main ( String args [ ] ) 
	{
		boolean ioProblem = false;
		Scanner agentInput;
		String [] flightData = new String [100];
		String flightFileName = "Default.txt";

		if (args.length > 0)
		{
			flightFileName = args[0];
		}

		try
		{
			agentInput = new Scanner (new File (flightFileName));
			int i = 0;

			while (agentInput.hasNext() && i != flightData.length)
			{
				flightData[i] = agentInput.nextLine();
				i++;	
			}        
			
			agentInput.close();
		}

		catch (IOException problem)
		{
			ioProblem = true;
			System.out.println ("I-O Exception: " + problem);
		}
		schedule (flightData); // method to format flight array

		report (flightData); // method for output		
	}
	
	/* schedule()

		formats array
	*/
	static void schedule (String [] flightData)
	{
		for (int i = 0; i < flightData.length; i++)
		{
			if (flightData[i] != null)
			{			
				String [] temp = flightData[i].split("~"); // split element @ [i]

				String number = temp[0]; // flight ####
				String cityState = formatCityState(temp); // city, state
				String flightTimes = formatTime(temp); // departure + arrival + flying time

				flightData[i] = number + "\t" + cityState + "\t" + flightTimes; // rebuild element @ [i]
			}
		}
	}
	
	/* formatCityState()

		formats city and state for array
	*/
	static String formatCityState(String [] temp)
	{
		String city = temp[1] + ", ";
		String state = temp[2];

		if (temp[1].length() > 12)
		{
			city = temp[1].substring(0,12) + ", ";
		}

		if (temp[1].length() < 12)
		{
			for (int i = 0; i < (12 - temp[1].length()); i++)
			{
				city += " ";
			}
		}
		return city + state;
	}
	
	/* formatTime()

		formats flight times, checks if valid, finds difference for array
	*/
	static String formatTime (String [] temp)
	{
		Time depTime = new Time ();
		//int depHour = Integer.parseInt(temp[3].substring(0, 2));
		//int depMinute = Integer.parseInt(temp[3].substring(2, 4));
		//String depError = depTime.setTime(depHour, depMinute);

		String depError = depTime.setTime(
				Integer.parseInt(temp[3].substring(0, 2)),
				Integer.parseInt(temp[3].substring(2, 4)));

		Time arrTime = new Time ();
		int arrHour = Integer.parseInt(temp[4].substring(0, 2));
		int arrMinute = Integer.parseInt(temp[4].substring(2, 4));
		String arrError = arrTime.setTime(arrHour, arrMinute);
		
		String departure = depError;
		String arrival = arrError;

		if (depError == null)
		{
			departure = depTime.ampmTime();
		}
		
		if (arrError == null)
		{
			arrival = arrTime.ampmTime();
		}

		Time flightTime = depTime.difference(arrTime);		

		return departure + "\t" + arrival + "\t" + flightTime.toString();
	}

	static void report (String [] flightData)
	{
		String exceptionReport = "****** EXCEPTION REPORT ******";

		for (int i = 0; i < flightData.length; i++)
		{
			if (flightData[i] != null)
			{
				System.out.println(flightData[i]);
			}
		}
	}
}
