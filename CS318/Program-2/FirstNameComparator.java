import java.util.Comparator;

/**
* <h1>FirstNameComparator.java</h1> 
* This is the comparator class for the Candidate ADT that compares first names.
* 
* @author Keith Bittner
* @version 1.3
* @since 2020-02-13
*/
public class FirstNameComparator implements Comparator<Candidate> {

	/**
	* This method is used to compare two candidates by first name.
	*
	* @param a The first Candidate object
	* @param b The second Candidate object
	* @return int This returns 0 if same or the value of the Objects compareTo()
	*/
	@Override
	public int compare(Candidate a, Candidate b) {

		String cand1 = a.getFirst().toUpperCase();
		String cand2 = b.getFirst().toUpperCase();
		char[] order = a.getSortOrder();
		int check1 = 0;
		int check2 = 0;

		//does a == b
		if (a.equals(b)) {
			return 0;
		} else {
			//check the first char in the candidate's name against the sortOrder
			for (int i = 0; i < order.length; i++) {
				if (Character.compare(cand1.charAt(0), order[i])==0) {
					check1 = i;
				}
				if (cand2.charAt(0) == order[i]) {
					check2 = i;
				}
			}
			//compare my check values
			int value = Integer.compare(check1, check2);

			if (value == 0){
				return a.compareTo(b);

			} else {
				return value;
			}
		}
	}
}
