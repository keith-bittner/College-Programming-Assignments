# bittnerP3.py
#
# A program that stores multiple strings into one variable, stores maximum and
#	minimum values, stores culumative total, and calculates average.
#
# Author: Keith Bittner
# Written: November 8, 2018
#
def insurance():

# Declaring variables for use later in program.
# Storing the report header in the "report" variable.
# Ask the user to input first associate name.
	report = "\n====== The Family Insurance Winner and Loser Report ======\n\n"
	name = input("Enter the first associate's name: ")
	nameCount = 0
	premiumMax = 0
	premiumMin = 10 ** 10
	premiumTotal = 0
	maxCollector = ""
	minCollector = ""

# "If" statement to end program when no data is to be entered.
	if name == "DOA":
		print("\n\tNo collections to report this week.\n")

# Main loop of program.
# Asks the user to input collected premium.
# Keeps running count of the total number of names entered.
# Keeps a culumative total of premiums.
# Stores associate name and collected premium as a string in "report" variable.
	while name != "DOA":
		premium = float(input("Enter amount collected by " + name + ": "))
		nameCount = nameCount + 1
		premiumTotal = premiumTotal + premium
		report = report + "%-20s"%name + "%26s"%"$" + "%12.2f"%premium + "\n"

# "If" statement to store the current maximum premium collected.
# Stores associate name that collected the maximum premium.
		if premium > premiumMax:
			premiumMax = premium
			maxCollector = name

# "If" statement to store the current minimum premium collected.
# Stores associate name that collected the minimum premium.
		if premium < premiumMin:
			premiumMin = premium
			minCollector = name

# Asks the user to input the next associates name.
# Returns to the begining of the "while" loop.
		name = input("Please enter the next associate's name: ")

# "If" statement to end the program if no additional names are to be entered.
# Calculates the average amount of collected premiums.
# Prints main report.
# Prints total collected and average premium.
# Prints the winning and losing associate.
# Calculates 20% of maximum premium.
		if name == "DOA":
			averagePremium = premiumTotal / nameCount
			print(report)
			print("Total Collections:" + "%28s"%"$" + "%12.2f"%premiumTotal)
			print("Average:" + "%38s"%"$" + "%12.2f"%averagePremium + "\n")
			print("The winner is", maxCollector, "who collected", "$ "
				+ str(premiumMax), "in premiums.")
			print("The loser is", minCollector, "who only collected", "$ "
				+ str(premiumMin), "in premiums.")
			premiumPercent = premiumMax * .2

# "If" statment that identifies if premiumMin >= 20% of premiumMax.
# Prints two differnet strings depending on "if" being TRUE or FALSE.
			if premiumMin >= premiumPercent:
				print("\t\t---Better luck next time!")
			else:
				print("\t\t---We'll miss him!\n")

insurance()
