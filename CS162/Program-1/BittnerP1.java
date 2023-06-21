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

/*
	Revision 1

	Added loadDataBase method to allow entry of order and customer information
	into their appropriate arrays.  Added showAll method to allow a user to
	see all actual data entered into arrays while not displaying any 'null'
	data.  Added search method to allow user to search for a specific order
	while specifing an order # of '0' as not listed and not returning the
	customer name as null.  Used .printf to format console output for ease of
	viewing.

	Contributing programmer: Keith Bittner
	Date:                    January 21, 2019

*/
import javax.swing.*;

public class BittnerP1
{
	public static void main (String [] args)
	{
		int [] orderNumber = new int [5];
		String [] custName = new String [5];
		int [] pizzaChoice = new int [5];
		// end is index after last used position

		int end = loadDataBase (orderNumber, custName, pizzaChoice); 

		if (end > 0)		// there is something to work with
		{
			databaseSort (orderNumber, custName, pizzaChoice, end);			
			processTransactions (orderNumber, custName, pizzaChoice, end);
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
	static void processTransactions(int [] order, String[] name, int [] pizza, int last)
	{
		String choice = JOptionPane.showInputDialog(null,
			"Please enter \n'L' to Look up an order number,\n\t" +
			"'S' to Show all orders, or\n\t'Q' to end");
		while ( !(choice.equalsIgnoreCase("Q") ) )
		{
			if (choice.equalsIgnoreCase("S") )
			{
				showAll( order, name, pizza, last );
			}
			else if (choice.equalsIgnoreCase("L") )
			{
				lookUpOrder ( order, name, pizza, last );
			}
			choice = JOptionPane.showInputDialog(null,
				"Please enter \n'L' to Look up an order number\n\t" +
				"'S' to Show all orders, or\n\t'Q' to end");
		}  // end while
	}  // end processTransactions method

	/*	lookUpOrder

		Given int and String arrays storing order data, and the number 
		of positions of the arrays that are in use, ask the user to enter 
		an order number to look up. If the entry is not null, call a search
		method to locate the value and	show database entries for the 
		position if it was found or report the failure.
	*/
	static void lookUpOrder(int[] order, String[] name, int [] pizza, int last)
	{
		int searchOrder;		
		String customer;
		String [] pizName = {"Cheese", "Pepperoni", "Sausage & Mushroom",
			"Marlon's Special", "Custom"};

		String inputValue = JOptionPane.showInputDialog ( null,
			"Enter the order you want to look up ('cancel' to skip)");
		
		if (inputValue != null)		// look for it
		{
			searchOrder = Integer.parseInt (inputValue);

			int position = search (last, order, searchOrder);

			if (position >= 0)	// success
			{
				customer = name [position];

				JOptionPane.showMessageDialog(null,"Order: "+searchOrder
					+ "   Customer: " + customer + "Pizza: " 
					+ pizName[pizza[position]]);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Order " + inputValue +
					" is not listed");
			}  // end if
		} // end if
	} // end lookUpOrder

	/* databaseSort

		Sorts both arrays
	*/
	static void databaseSort(int [] orderNumber, String [] custName, int [] pizzaChoice, int last)
	{
		int temp1 = 0;
		String temp2 = "";
		int temp3 = 0;

		for (int k = 0; k < (last - 1); k++)
		{
			for (int s = k + 1; s < last; s++)
			{
				if (orderNumber[k] > orderNumber[s])
				{
					temp1 = orderNumber[k];
					temp2 = custName[k];
					temp3 = pizzaChoice[k];
					orderNumber[k] = orderNumber[s];
					custName[k] = custName[s];
					pizzaChoice[k] = pizzaChoice[s];
					orderNumber[s] = temp1;
					custName[s] = temp2;
					pizzaChoice[s] = temp3;
				}
			}
		}
	}

	/* loadDataBase

		User inputs order number and customer name, order number is converted
		to integer and all information is stored into approriate arrays.
		Returns only the index of the last iteration. Will still allow user to
		'Look up order' or 'Show all' even is no data is entered into database.

	*/

	static int loadDataBase(int [] orderNumber, String [] custName, int [] pizzaChoice)
	{
		int i = 0; // iteration variable
		String [] pizzaNames = {"Cheese", "Peeperoni", "Sausage & Mushroom",
			"Marlon's Special", "Custom"};

		String orderInput = JOptionPane.showInputDialog(
			"Please enter the order number (click 'Cancel' when finished)");

		while ( i < orderNumber.length && orderInput != null) // input loop
		{
			orderNumber[i] = Integer.parseInt(orderInput);			
			custName[i] = JOptionPane.showInputDialog(
				"Please enter customer name for order " + orderNumber[i]);
			pizzaChoice[i] = JOptionPane.showOptionDialog(null, 
				"What kind of pizza for " + custName[i], "Pizza Ordered",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				pizzaNames, pizzaNames[0]);
			i++;
			if (i < orderNumber.length) // Check if there is room in array
			{
				orderInput = JOptionPane.showInputDialog(
					"Please enter the next order number (click 'Cancel' when" +
					" finished)");
			} // end array room check
		} // end input loop
		
		return i; // return index of last iteration
	} // end of loadDataBase

	/* showAll

		Builds a report of all order numbers and customer names stored in their
		arrays.  Outputs the report for user viewing. Method disregards any
		null values so if no actual data exists, method outputs empty report.

	*/

	static void showAll (int [] order, String [] name, int [] pizzaChoice, int last)
	{ // Declare static report shell
		String head_1 = "The Godfather's Pizza";
		String head_2 = "Order History";
		String head_3 = "********************************";
		String head_4 = "Order Number";
		String head_5 = "Customer";
		String head_6 = "Pizza";
		String head_7 = "----------";
		String footer = "End of Report";
		String [] menuName = {"Cheese", "Pepperoni", "Sausage & Mushroom",
			"Marlon's Special", "Custom"};

		// Begin showAll output
		System.out.printf("%n %27s %n %23s %n %31s", head_1, head_2, head_3);
		System.out.printf(" %n %15s %11s %7s %n %14s %13s %12s %n", head_4, head_5,
			head_6, head_7, head_7, head_7);

		for (int j = 0; j < last; j++) // loop adds array data into report
		{
			System.out.printf("%10d %17s %24s %n", order[j], name[j], menuName[pizzaChoice[j]]);
		} // end of report loop

		System.out.printf("%n %n %10s", footer);
	} // end of showAll

	/* search

		Searchs for customer name based on order number.  If no value matches
		searchOrder, method returns a '-1' to its method call.

	*/
/*****************************************************************************
Binary Search
******************************************************************************/
	static int search (int last, int [] order, int searchOrder)
	{
		int orderFound = -1;
		int m = last / 2;
		int s = 0;
		int e = last - 1;

		while (s <= e && searchOrder!= order[m])
		{
			if (order[m] > searchOrder)
			{
				e = m -1;
			}
			else if (order[m] < searchOrder)
			{
				s = m + 1;
			}
			m = (s+e)/2;
		}
		if (s > e)
		{
			orderFound = -1;
		}
		else
		{
			orderFound = m;
		}
		return orderFound;
	} //end search
} // end of program
