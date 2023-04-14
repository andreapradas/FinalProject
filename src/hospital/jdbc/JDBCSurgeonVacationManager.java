package hospital.jdbc;

import java.sql.PreparedStatement;

import hospital.pojos.SurgeonVacation;

public class JDBCSurgeonVacationManager {
	private JDBCManager manager;
	
	public JDBCSurgeonVacationManager(JDBCManager m)
	{
		this.manager = m;
	}
		
	//public  getReservedVacation;

	public void addVacation(SurgeonVacation sV) {
	try{
		String sql = "INSERT INTO surgeonVacation (starts, ends, surgeonID) VALUES (?,?,?,?)";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setLocalDate(1, sV.getStartDate());
		prep.setDate(2, sV.getEndDate());
		prep.setInt(3, sV.getSurgeonOnVacationId());
		prep.executeUpdate();			
				
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
