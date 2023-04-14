package hospital.jdbc;

import java.sql.PreparedStatement;

import hospital.ifaces.SurgeonManager;
import hospital.pojos.Patient;

public class JDBCSurgeon implements SurgeonManager{
	
	private JDBCManager manager;
	
	public JDBCSurgeon(JDBCManager m)
	{
		this.manager = m;
	}

	
}
	
