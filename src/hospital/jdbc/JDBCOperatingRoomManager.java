package hospital.jdbc;

import java.sql.PreparedStatement;
import java.util.List;

import hospital.pojos.OperatingRoom;

public class JDBCOperatingRoomManager {

	private JDBCManager manager;
	public JDBCOperatingRoomManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	//@Override
	public void addOperatingRoom(OperatingRoom r) {
		// TODO Auto-generated method stub
		try{
			String sql = "INSERT INTO operatingRoom (roomID, roomNumber, floor) VALUES (?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getRoomID());
			prep.setInt(2, r.getRoomNumber());
			prep.setInt(3, r.getRoomFloor());
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Override
	public List<OperatingRoom> getListOfOperatingRoom(){
		return null;
	}
	
	
}
