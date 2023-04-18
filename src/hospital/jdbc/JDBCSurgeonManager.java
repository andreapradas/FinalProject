package hospital.jdbc;

import java.sql.ResultSet;
import java.sql.Statement;

import hospital.ifaces.SurgeonManager;

public class JDBCSurgeonManager implements SurgeonManager{
	private JDBCManager manager;
	
	public JDBCSurgeonManager(JDBCManager m)
	{
		this.manager = m;
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