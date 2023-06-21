/*	The Godfather's Pizza Company Deliveries Database

	Maintain a database of information about pizzas delivered for The 
	Godfather's Pizza Company.

	This program is being developed in stages. Early stages will
	employ a limited database for testing purposes.

	Initial version: manual input of all information, which includes
	order number (up to six digits) and customer last name. After entry 
	of information, allow user to look up order by number, display all
	information, or quit.

	Programmer:	Sargent Pepperoni
	Date:		01/08/2019
*/

/*	Revision 1

	Added loadDataBase method to allow entry of order and customer information
	into their appropriate arrays. Added showAll method to allow a user to see
	all actual data entered into arrays while not displaying 'null' data. Added
	search method to allow user to search for a specific order and recieving
	customer information. Used .printf to format console output for ease of
	viewing

	Contributing Programmer: Keith Bittner
	Date:					 January 24, 2019

*******************************************************************************
	Revision 2

	Added databaseSort method that performs a selection sort of the database
	arrays.  Replaced the linear search method with a binary search to minimize
	the amount of steps required to locate a selected value in the lookup
	method.

	Contributing Programmer: Keith Bittner
	Date:					 February 1, 2019

*******************************************************************************
	Revision 3

	Added a new array to allow a user to store the pizza choice for a customer
	for future recollection.  Added a new dialog box with buttons that show the
	different pizza choices.  Clicking on a piiza choice will store a numerical
	value to the new array which can be recalled in the showall and lookUp
	methods.

	Contributing Programmer: Keith Bittner
	Date:					 February 7, 2019

*******************************************************************************
	Revision 4

	some text

	Contributing Programmer: Keith Bittner
	Date:					 February 15, 2019
	
*/
import javax.swing.*;

public class P3Bittner
{
	public static void main (String [] args)
	{
		PizzaRec [] myCustomers = new PizzaRec[5];


		// end is index after last used position

		int end = loadDataBase (myCustomers); 

		if (end > 0)		// there is something to work with
		{
			databaseSort (myCustomers, end);
			processTransactions (myCustomers, end);
		}

		System.exit(0);
	}
	
	/*	processTransactions

		Given arrays containing the order numbers and corresponding names,
		and the number of values actually being used, offer the use a 
		choice of actions: showAll values, which calls a method that 
		displays a report of the entire database; Look up an Order Number, 
		which calls a method that lets the user indicate a value to search; 
		and Quit, which termnates processing. 

		Any invalid choice is ignored.
	*/
	static void processTransactions(PizzaRec [] myCustomers, int last)
	{
		String choice = JOptionPane.showInputDialog(null,
			"Please enter \n'L' to Look up an order number,\n\t" +
			"'S' to Show all orders, or\n\t'Q' to end");

		while ( !(choice.equalsIgnoreCase("Q") ) )
		{
			if (choice.equalsIgnoreCase("S") )
			{
				showAll( myCustomers, last );
			}
			else if (choice.equalsIgnoreCase("L") )
			{
				lookUpOrder ( myCustomers, last );
			}
			choice = JOptionPane.showInputDialog(null,
				"Please enter \n'L' to Look up an order number\n\t" +
				"'S' to Show all orders, or\n\t'Q' to end");
		}  // end while
	}  // end processTransactions method

