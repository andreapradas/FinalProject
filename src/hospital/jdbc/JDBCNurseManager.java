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
			String sql = "INSERT INTO Nurse (nurseName, nurseSurname, nurseEmail) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, n.getNurseName());
			prep.setString(2, n.getNurseSurname());
			prep.setString(3, n.getEmail());
			
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
				Integer id = rs.getInt("nurseId");
				String name = rs.getString("nurseName");
				String surname = rs.getString("nurseSurname");
				String email = rs.getString("nurseEmail");
				Nurse n = new Nurse(id, name, surname, email);
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
	public void assign(int nurseId, int surgeonID) {
		// TODO Auto-generated method stub
		try{
			String sql = "INSERT INTO WorksWith (nurseId,surgeonID) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, nurseId);
			prep.setInt(2, surgeonID);		
			
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteNurseByID(int nurseId) {
		// TODO Auto-generated method stub
		try {
			
			String sql = "DELETE FROM ListOfNurses WHERE id=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,nurseId);
			
			prep.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Nurse getNurseByID(int nurseId) {
		// TODO Auto-generated method stub
		Nurse n = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM ListOfNurses WHERE id=" + nurseId;
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("nurseId");
			String name = rs.getString("nurseName");
			String surname = rs.getString("nurseSurname");
			String email = rs.getString("nameEmail");
			n= new Nurse (id, name, surname, email);
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return n;
	
	}

	@Override
	public int getIdByEmail(String email) {
		int id = 0;
		try {
			String sql = "SELECT nurseId FROM Nurse WHERE nurseEmail= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs = prep.executeQuery();
			id = rs.getInt("nurseId");
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
}
