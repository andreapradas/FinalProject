package hospital.jdbc;

import java.util.List;
import java.sql.PreparedStatement;

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
		return null;
	}

	@Override
	public void assign(int nurseID, int surgeonID) {
		// TODO Auto-generated method stub
		
	}
	
}