	/*	lookUpOrder

		Given int and String arrays storing oder data, and the number 
		of positions of the arrays that are in use, ask the user to enter 
		an order number to look up. If the entry is not null, call a search
		method to locate 	Added 1/20/2019 by Mo Tsarella
		the value and	show database entries for the 
		position if it was found or report the failure.
	*/
	static void lookUpOrder(PizzaRec [] myCustomers, int last)
	{		
		int searchOrder;

		String inputValue = JOptionPane.showInputDialog ( null,
			"Enter the order you want to look up ('cancel' to skip)");
		
		if (inputValue != null)		// look for it
		{
			searchOrder = Integer.parseInt (inputValue);

			int position = search (last, myCustomers, searchOrder);

			if (position >= 0)	// success
			{
				JOptionPane.showMessageDialog(null, myCustomers[position].toString());
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Order " + searchOrder +
					" is not listed");
			}  // end if
		} // end if
	} // end lookUpOrder

	/* loadDataBase

		User inputs order number and customer name, order number is converted
		to integer and all information is stored into appropriate arrays.
		Returns only the index of the last iteration. Program will exit and not
		crash if user hits 'Cancel' before any information is entered.
	*/
	static int loadDataBase(PizzaRec [] myCustomers)
	{
		int orderNumber;
		String custName;
		int pizzaChoice;		
		int i = 0; // iteration variable
		String [] pizzaNames = {"Cheese", "Pepperoni", "Sausage & Mushroom",
			"Marlon's Special", "Custom"};

		String orderInput = JOptionPane.showInputDialog(
			"Please enter the order number (click 'Cancel' when finished)");

		while (i < myCustomers.length && orderInput != null) // input loop
		{
			orderNumber = Integer.parseInt(orderInput);
			custName = JOptionPane.showInputDialog(
				"Please enter customer name for order " + orderNumber);
			pizzaChoice = JOptionPane.showOptionDialog(null,
				"What kind of pizza for " + custName, "Pizza Ordered",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				pizzaNames, pizzaNames[0]);

			myCustomers[i] = new PizzaRec(orderNumber, custName, pizzaChoice);
			
			i++;

			if (i < myCustomers.length) // Check if there is room in array
			{
				orderInput = JOptionPane.showInputDialog(
					"Please enter the next order number (click 'Cancel' when" +
					" finished)");
			} //end of array room check
		} // end of input loop

		return i; // return index of last iteration
	} // end of loadDataBase

	/* databaseSort

		Sorts database arrays according to the orderNumber array.  Provides the
		ability to use a more refined binary search method for the lookUpOrder
		method.
	*/
	static void databaseSort (PizzaRec [] myCustomers, int last)
	{
		for (int i = 0; i < last - 1; i++) // begining of sorting loop
		{
			for (int j = i + 1; j < last; j++) // inner sort loop
			{
				if (myCustomers[i].getOrderNumber() > myCustomers[j].getOrderNumber()) // compare position values
				{
					PizzaRec temp1 = myCustomers[i];
					myCustomers[i] = myCustomers[j];
					myCustomers[j] = temp1;
				} // end of compare and swap
			} // end of inner sort loop
		} // end of outer sort loop
	} // end of databaseSort

	/* showAll

		Builds a report of all order numbers and customer names stored in their
		arrays. Outputs the report for user viewing. Method disregards any null
		values so only actual entered data is displayed and not empty array
		slots.
	*/
	static void showAll(PizzaRec [] myCustomers, int last)
	{
		
		System.out.println("\n\n\n\n               The Godfather's Pizza\n"
			+ "                    Order History"
			+ "\n******************************************************");
			//+ "\nOrder Number        Customer            Pizza"
			//+ "\n-----------         --------      --------------------");

		for (int i = 0; i < last; i++) // loop adds array data into report
		{
			System.out.printf("\n   " + myCustomers[i].toString());
		} // end of report loop

		System.out.printf("\n\n\tEnd of Report\n\n");
	} // end of showAll

	/* search

		Binary search method for the lookUpOrder method.  Cuts down the number
		of steps the program makes in order to locate a value in the order
		array.  If order number is found it returns the position for the name
		array.  If the order number is not found it returns a '-1' to the call.
	*/
	static int search(int last, PizzaRec [] myCustomers, int searchOrder)
	{ // declaring search variables
		int orderStart = 0;
		int orderMid = last / 2;
		int orderEnd = last - 1;
		int orderFound = -1;
		/* while loop will run until the order number is found or my start
			position is greater then my end position */
		while (searchOrder != myCustomers[orderMid].getOrderNumber() && orderStart <= orderEnd)
		{
			if (myCustomers[orderMid].getOrderNumber() > searchOrder)
			{
				orderEnd = orderMid - 1;
			}
			else if (myCustomers[orderMid].getOrderNumber() < searchOrder)
			{
				orderStart = orderMid + 1;
			}
			orderMid = (orderStart + orderEnd) / 2;
		} // end of while loop
		if (orderStart > orderEnd)
		{
			orderFound = -1;
		}
		else
		{
			orderFound = orderMid;
		}
		return orderFound;
	} // end of search
} // end of program
	
		
