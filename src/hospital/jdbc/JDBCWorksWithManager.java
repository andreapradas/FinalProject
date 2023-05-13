package hospital.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.WorksWithManager;
import hospital.pojos.WorksWith;

public class JDBCWorksWithManager implements WorksWithManager {
	
	private JDBCManager manager;
	
	public JDBCWorksWithManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	@Override
	public int getNurseIdAssignedSurgeonDate(int sId, Date date) throws Exception {
		String sql = "SELECT nurseId FROM worksWith WHERE dateOfWork=? AND surgeonId=?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setDate(1, date);
		prep.setInt(2, sId);
		ResultSet rs = prep.executeQuery();
		int nurseId= rs.getInt("nurseId");
		prep.close();
		rs.close();
		return nurseId;
	}
	
	@Override
	public List<WorksWith> getListOfWorksWith(Date date) {//Metodo empleado al crear una SURGERY 
		List<WorksWith> ListOfWW = new ArrayList<WorksWith>();
		try {
			String sql = "SELECT * FROM worksWith WHERE dateOfWork=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, date);
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				int teamID = rs.getInt("teamID");
				int surgeonID = rs.getInt("surgeonID");
				int nurseID = rs.getInt("nurseID");
				WorksWith ww = new WorksWith(teamID, surgeonID, nurseID, date);
				ListOfWW.add(ww);
			}
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ListOfWW;
	}

	@Override
	public void assign(int nurseId, int surgeonID, Date date) {
		try{
			String sql = "INSERT INTO WorksWith (nurseId,surgeonID,dateOfWork) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, nurseId);
			prep.setInt(2, surgeonID);	
			prep.setDate(3, date);
			prep.executeUpdate();			
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
