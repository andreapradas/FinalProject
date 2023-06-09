package hospital.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.SurgeonVacationManager;
import hospital.pojos.Surgeon;
import hospital.pojos.SurgeonVacation;

public class JDBCSurgeonVacationManager implements SurgeonVacationManager{
	private JDBCManager manager;
	
	public JDBCSurgeonVacationManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	@Override
	public List<SurgeonVacation> getAllVacations () {
		List<SurgeonVacation> surgeonVacations= new ArrayList<SurgeonVacation>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM surgeonVacation ORDER BY starts";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
				int surgId = rs.getInt("surgeonId");
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
	
	@Override
	public List<SurgeonVacation> getSurgeonReservedVacation (int id) {
		List<SurgeonVacation> surgeonVacations= new ArrayList<SurgeonVacation>();
		try {
			String sql = "SELECT Surgeon.surgeonId, starts, ends, vacationId  FROM Surgeon INNER JOIN "
					+ "surgeonVacation ON (Surgeon.surgeonId= surgeonVacation.surgeonId)"
					+ " WHERE Surgeon.surgeonId= ? AND starts > ? ORDER BY starts";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);	
			Date today = Date.valueOf(LocalDate.now());
			prep.setDate(2, today);
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				int vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
				int surgId = rs.getInt("surgeonId");
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
	
	@Override
	public void addVacation(SurgeonVacation sV) {
		try{
			String sql = "INSERT INTO surgeonVacation (starts, ends, surgeonId) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, sV.getStartDate());
			prep.setDate(2, sV.getEndDate());
			prep.setInt(3, sV.getSurgeonOnVacationId());
			prep.executeUpdate();			
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Surgeon> getSurgeonsOnVacation(java.sql.Date start, java.sql.Date end){
		List<Surgeon> surgeons= new ArrayList<Surgeon>();
		try {
			String sql = "SELECT Surgeon.* FROM surgeonVacation INNER JOIN Surgeon "
					+ "ON Surgeon.surgeonId=surgeonVacation.surgeonId "
					+ "WHERE ((starts >=? AND starts <=?) OR (ends >=? AND ends <=?) OR (starts <=? AND ends >=?)) "
					+ "ORDER BY surgeonName";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, start);
			prep.setDate(2, end);
			prep.setDate(3, start);
			prep.setDate(4, end);
			prep.setDate(5, start);
			prep.setDate(6, end);
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				String name = rs.getString("surgeonName");
				String surname = rs.getString("surgeonSurname"); 
				int surgId = rs.getInt("surgeonId");
				String email = rs.getString("surgeonEmail");
				Boolean chief = rs.getBoolean("chief");
				Surgeon s= new Surgeon(surgId, name, surname ,email, chief);
				surgeons.add(s);
			}
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return surgeons;
	}
	
	@Override
	public List<Surgeon> getSurgeonsOnVacation(java.sql.Date date){
		return getSurgeonsOnVacation(date, date);
	}
	
	@Override
	public void modifySurgeonVacation(int vacationId, java.sql.Date start, java.sql.Date end) {
		try {
			String sql = "UPDATE surgeonVacation SET starts=?, ends=? WHERE vacationId=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, start);
			prep.setDate(2, end);
			prep.setInt(3, vacationId);
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteSurgeonVacationById(int vacationId) {
		try {			
			String sql = "DELETE FROM surgeonVacation WHERE vacationId=? AND starts>?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, vacationId);
			Date today = Date.valueOf(LocalDate.now());
			prep.setDate(2, today);
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public int countSurgeonVacations(int id, int year) {
		int countVac = 0;
		try {
			String sql = "SELECT COUNT(vacationId) AS count FROM surgeonVacation WHERE surgeonId= ? AND starts BETWEEN ? AND ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			prep.setDate(2, new Date(year-1900, 0, 1));
			prep.setDate(3, new Date(year-1900, 11, 31));
			ResultSet rs = prep.executeQuery();
			countVac= rs.getInt("count");
			rs.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return countVac;
	}

	@Override
	public List<SurgeonVacation> getMyVacationsSurgeon(int id) {
		List<SurgeonVacation> surgeonVacations= new ArrayList<SurgeonVacation>();
		try {
			String sql = "SELECT * FROM surgeonVacation WHERE surgeonId = ? ORDER BY starts";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,id);	
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				int vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
				int surgId = rs.getInt("surgeonId");
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
}
