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
				"surgeonId	INTEGER PRIMARY KEY AUTOINCREMENT," +
				"surgeonName	TEXT NOT NULL," +
				"surgeonSurname TEXT NOT NULL, " +
				"chief BOOLEAN," +
				"surgeonEmail	TEXT NOT NULL UNIQUE" +
				");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Patient (" +
				"patientId	INTEGER PRIMARY KEY AUTOINCREMENT," +
				"patientName TEXT NOT NULL," + 
				"patientSurname TEXT NOT NULL," + 
				"phoneNumber INTEGER NOT NULL UNIQUE" +
			");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Nurse (" +
				"nurseId INTEGER PRIMARY KEY AUTOINCREMENT," + 
				"nurseName TEXT NOT NULL," + 
				"nurseSurname TEXT NOT NULL," + 
				"nurseEmail	TEXT NOT NULL UNIQUE" +
				");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Surgery (" +
				"surgeryId	INTEGER PRIMARY KEY AUTOINCREMENT," + 
				"surgeryType TEXT NOT NULL," +
				"surgeryDate DATE DEFAULT NULL," +
				"startHour TIME DEFAULT NULL," +
				"done BOOLEAN DEFAULT FALSE,"	+
				"surgeonID INTEGER DEFAULT NULL," +
				"patientId INTEGER DEFAULT NULL," +
				"roomId INTEGER DEFAULT NULL," +
				"FOREIGN KEY(patientId) REFERENCES Patient(patientId) ON DELETE SET NULL ON UPDATE CASCADE," +
				"FOREIGN KEY(roomId) REFERENCES operatingRoom(roomId) ON DELETE SET NULL ON UPDATE CASCADE," +
				"FOREIGN KEY(surgeonId) REFERENCES Surgeon(surgeonId) ON DELETE SET NULL ON UPDATE CASCADE" +
			");"; 	
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE nurseVacation (" +
				"vacationId INTEGER PRIMARY KEY AUTOINCREMENT," +
				"starts	DATE NOT NULL," +
				"ends DATE NOT NULL," +
				"nurseId INTEGER,"	+
				"FOREIGN KEY(nurseId) REFERENCES Nurse(nurseId) ON DELETE CASCADE ON UPDATE CASCADE" +
			");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE operatingRoom (" +
					"roomId	INTEGER AUTOINCREMENT," +
					"roomNumber	INTEGER NOT NULL," +
					"roomFloor	INTEGER NOT NULL," +
					"active	BOOLEAN DEFAULT TRUE," +
					"PRIMARY KEY (roomId AUTOINCREMENT)," +
					"UNIQUE(roomNumber, roomFloor)" +
				");";			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE surgeonVacation (" +
				"vacationId INTEGER PRIMARY KEY AUTOINCREMENT," +
				"starts DATE NOT NULL," +
				"ends DATE NOT NULL," +
				"surgeonId INTEGER," +
				"FOREIGN KEY(surgeonId) REFERENCES Surgeon(surgeonId) ON DELETE CASCADE ON UPDATE CASCADE" +
			");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE worksWith (" +
				"teamId	INTEGER PRIMARY KEY AUTOINCREMENT," +
				"dateOfWork DATE NOT NULL," +
				"surgeonId INTEGER," +
				"nurseId INTEGER,"	+
				"FOREIGN KEY(nurseId) REFERENCES Nurse(nurseId) ON DELETE SET NULL ON UPDATE CASCADE," +
				"FOREIGN KEY(surgeonId) REFERENCES Surgeon(surgeonId) ON DELETE SET NULL ON UPDATE CASCADE" +
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