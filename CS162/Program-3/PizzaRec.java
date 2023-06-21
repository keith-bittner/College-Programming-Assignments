public class PizzaRec
{
	private int orderNumber;
	private String customerName;
	private int pizzaChoice;

	public PizzaRec(int order, String customer, int pizza)
	{
		this.orderNumber = order;
		this.customerName = customer;
		this.pizzaChoice = pizza;
	}

	public int getOrderNumber()
	{
		return this.orderNumber;
	}

	public String getCustomerName()
	{
		return this.customerName;
	}

	public int getPizzaChoice()
	{
		return this.pizzaChoice;
	}

	public void setOrderNumber(int newOrder)
	{
		this.orderNumber = newOrder;
	}

	public void setCustomerName(String newCustomer)
	{
		this.customerName = newCustomer;
	}

	public void setPizzaChoice(int newPizzaChoice)
	{
		this.pizzaChoice = newPizzaChoice;
	}

	public String toString()
	{
		String [] pizzaNames = {"Cheese", "Pepperoni", "Sausage & Mushroom",
			"Marlon's Special", "Custom"};

		return "Order: " + this.orderNumber + "    Name: " + this.customerName
			+ "    Pizza: "+ pizzaNames[this.pizzaChoice];
	}
}
