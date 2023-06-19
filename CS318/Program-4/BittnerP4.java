import edu.princeton.cs.algs4.DijkstraSP;
public class BittnerP4 {
	public static void main(String[] args) {
		if (args.length < 2) { //not enough command-line arguments
			System.out.println("Usage: java -cp .:algs4.jar BittnerP4 myCities.txt city_number");
			System.exit(1);
		} else {
			DijkstraSP.main(args); //use the main function from DijkstraSP.java
		} //end of if-else statment
	} //end of main
} //end of program
