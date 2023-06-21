/*	P4Bittner (Time.java)

	Time object class that represents 1 user input of hours and minutes. The
	computation methods in this object class performs a specific operation on
	2 instances of this object class.

	Programmer: Keith Bittner
	Date:		February 28, 2019
*/
public class Time
{
	private int hour;
	private int minute;

	// Default constructor
	public Time ()
	{
		this.hour = 0;
		this.minute = 0;
	}

	/* setTime()

		Checks if user input is within valid time range.  If inputs are valid,
		setTime changes hour and minutes for the Time object.  If inputs are
		invalid, setTime sends a message to user of the invalid input for the
		user to re-enter.
	*/
	public String setTime (int newHour, int newMin)
	{
		String valid = "";
		if ((newHour >= 0 && newHour <= 23) && (newMin >= 0 && newMin <= 59))
		{
			this.hour = newHour;
			this.minute = newMin;
			valid = null; // indicates time sucessfully changed
		}

		if (newHour < 0 || newHour > 23)
		{
			valid = "Hour out of range (0-23)."; // hour is out of range
		}
		
		if (newMin < 0 || newMin > 59)
		{
			valid = "Minute out of range (0-59)."; // minutes are out of range
		}
		
		return valid;
	}

	// plain toString
	public String toString ()
	{
		String minFormat = "" + this.minute;

		if (minute < 10) // if structure formats minutes of single integer
		{
			minFormat = "0" + this.minute;
		}		

		return this.hour + ":" + minFormat;
	}

	/* ampmTime()

		A toString that formats user input into standard time display for the
		user in the format HH:MM AM/PM or Noon/Midnight.
	*/	
	public String ampmTime ()
	{
		String minFormat = "" + this.minute;

		if (minute < 10) // if structure formats minutes of single integer
		{
			minFormat = "0" + this.minute;
		}
		
		String standardTime = " " + this.hour + ":" + minFormat + " AM";
		
		if (hour > 12) // if structure formats hours from military to standard
		{
			standardTime = " " + (this.hour - 12) + ":" + minFormat + " PM";
		}
		else
		{
			if (hour == 12 & minute == 0) // check if its Noon
			{
				standardTime = " Noon";
			}
			if (hour == 0 & minute == 0) // check if its Midnight
			{
				standardTime = " Midnight";
			}
		}
		return standardTime;
	}

	/* milTime()

		A toString that displays military time for the user in the format HHMM.
	*/	
	public String milTime ()
	{
		String milHour = " " + this.hour;
		String milMinute = "" + this.minute;

		if (hour < 12) // if structure to add 0 to single integer hours
		{
			milHour = " 0" + this.hour;
		}
		if (minute < 10) // if structure to add 0 to single integer minutes
		{
			milMinute = "0" + this.minute;
		}
		return milHour + milMinute + " Hours";
	}

	/* increment()

		This method adds 1 minute to time when called. If minutes are currently
		59, method adds 1 to the hour and resets minutes to 0.
	*/	
	public void increment ()
	{
		if (minute < 59)
		{
			this.minute = this.minute + 1;
		}
		else
		{
			if (hour == 23) // check if time is equal to 2359 case
			{
				this.hour = 0;
				this.minute = 0;
			}
			else // all other :59 cases
			{			
				this.hour = this.hour + 1;
				this.minute = 0;
			}
		}
	}

	/* difference()

		This method converts both user inputs for hour and minutes into minutes
		in order to find the time difference in minutes. Method then converts
		the minute difference back into hours and minutes.
	*/	
	public Time difference (Time that)
	{
		int startMin = (this.hour * 60) + this.minute; // first user input
		int endMin = (that.hour * 60) + that.minute; // second user input
		int timeDiffer = 0;

		if (startMin >= endMin) // if this > that, subtract differ from 24hrs
		{
			timeDiffer = 1440 - (startMin - endMin); // 24hrs * 60min = 1440
		}
		else // if that > this
		{
			timeDiffer = endMin - startMin;
		}

		int hourDiff = timeDiffer / 60; // convert minutes to hours
		int minDiff = timeDiffer % 60; // convert decimal hours to minutes
		that.hour =  hourDiff; // difference in hours
        that.minute = minDiff; // difference in minutes
		
		return that;
	}

	/* later()

		This method checks to see if this time is later than that time. If this
		time is later than that time, returns true.
	*/	
	public boolean later (Time that)
	{
        boolean isLater = false;

        if (this.hour == that.hour) // if hours are the same
        {
            if (this.minute > that.minute) // check if minutes are later
            {
                isLater = true;
            }
        }
        if (this.hour > that.hour) // this time is later than that time
        {
            isLater = true;
        }  		

        return isLater;
	}

	/* equals()

		This method checks to see if 2 inputted times are the same and returns
		true/false.
	*/	
	public boolean equals (Time that)
	{
		return ((this.hour == that.hour) && (this.minute == that.minute));
	}

	/* copy()

		This method builds a new Time object and copies user input to the new
		object.  Returns the new object with the same values but different
		memory address.
	*/	
	public Time copy ()
	{
        Time timeDupe = new Time(); // new object
        timeDupe.hour = this.hour; // copy hours
        timeDupe.minute = this.minute; // copy minutes
		
        return timeDupe;
	}
} // end of Time class
