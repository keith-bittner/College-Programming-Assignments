import java.util.Comparator;

/**
* <h1>AgeComparator.java</h1> 
* This is the comparator class for the Candidate ADT that compares ages.
* 
* @author Keith Bittner
* @version 1.3
* @since 2020-02-13
*/
public class AgeComparator implements Comparator<Candidate> {

	/**
	* This method is used to compare two candidates by age.
	*
	* @param a The first Candidate object
	* @param b The second Candidate object
	* @return int This returns 0 if same or the value of the Objects compareTo()
	*/
	@Override
	public int compare(Candidate a, Candidate b) {

		String cand1 = a.getLast().toUpperCase();
		String cand2 = b.getLast().toUpperCase();
		char[] order = a.getSortOrder();
		int check1 = 0;
		int check2 = 0;

		//check the ages
		int result = a.compareTo(b);

		//if the ages are the same we need to check the last names
		if (result == 0) {

			for (int i = 0; i < order.length; i++) {
				if (Character.compare(cand1.charAt(0), order[i])==0) {
					check1 = i;
				}
				if (cand2.charAt(0) == order[i]) {
					check2 = i;
				}
			}

			//compare my check values
			return Integer.compare(check1, check2);
		} else {
			return result;
		}
	}
}
