package hospital.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.NurseVacationManager;
import hospital.pojos.Nurse;
import hospital.pojos.NurseVacation;

public class JDBCNurseVacationManager implements NurseVacationManager{
	private JDBCManager manager;
	
	public JDBCNurseVacationManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	@Override
	public List<NurseVacation> getAllVacations () {
		List<NurseVacation> nurseVacations= new ArrayList<NurseVacation>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM nurseVacation";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
				Integer nurseId = rs.getInt("nurseId");
			
				NurseVacation vac= new NurseVacation(vacId, starts, ends, nurseId);
				nurseVacations.add(vac);
			}
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return nurseVacations;	
	}
	
	@Override
	public List<NurseVacation> getNurseReservedVacation (int id) {
		List<NurseVacation> nurseVacations= new ArrayList<NurseVacation>();
		try {
			String sql = "SELECT Nurse.nurseId, starts, ends, vacationId  FROM Nurse INNER JOIN "
					+ "nurseVacation ON (Nurse.nurseId= nurseVacation.nurseId)"
					+ " WHERE Nurse.nurseId= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);	
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				Integer vacId = rs.getInt("vacationId");
				java.sql.Date starts = rs.getDate("starts");
				java.sql.Date ends = rs.getDate("ends");
				Integer nurseId = rs.getInt("nurseId");
			
				NurseVacation vac= new NurseVacation(vacId, starts, ends, nurseId);
				nurseVacations.add(vac);
			}
			rs.close();
			prep.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return nurseVacations;	
	}
	
	@Override
	public void addVacation(NurseVacation nV) {
		try{
			String sql = "INSERT INTO nurseVacation (starts, ends, nurseId) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, nV.getStartDate());
			prep.setDate(2, nV.getEndDate());
			prep.setInt(3, nV.getNurseOnVacationId());
			prep.executeUpdate();			
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Nurse> getNursesOnVacation(Date start, Date end){
		List<Nurse> nurses= new ArrayList<Nurse>();
		try {
			String sql = "SELECT Nurse.* FROM nurseVacation INNER JOIN Surgeon "
					+ "ON Nurse.nurseId=nurseVacation.nurseId "
					+ "WHERE (starts >=? AND starts <=?) OR (ends >=? AND ends <=?) OR (starts <=? AND ends >=?)";
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
				String name = rs.getString("nurseName");
				String surname = rs.getString("nurseSurname");
				Integer nurseId = rs.getInt("nurseId");
				String email = rs.getString("nurseEmail");
				Nurse n= new Nurse(nurseId, name, surname ,email);
				nurses.add(n);
			}
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return nurses;
	}
	
	@Override
	public List<Nurse> getNursesOnVacation(Date date){
		return getNursesOnVacation(date, date);
	}
	
	@Override
	public void modifyNurseVacation(int vacationId, Date start, Date end) {
		try {
			String sql = "UPDATE nurseVacation SET starts=?, ends=? WHERE vacationId=?;";
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
	public void deleteNurseVacationById(int vacationId) {
		try {
			
			String sql = "DELETE FROM nurseVacation WHERE vacationId=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, vacationId);
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int countNurseVacations(int id) {
		// TODO Auto-generated method stub
		int countVac = 0;
		try {
			String sql = "SELECT COUNT(vacationId) AS count FROM nurseVacation WHERE nurseId= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			countVac= rs.getInt("count");
			rs.close();
			prep.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return countVac;
	}
}
