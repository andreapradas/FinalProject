package hospital.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.OperatingRoomManager;
import hospital.pojos.OperatingRoom;


public class JDBCOperatingRoomManager implements OperatingRoomManager {

	private JDBCManager manager;
	public JDBCOperatingRoomManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	@Override
	public void addOperatingRoom(OperatingRoom r) {
		try{
			String sql = "INSERT INTO operatingRoom (roomNumber, roomFloor, active) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getRoomNumber());
			prep.setInt(2, r.getRoomFloor());
			prep.setBoolean(3, r.getActive());
			prep.executeUpdate();		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<OperatingRoom> getListOfOperatingRoom(){
		List<OperatingRoom> rooms = new ArrayList<OperatingRoom>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM operatingRoom";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int roomId = rs.getInt("roomId");
				int roomNumber = rs.getInt("roomNumber");
				int roomFloor = rs.getInt("roomFloor");
				Boolean active = rs.getBoolean("active");
				OperatingRoom o = new OperatingRoom(roomId,roomNumber, roomFloor, active);
				rooms.add(o); //Add the room to the list
			}
			rs.close();
			stmt.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	
	//Return a list of ACTIVE rooms
	@Override
	public List<OperatingRoom> getListOfActiveOperatingRoom(){
		List<OperatingRoom> roomsActive = new ArrayList<OperatingRoom>();
		try {
			String sql = "SELECT * FROM operatingRoom WHERE active = ?";//Solo mostrar las que están activas
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, true);//Así solo selecciona a las ACTIVAS
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				int roomId = rs.getInt("roomId");
				int roomNumber = rs.getInt("roomNumber");
				int roomFloor = rs.getInt("roomFloor");
				Boolean active = rs.getBoolean("active");
				OperatingRoom o = new OperatingRoom(roomId,roomNumber, roomFloor, active);
				roomsActive.add(o); //Add the ACTIVE room to the list
			}
			rs.close();
			prep.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return roomsActive;
	}

	
	@Override
	public void updateActivity(int roomId, Boolean active) {
		try {
			String sql = "UPDATE operatingRoom SET active=? WHERE roomId=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, active);
			prep.setInt(2, roomId);
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	@Override
//	public void modifyOperatingRoom(int roomId, String parameterChange, String newParameter) {
//		OperatingRoom o = getRoomById(roomId);
//		try {
//			String sql;
//			PreparedStatement prep;
//			if(parameterChange.equalsIgnoreCase("roomNumber")) {
//				int roomNumber = Integer.valueOf(newParameter);
//				o.setRoomNumber(roomNumber);
//				sql = "UPDATE operatingRoom SET roomNumber=? WHERE roomId =?;";
//				prep = manager.getConnection().prepareStatement(sql);
//				prep.setInt(1, roomNumber);
//				prep.setInt(2, roomId);
//				prep.executeUpdate();
//				prep.close();
//			}else if(parameterChange.equalsIgnoreCase("roomFloor")) {
//				int roomFloor= Integer.valueOf(newParameter);
//				o.setRoomFloor(roomFloor);
//				sql = "UPDATE operatingRoom SET roomFloor= ? WHERE roomId =?;";
//				prep = manager.getConnection().prepareStatement(sql);
//				prep.setInt(1, roomFloor);
//				prep.setInt(2, roomId);
//				prep.executeUpdate();
//				prep.close();
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Override
//	public void deleteOperatingRoom(int roomId) {
//		try {
//			String sql = "DELETE FROM operatingRoom WHERE roomId=?;";
//			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
//			prep.setInt(1, roomId);
//			prep.executeUpdate();
//			prep.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

	
	@Override
	public OperatingRoom getRoomById(int roomId) {
		OperatingRoom o = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM operatingRoom WHERE roomId=" + roomId;
			ResultSet rs = stmt.executeQuery(sql);
			int roomNumber = rs.getInt("roomNumber");
			int roomFloor = rs.getInt("roomFloor");
			Boolean active = rs.getBoolean("active");
			o = new OperatingRoom(roomId, roomNumber, roomFloor, active);	
			rs.close();
			stmt.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return o;
	}
	
}
