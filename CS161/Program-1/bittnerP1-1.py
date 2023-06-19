# bittnerP1.py
# A simple arthmetic program to find the value of a base raised to a power of 1-8
# Author: Keith Bittner
# October 11, 2018

# This is a cleaner version of the following program I would prefer to use.
#def main ():
#	b = int (input ("Please enter a base value: "))
#	for i in range (1, 9, 1):
#		print (b, "to the", i, "power is", b ** i)
#main ()

def main ():

# Stores a user input as a string to variable "base".

	base = input ("Please enter a base value: ")

# Stores the integar 1 to the variable powInt.

	powInt = 1

# Converts "base" from a string to integer.
# Multiplies the values from "base" and "powInt".
# Stores value to the variable "answer".

	answer = int(base) * powInt

# Prints a specified string.

	print (base, "to the power of", powInt, "is: ", answer)

# Increases "powInt" by 1 and stores it as the new value to the variable.

	powInt = powInt + 1

# Converts "base" from a string to integar.
# Multiplies the values from "base" and "answer".
# Stores the new value to the variable.

	answer = answer * int(base)

# Rest of program repeats itself until all values are calculated and displayed.

	print (base, "to the power of", powInt, "is: ", answer)

	powInt = powInt +1
	answer = answer * int(base)
	print (base, "to the power of", powInt, "is: ", answer)

	powInt = powInt +1
	answer = answer * int(base)
	print (base, "to the power of", powInt, "is: ", answer)

	powInt = powInt +1
	answer = answer * int(base)
	print (base, "to the power of", powInt, "is: ", answer)

	powInt = powInt +1
	answer = answer * int(base)
	print (base, "to the power of", powInt, "is: ", answer)

	powInt = powInt +1
	answer = answer * int(base)
	print (base, "to the power of", powInt, "is: ", answer)

	powInt = powInt +1
	answer = answer * int(base)
	print (base, "to the power of", powInt, "is: ", answer)
main ()
