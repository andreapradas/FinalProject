package hospital.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.SurgeonManager;
import hospital.pojos.Nurse;
import hospital.pojos.Surgeon;

public class JDBCSurgeonManager implements SurgeonManager{
	private JDBCManager manager;
	
	public JDBCSurgeonManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	public void addSurgeon(Surgeon surg) {
		try{
			String sql = "INSERT INTO Surgeon (surgeon_name, surgeon_email, chief) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, surg.getName());
			prep.setString(2, surg.getEmail());
			prep.setBoolean(3, surg.getChief());
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
	
	@Override
	public List<Surgeon> getListOfSurgeons() {
		// TODO Auto-generated method stub
		List<Surgeon> ListOfSurgeons = new ArrayList<Surgeon>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Surgeon";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("surgeonID");
				String name = rs.getString("surgeon_name");
				String email = rs.getString("surgeon_email");
				Boolean chief = rs.getBoolean("chief");
				
				Surgeon s = new Surgeon(id, name, email, chief);
				ListOfSurgeons.add(s);
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ListOfSurgeons;
	}
	

	
}