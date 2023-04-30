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

//	@Override
//	public void addWorksWith(WorksWith wW) {
//		// TODO Auto-generated method stub
//		try{
//			String sql = "INSERT INTO worksWith (teamId, dateOfWork, surgeonID, nurseId) VALUES (?,?,?,?)";
//			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
//			prep.setInt(1, wW.getTeamID());
//			prep.setDate(2, wW.getDate());
//			prep.setInt(3, wW.getSurgeonID());
//			prep.setInt(4, wW.getNurseID());
//			prep.executeUpdate();			
//					
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public List<WorksWith> getListOfWorksWith() {
		// TODO Auto-generated method stub
		List<WorksWith> ListOfWW = new ArrayList<WorksWith>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM worksWith";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Integer teamID = rs.getInt("teamID");
				Integer surgeonID = rs.getInt("surgeonID");
				Integer nurseID = rs.getInt("nurseID");
				Date date = rs.getDate("dateOfWork");
				WorksWith ww = new WorksWith(teamID, surgeonID, nurseID, date);
				ListOfWW.add(ww);
			}
			rs.close();
			stmt.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ListOfWW;
	}


	@Override
	public WorksWith getWorksWithByID(int teamID) {
		// TODO Auto-generated method stub
		WorksWith ww = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM WorksWith WHERE id=" + teamID;
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("teamID");
			Integer surgeonID = rs.getInt("surgeonID");
			Integer nurseID = rs.getInt("nurseID");
			Date date = rs.getDate("date");
			ww = new WorksWith(id, surgeonID, nurseID, date);				
			
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return ww;
	}

	@Override
	public void deleteWorksWithByID(int teamID) {
		// TODO Auto-generated method stub
		try {
			
			String sql = "DELETE FROM ListOfWW WHERE id=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,teamID);
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
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
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void assign(int nurseId, int surgeonID, Date date) {
		// TODO Auto-generated method stub
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
