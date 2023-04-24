package hospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
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
				System.out.println("5. Get surgeons on vacation any day of the given period");
				System.out.println("6. Get all vacations");
				System.out.println("7. Delete vacation");
				System.out.println("8. Get list of surgeons");
				System.out.println("9. Change chief surgeon");
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
				case 6:
					getAllVacations();
					break;
				case 7:
					deleteVacations();
					break;
				case 8:
					getAllSurgeons();
					break;
				case 9:
					changeChiefSurg();
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
	
	private static void changeChiefSurg() {
	// TODO Auto-generated method stub
		System.out.println("The chief is going to be changed and the previous is not going to be deleted as chief");
		System.out.println("Type the new chief id");
		Integer chiefId =  Integer.parseInt(reader.readLine());
		surgeonManager.deleteSurgeonVacationById(vacId);
	
	}

	private static void deleteVacations() throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		System.out.println("Type the vacation id");
		Integer vacId =  Integer.parseInt(reader.readLine());
		surgeonVacationManager.deleteSurgeonVacationById(vacId);
	}

	public static void getAllVacations() throws Exception
	{
		List<SurgeonVacation> sVacations = new ArrayList<SurgeonVacation>();
		try {
			sVacations = surgeonVacationManager.getAllVacations();
			int i;
			for(i=0; i< sVacations.size(); i++)
			{
				
				System.out.print(sVacations.get(i).toString());
				System.out.println(" Surgeon id: "+ sVacations.get(i).getSurgeonId());
			}			
		}catch (Exception e) {
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
	
	public static void getAllSurgeons() throws Exception
	{
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
			surgeons = surgeonManager.getListOfSurgeons();
			int i;
			for(i=0; i< surgeons.size(); i++)
			{
				System.out.println(surgeons.get(i).toString());
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createSurgeon() throws Exception
	{
		Surgeon s;
		System.out.println("Type the name of the surgeon:");
		String name =  reader.readLine();
		Boolean chief;
		if (surgeonManager.getChiefSurgeon()== null) {
			while(true) {
				System.out.println("Is it a chief surgeon? (Y/N)");
				String response =  reader.readLine();
				if(response.equals("Y") || response.equals("y")) {
					System.out.println(response);
					chief= true;
					break;
				} else if(response.equals("N") || response.equals("n")) {
					chief= false;
					break;
				}
				System.out.println("Please, answer with the correct pattern");
			}
		}
		else {
			chief= false;
		}
		System.out.println("Type the email of the surgeon:");
		String email =  reader.readLine();
		s= new Surgeon(name, email,chief);
		surgeonManager.addSurgeon(s);		
	}
	
	
	public Integer askSurgeryId() {
		System.out.println("Type the new surgeryId: ");
		try {
			Integer surgeryId = Integer.parseInt(reader.readLine());
			return surgeryId;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String askSurgeryType() {
		System.out.println("Type the new surgeryType: ");
		try {
			String surgeryType = reader.readLine();
			return surgeryType;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public Date askSurgeryDate() {
		System.out.println("Type the new date for the surgery: ");
		try {
			System.out.println("Day: ");
			Integer day = Integer.parseInt(reader.readLine());
			System.out.println("Month: ");
			Integer month = Integer.parseInt(reader.readLine());
			System.out.println("Year: ");
			Integer year = Integer.parseInt(reader.readLine());
			Date surgeryDate = new Date(year, month, day);
			
			return surgeryDate;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	


	@SuppressWarnings("deprecation")
	public static void createSurgeonVacation() throws Exception
	{
		System.out.println("Type the id of the surgeon:");
		Integer surgId =  Integer.parseInt(reader.readLine());
		//System.out.println("Vacations" +surgeonVacationManager.countSurgeonVacations(surgId));
		if(surgeonVacationManager.countSurgeonVacations(surgId)==2) {
			throw new Exception();
		}		
		java.sql.Date start = null;
		Integer year;
		while(true) {
			System.out.println("Type the year:");
			year = Integer.parseInt(reader.readLine());
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
			year= year-1900;
			System.out.println(option);
			switch(option) {
			case 1:
				start= new java.sql.Date(year, 5, 1);
				break;
			case 2:
				start= new java.sql.Date(year, 5, 16);
				break;
			case 3:
				start= new java.sql.Date(year, 6, 1);
				break;
			case 4:
				start= new java.sql.Date(year, 6, 16);
				break;
			case 5:
				start= new java.sql.Date(year, 7, 1);
				break;
			case 6:
				start= new java.sql.Date(year, 7, 16);
				break;
			}
		} while (option< 1 || option> 6);
		java.sql.Date end= new java.sql.Date (start.getTime() + (1000 * 60 * 60 * 24 * 15));
		SurgeonVacation sVac= new SurgeonVacation(start, end, surgId);
		System.out.print(sVac.toString());
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
		java.sql.Date start= new java.sql.Date(startYear-1900, startMonth-1, startDay);
		java.sql.Date end= new java.sql.Date(endYear-1900, endMonth-1, endDay);
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
		if(start.compareTo(end)>0) {
			throw new Exception ();
		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("End date cannot be after start date");
			e.printStackTrace();
		}
		try {
			surgeons = surgeonVacationManager.getSurgeonsOnVacation(start, end);;
			int i;
			for(i=0; i< surgeons.size(); i++)
			{
				System.out.println(surgeons.get(i).toString());
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
