package hospital.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import hospital.ifaces.SurgeonManager;
import hospital.pojos.Surgeon;

public class JDBCSurgeonManager implements SurgeonManager{
	private JDBCManager manager;
	
	public JDBCSurgeonManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	public void addSurgeon(Surgeon surg) {
		try{
			String sql = "INSERT INTO Surgeon (surgeon_name, chief) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, surg.getName());
			prep.setBoolean(2, surg.getChief());
			prep.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getNameById(int id) {
		String name= null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT surgeon_name FROM Surgeon WHERE SurgeonID= id";
			ResultSet rs = stmt.executeQuery(sql);
			name = rs.getString("surgeon_name");
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	

	
}