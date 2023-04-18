package hospital.jdbc;


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
	public void addPatient(Patient p) {
		try{
			String sql = "INSERT INTO Patient (patientName, phoneNumber) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getName());
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
					Integer phoneNumber = rs.getInt("phoneNumber");
					
					Patient pat = new Patient(id, name, phoneNumber);
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
	public void updatePhoneNumber(int pat_id, int phoneNumber) {
		// TODO Auto-generated method stub
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
}
