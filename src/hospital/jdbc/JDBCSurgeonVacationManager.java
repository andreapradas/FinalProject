package hospital.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.SurgeonVacationManager;
import hospital.pojos.SurgeonVacation;

public class JDBCSurgeonVacationManager implements SurgeonVacationManager{
	private JDBCManager manager;
	
	public JDBCSurgeonVacationManager(JDBCManager m)
	{
		this.manager = m;
	}
		
	//public  getReservedVacation;
	public List<SurgeonVacation> getSurgeonReservedVacation (int id) {
		List<SurgeonVacation> surgeonVacations= new ArrayList<SurgeonVacation>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT surgeon_name, starts, ends  FROM Surgeon INNER JOIN"
					+ "surgeonVacation ON SurgeonID=SurgeonID WHERE SurgeonID= id";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer vacId = rs.getInt("vacationId");
				Date starts = rs.getDate("starts");
				Date ends = rs.getDate("ends");
				Integer surgId = rs.getInt("surgeonID");
			
				SurgeonVacation vac= new SurgeonVacation(vacId, starts, ends, surgId);
				surgeonVacations.add(vac);
			}
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return surgeonVacations;	
	}
	
	public void addVacation(SurgeonVacation sV) {
		try{
			String sql = "INSERT INTO surgeonVacation (starts, ends, surgeonID) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, sV.getStartDate());
			prep.setDate(2, sV.getEndDate());
			prep.setInt(3, sV.getSurgeonOnVacationId());
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
