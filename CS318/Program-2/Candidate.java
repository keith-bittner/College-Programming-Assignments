/**
* <h1>Candidate.java</h1> 
* This is the ADT for a Candidate object.  This ADT implements Comparable.
*
* @author Keith Bittner
* @version 1.3
* @since 2020-02-13
*/
public class Candidate implements Comparable <Candidate> {

		//declaring class variables
		private String firstName;
		private String lastName;
		private int age;
		private String party;
		private char[] sortOrder;

	/**
	* This method is used to create a Candidate object.
	*
	* @param arr The array storing the sortOrder
	* @param first The candidate's first name
	* @param last The candidate's last name
	* @param age The candidate's age
	* @param party The candidate's party
	*/
	public Candidate (char[] arr, String first, String last, int age, String party) {
		this.firstName = first;
		this.lastName = last;
		this.age = age;
		this.party = party;
		this.sortOrder = arr;
	}

	/**
	* This method is used to get a Candidate's first name.
	*
	* @return String This returns the first name
	*/
	public String getFirst() {
		return this.firstName;
	}

	/**
	* This method is used to get a Candidate's last name.
	*
	* @return String This returns the last name
	*/
	public String getLast() {
		return this.lastName;
	}

	/**
	* This method is used to get a Candidate's age.
	*
	* @return int This returns the age
	*/
	public int getAge() {
		return this.age;
	}

		/**
	* This method is used to get the sorting order for a Candidate.
	*
	* @return char[] This is the sortOrder array
	*/
	public char[] getSortOrder() {
		return this.sortOrder;
	}

	/**
	* This method is used to determine if two Candidates are the same.
	*
	* @param other
	* @return boolean This returns true or false
	*/
	@Override
	public boolean equals(Object other) {
		Candidate that = (Candidate) other;
		return ((firstName.equalsIgnoreCase(that.firstName))
				&& (lastName.equalsIgnoreCase(that.lastName))
				&& (age == that.age) && (party.equalsIgnoreCase(that.party)));
	}

	/**
	* This method is used to compare two Candidate's ages.
	*
	* @param that The candidate we want to compare to
	* @return int This returns 0 if same, -1 if less, 1 if greater
	*/
	@Override
	public int compareTo(Candidate that) {
		if (age == that.age) {
			return 0;
		} else if (age < that.age) {
			return -1;
		}
		return 1;
	}
	
	/**
	* This method is used to print a Candidate object.
	*
	* @return String This returns the Candidates information
	*/
	@Override
	public String toString() {
		return firstName + ", " + lastName + ", " + age + ", " + party;
	}
}
