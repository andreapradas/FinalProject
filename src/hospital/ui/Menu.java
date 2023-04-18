package hospital.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.*;
import hospital.jdbc.*;
import hospital.pojos.Patient;

public class Menu {

	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	private static SurgeonManager surgeonManager;
	private static SurgeonVacationManager surgeonVacationManager;
	private static PatientManager patientManager;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	JDBCManager jdbcManager = new JDBCManager();
	surgeonManager = new JDBCSurgeonManager(jdbcManager);
	surgeonVacationManager = new JDBCSurgeonVacationManager(jdbcManager);
	patientManager = new JDBCPatientManager(jdbcManager);
	
	
		try {
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add new patient");
				System.out.println("2. Get list of patients");
				System.out.println("0. exit");
	
				int choice = Integer.parseInt(reader.readLine());
				switch(choice)
				{
				case 1:
					createPatient();
					break;
				case 2:
					getPatients();
					break;
				case 0: 
					jdbcManager.disconnect();
					System.exit(0);
				default:
					break;
				}
			}while(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void createPatient() throws Exception
	{
		System.out.println("Type the name of the patient:");
		String name =  reader.readLine();
		System.out.println("Type the phone number");
		Integer phone =  Integer.parseInt(reader.readLine());
		
		Patient p = new Patient(name, phone);
		patientManager.addPatient(p);		
	}
	
	public static void getPatients() throws Exception
	{
		List<Patient> patients = new ArrayList<Patient>();
		try {
			patients = patientManager.getListOfPatients();
			int i;
			for(i=0; i< patients.size(); i++)
			{
				System.out.println(patients.get(i).toString());
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
