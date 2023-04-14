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
	public void addNurse(Nurse nurse) {
		// TODO Auto-generated method stub
		try{
			String sql = "INSERT INTO Nurse (nurseID, nurseName, nurseAvailability) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, nurse.getNurseID());
			prep.setString(2, nurse.getNurseName());
			prep.setBoolean(3, nurse.getNurseAvailability());
			
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
			String sql = "SELECT * FROM vets";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String speciality = rs.getString("speciality");
				
				Vet v = new Vet(id,name, speciality);
				vets.add(v);
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return vets;
	}

	@Override
	public void assign(int nurseID, int surgeonID) {
		// TODO Auto-generated method stub
		
	}
	
}
