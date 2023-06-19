/***********************************************************
*
*	Keith Bittner - bittnerP4.java
*
*	CS330B - Winter 2022
*
*
*
*************************************************************/

import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class BittnerP4 {
	
	public static void mainMenu() {
		System.out.println("\n   ~~ ~~~ ~~~~ Track and Field System ~~~~ ~~~ ~~ ");
		System.out.println("\n    Please select from the following options:");
		System.out.println("  ________________________________________________");
		System.out.println("  |                                              |");
		System.out.println("  |  1. Enter a new school.                      |");
		System.out.println("  |  2. Enter a new athlete.                     |");
		System.out.println("  |  3. Enter a new event.                       |");
		System.out.println("  |  4. Enter an event's results.                |");
		System.out.println("  |  5. Get an event's results.                  |");
		System.out.println("  |  6. Score an event.                          |");
		System.out.println("  |  7. Check an athlete's event participation.  |");
		System.out.println("  |  8. Disqualify athlete from an event.        |");
		System.out.println("  |  9. Disqualify athlete from the meet.        |");
		System.out.println("  |  10. Show final team scores.                 |");
		System.out.println("  |  11. Show meet champion.                     |");
		System.out.println("  |  12. Exit System.                            |");
		System.out.println("  |______________________________________________|");
		
		Scanner in = new Scanner(System.in);
		System.out.print("----->>");
		int choice = in.nextInt();
		
		switch (choice) {
			case 1:
				newSchool();
				break;
			case 2:
				newAthlete();
				break;
			case 3:
				addEvent();
				break;
			case 4:
				addEventResult();
				break;
			case 5:
				getEventResult();
				break;
			case 6:
				scoreEvent();
				break;
			case 7:
				athleteCheck();
				break;
			case 8:
				singleDisq();
				break;
			case 9:
				fullMeetDisq();
				break;
			case 10:
				teamScores();
				break;
			case 11:
				meetChamp();
				break;
			case 12:
				System.exit(0);
		}
	}
	
	public static void newSchool() {
		
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
						
			System.out.print("Enter school name: ");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			String sql = "INSERT INTO schools (school_Name) VALUES ('" + input+ "')";
			Statement stmt = conn.createStatement();
			int count= stmt.executeUpdate(sql);
			boolean isUpdated = (count > 0);
			
			if (isUpdated) {
				System.out.println("School added succesfully.");
				mainMenu();
			} else {
				System.out.println("School not added succesfully.");
				newSchool();
			}			
			
            conn.close();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			newSchool();
		}
	}
	
	public static void newAthlete() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
						
			System.out.print("Enter athlete's first name: ");
			Scanner in = new Scanner(System.in);
			String firstName = in.nextLine();
			System.out.print("Enter athlete's last name: ");
			String lastName = in.nextLine();
			System.out.print("Enter athlete's gender (M or F): ");
			char gender = in.next().charAt(0);
						
			Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults = stmt1.execute("SELECT * FROM schools ORDER BY school_Id");
			ResultSet rs = stmt1.getResultSet();
			rs.first();
			
			while (gotResults) {
				int column1 = rs.getInt("school_Id");
				String column2 = rs.getString("school_Name");
				System.out.println(column1 + "\t" + column2);
				gotResults = rs.next();
			}
						
			System.out.print("Choose athlete's school from list: ");
			int school = in.nextInt();
			String sql = "INSERT INTO athletes (first_Name, last_Name, gender, school_Id)" 
						+ "VALUES('" + firstName + "', '" + lastName + "', '" + gender + "', "
						+ school + ")";
			Statement stmt2 = conn.createStatement();
			int count= stmt2.executeUpdate(sql);
			boolean isUpdated = (count > 0);
			
			if (isUpdated) {
				System.out.println("Athlete added succesfully.");
				mainMenu();
			} else {
				System.out.println("Athlete not added succesfully.");
				newAthlete();
			}	
			
            conn.close();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			newAthlete();
		}
	}
	
	public static void addEvent() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
			
			System.out.println("**Note: An event will be created for both genders.");
			System.out.print("Enter the event name: ");
			Scanner in = new Scanner(System.in);
			String eventName = in.nextLine();
			System.out.print("Enter the event type (Track or Field): ");
			String eventType = in.nextLine();
			String sql = "INSERT INTO _events (event_Name, event_Type, event_Gender) VALUES ('" 
							+ eventName + "', '" + eventType + "', 'M'),"
							+ "('" + eventName + "', '" + eventType + "', 'F')";
			Statement stmt = conn.createStatement();
			int count= stmt.executeUpdate(sql);
			boolean isUpdated = (count > 0);
			
			if (isUpdated) {
				System.out.println("Event added succesfully.");
				mainMenu();
			} else {
				System.out.println("Event not added succesfully.");
				addEvent();
			}	
			
            conn.close();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			addEvent();
		}
	}
	
	public static void addEventResult() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
			
			System.out.print("Enter event id number: ");
			Scanner in = new Scanner(System.in);
			int eventid = in.nextInt();
			System.out.print("Enter athlete's id number: ");
			int athlete = in.nextInt();
			System.out.print("Enter athlete's event score: ");
			float score = in.nextFloat();
			String gender = "";
			int school = 0;
							
			Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults = stmt1.execute("SELECT school_Id, gender FROM athletes WHERE comp_Id = " + athlete);
							
			ResultSet rs = stmt1.getResultSet();
			rs.first();
							
			while (gotResults) {
				school = rs.getInt("school_Id");
				gender = rs.getString("gender");
				gotResults = rs.next();
			}
								
			String sql = "INSERT INTO event_Results (event_Id, comp_Id, school_Id, gender, score)"
							+ "VALUES (" + eventid + ", " + athlete + ", " + school + ", '"
							+ gender + "', " + score + ")";
			Statement stmt2 = conn.createStatement();
			int count= stmt2.executeUpdate(sql);
			boolean isUpdated = (count > 0);
			
			if (isUpdated) {
				System.out.println("Event result added succesfully.");
				mainMenu();
			} else {
				System.out.println("Event result not added succesfully.");
				addEventResult();
			}	
			
            conn.close();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			addEventResult();
		}
	}
	
		public static void getEventResult() {
		
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
						
			System.out.println("~ ~~ ~~~ List of Events ~~~ ~~ ~");
			System.out.println("Event Id    Event Name    Gender");
			System.out.println("--------    ----------    ------");
			Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults1 = stmt1.execute("SELECT * FROM _events");
			ResultSet rs1 = stmt1.getResultSet();
			rs1.first();
						
			while (gotResults1) {
				int eventNumber = rs1.getInt("event_Id");
				String eventName = rs1.getString("event_Name");
				String eventGender = rs1.getString("event_Gender");
				System.out.println(String.format("%1$5d %2$17s %3$6s", eventNumber, eventName, eventGender));
				gotResults1 = rs1.next();
			}
			System.out.print("\nEnter the event you want to view: ");
						
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
						
			CallableStatement cStmt = conn.prepareCall("{call getEventResults(?)}");
			cStmt.setInt(1, choice);
			ResultSet rs = cStmt.executeQuery();
						
			System.out.println(" ID       Event Name            Athlete                   School                Score     Placing   Points      DQ");
			System.out.println("----     ------------     --------------------     ----------------------     ---------     ----     ----     --------");

			while (rs.next()) {
				int eventId = rs.getInt("event_Id");
				String eventName = rs.getString("event_Name");
				String fName = rs.getString("first_Name");
				String lName = rs.getString("last_Name");
				String athleteName = fName + " " + lName;
				String schoolName = rs.getString("school_Name");
				float score = rs.getFloat("score");
				int placing = rs.getInt("placing");
				int points = rs.getInt("points");
				boolean dqed = rs.getBoolean("event_Dq");
							
				System.out.println(String.format("%1$3d %2$17s %3$24s %4$26s %5$11.2f %6$9d %7$8d %8$12s", 
									eventId, eventName, athleteName, schoolName, score, placing, points, dqed));
			}		
			
            conn.close();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			getEventResult();
		}
	}
	
	public static void scoreEvent() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
			
			System.out.println("~ ~~ ~~~ List of Events ~~~ ~~ ~");
			System.out.println("Event Id    Event Name    Gender");
			System.out.println("--------    ----------    ------");
			Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults1 = stmt1.execute("SELECT * FROM _events");
			ResultSet rs1 = stmt1.getResultSet();
			rs1.first();
						
			while (gotResults1) {
				int eventNumber = rs1.getInt("event_Id");
				String eventName = rs1.getString("event_Name");
				String eventGender = rs1.getString("event_Gender");
				System.out.println(String.format("%1$5d %2$17s %3$6s", eventNumber, eventName, eventGender));
				gotResults1 = rs1.next();
			}			
			
            System.out.print("\nEnter the event you want to score: ");
						
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
						
			CallableStatement cStmt = conn.prepareCall("{call scoreEvent(?)}");
			cStmt.setInt(1, choice);
			cStmt.executeUpdate();			
            conn.close();
			mainMenu();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			scoreEvent();
		}
	}
	
	public static void athleteCheck() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
						
			System.out.print("Select Athlete's participation you want to view: ");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults = stmt.execute("SELECT * FROM event_Results WHERE comp_Id = " + choice);
			ResultSet rs = stmt.getResultSet();
			rs.first();
			
			System.out.println("Event ID     Athlete ID     Score     Place     Points     DQ");
			System.out.println("________     __________     _____     _____     ______     __");
			
			while (gotResults) {
				int eventId = rs.getInt("event_Id");
				int compId = rs.getInt("comp_Id");
				int mark = rs.getInt("score");
				int rank = rs.getInt("placing");
				int tpoints = rs.getInt("points");
				boolean dqed = rs.getBoolean("event_Dq");
				System.out.println(String.format("%1$4d %2$14d %3$11d %4$9d %5$10d %6$9s", eventId, compId, mark, rank, tpoints, dqed));
				gotResults = rs.next();
			}
			
            conn.close();
			mainMenu();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			athleteCheck();
		}
	}
	
	public static void singleDisq() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
			
			System.out.print("Select Athlete you want to disqualify from an event: ");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults = stmt.execute("SELECT * FROM event_Results WHERE comp_Id = " + choice);
			ResultSet rs = stmt.getResultSet();
			rs.first();
			
			System.out.println("Event ID     Athlete ID     Score     Place     Points     DQ");
			System.out.println("________     __________     _____     _____     ______     __");
			
			while (gotResults) {
				int eventId = rs.getInt("event_Id");
				int compId = rs.getInt("comp_Id");
				int mark = rs.getInt("score");
				int rank = rs.getInt("placing");
				int tpoints = rs.getInt("points");
				boolean dqed = rs.getBoolean("event_Dq");
				System.out.println(String.format("%1$4d %2$14d %3$11d %4$9d %5$10d %6$9s", eventId, compId, mark, rank, tpoints, dqed));
				gotResults = rs.next();
			}
			
			System.out.print("Enter the event you wish to DQ athlete from: ");
			Scanner in2 = new Scanner(System.in);
			int choice2 = in.nextInt();
			
			CallableStatement cStmt = conn.prepareCall("{call athleteEventDq(?,?)}");
			cStmt.setInt(1, choice2);
			cStmt.setInt(2, choice);
			int count= cStmt.executeUpdate();
			boolean isUpdated = (count > 0);
			
			if (isUpdated) {
				System.out.println("Athlete successfully DQed.");
				mainMenu();
			} else {
				System.out.println("Athlete not successfully DQed.");
				singleDisq();
			}
			
			Statement stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults3 = stmt3.execute("SELECT * FROM event_Results WHERE comp_Id = " + choice);
			ResultSet rs3 = stmt3.getResultSet();
			rs3.first();
			
			System.out.println("Event ID     Athlete ID     Score     Place     Points     DQ");
			System.out.println("________     __________     _____     _____     ______     __");
			
			while (gotResults3) {
				int eventId = rs3.getInt("event_Id");
				int compId = rs3.getInt("comp_Id");
				int mark = rs3.getInt("score");
				int rank = rs3.getInt("placing");
				int tpoints = rs3.getInt("points");
				boolean dqed = rs3.getBoolean("event_Dq");
				System.out.println(String.format("%1$4d %2$14d %3$11d %4$9d %5$10d %6$9s", eventId, compId, mark, rank, tpoints, dqed));
				gotResults3 = rs3.next();
			}
			
            conn.close();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			singleDisq();
		}
	}
	
	public static void fullMeetDisq() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
			
			System.out.print("Select Athlete you want to disqualify from the meet: ");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			
			CallableStatement cStmt = conn.prepareCall("{call fullAthleteDq(?)}");
			cStmt.setInt(1, choice);
			int count= cStmt.executeUpdate();
			boolean isUpdated = (count > 0);
			
			if (isUpdated) {
				System.out.println("Athlete successfully DQed.");
				mainMenu();
			} else {
				System.out.println("Athlete not successfully DQed.");
				fullMeetDisq();
			}
			
            conn.close();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			fullMeetDisq();
		}
	}
	
	public static void teamScores() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
						
			String line1 = "";
			String line2 = "";
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			boolean gotResults = stmt.execute("SELECT school_Id, school_Name, points, gender FROM meet_Results ORDER BY points DESC");
			ResultSet rs = stmt.getResultSet();
			rs.first();
			while (gotResults) {
				String column1 = rs.getString("school_Name");
				int column2 = rs.getInt("points");
				String column3 = rs.getString("gender");
				if (column3.charAt(0) == 'M') {
					line1 = line1 + "\n" + String.format("%1$-22s %2$5d", column1, column2);
				} else {
					line2 = line2 + "\n" + String.format("%1$-22s %2$5d", column1, column2);
				}
				gotResults = rs.next();
			}
			System.out.println("Final Meet Results - Men");
			System.out.println("________________________");
			System.out.println(line1);
			System.out.println("\nFinal Meet Results - Women");
			System.out.println("________________________");
			System.out.println(line2);
			
            conn.close();
			
			mainMenu();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			teamScores();
		}
	}
	
	public static void meetChamp() {
		try {
			
			String user = "root";
			String password = "MyMoto2021!@";
			String myURL = "jdbc:mysql://localhost:3306/dbBittnerTrack?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";

			Connection conn = DriverManager.getConnection(myURL, user, password);
						
			CallableStatement cStmt = conn.prepareCall("{call finalMeetResults()}");
			ResultSet rs = cStmt.executeQuery();

			while (rs.next()) {
				String schoolName = rs.getString("school_Name");
				int schoolPoints = rs.getInt("sum(points)");
				System.out.println("~ ~~ ~~~ Meet Champion ~~~ ~~ ~");
				System.out.println("_______________________________");
				System.out.println(schoolName + " with " + schoolPoints + " points.");
				System.out.println("\n\nCONGRATULATIONS");
			}
			
            conn.close();
			mainMenu();
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			//ex.printStackTrace();
			meetChamp();
		}
	}
	
	public static void main(String args[]) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		mainMenu();
	}
}