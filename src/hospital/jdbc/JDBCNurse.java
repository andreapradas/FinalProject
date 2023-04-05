package hospital.jdbc;

public class JDBCNurse implements NurseManager {
	
	private JDBCManager manager;
	
	public JDBCNurse(JDBCManager m)
	{
		this.manager = m;
	}
	
	//Override todos los métodos de NurseManager
}
