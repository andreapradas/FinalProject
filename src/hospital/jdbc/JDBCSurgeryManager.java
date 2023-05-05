package hospital.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.SurgeryManager;
import hospital.pojos.Surgery;

public class JDBCSurgeryManager implements SurgeryManager {

	private JDBCManager manager;

	public JDBCSurgeryManager(JDBCManager m) {
		this.manager = m;
	}

	public void addSurgery(Surgery s) {
		try {
			String sql = "INSERT INTO Surgery (surgeryType, patientId, done) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, s.getSurgeryType());
			prep.setInt(2, s.getPatientId());
			prep.setBoolean(3, false);
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createSurgery(Surgery s) {
		try {
			String sql = "INSERT INTO surgery (surgeryId, surgeryType, surgeryDate, startHour,"
					+ "done, patientId, surgeonId, roomId ) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, s.getSurgeryId());
			prep.setString(2, s.getSurgeryType());
			prep.setDate(3, s.getSurgeryDate());
			prep.setTime(4, s.getStartHour());
			prep.setBoolean(5, s.getDone());
			prep.setInt(6, s.getPatientId());
			prep.setInt(7, s.getSurgeonId());
			prep.setInt(8, s.getRoomId());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void programTheSurgery(int surgeryId, Date surgeryDate, Time startHour, int surgeonId, int roomId) {
		try {
			String sql = "UPDATE Surgery SET surgeryDate=?, startHour=?, "
					+ " surgeonId=?, roomId=?, done=? WHERE surgeryId=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, surgeryDate);
			prep.setTime(2, startHour);
			prep.setInt(3, surgeonId);
			prep.setInt(4, roomId);
			prep.setBoolean(5, true);
			prep.setInt(5, surgeryId);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void modifySurgery(int surgeryId, String parameterChange, String newParameter) {
//		Surgery s = getSurgeryById(surgeryId);
//		try {
//			String sql;
//			PreparedStatement prep;
//			Date surgeryDate = null;
//			int startHour = 0;
//			if(parameterChange.equalsIgnoreCase("surgeryType")) {
//				s.setSurgeryType(newParameter);
//				sql = "UPDATE surgery SET " + parameterChange + " = ? WHERE surgeryId = " + surgeryId;
//			}else if(parameterChange.equalsIgnoreCase("surgeryDate")) {
//				surgeryDate= Date.valueOf(newParameter);
//				s.setSurgeryDate(surgeryDate);
//				sql = "UPDATE surgery SET" + surgeryDate + " = ? WHERE surgeryId = " + surgeryId;
//			}else {
//				startHour = Integer.valueOf(parameterChange);
//				s.setStartHour(startHour);
//				sql = "UPDATE surgery SET startHour= ? WHERE surgeryId=?;";
//				prep = manager.getConnection().prepareStatement(sql);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void deleteSurgery(int surgeryId) {
		try {
			String sql = "DELETE FROM Surgery WHERE surgeryId=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, surgeryId);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Surgery> getListOfSurgeries() {
		List<Surgery> surgeries = new ArrayList<Surgery>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Surgery";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int surgeryId = rs.getInt("surgeryId");
				String surgeryType = rs.getString("surgeryType");
				Date surgeryDate = rs.getDate("surgeryDate");
				Time startHour = rs.getTime("startHour");
				Boolean done = rs.getBoolean("done");
				int patientId = rs.getInt("patientId");
				int surgeonId = rs.getInt("surgeonId");
				int roomId = rs.getInt("roomId");
				Surgery s = new Surgery(surgeryId, surgeryType, surgeryDate, startHour, done, patientId, surgeonId,roomId);
				surgeries.add(s);
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return surgeries;
	}

	@Override
	public List<Surgery> getListOfSurgeriesNotDone() {
		List<Surgery> surgeriesNotDone = new ArrayList<Surgery>();
		try {
			String sql = "SELECT * FROM Surgery WHERE done= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, false);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer surgeryId = rs.getInt("surgeryId");
				String surgeryType = rs.getString("surgeryType");
				Date surgeryDate = rs.getDate("surgeryDate");
				Time startHour = rs.getTime("startHour");
				Boolean done = rs.getBoolean("done");
				int patientId = rs.getInt("patientId");
				int surgeonId = rs.getInt("surgeonId");
				int roomId = rs.getInt("roomId");

				Surgery s = new Surgery(surgeryId, surgeryType, surgeryDate, startHour, done, patientId, surgeonId,roomId);
				surgeriesNotDone.add(s);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return surgeriesNotDone;
	}

	@Override
	public Surgery getSurgeryById(int surgeryId) {
		Surgery s = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Surgery WHERE surgeryId=" + surgeryId;
			ResultSet rs = stmt.executeQuery(sql);

			surgeryId = rs.getInt("surgeryId");
			String surgeryType = rs.getString("surgeryType");
			Date surgeryDate = rs.getDate("surgeryDate");
			Time startHour = rs.getTime("startHour");
			Boolean done = rs.getBoolean("done");
			Integer patientId = rs.getInt("patientId");
			Integer surgeonId = rs.getInt("surgeonId");
			Integer roomId = rs.getInt("roomId");

			s = new Surgery(surgeryId, surgeryType, surgeryDate, startHour, done, patientId, surgeonId, roomId);

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
