# bittnerP2.py
# A looping program that keeps culumative total and calculates averages.
# Author: Keith Bittner
# October 19, 2018

def coffeeReport():

# Prints the report header.
	print("______________________________________________________________")
	print("\n\n======== R, P, & P DP Dept. Coffee Consumption Report" 
		+ " ========\n\n")
	print("\t     Hour\t     Regular\t     Decaf")
	print("______________________________________________________________")

# Declaring the 2 variables used for storing culumative total within the loop.
	regularTotal = 0
	decafTotal = 0

# Loop that asks for the user to input a value for 2 inputs over a set range.
# Prints the current hour and user input for current hour.
# Stores a culumative total to pre-declared variables for use later.
	for hour in range(1, 9):
		regularCoffee = int(input("Please enter the number of regular cups" 
			+ " of coffee for hour " + str(hour) + ":"))
		decafCoffee = int(input("Please enter the number of decaf cups of" 
			+ " coffee for hour " + str(hour) + ":"))
		print("\t     ", hour, "\t       ", str(regularCoffee), "\t      ", 
			str(decafCoffee))
		regularTotal = regularTotal + regularCoffee
		decafTotal = decafTotal + decafCoffee

# Finds the average from __Totals divided by last hour in loop.
		regularAvg = regularTotal / hour
		decafAvg = decafTotal / hour

# Prints the total and average footer.
	print("\n\n\tRegular Total: ", str(regularTotal), "cups")
	print("\t\tAverage: ", str(round(regularAvg, 2)), "cups per hour")
	print("\n\tDecaf Total: ", str(decafTotal), "cups")
	print("\t\tAverage: ", str(round(decafAvg, 2)), "cups per hour")
	print("\n\n")

coffeeReport()
