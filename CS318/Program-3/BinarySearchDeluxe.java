import java.util.Collections;
import java.util.Comparator;

/**
* <h1>BinarySearchDeluxe.java</h1> 
* This is the implementation for the BinarySearchDeluxe API. This class
* implements Comparable. BinarySearchDeluxe is used when searching a sorted
* array that contains more than one key equal to the search key.
*
* @author Keith Bittner
* @version 1.0
* @since 2020-02-27
*/
public class BinarySearchDeluxe {

	/**
	* This method finds the matching key starting with the first index of the
	* Key array.
	*
	* @param a An array of Keys
	* @param key The Key object
	* @param comparator The Key comparator being compared to
	* @return int Returns 0 if keys are the same, -1 if no key exists, or the
	* index of the first matching key
	* @exception NullPointerException On any arguments being a null value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	* NullPointerException</a>
	*/
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

		//Check if all arguments are not null.
    	if (a == null || key == null || comparator == null) {
			throw new NullPointerException("Method arguments can not be null.");
		}

		//Set position of lo and hi.
    	int lo = 0;
    	int hi = a.length - 1;

		//Keys @ index 0 is the same.
    	if (comparator.compare(a[0], key) == 0) {
			return 0;
		}

		//Keys are not the same, find the matching key.
    	while (lo <= hi) {
    		int mid = lo + (hi - lo) / 2;

    		if (comparator.compare(key, a[mid]) < 0) {
				hi = mid - 1;
			} else if (comparator.compare(key, a[mid]) > 0) {
				lo = mid + 1;
			} else if (comparator.compare(a[mid - 1], a[mid]) == 0) {
				hi = mid - 1;
			} else {
				return mid;
			}
    	}
		return -1;
	}

	/**
	* This method finds the matching key starting with the last index of the
	* Key array.
	*
	* @param a An array of Keys
	* @param key The Key object
	* @param comparator The Key comparator being compared to
	* @return int Returns 0 if keys are the same, -1 if no key exists, or the
	* index of the last matching key
	* @exception NullPointerException On any arguments being a null value
	* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	* NullPointerException</a>
	*/
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

		//Check is all arguments are not null.
    	if (a == null || key == null || comparator == null) {
			throw new NullPointerException("Method arguments can not be null.");
		}

		//Set position of lo and hi.
    	int lo = 0;
    	int hi = a.length - 1;

		//Keys @ index (a.length - 1) are the same.
    	if (comparator.compare(a[hi], key) == 0) {
			return hi;
		}

		//Keys are not the same, find the matching key.
    	while (lo <= hi) {
    		int mid = lo + (hi - lo) / 2;

    		if (comparator.compare(key, a[mid]) < 0) {
				hi = mid - 1;
			} else if (comparator.compare(key, a[mid]) > 0) {
				lo = mid + 1;
			} else if (comparator.compare(a[mid + 1], a[mid]) == 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
    	}
		return -1;
	}

	/**
	* This is the main method used to test the implementation of the
	* BinarySearchDeluxe API.
	*
	* @param args The command line argument array
	*/
	public static void main(String[] args) {

		//Test cases no longer needed and removed.
	}
}
