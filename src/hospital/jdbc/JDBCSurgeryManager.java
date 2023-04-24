package hospital.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import hospital.ui.Menu;

import hospital.ifaces.SurgeryManager;
import hospital.pojos.Surgery;


public class JDBCSurgeryManager implements SurgeryManager{
	
	private JDBCManager manager;
	
	public JDBCSurgeryManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	public void addSurgery(Surgery s) {
		try{
			String sql = "INSERT INTO surgery (surgeryId, surgeryType, surgeryDate, startHour, "
					+ "endHour, done, patientId, surgeonId, roomId) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, s.getSurgeryId());
			prep.setString(2, s.getSurgeryType());
			//prep.setInt(3, s.getDuration());
			prep.setDate(3, s.getSurgeryDate());
			prep.setTime(4, s.getStartHour());
			prep.setTime(5, s.getEndHour());
			prep.setBoolean(6, s.getDone());
			
			prep.setInt(7, s.getPatientId());
			prep.setInt(8, s.getSurgeonId());
			prep.setInt(9, s.getRoomId());
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public List<Surgery> getListOfSurgeries(){
		List<Surgery> surgeries = new ArrayList<Surgery>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM surgery";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer surgeryId = rs.getInt("surgeryId");
				String surgeryType = rs.getString("surgeryType");
				//Integer duration = rs.getInt("duration");
				Date surgeryDate = rs.getDate("surgeryDate");
				Time startHour = rs.getTime("startHour");
				Time endHour = rs.getTime("endHour");
				Integer patientId = rs.getInt("patientId");
				Integer surgeonId = rs.getInt("surgeonId");
				Integer roomId = rs.getInt("roomId");
				
				
				Surgery s = new Surgery(surgeryId,surgeryType,surgeryDate, startHour, endHour, patientId, surgeonId, roomId);
				surgeries.add(s);//Add the new surgery to the list
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return surgeries;
	}
	
	
	@Override 
	public void createSurgery(Surgery s) {
		try{
			String sql = "INSERT INTO surgery (surgeryId, surgeryType, surgeryDate, startHour, endHour,"
					+ "patientId, surgeonId, roomId ) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, s.getSurgeryId());
			prep.setString(2, s.getSurgeryType());
			//prep.setInt(3, s.getDuration());
			prep.setDate(4, s.getSurgeryDate());
			prep.setTime(5, s.getStartHour());
			prep.setTime(6, s.getEndHour());
			prep.setBoolean(7, s.getDone());
			prep.setInt(8, s.getPatientId());
			prep.setInt(9, s.getSurgeonId());
			prep.setInt(10, s.getRoomId());
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void modifySurgery(int surgeryId, int parameterChange) {
		Surgery s = getSurgeryById(surgeryId);
		switch(parameterChange) {
		case 1: //Cambiar el surgeryId
			//s.setSurgeryId(askSurgeryId());
			break;
		case 2: //surgeryType
			//s.setSurgeryType(askSurgeryType());
			break;
		case 3://surgeryDate
			//s.setSurgeryDate(askSurgeryDate());
			break;
		case 4://startHour y endHour
			s.getStartHour();
			s.getEndHour();
			break;
		}
	}
	
	@Override
	public Surgery getSurgeryById(int surgeryId) {
		// TODO Auto-generated method stub
		
		Surgery s = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM surgery WHERE id=" + surgeryId;
			ResultSet rs = stmt.executeQuery(sql);
			
			//Integer surgeryId = rs.getInt("surgeryId");
			String surgeryType = rs.getString("surgeryType");
			//Integer duration = rs.getInt("duration");
			Date day = rs.getDate("day");
			Time startHour = rs.getTime("startHour");
			Time endHour = rs.getTime("endHour");
			//Boolean done = rs.getBoolean("done");
			Integer patientId = rs.getInt("patientId");
			Integer surgeonId = rs.getInt("surgeonId");
			Integer roomId = rs.getInt("roomId");
			
			s = new Surgery(surgeryId, surgeryType, day, startHour, endHour, patientId, surgeonId, roomId);
			
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return s;
	}

	
	
}
