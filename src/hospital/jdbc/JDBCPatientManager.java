package hospital.jdbc;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.PatientManager;
import hospital.pojos.Patient;

public class JDBCPatientManager implements PatientManager{
	private JDBCManager manager;
	
	public JDBCPatientManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	@Override
	public void addPatient(Patient p) throws Exception{
		String sql = "INSERT INTO Patient (patientName, patientSurname,phoneNumber) VALUES (?,?,?)";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setString(1, p.getPatientName());
		prep.setString(2, p.getPatientSurname());
		prep.setInt(3, p.getPhoneNumber());
		prep.executeUpdate();	
		prep.close();
	}
	
	@Override
	public void addPatient(Patient p, String fileName) throws Exception{
		String sql = "INSERT INTO Patient (patientName, patientSurname,phoneNumber, photo) VALUES (?,?,?,?)";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setString(1, p.getPatientName());
		prep.setString(2, p.getPatientSurname());
		prep.setInt(3, p.getPhoneNumber());
		File photo = new File(".\\photos\\" + fileName);
		InputStream streamBlob = new FileInputStream(photo);
		byte[] bytesBlob = new byte[streamBlob.available()];
		streamBlob.read(bytesBlob);
		streamBlob.close();
		prep.setBytes(4, bytesBlob);
		prep.executeUpdate();
		prep.close();
	}	
	
	@Override
	public List<Patient> getListOfPatients(){
		List<Patient> patients = new ArrayList<Patient>();
			try {
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM Patient ORDER BY patientName";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					int id = rs.getInt("patientId");
					String name = rs.getString("patientName");
					String surname = rs.getString("patientSurname");
					int phoneNumber = rs.getInt("phoneNumber");
					byte[] photo = rs.getBytes("photo");
					Patient pat = new Patient(id, name, surname,phoneNumber, photo);
					patients.add(pat);
				}
				rs.close();
				stmt.close();	
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return patients;
	}
	
	
	@Override
	public List<Patient> getListPatientByName(String patientName) {
		List <Patient> patients= new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM Patient WHERE UPPER(patientName) =UPPER(?) ORDER BY patientSurname";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,patientName);
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				String name = rs.getString("patientName");
				String surname = rs.getString("patientSurname");
				int phoneNumber = rs.getInt("phoneNumber");
				int id = rs.getInt("patientId");
				Patient pat = new Patient(id, name, surname,phoneNumber);
				patients.add(pat);
			}
			rs.close();
			prep.close();
			return patients;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void updatePhoneNumber(int pat_id, int phoneNumber) throws Exception {
		String sql = "UPDATE Patient SET phoneNumber=? WHERE patientId=?;";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, phoneNumber);
		prep.setInt(2, pat_id);
		prep.executeUpdate();
		prep.close();
	}
	
	
	@Override
	public Patient getPatientByName(String patientName) {
		try {
			Patient p = null;
			String sql = "SELECT * FROM patient WHERE UPPER(patientName)=UPPER(?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, patientName);
			ResultSet rs = prep.executeQuery();
			
			String name = rs.getString("patientName");
			int patientId = rs.getInt("patientId");
			String patientSurname = rs.getString("patientSurname");
			int phoneNumber = rs.getInt("phoneNumber");
			p = new Patient(patientId, name, patientSurname, phoneNumber);	
			rs.close();
			prep.close();
			return p;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Patient getPatientById(int id) {
		try {
			Patient p = null;
			String sql = "SELECT * FROM Patient WHERE patientId=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			String name = rs.getString("patientName");
			int patientId = rs.getInt("patientId");
			String patientSurname = rs.getString("patientSurname");
			int phoneNumber = rs.getInt("phoneNumber");
			p = new Patient(patientId, name, patientSurname, phoneNumber);	
			rs.close();
			prep.close();
			return p;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getPatCompleteNametById(int id) {
		try {
			String sql = "SELECT patientName, patientSurname FROM patient WHERE patientId=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			String name = rs.getString("patientName");
			String surname = rs.getString("patientSurname");
			String completeName= name + " " + surname;
			rs.close();
			prep.close();
			return completeName;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
