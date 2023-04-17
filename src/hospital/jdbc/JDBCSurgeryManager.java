package hospital.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

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
			String sql = "INSERT INTO surgery (surgeryId, surgeryType, duration, day, startHour, "
					+ "endHour, done, patientId, surgeonId, roomId) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, s.getSurgeryId());
			prep.setString(2, s.getSurgeryType());
			prep.setInt(3, s.getDuration());
			prep.setDate(4, s.getDay());
			prep.setTime(4, s.getStartHour());
			prep.setTime(5, s.getEndHour());
			prep.setBoolean(3, s.getDone());
			/*prep.setInt(3, s.get());
			prep.setInt(3, s.());
			prep.setInt(3, s.());*/
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
			java.sql.ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer surgeryId = rs.getInt("surgeryId");
				String surgeryType = rs.getString("surgeryType");
				Integer duration = rs.getInt("duration");
				Date day = rs.getDate("day");
				
				Vet v = new Vet(id,name, speciality);
				vets.add(v);
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return surgeries;
	}

	
	
}
