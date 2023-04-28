package hospital.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
		// TODO Auto-generated method stub
		try{
			String sql = "INSERT INTO operatingRoom (roomID, roomNumber, floor) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getRoomId());
			prep.setInt(2, r.getRoomNumber());
			prep.setInt(3, r.getRoomFloor());
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
				Integer roomId = rs.getInt("roomId");
				Integer roomNumber = rs.getInt("roomNumber");
				Integer roomFloor = rs.getInt("roomFloor");
				Boolean active = rs.getBoolean("active");
				List<Boolean> hoursAvailable = null;//FALTA 
				
				OperatingRoom o = new OperatingRoom(roomId,roomNumber, roomFloor, active, hoursAvailable);
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
	
	@Override
	public void updateActivity(int roomId, Boolean active) {
		try {
			
			String sql = "UPDATE operatingRoom SET active=? WHERE roomId=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, active);
			prep.setInt(2, roomId);
			//Hace falta cambiar la lista de habitaciones al cambiar el active??
			prep.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void modifyOperatingRoom(int roomId, String parameterChange, String newParameter) {
		OperatingRoom o = getRoomById(roomId);
		try {
			String sql;
			if(parameterChange.equalsIgnoreCase("roomNumber")) {
				int roomNumber = Integer.valueOf(newParameter);
				o.setRoomNumber(roomNumber);
				sql = "UPDATE operatingRoom SET " + roomNumber + " = ? WHERE roomId = " + roomId;
			}else if(parameterChange.equalsIgnoreCase("roomFloor")) {
				int roomFloor= Integer.valueOf(newParameter);
				o.setRoomFloor(roomFloor);
				sql = "UPDATE operatingRoom SET" + roomFloor + " = ? WHERE roomId = " + roomId;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public OperatingRoom getRoomById(int roomId) {
		// TODO Auto-generated method stub
		
		OperatingRoom o = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM operatingRoom WHERE roomId=" + roomId;
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer roomNumber = rs.getInt("roomNumber");
			Integer roomFloor = rs.getInt("roomFloor");
			Boolean active = rs.getBoolean("active");
			List<Boolean> hoursAvailable = null;
			o = new OperatingRoom(roomId, roomNumber, roomFloor, active, hoursAvailable);				
			
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return o;
	}
	
	
}
