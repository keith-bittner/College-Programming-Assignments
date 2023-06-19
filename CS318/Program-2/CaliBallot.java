import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Comparator;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Stopwatch;

/**
* <h1>CaliBallot.java</h1> 
* This program sorts a list of candidates based on a predetermined order or by
* the age of the candidates.
*
* @author Keith Bittner
* @version 1.3
* @since 2020-02-13
*/

public class CaliBallot {

	/**
	* This method is used to sort the candidates based on the type of sort and
	* the criteria to be used.
	*
	* @param cand The array of candidate objects
	* @param candidateCount The number of candidates to be sorted
	* @param sortName The type of sort to be performed
	* @param sortCriteria What should be sorted
	*/
	public static void sort (Candidate [] cand, int candidateCount,
								String sortName, String sortCriteria) {

		//start the stop watch
		Stopwatch timer = new Stopwatch();

		//firstname
		if (sortCriteria.equalsIgnoreCase("FirstName")) {
			if (sortName.equalsIgnoreCase("Selection")) {
				Selection.sort(cand, new FirstNameComparator());
			} else {
				Insertion.sort(cand, new FirstNameComparator());
			}
		}
		//lastname
		if (sortCriteria.equalsIgnoreCase("LastName")) {
			if (sortName.equalsIgnoreCase("Selection")) {
				Selection.sort(cand, new LastNameComparator());
			} else {
				Insertion.sort(cand, new LastNameComparator());
			}
		}
		//default
		if (sortCriteria.equalsIgnoreCase("Default")) {
			if (sortName.equalsIgnoreCase("Selection")) {
				Selection.sort(cand, new AgeComparator());
			} else {
				Insertion.sort(cand, new AgeComparator());
			}
		}
		//print
		System.out.println("Sorted Candidates");
		for (int i = 0; i < cand.length; i++) {
			System.out.println(cand[i].toString());
		}
		System.out.println("\nSort took: " + timer.elapsedTime() + " seconds.");
	}

	/**
	* This is the main method which makes use of the Candidate ADT to store
	* Candidate objects for sorting.
	*
	* @param args SortName SortCriteria inputfile.txt
	* @exception ArrayIndexOutOfBoundsException On missing data on input file
	* @see <a href=
	* "https://docs.oracle.com/javase/7/docs/api/java/lang/ArrayIndexOutOfBoundsException.html">
	* ArrayIndexOutOfBoundsException</a>
	*/
    public static void main(String[] args) {

		//usage statement
		if (args.length < 3 || args.length >= 4) {
			System.out.println("Error: missing correct argument count.\n");
			System.out.println("Usage: CaliBallot sort_name sort_criteria "
								+ "input_file");
			System.out.println("\tsort_name:\n\t\tInsertion\n\t\tSelection");
			System.out.println("\tsort_criteria:\n\t\tDefault\n\t\t"
								+ "FirstName\n\t\tLastName");
			System.out.println("\tinput_file:\n\t\tExample: candidates.txt");
			System.exit(1);
		}

		//bad sort_name
		if (!args[0].equals("Insertion") && !args[0].equals("Selection")) {
			System.out.println("Error: wrong sort_name.\n");
			System.out.println("sort_name:\n\t\tInsertion\n\t\tSelection");
			System.exit(1);
		}

		//bad sort_criteria
		if (!args[1].equals("Default") && !args[1].equals("FirstName") 
			&& !args[1].equals("LastName")) {
			System.out.println("Error: wrong sort_criteria.\n");
			System.out.println("sort_criteria:\n\t\tDefault\n\t\t"
								+ "FirstName\n\t\tLastName");
			System.exit(1);
		}

		//store the command-line args into variables
		char[] sortOrder = new char[26];
		String sortName = args[0];
		String sortCriteria = args[1];
		String fileName = args[2];

		//try to read the input file
		try {
			Scanner temp = new Scanner(new File (fileName));
			String currentLine = temp.nextLine();
			sortOrder = currentLine.toCharArray();
			int candidateCount = Integer.parseInt(temp.nextLine());
			Candidate [] cand = new Candidate [candidateCount];

			//while there is still data to be read
			while (temp.hasNext()) {
				for (int i = 0; i < cand.length; i++) {
					currentLine = temp.nextLine();
					String line = currentLine;
					String [] info = line.split(", ");					

					if (info.length != 4){
						throw new ArrayIndexOutOfBoundsException("Missing " +
							"information:\n\tAt Line" + (i + 2)
							 + " of the text file");
					}

					String first = info[0];
					String last = info[1];
					int age = Integer.parseInt(info[2]);
					String party = info[3];
					cand[i] = new Candidate(sortOrder, first, last, age, party);
					
				}
			}
			//close the input file
			temp.close();

			//begin printing
			System.out.println("Sort name: " + sortName);
			System.out.println("Sort criteria: " + sortCriteria);
			System.out.print("Sort order: ");
			for (int i = 0; i < sortOrder.length; i++) {
				System.out.print(sortOrder[i]);
			}
			System.out.println("\n");

			//sort the candidates
			sort (cand, candidateCount, sortName, sortCriteria);

		//there was a problem reading the input file
		} catch (IOException e) {
			System.out.println("Error: Bad or missing input file.");
			System.exit(1);
		}		
    }
}
