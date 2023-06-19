import java.util.Arrays;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

/**
* <h1>Autocomplete.java</h1> 
* This is the implementation for the BinarySearchDeluxe API. This class
* implements Comparable. BinarySearchDeluxe is used when searching a sorted
* array that contains more than one key equal to the search key.
*
* @author Keith Bittner
* @version 1.0
* @since 2020-02-27
*/
public class Autocomplete {

	//Initialize an array of Terms.
	private Term[] terms;

	/**
	* This method initializes the data structure from the given array of terms.
	* Performs a Quicksort to organize the array for use.
	*
	* @param terms The array of Term objects
	* @exception NullPointerException On terms being a null value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	* NullPointerException</a>
	*/
	public Autocomplete(Term[] terms) {

		//Check if terms is null.
		if (terms == null) {
			throw new NullPointerException("Terms can not be null.");
		}

		//Make a new Term object array.
		this.terms = new Term[terms.length];

		//Fill the array with the objects.
		for (int i = 0; i < terms.length; i++) {
			this.terms[i] = terms[i];
		}

		//Sort the array for use.
		Quick.sort(this.terms);
	}

	/**
	* This method finds all Term objects in the array that match the prefix.
	*
	* @param prefix The String of characters to match
	* @return Term[] Returns the matching Term objects in descending order by
	* weight.
	* @exception NullPointerException On the prefix being a null value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	* NullPointerException</a>
	*/
	public Term[] allMatches(String prefix) {

		//Check if prefix is null.
		if (prefix == null) {
			throw new NullPointerException("Prefix can not be null.");
		}

		//Set the start with the first index.
		int start = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		if (start == -1) {
			return (new Term[0]);
		}

		//Set the end with the last index.
		int end = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		//Make an array of matching Term objects.
		Term[] matches = new Term[1 + (end - start)];

		//Fill the matches array.
		for (int i = 0; i < matches.length; i++) {
			matches[i] = terms[start++];
		}

		//Sort the matches array in descending order by weight.
		Arrays.sort(matches, Term.byReverseWeightOrder());
		return matches;
	}

	/**
	* This method finds number of matches based on the given prefix.
	*
	* @param prefix The String of characters to match
	* @return int Returns the number of matches
	* @exception NullPointerException On the prefix being a null value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	* NullPointerException</a>
	*/
	public int numberOfMatches(String prefix) {

		//Check if prefix is not null.
		if (prefix == null) {
			throw new NullPointerException("Prefix can not be null.");
		}

		//Set the start by the first index and the end by the last index.
		int start = BinarySearchDeluxe.firstIndexOf (terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int end = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		return (1 + (end - start));
	}

	/**
	* This is the main method used to test the implementation of the
	* Autocomplete API.
	* <p>
	*<b>Note:</b> This section of code was given in the assignment documentation
	* in order to test the functionality of all 3 API's together. When the
	* AutocompleteGUI is used, its main method takes this place.
	*
	* @param args The command line argument array
	*/
	public static void main(String[] args) {

		// read in the terms from a file
		String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();
		Term[] terms = new Term[N];
		for (int i = 0; i < N; i++) {
			long weight = in.readLong();
			in.readChar();
			String query = in.readLine();
			terms[i] = new Term(query, weight);
		}

		// read the next weight
		// scan past the tab
		// read the next query
		// construct the term
		// read in queries from standard input and print out the top k matching terms
		int k = Integer.parseInt(args[1]);
		Autocomplete autocomplete = new Autocomplete(terms);
		while (StdIn.hasNextLine()) {
			String prefix = StdIn.readLine();
			Term[] results = autocomplete.allMatches(prefix);
			for (int i = 0; i < Math.min(k, results.length); i++) {
				StdOut.println(results[i]);
			}
		}
	}
}
