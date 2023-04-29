package hospital.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	@Override
	public void addSurgeon(Surgeon surg) {
		try{
			String sql = "INSERT INTO Surgeon (surgeonName, surgeonSurname, surgeonEmail, chief) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, surg.getName());
			prep.setString(2, surg.getSurname());
			prep.setString(3, surg.getEmail());
			prep.setBoolean(4, surg.getChief());
			prep.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getNameById(int id) {
		String name= null;
		try {			
			String sql = "SELECT surgeonName FROM Surgeon WHERE surgeonId= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			name = rs.getString("surgeonName");
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	@Override
	public int getIdByEmail(String email) {
		int id = 0;
		try {
			String sql = "SELECT surgeonId FROM Surgeon WHERE surgeonEmail= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs = prep.executeQuery();
			id = rs.getInt("surgeonId");
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public String getEmailById(int id) {
		String email = null;
		try {
			String sql = "SELECT surgeonEmail FROM Surgeon WHERE surgeonId= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			email = rs.getString("surgeonEmail");
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return email;
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
				Integer id = rs.getInt("surgeonId");
				String name = rs.getString("surgeonName");
				String surname = rs.getString("surgeonSurname");
				String email = rs.getString("surgeonEmail");
				Boolean chief = rs.getBoolean("chief");
				Surgeon s = new Surgeon(id, name, surname,email, chief);
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

	@Override
	public Surgeon getChiefSurgeon() {
		// TODO Auto-generated method stub
		Surgeon s = null;
		try {
			
			String sql = "SELECT * FROM Surgeon WHERE chief = ?";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, true);
			ResultSet rs = prep.executeQuery();
			Integer id = rs.getInt("surgeonId");
			String name = rs.getString("surgeonName");
			String surname = rs.getString("surgeonSurname");
			String email = rs.getString("surgeonEmail");
			Boolean chief = rs.getBoolean("chief");			
			s = new Surgeon(id, name, surname, email, chief);
			rs.close();
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void changeChief(int id) {
		// TODO Auto-generated method stub
		try{
			String sql = "UPDATE Surgeon SET chief= ? WHERE chief= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, false);
			prep.setBoolean(2, true);
			prep.executeUpdate();	
			prep.close();
			String sql2 = "UPDATE Surgeon SET chief= ? WHERE surgeonId= ?";
			PreparedStatement prep2 = manager.getConnection().prepareStatement(sql2);
			prep2.setBoolean(1, true);
			prep2.setInt(2, id);
			prep2.executeUpdate();
			prep2.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteChief() {
		// TODO Auto-generated method stub
		Surgeon s= getChiefSurgeon();
		PreparedStatement prep;
		try {
			String sql = "UPDATE Surgeon SET chief=false WHERE surgeonId=?";
			prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, s.getSurgeonId());
			ResultSet rs = prep.executeQuery();
			rs.close();
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}