#	bittnerP4.py
#
###############################################################################
#                                                                             #
#	Harry & Bob's Bigger Hammer Daily Rental Report                           #
#	"See Us When You Need a Bigger Hammer!"                                   #
#                                                                             #
#	This program is used to process daily rental tickets                      #
#	and produce invoices showing customer name, equipment category,           #
#	time out, time returned, time in use, and charge for each                 #
#	rental of the day.                                                        #
#                                                                             #
#	Programmer:		Blackie Decker                                            #
#	Date Written:	13 Feb. 2016                                              #
#                                                                             #
###############################################################################
#
# 	Added logical functions to generate the specified invoice showing the
#	customer name, equipment category, time rented, time returned, total time
#	rented, and the cost for rental. Each function has a comment header that
#	describes its purpose.
#
#	Contributing Programmer: Keith Bittner
#	Date Edited: November 23, 2018
#
##############################

def main():		# Here begins the top level of processing

	#	initialize the invoice header

	invHead = "\n\n\n  Harry & Bob's Bigger Hammer Equipment Rentals\n" + \
		"                      INVOICE\n" + \
		"------------------------------------------------\n"

	customer = input("Please enter first customer name ('Q' to end): ")

	while customer != "Q" and customer != "q":		# process tickets
		#
		#	get rental information
		#
		equipment = int(input("What equipment category (1, 2, or 3)? ") )

		hourOut = inputHour("time out")	# call functions to get time rented
		minOut = inputMinute("time out")

		hourIn = inputHour("time returned") # call functions for return time
		minIn = inputMinute("time returned")
		#
		# add a line to the report
		#
		invoice = generateInvoice(
					customer,equipment,hourOut,minOut,hourIn,minIn)
        
		print(invHead + invoice)

		customer = input("Please enter next customer name ('Q' to end): ")

	print("\n\nTicket Processing Complete\n\n")

##############################
#
#	inputHour
#
#	This function is given a string describing the desired input time and
#	uses python's input function to request the hour for the time.
#	The function continues asking until a number between zero and 23 
#	(inclusive) is entered. This function does NOT guard against
#	non-numeric input.
#
##############################

def inputHour(description):

	inHour = int(input("Enter hour for " + description + " (0-23): "))

	while inHour < 0 or inHour > 23:
		print("You entered " + str(inHour))
		inHour = int(input("Enter hour for " + description + " (0-23): "))

	return inHour

##############################
#
#	inputMinute
#
#	This function performs the same way as the inputHour function where if you
#	enter a number outside of 0-59, the program will repeat your entry and then
#	ask you to re-enter your input. This returns the integer value to the main
#	program function where it is then passed to other functions as needed.
#
##############################

def inputMinute(description):

    inMinute = int(input("Enter minutes for " + description + " (0-59): "))

    while inMinute < 0 or inMinute > 59:
        print("You entered " + str(inMinute))
        inMinute = int(input("Enter minutes for " + description + "(0-59): "))

    return inMinute

##############################
#
#	generateInvoice
#
#	This function generates the invoice that will be printed in the main
#	program.  It uses logical functions in combination with format specifiers
#	to display the invoice appropriately.
#
##############################

def generateInvoice(custName, equipCat, rentHR, rentMIN, returnHR, returnMIN):

	invoiceFormat = "Customer: %-40s"%custName + "\n" \
		+ "Equipment Code: %-2s"%equipCat + "\n" \
		+ "Time Rented: %3s"%convert24hr(rentHR) + ":" + "%02d"%rentMIN \
		+ "%2s"%amPm(rentHR) + "\t" \
		+ "Time Returned: %3s"%convert24hr(returnHR) + ":" + "%02d"%returnMIN \
		+ "%2s"%amPm(returnHR) + "\n" \
		+ "Time Charged: %5s"%timeCharged(rentHR, returnHR, rentMIN, \
		returnMIN) + "\t" + "Charge: $%7.2f"%calcCost(convertMinutes(rentHR, \
		returnHR, rentMIN, returnMIN), equipCat) + "\n\n\n"

	return invoiceFormat

##############################
#
#	timeCharged
#
#	This function calculates the absolute value difference between the 
#	hour/min out and the hour/min in. Returns a string to the invoice function.
#
##############################

def timeCharged(hourRent, hourReturn, minRent, minReturn):

	chargeHour = str(abs(hourRent - hourReturn))
	chargeMin = abs(minRent - minReturn)

	if chargeMin < 10:
		chargeMin = "0" + str(chargeMin)
	else:
		chargeMin = str(abs(minRent - minReturn))

	totalTime = chargeHour + ":" + chargeMin

	return totalTime

##############################
#
#	convertMinutes
#
#	This function converts the difference in hour out/in to minutes and adds
#	this value to the difference in min out/in. This function is used in the
#	calcCost function.
#
##############################

def convertMinutes(timeOutHR, timeInHR, timeOutMIN, timeInMIN):
    
    diffHour = abs(timeInHR - timeOutHR) * 60
    diffMinute = abs(timeInMIN - timeOutMIN)
    totalMinutes = diffHour + diffMinute

    return totalMinutes

##############################
#
#	calcCost
#
#	This function converts the total minutes to hours as a float. Calculates
#	the cost of rental based on the equipment category multiplied by the hours.
#	Returns the total cost rounded to 2 decimal places to the invoice function.
#
##############################

def calcCost(totalMinutes, category):

    totalCost = 0
    hours = totalMinutes / 60

    if category == 1:
        totalCost = hours * 7.50
    elif category == 2:
        totalCost = hours * 12.00
    elif category == 3:
        totalCost = hours * 18.00
    
    return round(totalCost, 2)

##############################
#
#	convert24hr
#
#	This function converts the hour rented out from 24hr to standard time if 
#	past 12:00 PM. Returns the converted hour to the invoice function.
#
##############################

def convert24hr(hourToConvert):

	hourStandard = 0
	if hourToConvert > 12:
		hourStandard = hourToConvert - 12

	else:
		hourStandard = hourToConvert

	return hourStandard

##############################
#
#	amPm
#
#	This function determines if the input hour is "AM" or "PM".  Returns the
#	appropriate identifier to the invoice function.
#
##############################

def amPm(h):

	s = " AM"
	if h >= 12:
		s = " PM"
	return s

main()
		
