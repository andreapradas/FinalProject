package hospital.jdbc;

import java.sql.PreparedStatement;

import hospital.ifaces.SurgeonManager;

public class JDBCSurgeonManager implements SurgeonManager{
	private JDBCManager manager;
	
	public JDBCSurgeonManager(JDBCManager m)
	{
		this.manager = m;
	}
	

	
}