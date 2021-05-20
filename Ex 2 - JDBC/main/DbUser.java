package main;// STUBBED FILE
import java.sql.*;

// this is the class through which all Database calls go
public class DbUser extends DbBasic {

	private ResultSet rs = null;
	static private final int STR_SIZE = 25;

	// this method takes a String, converts it into an array of bytes;
	// copies those bytes into a bigger byte array (STR_SIZE worth), and
	// pads any remaining bytes with spaces. Finally, it converts the bigger
	// byte array back into a String, which it then returns.
	// e.g. if the String was "s_name", the new string returned is
	// "s_name                    " (the six characters followed by 18 spaces).
	private String pad( String in ) {
		byte [] org_bytes = in.getBytes( );
		byte [] new_bytes = new byte[STR_SIZE];
		int upb = in.length( );

		if ( upb > STR_SIZE )
			upb = STR_SIZE;

		for ( int i = 0; i < upb; i++ )
			new_bytes[i] = org_bytes[i];

		for ( int i = upb; i < STR_SIZE; i++ )
			new_bytes[i] = ' ';

		return new String( new_bytes );
	}

	/*
	 * Creates a connection to the named database
	 */
	DbUser ( String dbName ) {
		super( dbName );
	}

	/*
	 * You need to complete the following methods
	 */
	public void doQuery_1_a( ) {
		// 1 a) what is the title of the course with code 361?';
		// Write the Java code that executes the "select" statement that answers query 1a).
		// See the example output to see what should be produced.
		System.out.println("1 a) what is the title of the course with code 361?");
	}

	public void doQuery_1_b( ) {
		// 1 b) what position and qualifications does Davies have?';
		// Write the Java code that executes the "select" statement that answers query 1b).
		// See the example output to see what should be produced.
		System.out.println("1 b) what position and qualifications does Davies have?");
	}

	public void doQuery_2_a( ) {
		System.out.println("2 a) what is the name of the department that Bear works for?");
	}

	public void doQuery_2_b( ) {
		System.out.println("2 b) what are the course codes of courses offered by the Computing department? ");
	}

	public void doQuery_3_a( ) {
		System.out.println("3 a) What are the titles of course given by Mariani?");
	}

	public void doQuery_3_b( ) {
		System.out.println("3 b) What are the names and initials of the staff who work on the COMIC project?");
	}

	public void doQuery_4_a( ) {
		System.out.println("4 a) add a row to the Department relation regarding the Sociology department, with a location at Cartmel college and a code of SOCIO");
	}

	public void doQuery_4_b( ) {
		System.out.println("4 b) add a row to Staff regarding J. Hughes, who is a Professor and has a PhD, an S_ID of JH, and works for Sociology");
	}

	public void doQuery_4_c( ) {
		System.out.println("4 c) add a row to Work_on for J.Hughes working on COMIC from 1991 to 1994.");
	}

	public void doQuery_5_a( ) {
		System.out.println("5 a) what are the names of people who started work on COMIC between 1990 and 1992?");
	}

	public void doQuery_5_b( ) {
		System.out.println("5 b) what is the total amount of funding, and the average amount of funding, of all projects?");
	}

	void doQuery_6( ) {
		System.out.println("6. List staff names and the number of projects they have worked on.");
	}

	void execute_7c( ) {
		System.out.println("7c) Delete tuples in work_on for s_id equal tp 'JH'");
	}

	void execute_7a( ) {
		System.out.println("7a) Delete tuples in department for d_id equal to 'SOCIO'");
	}

	void execute_7b( ) {
		System.out.println("7b) Delete tuples in staff where s_id equal to 'JH'");
	}

	public void parameterise_3_a(String lecturerName) {
		// in this method, we want you to "parameterise" query 3a) so that, in the
		// argument to this method, your provide the name of the staff member, and can
		// subsequently find out what course code and name they give
		System.out.println("Parameterising Q3a) using " + lecturerName);
	}
}
