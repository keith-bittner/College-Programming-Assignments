import java.util.Comparator;
import java.util.Arrays;

/**
* <h1>Term.java</h1> 
* This is the implementation for the Term API. This class implements Comparable.
* and Comparator. Term.java is an immutable data type that represents one
* AutoComplete term. A term is comprised of 2 parts: a query string;
* an associated integer weight.
*
* @author Keith Bittner
* @version 1.0
* @since 2020-02-27
*/
public class Term implements Comparable<Term> {

	//Declaring class variables.
	private String query;
	private long weight;

	/**
	* This method initializes a Term object with the given query string and
	* weight.
	*
	* @param query The String name of the term
	* @param weight The long integer value associated with the query
	* @exception NullPointerException On query string being a null value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	* NullPointerException</a>
	* @exception IllegalArgumentException On weight being a negative value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html">
	* IllegalArgumentException</a>
	*/
	public Term(String query, long weight) {

		//Check if query is null.
		if (query == null) {
			throw new NullPointerException("Error: query can not be null.");
		} else {
			this.query = query;
		}

		//Check is weight is not negative.
		if (weight < 0) {
			throw new IllegalArgumentException("Error: weight can not be negative.");
		} else {
			this.weight = weight;
		}
	}

	/**
	* This method compares 2 Term objects in decending order by weight.
	*
	* @return Comparator This returns the result of the inner class
	* ComparatorByReverseOrderWeight()
	*/
	public static Comparator<Term> byReverseWeightOrder() {

		return (new ComparatorByReverseOrderWeight());
	}

	/**
	* This method compares 2 Term objects in lexicographic order by using only
	* the first character of each query.
	*
	* @param r number of characters to compare
	* @return Comparator This returns the result of the inner class
	* ComparatorByPrefixOrderQuery(r)
	* @exception IllegalArgumentException On weight being a negative value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html">
	* IllegalArgumentException</a>
	*/
	public static Comparator<Term> byPrefixOrder(int r) {

		//Check if r is not negative.
		if (r < 0) {
			throw new IllegalArgumentException("Error: r can not be negative.");
		} else {
			return (new ComparatorByPrefixOrderQuery(r));
		}
	}

	/**
	* This method is used to compare two Term objects recursively.
	*
	* @param that The Term object comparing to.
	* @return int This returns 0 if same, -1 if less, 1 if greater
	*/
	@Override
	public int compareTo(Term that) {

		return (this.query.compareTo(that.query));
	}

	/**
	* This method returns a string representation of a Term object in the
	* following format: weight + tab + query.
	*
	* @return String the string representation of a Term object
	*/
	@Override
	public String toString() {

		return (weight + "\t" + query);
	}

/**
* This the comparator class for the Term API that compares two Term objects
* in reverse order by their associated weights. This implements Comparator.
*
* @author Keith Bittner
* @version 1.0
* @since 2020-02-27
*/
private static class ComparatorByReverseOrderWeight implements Comparator<Term> {

	/**
	* This method is used to compare 2 Term objects by weight.
	*
	* @param a The first Term object
	* @param b The second Term object
	* @return int This returns 0 if same, -1 if less, 1 if greater
	*/
	@Override
	public int compare(Term a, Term b) {

		if (a.weight == b.weight) {
			return 0;
		}
		if (a.weight > b.weight) {
			return -1;
		}
		return 1;
	}
}

/**
* This the comparator class for the Term API that compares two Term objects
* in order of their beginning characters. This implements Comparator.
*
* @author Keith Bittner
* @version 1.0
* @since 2020-02-27
*/
private static class ComparatorByPrefixOrderQuery implements Comparator<Term> {

	//Declaring class variable.
	private int r;

	/**
	* This method initializes the number of characters to be compared.
	*
	* @param r The number of characters to be compared
	*/		
	public ComparatorByPrefixOrderQuery(int r) {

		this.r = r;
	}

	/**
	* This method compares 2 Term objects in order of characters based on the
	* number of characters to compare.
	*
	* @param a The first Term object
	* @param b The second Term object
	* @return int This returns the number of matching characters.
	*/
	@Override 
	public int compare(Term a, Term b) {

		String prefixA;
		String prefixB;

		//Let's set prefixA to the characters of Term a from 0 to r.
		if (a.query.length() < r) {
			prefixA = a.query;
		} else {
			prefixA = a.query.substring(0, r);
		}	

		//Let's set prefixB to the characters of Term b from 0 to r.
		if (b.query.length() < r) {
			prefixB = b.query;
		} else {
			prefixB = b.query.substring(0, r);
		}
		return (prefixA.compareTo(prefixB));
	}
}

	/**
	* This is the main method used to test the implementation of the Term API.
	*
	* @param args The command line argument array
	*/
	public static void main(String[] args) {

		//Test cases no longer needed and removed.
	}
}
