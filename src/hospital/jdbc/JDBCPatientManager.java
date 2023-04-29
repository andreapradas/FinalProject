package hospital.jdbc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.PatientManager;
import hospital.pojos.OperatingRoom;
import hospital.pojos.Patient;

public class JDBCPatientManager implements PatientManager{
	private JDBCManager manager;
	
	public JDBCPatientManager(JDBCManager m)
	{
		this.manager = m;
	}
	
	@Override
	public void addPatient(Patient p) {
		try{
			String sql = "INSERT INTO Patient (patientName, phoneNumber) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getPatientName());
			prep.setInt(2, p.getPhoneNumber());
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Patient> getListOfPatients(){
		List<Patient> patients = new ArrayList<Patient>();
			try {
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM Patient";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					Integer id = rs.getInt("patientId");
					String name = rs.getString("patientName");
					String surname = rs.getString("patientSurname");
					Integer phoneNumber = rs.getInt("phoneNumber");
					
					Patient pat = new Patient(id, name, surname,phoneNumber);
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
		List <Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM Patient WHERE patientName =?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,patientName);
			ResultSet rs = prep.executeQuery(sql);
			
			while(rs.next())
			{
				String name = rs.getString("patientName");
				String surname = rs.getString("patientSurname");
				Integer phoneNumber = rs.getInt("phoneNumber");
				
				Patient pat = new Patient(name, surname,phoneNumber);
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
	public void updatePhoneNumber(int pat_id, int phoneNumber) {
		try {
			String sql = "UPDATE Patient SET phoneNumber=? WHERE id=?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, phoneNumber);
			prep.setInt(2, pat_id);
			prep.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Patient getPatientByName(String patientName) {
		try {
			Patient p = null;
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient WHERE patientName=" + patientName;
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer patientId = rs.getInt("patientId");
			String patientSurname = rs.getString("patientSurname");
			Integer phoneNumber = rs.getInt("phoneNumber");

			p = new Patient(patientId, patientName, patientSurname, phoneNumber);				
			
			rs.close();
			stmt.close();
			return p;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Patient getPatientBySurname(String patientSurname) {
		Patient p = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient WHERE patientName=" + patientSurname;
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer patientId = rs.getInt("patientId");
			String patientName = rs.getString("patientName");
			Integer phoneNumber = rs.getInt("phoneNumber");

			p = new Patient(patientId, patientName, patientSurname, phoneNumber);				
			
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return p;
	}
}
