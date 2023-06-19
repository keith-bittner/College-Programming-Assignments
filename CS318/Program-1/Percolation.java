/*
	Keith Bittner
	CS318 - Winter 2020

	Assignment 1 - Percolation.java

	This class implements the Percolation API for use in determining if a system
	percolates using the PercolationVisualizer.java program.
*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	//variable declaration
	private int size;
	private boolean mySites[][];
	private int sourceSite = 0;
	private int sinkSite;
	private WeightedQuickUnionUF percCheck;
	private WeightedQuickUnionUF fullCheck;

	//constructor
	//create n-by-n grid, with all sites blocked
	public Percolation(int n) {

		//can we perform the constructor?
		if (n <= 0) {
			throw new IllegalArgumentException("Size must be greater than 0");
		}

		size = n;
		sinkSite = ((n * n) + 1);

		percCheck = new WeightedQuickUnionUF ((n * n) + 2);
		fullCheck = new WeightedQuickUnionUF ((n * n) + 2);

		//let's build our 2d matrix of sites
		mySites = new boolean[n + 1][n + 1];
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				mySites[i][j] = false;
			}
		}
	}

	/*	
		open (int row, int col)
		
		Description: Open site (row, col) in our 2D matrix.
		
		Parameters: row - integer of the row in our matrix
					col - integer of the column in our matrix
		
		Return: None		
	*/
	public void open(int row, int col) {

		isValid(row, col);
		mySites[row][col] = true;

		//source
		if (row == 1) {
			//join site with source
			percCheck.union(gridToPoint(row, col), sourceSite);
			fullCheck.union(gridToPoint(row, col), sourceSite);
		}

		//sink
		if (row == (size)) {
			//join site with sink
			percCheck.union(gridToPoint(row, col), sinkSite);
		}
		//right
		if (((col + 1) <= size) && (isOpen(row, (col + 1)))) {
			//join the 2 sites
			percCheck.union(gridToPoint(row, col), gridToPoint(row, (col + 1)));
			fullCheck.union(gridToPoint(row, col), gridToPoint(row, (col + 1)));
		}
		//left
		if (((col - 1) >= 1) && (isOpen(row, (col - 1)))) {
			//join the 2 sites
			percCheck.union(gridToPoint(row, col), gridToPoint(row, (col - 1)));
			fullCheck.union(gridToPoint(row, col), gridToPoint(row, (col - 1)));
		}
		//top
		if (((row - 1) >= 1 ) && (isOpen((row - 1), col))) {
			//join the 2 sites
			percCheck.union(gridToPoint(row, col), gridToPoint((row - 1), col));
			fullCheck.union(gridToPoint(row, col), gridToPoint((row - 1), col));
		} 
		//bottom
		if (((row + 1) <= size) && (isOpen((row + 1), col))) {
			//join the 2 sites
			percCheck.union(gridToPoint(row, col), gridToPoint((row + 1), col));
			fullCheck.union(gridToPoint(row, col), gridToPoint((row + 1), col));
		} 
	}

	/*	
		isOpen (int row, int col)
		
		Description: Checks to see if site (row, col) is open in our 2d matrix.
		
		Parameters: row - integer of the row in our matrix
					col - integer of the column in our matrix
		
		Return: boolean		
	*/
	public boolean isOpen(int row, int col) {

		isValid(row, col);

		return (mySites[row][col]);
	}

	/*	
		isFull (int row, int col)
		
		Description: Checks to see if site (row, col) is connected to source.
		
		Parameters: row - integer of the row in our matrix
					col - integer of the column in our matrix
		
		Return: boolean		
	*/
	public boolean isFull(int row, int col) {

		isValid(row, col);

		return (fullCheck.connected(gridToPoint(row, col), sourceSite));
	}

	/*	
		numberOfOpenSites ()
		
		Description: Counts how many sites are open in our 2d matrix.
		
		Parameters: None
		
		Return: integer		
	*/
	public int numberOfOpenSites() {

		int count = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (mySites[i][j] == true) {
					count++;
				}
			}
		}

		return count;
	}

	/*	
		percolates ()
		
		Description: Checks to see if the source is connected to the sink.
		
		Parameters: None
		
		Return: boolean		
	*/
	public boolean percolates() {

		return (percCheck.connected(sourceSite, sinkSite));
	}

	/*	
		gridToPoint (int row, int col)
		
		Description: Converts 2d grid location to 1d point.
		
		Parameters: row - integer of the row in our matrix
					col - integer of the column in our matrix
		
		Return: integer		
	*/
	public int gridToPoint(int row, int col) {

		return (((row - 1) * size) + (col + 1));
	}

	/*	
		isValid (int row, int col)
		
		Description: Checks to see if site (row, col) is a valid location. Will
			throw exception is the row or column are out of bounds.
		
		Parameters: row - integer of the row in our matrix
					col - integer of the column in our matrix
		
		Return: None		
	*/
	public void isValid(int row, int col) {

		if ((row < 0) || (row > (size))) {
			throw new IndexOutOfBoundsException("Row index must be between 0"
				+ " and " + (size - 1));
		}

		if ((col < 0 ) || (col > (size))) {
			throw new IndexOutOfBoundsException("Column index must between 0"
				+ " and " + (size - 1));
		}
	}
}
