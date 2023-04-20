package hospital.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.*;
import hospital.jdbc.*;
import hospital.pojos.Patient;
import hospital.pojos.Surgeon;
import hospital.pojos.SurgeonVacation;
import hospitalJPA.JPAUserManager;

public class Menu {

	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	private static SurgeonManager surgeonManager;
	private static SurgeonVacationManager surgeonVacationManager;
	private static PatientManager patientManager;
//	private static UserManager userManager;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	JDBCManager jdbcManager = new JDBCManager();
	surgeonManager = new JDBCSurgeonManager(jdbcManager);
	surgeonVacationManager = new JDBCSurgeonVacationManager(jdbcManager);
	patientManager = new JDBCPatientManager(jdbcManager);
	//userManager = new JPAUserManager();
	
		try {
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add new patient");
				System.out.println("2. Get list of patients");
				System.out.println("3. Add new surgeon");
				System.out.println("4. Add new surgeon vacation");
				System.out.println("5. Get surgeons on vacation (during a given period)");
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
				case 3:
					createSurgeon();
					break;
				case 4:
					createSurgeonVacation();
					break;
				case 5:
					getSurgeonsOnVacation();
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
	
	public static void createSurgeon() throws Exception
	{
		System.out.println("Type the name of the surgeon:");
		String name =  reader.readLine();
		Boolean chief;
		while(true) {
			System.out.println("Is it a chief surgeon? (Y/N)");
			String response =  reader.readLine();
			if(response== "Y" || response== "y") {
				chief= true;
				break;
			} else if(response== "N" || response== "n") {
				chief= false;
			}
			System.out.println("Please, answer with the correct pattern");
		}
		
		Surgeon s= new Surgeon(name, chief);
		surgeonManager.addSurgeon(s);		
	}


	@SuppressWarnings("deprecation")
	public static void createSurgeonVacation() throws Exception
	{
		System.out.println("Type the id of the surgeon:");
		Integer surgId =  Integer.parseInt(reader.readLine());
		Date start = null;
		Integer year;
		while(true) {
			System.out.println("Type the year:");
			year =  Integer.parseInt(reader.readLine());
			if(year.toString().length()== 4) {
				break;
			}
			System.out.println("Not valid year");
		}	
		Integer option;
		do {
			System.out.println("These are the vacation periods, please choose one of them:");
			System.out.println("1) 1 june to 15 june");
			System.out.println("2) 16 june to 30 june");
			System.out.println("3) 1 july to 15 july");
			System.out.println("4) 16 july to 30 july");
			System.out.println("5) 1 august to 15 august");
			System.out.println("6) 16 august to 30 august");
			option= Integer.parseInt(reader.readLine());
			switch(option) {
			case 1:
				start= new Date(year, 5, 1);
			case 2:
				start= new Date(year, 5, 16);
			case 3:
				start= new Date(year, 6, 1);
			case 4:
				start= new Date(year, 6, 16);
			case 5:
				start= new Date(year, 7, 1);
			case 6:
				start= new Date(year, 7, 16);
			}
		} while (option>= 1 && option<= 6);
		Date end= new Date (start.getTime() + (1000 * 60 * 60 * 24 * 15));
		SurgeonVacation sVac= new SurgeonVacation(start, end, surgId);
		surgeonVacationManager.addVacation(sVac);		
	}
	
	@SuppressWarnings("deprecation")
	public static void getSurgeonsOnVacation() throws Exception {
		System.out.println("Show all surgeons on vacation during any of these days");
		System.out.println("Type the start date:");
		Integer startYear;
		Integer startMonth;
		Integer startDay;
		do {
			System.out.println("Type the year:");
			startYear= Integer.parseInt(reader.readLine());
		} while (startYear.toString().length()!= 4);
		do {
			System.out.println("Type the month [1-12]:");
			startMonth= Integer.parseInt(reader.readLine());
		} while (startMonth<1 || startMonth>12);
		do {
			System.out.println("Type the day:");
			startDay= Integer.parseInt(reader.readLine());
		} while (startDay<1 || startMonth>31);
		
		System.out.println("Type the end date:");
		Integer endYear;
		Integer endMonth;
		Integer endDay;
		do {
			System.out.println("Type the year:");
			endYear= Integer.parseInt(reader.readLine());
		} while (endYear.toString().length()!= 4);
		do {
			System.out.println("Type the month [1-12]:");
			endMonth= Integer.parseInt(reader.readLine());
		} while (endMonth<1 || endMonth>12);
		do {
			System.out.println("Type the day:");
			endDay= Integer.parseInt(reader.readLine());
		} while (endDay<1 || endMonth>31);
		Date start= new Date(startYear, startMonth, startDay);
		Date end= new Date(endYear, endMonth, endDay);
		surgeonVacationManager.getSurgeonsOnVacation(start, end);
	
	}
	
}
