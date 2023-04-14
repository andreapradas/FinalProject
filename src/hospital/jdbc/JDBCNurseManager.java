package hospital.jdbc;

import hospital.ifaces.NurseManager;

public class JDBCNurseManager implements NurseManager {
	
	private JDBCManager manager;
	
	public JDBCNurse(JDBCManager m)
	{
		this.manager = m;
	}
	
	//Override todos los métodos de NurseManager
}
