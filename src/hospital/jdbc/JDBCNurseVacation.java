package hospital.jdbc;

public class JDBCNurseVacation implements NurseVacationManager {

	private JDBCManager manager;
	
	public JDBCNurseVacation(JDBCManager m)
	{
		this.manager = m;
	}
	
	//Override todos los métodos de NurseVacationManager
}
