package server;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLConnection {
	final String DATABASE_URL = "jdbc:mysql://localhost/";
	private Connection conn;
	private String schema;

	public MySQLConnection(String schema, String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			this.schema = schema;
			conn = DriverManager.getConnection(DATABASE_URL + schema, userName, password);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(0);
		}
	}

	public Object executeQuery(Object msg) {
		String query;
		try {
			if (msg instanceof ArrayList) {
				ArrayList<String> arr = (ArrayList<String>) msg;
				query = String.valueOf(arr.get(arr.size() - 1));
				if (query.startsWith("INSERT") || query.startsWith("UPDATE") || query.startsWith("DELETE")) {
					int i;
					PreparedStatement ps = conn.prepareStatement(query);
					for (i = 0; i < arr.size() - 1; i++) {
						ps.setString(i + 1, arr.get(i));
					}
					ps.executeUpdate();
					System.out.println("DB: Query => Executed Successfully");
					return null;
				} else
					return executeSelectQuery(msg);

			} else if (msg instanceof String) {
				query = msg.toString();
				if (query.startsWith("INSERT") || query.startsWith("UPDATE") || query.startsWith("DELETE")) {
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(query);
					System.out.println("DB: " + query + " => Executed Successfully");
					return null;
				} else
					return executeSelectQuery(msg);
			}

		} catch (SQLException sqlException) {
			System.out.println("Couldn't execute query");
			sqlException.printStackTrace();
			return null;
		}
		return null;
	}

	public Object executeSelectQuery(Object msg) {
		String query = msg.toString();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("DB: " + query + " => Executed Successfully");
			return parseResultSet(rs);

		} catch (SQLException sqlException) {
			System.out.println("Couldn't execute query");
			sqlException.printStackTrace();
			return null;
		}
	}
	
	/**
	 * save the file in bookContentsFile table
	 * @param inputStream
	 * @param id
	 */
	public void updateFile(InputStream inputStream, int id) {
		
		String sql = "UPDATE Book SET tableOfContents =? WHERE bookID = '" + id + "';";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBinaryStream(1, inputStream);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parse database result set into an ArrayList with rows separated by commas
	 * 
	 * @param rs
	 * @return ArrayList<String>
	 */
	public ArrayList<String> parseResultSet(ResultSet rs) {
		ArrayList<String> arr = new ArrayList<>();
		int i;

		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				i = 1;
				while (i <= rsmd.getColumnCount()) {
					arr.add(rs.getString(i++));
				}
			}
		} catch (SQLException Exception) {
			System.out.println("ERROR while parsing array!");
		}
		return arr;
	}
	
	public ResultSet executeFileQuery(int bookID) {
		String query = "SELECT tableOfContents FROM Book WHERE bookID = '" + bookID + "';";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("DB: " + query + " => Executed Successfully");
			return rs;
		} catch (SQLException sqlException) {
			System.out.println("Couldn't execute query");
			sqlException.printStackTrace();
			return null;
		}
	}
	
}