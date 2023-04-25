package hospital.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
		
	private Connection c= null;

	public JDBCManager() {
		
		try 
		{			
			// Open the DB connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/projectSurgeries.db");
			System.out.println("Database connection opened.");
				
			//create tables
			this.createTables();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			System.out.print("Libraries not loaded");
		}
		}
		
	private void createTables() {
		// Create Tables
		try {
			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE Surgeon (" +
				"surgeonID	INTEGER PRIMARY KEY AUTOINCREMENT," +
				"surgeon_name	TEXT NOT NULL," +
				"chief BOOLEAN," +
				"surgeon_email	TEXT NOT NULL UNIQUE," +
				"onVacation	BOOLEAN," +
				");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Patient (" +
				"patientId	INTEGER PRIMARY KEY AUTOINCREMENT," +
				"patientName TEXT NOT NULL," + 
				"phoneNumber INTEGER NOT NULL UNIQUE," +
			");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Nurse (" +
				"nurseId INTEGER PRIMARY KEY AUTOINCREMENT," + 
				"nurse_name TEXT NOT NULL," + 
				"nurse_email	TEXT NOT NULL UNIQUE," +
				"onVacation	BOOLEAN," +
				");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Surgery (" +
				"surgeryId	INTEGER PRIMARY KEY AUTOINCREMENT," + 
				"surgeryType TEXT NOT NULL," +
				"day DATE DEFAULT NULL," +
				"startHour TIME DEFAULT NULL," +
				"endHour	TIME DEFAULT NULL," +
				"done BOOLEAN,"	+
				"surgeonID INTEGER DEFAULT NULL," +
				"patientId INTEGER DEFAULT NULL," +
				"roomId INTEGER DEFAULT NULL," +
				"FOREIGN KEY(patientId) REFERENCES Patient(patientId) ON DELETE SET NULL ON UPDATE CASCADE," +
				"FOREIGN KEY(roomId) REFERENCES operatingRoom(roomId) ON DELETE SET NULL ON UPDATE CASCADE," +
				"FOREIGN KEY(surgeonID) REFERENCES Surgeon(surgeonID) ON DELETE SET NULL ON UPDATE CASCADE," +
			");"; 	
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE nurseVacation (" +
				"vacationId INTEGER PRIMARY KEY AUTOINCREMENT," +
				"starts	DATE NOT NULL," +
				"ends DATE NOT NULL," +
				"nurseId INTEGER,"	+
				"FOREIGN KEY(nurseId) REFERENCES Nurse(nurseId) ON DELETE SET NULL ON UPDATE CASCADE," +
			");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE operatingRoom (" +
				"roomId	INTEGER PRIMARY KEY AUTOINCREMENT," +
				"roomNumber	INTEGER NOT NULL UNIQUE," +
				"roomFloor	INTEGER NOT NULL," +
				"active BOOLEAN," +
			");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE surgeonVacation (" +
				"vacationId INTEGER PRIMARY KEY AUTOINCREMENT," +
				"starts DATE NOT NULL," +
				"ends DATE NOT NULL," +
				"surgeonID INTEGER," +
				"FOREIGN KEY(surgeonID) REFERENCES Surgeon(surgeonID) ON DELETE SET NULL ON UPDATE CASCADE," +
			");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE worksWith (" +
				"teamId	INTEGER PRIMARY KEY AUTOINCREMENT," +
				"dateOfWork DATE NOT NULL," +
				"surgeonID INTEGER," +
				"nurseId INTEGER,"	+
				"FOREIGN KEY(nurseId) REFERENCES Nurse(nurseId) ON DELETE SET NULL ON UPDATE CASCADE," +
				"FOREIGN KEY(surgeonID) REFERENCES Surgeon(surgeonID) ON DELETE SET NULL ON UPDATE CASCADE," +
			");";
			stmt.executeUpdate(sql);
			
			} catch (SQLException e) {
				// Do not complain if tables already exist
				if (!e.getMessage().contains("already exists")) {
					e.printStackTrace();
				}
			}
		}
		
	public Connection getConnection() {
		return c;
	}
	
	public void disconnect()
	{		
		try {
			c.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
	}

}