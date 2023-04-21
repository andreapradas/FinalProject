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
		
	public List<SurgeonVacation> getAllVacations () {
		List<SurgeonVacation> surgeonVacations= new ArrayList<SurgeonVacation>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM surgeonVacation";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
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
	
	public List<SurgeonVacation> getSurgeonReservedVacation (int id) {
		List<SurgeonVacation> surgeonVacations= new ArrayList<SurgeonVacation>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT surgeon_name, starts, ends  FROM Surgeon INNER JOIN"
					+ "surgeonVacation ON Surgeon.surgeonID= surgeonVacation.surgeonID"
					+ " WHERE Surgeion.surgeonID= id";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
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
			String sql = "INSERT INTO surgeonVacation (starts, ends, surgeonID) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, sV.getStartDate());
			prep.setDate(2, sV.getEndDate());
			prep.setInt(3, sV.getSurgeonOnVacationId());
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<SurgeonVacation> getSurgeonsOnVacation(java.sql.Date start, java.sql.Date end){
		List<SurgeonVacation> surgeonVacations= new ArrayList<SurgeonVacation>();
		try {
			String sql = "SELECT * FROM Surgeon INNER JOIN surgeonVacation"
					+ "ON Surgeon.surgeonID = surgeonVacation.surgeonID "
					+ "WHERE((starts>= ? AND starts<= ?) OR (ends>= ? AND ends<= ?))";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);;
			prep.setDate(1, start);
			prep.setDate(2, end);
			prep.setDate(3, start);
			prep.setDate(4, end);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				Integer vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
				Integer surgId = rs.getInt("surgeonID");
				SurgeonVacation vac= new SurgeonVacation(vacId, starts, ends, surgId);
				surgeonVacations.add(vac);
			}
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return surgeonVacations;
	}
	
	public void modifySurgeonVacation(int vacationId, java.sql.Date start, java.sql.Date end) {
		try {
			String sql = "UPDATE surgeonVacation SET starts=?, ends=? WHERE vacationId=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, start);
			prep.setDate(2, end);
			prep.setInt(3, vacationId);
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getSurgeonVacationId(int surgeonId, java.sql.Date start, java.sql.Date end) {
		Integer vacId= null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT vacationId FROM surgeonVacation WHERE surgeonID= surgeonId"
					+ " AND starts= start AND ends= end";
			ResultSet rs = stmt.executeQuery(sql);
			vacId= rs.getInt("vacationId");
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vacId;
	}
	
	public void deleteSurgeonVacationById(int vacationId) {
		try {
			
			String sql = "DELETE FROM surgeonVacation WHERE vacationId=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, vacationId);
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
