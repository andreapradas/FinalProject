package hospital.jdbc;

import java.util.List;

import hospital.ifaces.NurseVacationManager;
import hospital.pojos.NurseVacation;

public class JDBCNurseVacationManager implements NurseVacationManager {

	private JDBCManager manager;
	
	public JDBCNurseVacationManager(JDBCManager m)
	{
		this.manager = m;
	}

	@Override
	public void addNurseVacation(NurseVacation nV) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NurseVacation> getListOfNurseVacation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assign(int nurseVacationID, int nurseID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNurseVacationByID(int iD) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNurseVacationByID(int ID) {
		// TODO Auto-generated method stub
		
	}
	
	//Override todos los métodos de NurseVacationManager
}
