package hospital.jdbc;

import hospital.ifaces.NurseVacationManager;

public class JDBCNurseVacationManager implements NurseVacationManager {

	private JDBCManager manager;
	
	public JDBCNurseVacation(JDBCManager m)
	{
		this.manager = m;
	}
	
	//Override todos los métodos de NurseVacationManager
}
