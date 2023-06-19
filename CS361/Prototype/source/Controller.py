################################################################################
#
#	Controller.py - Vending Machine Control System Simulator
#
#	This program was designed to simulate the software behind a Vending Machine
#	Control System that communicates with the various hardware included inside
#	a Vending Machine. It simulates both customers and service technician
#	operations.
#
#	Customer operations include inserting currency (coins and/or bills), picking
#	a product, dispensing product, retrieving product, retrieving change, and
#	cancelling a transaction.
#
#	Service technician operations include, putting the machine into service,
#	taking the machine out of service, refilling the inventory, resetting the
#	coin/bill acceptors, emptying the coin overflow bin, and changing a products
#	price.
#
#	Author:		Keith Bittner
#	Version:	1.2
#	Date:		3-15-2020
#
################################################################################

#importing tkinter for GUI
from tkinter import *
import tkinter as tk

#currency class
class Currency:

	def __init__(self, name, category, value, quantity):
	
		self.name = name
		self.category = category
		self.value = value
		self.quantity = quantity

#inventory item class
class Inventory:

	def __init__(self, name, price, quantity):

		self.name = name
		self.price = price
		self.quantity = quantity

#main vending machine controller
class Controller:

	def __init__(self, parent):

		#create tech pin and price change variables
		self.techPin = "#0000"
		self.keyInput = ""
		self._pID = ""
		self.newPrice = 0.00

		#create the coin repo
		self.repository = {}
		self.isValid = True
		self.isFull = True
		self.isLow = False

		#build inventory
		self.inventory = {}

		#inserted payment, change to dispense, overflow bin
		self.coinPayment = 0
		self.billPayment = 0
		self.payment = 0
		self.change = 0
		self.overflow = 0

		#create GUI
		self.parent = parent
		self.parent.wm_title("Prototype: Vending Machine Controller")

		#display message
		self.message = ""

		#product buttons
		self.button1 = tk.Button(parent, text = "Product 1", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("1"))
		self.button2 = tk.Button(parent, text = "Product 2", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("2"))
		self.button3 = tk.Button(parent, text = "Product 3", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("3"))
		self.button4 = tk.Button(parent, text = "Product 4", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("4"))
		self.button5 = tk.Button(parent, text = "Product 5", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("5"))
		self.button6 = tk.Button(parent, text = "Product 6", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("6"))
		self.button7 = tk.Button(parent, text = "Product 7", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("7"))
		self.button8 = tk.Button(parent, text = "Product 8", state = DISABLED, width = 15, height = 2, command = lambda: self.invSelect("8"))

		self.button1.grid(row = 0, column = 0)
		self.button2.grid(row = 0, column = 1)
		self.button3.grid(row = 1, column = 0)
		self.button4.grid(row = 1, column = 1)
		self.button5.grid(row = 2, column = 0)
		self.button6.grid(row = 2, column = 1)
		self.button7.grid(row = 3, column = 0)
		self.button8.grid(row = 3, column = 1)

		#display screen
		self.display = tk.Text(parent, width = 30, height = 2)
		self.display.grid(row = 0, column = 2, columnspan = 2)

		#coin acceptor
		self.button9 = tk.Button(parent, text = "Insert Coins", state = DISABLED, width = 15, height = 2, command = lambda: self.insertCoin())
		self.button9.grid(row = 1, column = 2)

		#bill acceptor
		self.button10 = tk.Button(parent, text = "Insert Bills", state = DISABLED, width = 15, height = 2, command = lambda: self.insertBill())
		self.button10.grid(row = 1, column = 3)

		#change return
		self.button11 = tk.Button(parent, text = "Change Return", state = DISABLED, width = 15, height = 2, command = lambda: self.returnPay())
		self.button11.grid(row = 2, column = 2)

		#coin return tray
		self.coinTray = tk.Text(parent, width = 15, height = 2)
		self.coinTray.grid(row = 3, column = 2)

		#technician keypad
		techPad = tk.Button(parent, text = "Service", width = 15, height = 4, command = lambda: self.serviceMode())
		techPad.grid(row = 2, column = 3, rowspan = 2)

		#product dispenser
		self.dispenser = tk.Text(parent, width = 30, height = 2)
		self.dispenser.grid(row = 4, column = 0, columnspan = 2)

		#collect product and change
		self.collect = tk.Button(parent, text = "Collect", state = DISABLED, width = 10, height = 2, command = lambda: self.clearSys())
		self.collect.grid(row = 4, column = 2)

	#fill the machine with currency
	def makeCurrency(self):

		self.repository["0"] = Currency("$5", "Bill", 5.00, 0)
		self.repository["1"] = Currency("$1", "Bill", 1.00, 0)
		self.repository["2"] = Currency("$1", "Coin", 1.00, 200)
		self.repository["3"] = Currency("$.25", "Coin", .25, 200)
		self.repository["4"] = Currency("$.10", "Coin", .10, 200)
		self.repository["5"] = Currency("$.05", "Coin", .05, 200)

	#fills the inventory with default stock and prices
	def fillInventory(self):

		self.inventory["1"] = Inventory("Product 1", 3.00, 5)
		self.inventory["2"] = Inventory("Product 2", 2.75, 5)
		self.inventory["3"] = Inventory("Product 3", 2.50, 5)
		self.inventory["4"] = Inventory("Product 4", 2.25, 5)
		self.inventory["5"] = Inventory("Product 5", 2.00, 5)
		self.inventory["6"] = Inventory("Product 6", 1.75, 5)
		self.inventory["7"] = Inventory("Product 7", 1.50, 5)
		self.inventory["8"] = Inventory("Product 8", 1.25, 5)

	#insert coins
	def insertCoin(self):

		self.win = tk.Toplevel()
		self.win.wm_title("Insert Coins")

		button12 = tk.Button(self.win, text = "$1.00", width = 5, height = 2, command = lambda: self.insertPay("2"))
		button13 = tk.Button(self.win, text = "$0.25", width = 5, height = 2, command = lambda: self.insertPay("3"))
		button14 = tk.Button(self.win, text = "$0.10", width = 5, height = 2, command = lambda: self.insertPay("4"))
		button15 = tk.Button(self.win, text = "$0.05", width = 5, height = 2, command = lambda: self.insertPay("5"))
		button16 = tk.Button(self.win, text = "Invalid Coin", width = 10, height = 1, command = lambda: self.insertPay("Bad"))
		button12.grid(row = 0,column = 0)
		button13.grid(row = 0,column = 1)
		button14.grid(row = 1,column = 0)
		button15.grid(row = 1,column = 1)
		button16.grid(row = 2, column = 0, columnspan = 2)

	#insert bills
	def insertBill(self):

		self.win = tk.Toplevel()
		self.win.wm_title("Insert Bills")

		button17 = tk.Button(self.win, text = "$5.00", width = 5, height = 2, command = lambda: self.insertPay("0"))
		button18 = tk.Button(self.win, text = "$1.00", width = 5, height = 2, command = lambda: self.insertPay("1"))
		button19 = tk.Button(self.win, text = "Invalid Bill", width = 10, height = 1, command = lambda: self.insertPay("Bad"))
		button17.grid(row = 0,column = 0)
		button18.grid(row = 0,column = 1)
		button19.grid(row = 2, column = 0, columnspan = 2)

	#take payment method
	def insertPay(self, cID):

		self.display.delete(1.0, "end-1c")
		if (cID == "Bad"):
			self.isValid = False
		else:
			self.isValid = True

		if (self.isValid):
			self.payment = self.payment + self.repository[cID].value
			self.display.insert("end-1c", str(round(self.payment, 3)))
			if (self.repository[cID].category == "Coin"):
				self.coinPayment = self.coinPayment + self.repository[cID].value
				if (self.repository[cID].quantity == 200):
					self.overflow = self.overflow + self.repository[cID].value
				else:
					self.repository[cID].quantity = self.repository[cID].quantity + 1
			if (self.repository[cID].category == "Bill"):
				if (self.repository[cID].quantity == 300):
					self.display.insert("end-1c", "Exact Change Only")
				if ((self.repository["0"].quantity + self.repository["1"].quantity) == 300):
					self.display.insert("end-1c", "Exact Change Only")
				else:
					self.billPayment = self.billPayment + self.repository[cID].value

		if (self.payment >= 3.00):
			self.button9['state'] = DISABLED
			self.button10['state'] = DISABLED
			self.win.destroy()
			self.win.update()

	#select product to purchase
	def invSelect(self, pID):

		pID = pID
		self.display.delete(1.0, "end-1c")

		if (self.payment < self.inventory[pID].price):
			self.display.insert("end-1c", str(self.inventory[pID].price))
		else:
			if (self.inventory[pID].quantity == 0):
				self.display.delete(1.0, "end-1c")
				self.display.insert("end-1c", "Sold Out")
			if (self.inventory[pID].quantity > 0):
				self.invDispense(pID)

	#dispense selected product
	def invDispense(self, pID):

		self.coinTray.delete(1.0, "end-1c")
		self.inventory[pID].quantity = self.inventory[pID].quantity - 1
		self.change = self.payment - self.inventory[pID].price
		self.coinTray.insert("end-1c", str(round(self.change, 2)))
		self.dispenser.insert("end-1c", self.inventory[pID].name)

		self.button1['state'] = DISABLED
		self.button2['state'] = DISABLED
		self.button3['state'] = DISABLED
		self.button4['state'] = DISABLED
		self.button5['state'] = DISABLED
		self.button6['state'] = DISABLED
		self.button7['state'] = DISABLED
		self.button8['state'] = DISABLED

		self.button11['state'] = DISABLED
		self.collect['state'] = NORMAL

	#retrieve product and/or change
	def clearSys(self):

		self.payment = 0
		self.display.delete(1.0, "end-1c")
		self.coinTray.delete(1.0, "end-1c")
		self.dispenser.delete(1.0, "end-1c")
		self.button9['state'] = NORMAL
		self.button10['state'] = NORMAL

		self.button1['state'] = NORMAL
		self.button2['state'] = NORMAL
		self.button3['state'] = NORMAL
		self.button4['state'] = NORMAL
		self.button5['state'] = NORMAL
		self.button6['state'] = NORMAL
		self.button7['state'] = NORMAL
		self.button8['state'] = NORMAL

		self.button11['state'] = NORMAL
		self.collect['state'] = DISABLED

	#cancell transaction without purchase
	def returnPay(self):

		self.collect['state'] = NORMAL
		self.display.delete(1.0, "end-1c")
		self.coinTray.delete(1.0, "end-1c")
		self.coinTray.insert("end-1c", str(round(self.payment, 2)))

	#build the service tech mode
	def serviceMode(self):

		self.win = tk.Toplevel()
		self.win.wm_title("Service Mode")
		self.frame1 = Frame(self.win, width = 15, height = 2)
		self.frame1.grid(row = 3, column = 0)

		self.button20 = tk.Button(self.win, text = "Start Machine", state = DISABLED, width = 15, height = 2, command = lambda: self.fillMachine())
		self.button21 = tk.Button(self.win, text = "Stop Machine", state = DISABLED, width = 15, height = 2, command = lambda: self.closeMachine())
		self.button22 = tk.Button(self.win, text = "Restock", width = 15, height = 2, command = lambda: self.invRefill())
		self.button23 = tk.Button(self.win, text = "Enter Pin", width = 15, height = 2, command = lambda: self.enterPin())
		self.label1 = tk.Label(self.win, text = "Product Price Change")
		self.button24 = tk.Button(self.frame1, text = "Select Product", wraplength = 45, state = DISABLED, width = 7, height = 2, command = lambda: self.pickProduct())
		self.button25 = tk.Button(self.frame1, text = "Select Price", wraplength = 45, state = DISABLED, width = 7, height = 2, command = lambda: self.pickPrice())
		self.button26 = tk.Button(self.win, text = "Change Price", state = DISABLED, width = 15, height = 2, command = lambda: self.changePrice())

		self.button20.grid(row = 0, column = 0)
		self.button21.grid(row = 1, column = 0)
		self.button22.grid(row = 0, column = 1)
		self.button23.grid(row = 1, column = 1)
		self.label1.grid(row = 2, column = 0, columnspan = 2)
		self.button24.grid(row = 0, column = 0)
		self.button25.grid(row = 0, column = 1)
		self.button26.grid(row = 3, column = 1)

	#put the machine into service
	def fillMachine(self):

		self.makeCurrency()
		self.fillInventory()
		self.overflow = 0

		self.button1['state'] = NORMAL
		self.button2['state'] = NORMAL
		self.button3['state'] = NORMAL
		self.button4['state'] = NORMAL
		self.button5['state'] = NORMAL
		self.button6['state'] = NORMAL
		self.button7['state'] = NORMAL
		self.button8['state'] = NORMAL
		self.button9['state'] = NORMAL
		self.button10['state'] = NORMAL
		self.button11['state'] = NORMAL
		self.collect['state'] = DISABLED

		self.win.destroy()
		self.win.update()

	#refill machine inventory
	def invRefill(self):

		self.fillInventory()
		self.win.destroy()
		self.win.update()

	#put machine out of service
	def closeMachine(self):

		self.button1['state'] = DISABLED
		self.button2['state'] = DISABLED
		self.button3['state'] = DISABLED
		self.button4['state'] = DISABLED
		self.button5['state'] = DISABLED
		self.button6['state'] = DISABLED
		self.button7['state'] = DISABLED
		self.button8['state'] = DISABLED
		self.button9['state'] = DISABLED
		self.button10['state'] = DISABLED
		self.button11['state'] = DISABLED
		self.collect['state'] = DISABLED
		self.win.destroy()
		self.win.update()

	#build the service tech keypad
	def keyPad(self):

		self.win1 = tk.Toplevel()
		self.win1.wm_title("Key Pad")

		_1button = tk.Button(self.win1, text = "1", width = 4, height = 2, command = lambda: self.isPressed("1"))
		_2button = tk.Button(self.win1, text = "2", width = 4, height = 2, command = lambda: self.isPressed("2"))
		_3button = tk.Button(self.win1, text = "3", width = 4, height = 2, command = lambda: self.isPressed("3"))
		_4button = tk.Button(self.win1, text = "4", width = 4, height = 2, command = lambda: self.isPressed("4"))
		_5button = tk.Button(self.win1, text = "5", width = 4, height = 2, command = lambda: self.isPressed("5"))
		_6button = tk.Button(self.win1, text = "6", width = 4, height = 2, command = lambda: self.isPressed("6"))
		_7button = tk.Button(self.win1, text = "7", width = 4, height = 2, command = lambda: self.isPressed("7"))
		_8button = tk.Button(self.win1, text = "8", width = 4, height = 2, command = lambda: self.isPressed("8"))
		_9button = tk.Button(self.win1, text = "9", width = 4, height = 2, command = lambda: self.isPressed("9"))
		_10button = tk.Button(self.win1, text = "#", width = 4, height = 2, command = lambda: self.isPressed("#"))
		_11button = tk.Button(self.win1, text = "0", width = 4, height = 2, command = lambda: self.isPressed("0"))
		_12button = tk.Button(self.win1, text = ".", width = 4, height = 2, command = lambda: self.isPressed("."))
		_13button = tk.Button(self.win1, text = "Enter", width = 8, height = 2, command = lambda: self.isPressed("Enter"))

		_1button.grid(row = 0, column = 0)
		_2button.grid(row = 0, column = 1)
		_3button.grid(row = 0, column = 2)
		_4button.grid(row = 1, column = 0)
		_5button.grid(row = 1, column = 1)
		_6button.grid(row = 1, column = 2)
		_7button.grid(row = 2, column = 0)
		_8button.grid(row = 2, column = 1)
		_9button.grid(row = 2, column = 2)
		_10button.grid(row = 3, column = 0)
		_11button.grid(row = 3, column = 1)
		_12button.grid(row = 3, column = 2)
		_13button.grid(row = 4, columnspan = 3)

	#enter service tech pin to access options
	def enterPin(self):

		if (self.keyInput == self.techPin):
			self.keyInput = ""
			self.button20['state'] = NORMAL
			self.button21['state'] = NORMAL
			self.button24['state'] = NORMAL
			self.button25['state'] = NORMAL
			self.button26['state'] = NORMAL
		else:
			self.keyPad()

	#is a keypad button pressed
	def isPressed(self, pressed):

		if (pressed == "Enter"):
			self.win1.destroy()
			self.win1.update()
		else:
			self.keyInput = self.keyInput + pressed

	#change the product price
	def changePrice(self):

		self.inventory[self._pID].price = self.newPrice

	#choose product to change price
	def pickProduct(self):

		if (self.keyInput != ""):
			self._pID = self.keyInput
			self.keyInput = ""
		else:
			self.keyPad()

	#choose new price for product
	def pickPrice(self):

		if (self.keyInput != ""):
			self.newPrice = float(self.keyInput)
			self.keyInput = ""
		else:
			self.keyPad()





#run the application
root = tk.Tk()
vending = Controller(root)
root.mainloop()

