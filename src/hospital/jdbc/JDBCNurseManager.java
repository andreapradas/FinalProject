package hospital.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import hospital.ifaces.NurseManager;
import hospital.pojos.Nurse;


public class JDBCNurseManager implements NurseManager {
	
	private JDBCManager manager;
	
	public JDBCNurseManager(JDBCManager m)
	{
		this.manager = m;
	}

	@Override
	public void addNurse(Nurse n) {
		// TODO Auto-generated method stub
		try{
			String sql = "INSERT INTO Nurse (nurseId, nurseName, nurseAvailability) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, n.getNurseID());
			prep.setString(2, n.getNurseName());
			prep.setBoolean(3, n.getNurseAvailability());
			
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Nurse> getListOfNurses() {
		// TODO Auto-generated method stub
		List<Nurse> ListOfNurses = new ArrayList<Nurse>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Nurse";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("nurseID");
				String name = rs.getString("nurseName");
				Boolean availability = rs.getBoolean("nurseAvailability");
				
				Nurse n = new Nurse(id, name, availability);
				ListOfNurses.add(n);
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ListOfNurses;
	}

	@Override
	public void assign(int nurseID, int surgeonID) {
		// TODO Auto-generated method stub
		try{
			String sql = "INSERT INTO WorksWith (nurseID,surgeonID) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, nurseID);
			prep.setInt(2, surgeonID);		
			
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteNurseByID(int nurseID) {
		// TODO Auto-generated method stub
		try {
			
			String sql = "DELETE FROM ListOfNurses WHERE id=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,nurseID);
			
			prep.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Nurse getNurseByID(int nurseID) {
		// TODO Auto-generated method stub
		Nurse n = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM ListOfNurses WHERE id=" + nurseID;
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("nurseID");
			String name = rs.getString("nurseName");
			Boolean availability = rs.getBoolean("nurseAvailability");
			n = new Nurse(id, name, availability);				
			
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return n;
	
	}
	
}
