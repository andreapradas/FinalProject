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
		// TODO Auto-generated method stub
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
}
