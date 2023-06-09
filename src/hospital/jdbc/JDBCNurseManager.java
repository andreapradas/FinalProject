package hospital.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
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
		try{
			String sql = "INSERT INTO Nurse (nurseName, nurseSurname, nurseEmail) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, n.getNurseName());
			prep.setString(2, n.getNurseSurname());
			prep.setString(3, n.getEmail());
			prep.executeUpdate();			
			prep.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Nurse> getListOfNurses() {
		List<Nurse> ListOfNurses = new ArrayList<Nurse>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Nurse";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int id = rs.getInt("nurseId");
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
	public String getNameById(int id) {
		String name= null;
		try {			
			String sql = "SELECT nurseName FROM Nurse WHERE nurseId= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			name = rs.getString("nurseName");
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public void deleteNurseByID(int nurseId) {
		try {
			String sql = "DELETE FROM Nurse WHERE nurseId=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,nurseId);
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Nurse getNurseById(int nurseId) {
		Nurse n = null;
		try {
			String sql = "SELECT * FROM Nurse WHERE nurseId=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,nurseId);
			ResultSet rs = prep.executeQuery();
			int id = rs.getInt("nurseId");
			String name = rs.getString("nurseName");
			String surname = rs.getString("nurseSurname");
			String email = rs.getString("nurseEmail");
			n= new Nurse (id, name, surname, email);
			rs.close();
			prep.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int getIdByEmail(String email) {
		int id = 0;
		try {
			String sql = "SELECT nurseId FROM Nurse WHERE nurseEmail= LTRIM(RTRIM(?))";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs = prep.executeQuery();
			id = rs.getInt("nurseId");
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
		}
		return id;
	}
	
	@Override
	public List<Nurse> getNursesAssignedThisDay(Date date) {
		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			String sql = "SELECT Nurse.* FROM worksWith INNER JOIN Nurse ON "
					+ "Nurse.nurseId= worksWith.nurseId "
					+ "WHERE dateOfWork= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, date);
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				int nurseId = rs.getInt("nurseId");
				String name = rs.getString("nurseName");
				String surname = rs.getString("nurseSurname");
				String email = rs.getString("nurseEmail");
				Nurse s = new Nurse(nurseId, name, surname, email);
				nurses.add(s);
			}
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return nurses;
	}
}
