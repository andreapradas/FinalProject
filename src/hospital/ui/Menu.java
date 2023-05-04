package hospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hospital.ifaces.*;
import hospital.jdbc.*;
import hospital.pojos.Nurse;
import hospital.pojos.NurseVacation;
import hospital.pojos.OperatingRoom;
import hospital.pojos.Patient;
import hospital.pojos.Role;
import hospital.pojos.Surgeon;
import hospital.pojos.SurgeonVacation;
import hospital.pojos.Surgery;
import hospital.pojos.User;
import hospital.pojos.WorksWith;
import hospital.pojos.phoneException;
import hospitalJPA.JPAUserManager;

public class Menu {

	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	private static SurgeonManager surgeonManager;
	private static SurgeonVacationManager surgeonVacationManager;
	private static NurseVacationManager nurseVacationManager; 
	private static PatientManager patientManager;
	private static SurgeryManager surgeryManager;
	private static OperatingRoomManager operatingRoomManager;
	private static UserManager userManager;
	private static JDBCManager jdbcManager;
	private static NurseManager nurseManager;
	private static WorksWithManager worksWithManager;
	private static User u;
	
	public static void main(String[] args) {

	jdbcManager = new JDBCManager();
	surgeonManager = new JDBCSurgeonManager(jdbcManager);
	surgeonVacationManager = new JDBCSurgeonVacationManager(jdbcManager);
	patientManager = new JDBCPatientManager(jdbcManager);
	userManager = new JPAUserManager();
	nurseManager = new JDBCNurseManager(jdbcManager);
	worksWithManager = new JDBCWorksWithManager(jdbcManager);
	surgeryManager = new JDBCSurgeryManager(jdbcManager);
	nurseVacationManager = new JDBCNurseVacationManager(jdbcManager);
	operatingRoomManager = new JDBCOperatingRoomManager(jdbcManager);
	
		try {
			userManager.getRole("surgeon").setUsers(userManager.getSpecificUsers("surgeon"));
			userManager.getRole("nurse").setUsers(userManager.getSpecificUsers("nurse"));
			userManager.getRole("chiefSurgeon").setUsers(userManager.getSpecificUsers("chiefSurgeon"));
			do {
				System.out.println("Choose an option");
				System.out.println("1. Sign in");
				System.out.println("2. Log in");
				System.out.println("3. List of users");	
				System.out.println("0. exit");
				int choice = Integer.parseInt(reader.readLine());
				switch(choice)
				{
				case 1:
					signIn();
					break;
				case 2:
					logIn();
					break;
				case 3:
					getUsers();
					break;
				case 0: 
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				default:
					System.out.println("Please choose one of the options");
					break;
				}
			}while(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getUsers() {
		System.out.format("%-20s %s\n", "Email", "Role");
		System.out.println("------------------------------------");
		for(User u: userManager.getUsers()) {
            System.out.format("%-20s %s\n", u.getEmail(), u.getRole().getName());
		}
		System.out.println();
	}

	private static void chiefSurgeonMenu() throws Exception{
		try {
			do {
				System.out.println("\nChoose an option");
				System.out.println(" 1. Add new patient");
				System.out.println(" 2. Get list of patients");
				System.out.println(" 3. Add new vacation");
				System.out.println(" 4. Modify vacation");
				System.out.println(" 5. Delete vacation");
				System.out.println(" 6. Get all employee vacations");
				System.out.println(" 7. Get list of surgeons");
				System.out.println(" 8. Get list of nurses");
				System.out.println(" 9. Get list of all employees");
				System.out.println("10. Add new operating room");
				System.out.println("11. Change chief surgeon");
				System.out.println("12. Get surgeons on vacation any day of the given period");
				System.out.println("13. Delete account");
				System.out.println("14. Create surgery");
				System.out.println("15. Get list of surgeries");
				System.out.println("16. Create schedule");
				System.out.println("17. Get my vacations");
		
				System.out.println("18. Log out");
				System.out.println(" 0. exit");
				
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
					createVacation("surgeon"); //estamos dentro del menu de chiefSurgeons por lo que el
					// que este aqui metido va a ser un chief y solo podra añadir sus vacaciones
					break;
				case 4:
					modifyVacation("surgeon");
					break;
				case 5:
					deleteVacations("surgeon");
					break;
				case 6:
					getAllVacations();
					break;
				case 7:
					getAllSurgeons();
					break;
				case 8:
					getAllNurses();;
					break;
				case 9:
					getUsers();
					break;
				case 10:
					newOperatingRoom();
					break;
		 		case 11:
					changeChiefSurg();
					break;
				case 12: 
					getSurgeonsOnVacation();
					break;
				case 13:
					deletAccount("surgeon", surgeonManager.getIdByEmail(u.getEmail()), u.getEmail());
					main(null);
				case 14:
					createSurgery();
					break;
				case 15:
					getAllSurgeries();
					break;
				case 16:
					createSchedule();
					break;
				case 17:
					getMyVacations();
					break;
				case 18:
					main(null);
					break;
					
				case 0: 
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				default:
					break;
				}
			}while(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void surgeonMenu() throws Exception{
		try {
			do {System.out.println("\nChoose an option");
			System.out.println(" 1. Add new patient");
			System.out.println(" 2. Get list of patients");
			System.out.println(" 3. Add new vacation");
			System.out.println(" 4. Modify vacation");
			System.out.println(" 5. Delete vacation");
			System.out.println(" 6. Get all employee vacations");
			System.out.println(" 7. Get list of surgeons");
			System.out.println(" 8. Get list of nurses");
			System.out.println(" 9. Get list of all employees");
			System.out.println("10. Delete account");
			System.out.println("11. Create surgery");
			System.out.println("12. Get list of surgeries");
			System.out.println("13. Get my vacations");
	
			System.out.println("14. Log out");
			System.out.println(" 0. exit");
			
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
					createVacation("surgeon"); //estamos dentro del menu de Surgeons por lo que 
					//el que este aqui metido va a ser un surgeon y solo podra añadir sus vacaciones
					break;
				case 4:
					modifyVacation("surgeon");
					break;
				case 5:
					deleteVacations("surgeon");
					break;
				case 6:
					getAllVacations();
					break;
				case 7:
					getAllSurgeons();
					break;
				case 8:
					getAllNurses();;
					break;
				case 9:
					getUsers();
					break;
				case 10:
					deletAccount("surgeon", surgeonManager.getIdByEmail(u.getEmail()), u.getEmail());
					main(null);
					break;
				case 11:
					createSurgery();
					break;
				case 12: 
					getAllSurgeries();
					break;
				case 13:
					getMyVacations();
					break;
				
				case 14:
					main(null);
					break;
				case 0: 
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				default:
					break;
				}
			}while(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void NurseMenu() throws Exception{
		try {
			do {
				System.out.println("\nChoose an option");
				System.out.println(" 1. Get list of patients");
				System.out.println(" 2. Add new vacation");
				System.out.println(" 3. Modify vacation");
				System.out.println(" 4. Delete vacation");
				System.out.println(" 5. Get all employee vacations");
				System.out.println(" 6. Get list of surgeons");
				System.out.println(" 7. Get list of nurses");
				System.out.println(" 8. Get list of all employees");
				System.out.println(" 9. Delete account");
				System.out.println("10. Get my vacations");
				
				System.out.println("11. Log out");
				System.out.println(" 0. exit");
				//
				int choice = Integer.parseInt(reader.readLine());
				switch(choice)
				{
					case 1:
						getPatients();
						break;
					case 2:
						createVacation("nurse"); //estamos dentro del menu de nurse 
						//por lo que el que este aqui metido va a ser un nurse y solo podra
						//añadir sus vacaciones
						break;
					case 3:
						modifyVacation("nurse");
						break;
					case 4:
						deleteVacations("nurse");
						break;
					case 5:
						getAllVacations();
						break;
					case 6:
						getAllSurgeons();
						break;
					case 7:
						getAllNurses();;
						break;
					case 8:
						getUsers();
						break;
					case 9:
						deletAccount("nurse", nurseManager.getIdByEmail(u.getEmail()), u.getEmail());
						main(null);
						break;
					case 10:
						getMyVacations();
						break;
					case 11:
						main(null);
						break;
					case 0: 
						jdbcManager.disconnect();
						userManager.disconnect();
						System.exit(0);
					default:
						break;
				}
			}while(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void modifyVacation(String role) throws Exception {
		if(role.equals("surgeon")) {
			do {
				List<SurgeonVacation> surgVac= surgeonVacationManager.getSurgeonReservedVacation(surgeonManager.getIdByEmail(u.getEmail()));
				if(surgVac.size()<1) {
					System.out.println("\nYou do not have any vacations yet");
					break;
				}
				System.out.println(surgVac);
				System.out.println("Select the id of the vacation you want to modify");
				Integer vacId =  Integer.parseInt(reader.readLine());
				if(surgVac.get(0).getVacationId()== vacId || surgVac.get(1).getVacationId()== vacId) {
					System.out.println("Select the new vacation dates");
					Date start= selectStartDate();
					Date end= new java.sql.Date (start.getTime() + (1000 * 60 * 60 * 24 * 15));
					surgeonVacationManager.modifySurgeonVacation(vacId, start, end);
					break;
				} else {
					System.out.println("Incorrect vacationId\n");
				}
			}while(true);			
		} else if (role.equals("nurse")) {
			do {
				List<NurseVacation> nurseVac= nurseVacationManager.getNurseReservedVacation(nurseManager.getIdByEmail(u.getEmail()));
				if(nurseVac.size()<1) {
					System.out.println("\nYou do not have any vacations yet");
					break;
				}
				System.out.println(nurseVac);
				System.out.println("Select the id of the vacation you want to modify");
				Integer vacId =  Integer.parseInt(reader.readLine());
				if(nurseVac.get(0).getVacationId()== vacId || nurseVac.get(1).getVacationId()== vacId) {
					System.out.println("Select the new vacation dates");
					Date start= selectStartDate();
					Date end= new java.sql.Date (start.getTime() + (1000 * 60 * 60 * 24 * 15));
					nurseVacationManager.modifyNurseVacation(vacId, start, end);
				}else {
					System.out.println("Incorrect vacationId\n");
				}
			} while(true);
		}
	}

	private static void logIn() throws Exception{
		System.out.println("Email: ");
		String email= reader.readLine();
		System.out.println("Password: ");
		String password= reader.readLine();
		u= userManager.checkPassword(email, password);
		if(u!=null && u.getRole().getName().equals("surgeon")) {
			System.out.println("Surgeon Login Successful!\n");
			surgeonMenu();
		} else if(u!=null && u.getRole().getName().equals("nurse")) {
			System.out.println("Nurse Login Successful!\n");
			NurseMenu();
		} else if(u!=null && u.getRole().getName().equals("chiefSurgeon")) {
			System.out.println("ChiefSurgeon Login Successful!\n");
			chiefSurgeonMenu();
		} else {
			System.out.println("Incorrect user or password\n");
		}
	}
	
	private static void signIn() throws Exception{
		try {
			System.out.println("Choose your role: ");
			System.out.println("1. Surgeon");
			System.out.println("2. Nurse");
			System.out.println("3. Chief surgeon");
			Integer option= Integer.parseInt(reader.readLine());
			Role role = null;
			Boolean chief = null;
//			List<User> users= new ArrayList<User>();
			switch (option) {
				case 1:
					role= userManager.getRole("surgeon");
					chief= false;
					break;
				case 2: 
					role= userManager.getRole("nurse");
					break;
				case 3:
					if(userManager.getRole("chiefSurgeon").getUsers().size()!=0) { 
						System.out.println("There is already one chief, no more chief avaliables");
						main(null);				}
					else {
						role= userManager.getRole("chiefSurgeon");
					}
					chief= true;
					break;
			}
			System.out.println("Type your email: ");
			String email= reader.readLine();
			System.out.println("Type the password: ");
			String password= reader.readLine();
			System.out.println("Type your name:");
			String name =  reader.readLine();
			System.out.println("Type your surname:");
			String surname =  reader.readLine();
			Surgeon s;
			Nurse n;
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash= md.digest();
			User u= new User(email, hash, role);
			u.getRole().addUser(u);
		if(role.getName().equals("surgeon") || role.getName().equals("chiefSurgeon")){
			s= new Surgeon(name, surname, email,chief);
			surgeonManager.addSurgeon(s);
		}else if(role.getName().equals("nurse")){
			n= new Nurse(name, surname, email);
			nurseManager.addNurse(n);
		}
		System.out.println("User has been created correctly");
		userManager.newUser(u);
		System.out.println(u.getRole().getUsers());
		}
		catch (Exception e) {
			System.out.println("It was not created");
			main(null);
		}
	}
	
	private static void deletAccount(String role, int id, String email) {
		try {
			userManager.deletUser(email);
			if(role.equals("surgeon") || role.equals("chiefSurgeon")){
				deleteVacAutom(role);
				userManager.getRole(role).getUsers().remove(surgeonManager.getSurgeonById(id));	
				surgeonManager.deleteSurgeonByID(id);
			} else if(role.equals("nurse")){
				deleteVacAutom(role);
				userManager.getRole(role).getUsers().remove(nurseManager.getNurseById(id));	
				nurseManager.deleteNurseByID(id);
			}
			System.out.println("Your account has been deleted\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void createTeams(Date date) throws Exception {
		List<Nurse> nurses= avaliableNurses(date);
		List<Surgeon> surgeons= avaliableSurgeons(date);		
		if(nurses.size()>0 && surgeons.size()>0) {
			do {
				Integer nurseId;
				do {				
					System.out.println("\nThese are the avaliable nurses:");
					System.out.format("%-10s %-15s %-3s\n", "Name", "Surname", "Id");
					System.out.println("-----------------------------------------------------------");
					nurses.removeAll(nurseManager.getNursesAssignedThisDay(date));
					for(Nurse n: nurses) {
						System.out.format("%-10s %-15s %-3d\n", n.getNurseName(), n.getNurseSurname(), n.getNurseId());
					}
					System.out.print("\nPlease enter the nurse ID to assign: ");
					nurseId = Integer.parseInt(reader.readLine());
					if(!avaliableNurses(date).contains(nurseManager.getNurseById(nurseId))) {
						System.out.println("That nurse is not avaliable\n");
					}
					else {
						break;
					}
				} while(true);
				
				Integer surgeonId;
				do {
					System.out.println("\nThese are the avaliable surgeons:");
					System.out.format("%-10s %-15s %-3s\n", "Name", "Surname", "Id");
					System.out.println("-----------------------------------------------------------");
					surgeons.removeAll(surgeonManager.getSurgeonsAssignedThisDay(date));
					for(Surgeon s: avaliableSurgeons(date)) {
						System.out.format("%-10s %-15s %-3d\n", s.getName(), s.getSurname(), s.getSurgeonId());
					}
					System.out.print("\nPlease enter the surgeon ID to assign: ");
					surgeonId = Integer.parseInt(reader.readLine());
					if(!avaliableSurgeons(date).contains(surgeonManager.getSurgeonById(surgeonId))) {
						System.out.println("That surgeon is not avaliable");
					}else {
						break;
					}
				} while(true);
				worksWithManager.assign(nurseId, surgeonId, date);
				nurses.removeAll(nurseManager.getNursesAssignedThisDay(date));
				surgeons.removeAll(surgeonManager.getSurgeonsAssignedThisDay(date));
				System.out.print("\nDo you want to create another team (Y/N): ");
				String confirmation = reader.readLine();
				if(confirmation.equalsIgnoreCase("y") && nurses.size()>0 && surgeons.size()>0) {
				} else {
					System.out.println("No more surgeons or nurses avaliable\n");
					break;
				}
			}while(true);
		} else {
			System.out.println("No surgeons or nurses avaliable\n");
		}
	}

	private static void getAllNurses() throws Exception {
		System.out.format("%-10s %-15s %-20s %-3s\n", "Name", "Surname", "Email", "Id");
		System.out.println("----------------------------------------------------");
		try {
			for(Nurse n: nurseManager.getListOfNurses()) {
				System.out.format("%-10s %-15s %-20s %-3d\n", n.getNurseName(), n.getNurseSurname(), n.getEmail(), n.getNurseId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	private static void changeChiefSurg() {
		System.out.println("The chief is going to be changed and you are going to be deleted as chief");
		for(Surgeon s: surgeonManager.getListOfSurgeons()){
			if(s.equals(surgeonManager.getChiefSurgeon())){
				continue;
			}
            System.out.format("%-10s %-15s Id: %d\n", s.getName(), s.getSurname(), s.getSurgeonId());
        }
		System.out.println("\nType the new chief id");
		Integer newChiefId = null;
		try {
			newChiefId = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		if(userManager.getChief().getEmail().equals(surgeonManager.getEmailById(newChiefId))) {
			System.out.println("That's the actual chief, no changes done\n");
			
		} else if(surgeonManager.getListOfSurgeons().contains(surgeonManager.getSurgeonById(newChiefId))){
			surgeonManager.changeChief(newChiefId);
			String email= surgeonManager.getEmailById(newChiefId);
			userManager.changeChief(email);
			System.out.println("You will have to log in again in order to have the appropiate menu to your new role");
			main(null);
		} else {
			System.out.println("Not valid Id\n");
		}
	}

	private static void deleteVacations(String role) throws Exception{
		try {
			if(role.equals("surgeon")) {
				do {
					List<SurgeonVacation> surgVac= surgeonVacationManager.getSurgeonReservedVacation(surgeonManager.getIdByEmail(u.getEmail()));
					if(surgVac.size()<1) {
						System.out.println("\nYou do not have any vacations yet");
						break;
					}
					System.out.println(surgVac);
					System.out.println("Type the vacation id");
					Integer vacId =  Integer.parseInt(reader.readLine());
					if(surgVac.get(0).getVacationId()== vacId || surgVac.get(1).getVacationId()== vacId) {
						surgeonVacationManager.deleteSurgeonVacationById(vacId);
						break;
					} else {
						System.out.println("Incorrect vacationId\n");
					}
				}while(true);
			} else if (role.equals("nurse")) {
				do {
					List<NurseVacation> nurseVac= nurseVacationManager.getNurseReservedVacation(nurseManager.getIdByEmail(u.getEmail()));
					if(nurseVac.size()<1) {
						System.out.println("\nYou do not have any vacations yet");
						break;
					}
					System.out.println(nurseVac);
					System.out.println("Type the vacation id");
					Integer vacId =  Integer.parseInt(reader.readLine());
					if(nurseVac.get(0).getVacationId()== vacId || nurseVac.get(1).getVacationId()== vacId) {
						nurseVacationManager.deleteNurseVacationById(vacId);
						break;
					} else {
						System.out.println("Incorrect vacationId\n");
					}
				}while(true);
				
			}
		}catch(Exception e) {
		}
	}
	
	private static void deleteVacAutom(String role) throws Exception{
		try {
			if(role.equals("surgeon")) {
				do {
					List<SurgeonVacation> surgVac= surgeonVacationManager.getSurgeonReservedVacation(surgeonManager.getIdByEmail(u.getEmail()));
					if(surgVac.size()<1) {
						break;
					} else {
						surgeonVacationManager.deleteSurgeonVacations(surgeonManager.getIdByEmail(u.getEmail()));
						break;
					}
				}while(true);
			} else if (role.equals("nurse")) {
				do {
					List<NurseVacation> nurseVac= nurseVacationManager.getNurseReservedVacation(nurseManager.getIdByEmail(u.getEmail()));
					if(nurseVac.size()<1) {
						break;
					}else {
						nurseVacationManager.deleteNurseVacations(nurseManager.getIdByEmail(u.getEmail()));
						break;
					}
				}while(true);
				
			}
		}catch(Exception e) {
		}
	}

	public static void getAllVacations() throws Exception{
		List<SurgeonVacation> sVacations = new ArrayList<SurgeonVacation>();
		List<NurseVacation> nVacations = new ArrayList<NurseVacation>();
		try {
			sVacations = surgeonVacationManager.getAllVacations();
			nVacations = nurseVacationManager.getAllVacations();
			int i;
			for(i=0; i< sVacations.size(); i++)
			{
				System.out.print(sVacations.get(i).toString());
				System.out.println(" Surgeon name: "+ surgeonManager.getNameById(sVacations.get(i).getSurgeonId()));
			}	
			for(i=0; i< nVacations.size(); i++)
			{
				System.out.print(nVacations.get(i).toString());
				System.out.println(" Nurse name: "+ nurseManager.getNameById(nVacations.get(i).getNurseId()));
			}	
		}catch (Exception e) {
		}
	}

	public static void getMyVacations() throws Exception{
		try {
			List<SurgeonVacation> sVacations;
			List<NurseVacation> nVacations;
			if(u.getRole().getName().equals("surgeon") || u.getRole().getName().equals("chiefSurgeon")) {
				sVacations = new ArrayList<SurgeonVacation>();
				sVacations = surgeonVacationManager.getMyVacationsSurgeon(surgeonManager.getIdByEmail(u.getEmail()));
				if(sVacations.size()<1) {
					System.out.println("\nYou do not have any vacations yet");
				}
				for(int i=0; i< sVacations.size(); i++)
				{
					System.out.print(sVacations.get(i).toString());
				}
			}
			else if (u.getRole().getName().equals("nurse")) {
				nVacations = new ArrayList<NurseVacation>();
				nVacations = nurseVacationManager.getMyVacationsNurse(nurseManager.getIdByEmail(u.getEmail()));
				if(nVacations.size()<1) {
					System.out.println("\nYou do not have any vacations yet");
				}
				for(int i=0; i< nVacations.size(); i++)
				{
					System.out.print(nVacations.get(i).toString());
				}	
			}	
		}catch (Exception e) {
		}
	}
	
	public static void createPatient() throws phoneException{
		try {
			System.out.println("Type the name of the patient:");
			String name =  reader.readLine();
			System.out.println("Type the surname of the patient:");
			String surname =  reader.readLine();
			System.out.println("Type the phone number");
			Integer phone =  Integer.parseInt(reader.readLine());
			Patient patient = new Patient(name, surname, phone);
			patientManager.addPatient(patient);
			System.out.println("Patient created successfully\n");
		}
		catch (Exception e) {
			System.out.println("The phone number is incorrect, introduce the patient again:");
			createPatient();
		}
	}
	
	public static void getPatients() throws Exception{
		try {
			System.out.format("%-10s %-15s %-10s %-3s\n", "Name", "Surname", "PhoneNumber", "Id");
			System.out.println("----------------------------------------------------");
			for(Patient p: patientManager.getListOfPatients()) {
				System.out.format("%-10s %-15s %-12d %-3d\n", p.getPatientName(), p.getPatientSurname(), p.getPhoneNumber(), p.getPatientId());
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllSurgeons() throws Exception{
		System.out.format("%-10s %-15s %-20s %-3s %s\n", "Name", "Surname", "Email", "Id", "Chief");
		System.out.println("-----------------------------------------------------------");
		try {
			for(Surgeon s: surgeonManager.getListOfSurgeons()) {
				System.out.format("%-10s %-15s %-20s %-3d %s\n", s.getName(), s.getSurname(), s.getEmail(), s.getSurgeonId(), s.getChief());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private static void getAllSurgeries() {
		System.out.format("%-15s %-18s %-13s %-15s %s\n", "Patient name", "Patient surname", "Patient id", "Surgery type", "Surgery Id");
		System.out.println("-------------------------------------------------------------------------");
		try {
			for(Surgery s: surgeryManager.getListOfSurgeries()) {
				System.out.format("%-15s %-18s %-13d %-15s %d\n", 
						patientManager.getPatientById(s.getPatientId()).getPatientName(),
						patientManager.getPatientById(s.getPatientId()).getPatientSurname(),
						s.getPatientId(), s.getSurgeryType(), s.getSurgeryId());
			}
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private static void createSurgery() throws Exception {
		try {
			patientManager.getListOfPatients();
		}catch(Exception e) {
			System.out.println("No patients in the data base");
		}
		System.out.println("Type the name of the patient: ");
		Surgery s = null;
		do {
			String patientName = reader.readLine();
			//Prove that exits and there is only one
			List<Patient> patients = new ArrayList<Patient>();
			patients = patientManager.getListPatientByName(patientName);
			if(patients.size() == 0){//Si no existe ese nombre entre los pacientes
					System.out.println("There no such a patient with that name. Please enter another name: ");
					System.out.println("These are the patients\n");
					System.out.format("%-10s %-15s\n", "Name", "Surname");
					System.out.println("-----------------------");
					for(Patient p: patientManager.getListOfPatients()) {
						System.out.format("%-10s %-15s\n", p.getPatientName(), p.getPatientSurname());
					}
			 } else if(patients.size()>1) {//quiere decir que hay dos o mas pacientes con el mismo nombre
				System.out.println("\nSeems that there are 2 patients or more with the same name ");
				System.out.println("Do you mean (type the Id)...");
				int patientId;
				do {
					System.out.format("%-10s %-15s %-3s\n", "Name", "Surname", "Id");
					System.out.println("-----------------------------");
					for(Patient p: patients) {
						System.out.format("%-10s %-15s %-3d\n", p.getPatientName(), p.getPatientSurname(), p.getPatientId());
					}
					patientId = Integer.parseInt(reader.readLine());
					if(patientManager.getListOfPatients().contains(patientManager.getPatientById(patientId))) {
						break;
					} else {
						System.out.println("Incorrect id, type id again: ");
					}
				} while (true);
				System.out.println("Type the type of the surgery: ");
				String surgeryType =  reader.readLine();
				System.out.println("Type the date of the surgery: ");
				Date surgeryDate = getDate();
				s= new Surgery(surgeryType, surgeryDate, patientId);
				break;
			}else {//Si solo hay un paciente con ese nombre
				Patient p= patientManager.getPatientByName(patientName);
				int patientId = p.getPatientId();
				System.out.println("Type the type of the surgery: ");
				String surgeryType =  reader.readLine();
				System.out.println("Type the date of the surgery: ");
				Date surgeryDate = getDate();
				s= new Surgery(surgeryType, surgeryDate, patientId);
				break;
			}
		}while(true);
		surgeryManager.addSurgery(s);
	}
	
	private static int assignRoom (int surgeryId) {
		List<OperatingRoom> rooms = new ArrayList<OperatingRoom>();
		rooms = operatingRoomManager.getListOfActiveOperatingRoom();
		int roomId;
		Surgery s = surgeryManager.getSurgeryById(surgeryId);
		for(int i=0; i<rooms.size();i++) {
			for(int j =0; j<4; j++) {
				if(rooms.get(i).getHoursAvailable().get(j) == true) {
					//Ya se encontró un hueco disponible en una hab
					roomId = rooms.get(i).getRoomId();
					rooms.get(i).changeHoursAvailable(j);//Cambiar el hueco a OCUPADO
					s.setStartHour(rooms.get(i).getStartHour(j));//Obtener la hora de la SURGERY
					return roomId;
				}
			}
		}
		return -1;//NO se han encontrado HUECOS LIBRES y NO hay más ROOMS
	}
	
	private static int assignSurgeon(int surgeryId, Date date) {//La fecha para buscar en la lista de WORKS WITH
		List<WorksWith> teamSurgeonNurse = new ArrayList<WorksWith>();
		teamSurgeonNurse = worksWithManager.getListOfWorksWith(date);
		int surgeonId;
		for(int i=0; i<teamSurgeonNurse.size();i++) {//Recorrer toda la lista disponible
			for(int j=0; j<4; j++) {
				if(teamSurgeonNurse.get(i).getHoursAvailable().get(j) == true) {
					surgeonId = teamSurgeonNurse.get(i).getSurgeonID();
					teamSurgeonNurse.get(i).changeHoursAvailable(j);
					return surgeonId;
				}
			}
		}
		return -1;//No se han encontrado HUECOS LIBRES en los SURGEON
	}
	
	private static void manageSurgery(int surgeryId, Date date) {//La fecha me ralla
		//Asignarle una room
		Surgery s = surgeryManager.getSurgeryById(surgeryId);
		int roomId = assignRoom(surgeryId);
		if(roomId==-1) {//No hay huecos disponibles
			System.out.println("There are not empty spaces in the rooms for today.");
		}else {
			s.setRoomId(roomId);
		}
		//Asignarle un surgeon
		if(assignSurgeon(surgeryId, date)==-1) {//NO hay huecos disponibles
			System.out.println("No more available teams for today.");
		}else {
			s.setSurgeonId(assignSurgeon(surgeryId, date));
		}
	}

	//	private static void modifySurgery() {
//		try {
//			System.out.println("Type the surgeryId you want to change: ");
//			int surgeryId = Integer.parseInt(reader.readLine());
//			System.out.println("Type the atribute you want to change(surgeryType, surgeryDate, startHour): ");
//			String parameterChange = reader.readLine();
//			System.out.println("Introduce the new value: ");
//			if(parameterChange.equalsIgnoreCase("surgeryType")) {
//				System.out.println("Type the new surgeryType: ");
//			}else if(parameterChange.equalsIgnoreCase("surgeryDate")) {
//				System.out.println("Type the new surgeryDate: ");
//				/*System.out.println("Day: ");
//				System.out.println("Month: ");
//				System.out.println("Year: ");*/
//			}else {
//				System.out.println("Type the new startHour: ");
//			}
//			String newAtribute = reader.readLine();
//			surgeryManager.modifySurgery(surgeryId, parameterChange, newAtribute);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private static void createSchedule() {
		try {
			System.out.println();
			//Esto depende de si decidimos que siempre va a ser mañana 
			System.out.println("Type the date for which you want to create the schedule: "); 
			Date date = getDate(); //si es siempre "mañana" date= tomorrow
			List<Surgery> surgeries = surgeryManager.getListOfSurgeriesNotDone();
			createTeams(date);//Hacer Surgeon-Nurse
			chooseActiveRooms();			
			for(int surgeriesCount = 0;surgeriesCount<surgeries.size();surgeriesCount++) {//Mientras haya SURGERIES que hacer
				manageSurgery(surgeries.get(surgeriesCount).getSurgeryId(),date);
				//Mostrar SCHEDULE
				showSchedule();
				surgeries.get(surgeriesCount).setDone(true);//Ya se han realizado
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void showSchedule() {
		
	}
	
	private static void chooseActiveRooms() {
		try {
			if(operatingRoomManager.getListOfActiveOperatingRoom().size() !=0) {
				while(true) {
					System.out.println("These are the active rooms, do you want to make any modification?");
					for (OperatingRoom oR: operatingRoomManager.getListOfActiveOperatingRoom()) {
						System.out.println(oR);
					}
					System.out.println("1. Activate new room");
					System.out.println("2. Disable a room");
					System.out.println("3. No more modifications");
					int option = Integer.parseInt(reader.readLine());
					switch(option) {
						case 1:
							List<OperatingRoom> rooms= operatingRoomManager.getListOfOperatingRoom();
							rooms.removeAll(operatingRoomManager.getListOfActiveOperatingRoom());
							for (OperatingRoom oR: rooms) {
								System.out.println(oR);
							}
							System.out.print("Activate room: ");
							int roomId = Integer.parseInt(reader.readLine());
							operatingRoomManager.updateActivity(roomId, true);
							break;
						case 2: 
							for (OperatingRoom oR: operatingRoomManager.getListOfActiveOperatingRoom()) {
								System.out.println(oR);
							}
							System.out.print("Disable room: ");
							int rId = Integer.parseInt(reader.readLine());
							operatingRoomManager.updateActivity(rId, false);
							break;
						case 3: 
							break;
					}
					if(option==3) {
						break;
					}
				}
			}
			else {
				System.out.println("Choose the active rooms: ");
				List<OperatingRoom> rooms= operatingRoomManager.getListOfOperatingRoom();
				while(true) {
					rooms.removeAll(operatingRoomManager.getListOfActiveOperatingRoom());
					for (OperatingRoom oR: rooms) {
						System.out.println(oR);
					}
					System.out.print("Activate room: ");
					int roomId = Integer.parseInt(reader.readLine());
					operatingRoomManager.updateActivity(roomId, true);
					System.out.print("Do you want to activate another room (Y/N): ");
					String confirmation = reader.readLine();
					if(confirmation.equalsIgnoreCase("y")) {
					} else {
						break;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static void newOperatingRoom() throws Exception {
		System.out.println("Type the room number: ");
		Integer roomNumber =  Integer.parseInt(reader.readLine());
		System.out.println("Type the room floor: ");
		Integer roomFloor =  Integer.parseInt(reader.readLine());
		OperatingRoom r= new OperatingRoom(roomNumber, roomFloor);
		operatingRoomManager.addOperatingRoom(r);
		System.out.println("OperatingRoom added successfully\n");
	}
	
//	private static void modifyOperatingRoom() {
//		try {
//			System.out.println("Type the roomId you want to change: ");
//			int roomId = Integer.parseInt(reader.readLine());
//			System.out.println("Type the atribute you want to change(roomNumber, roomFloor): ");
//			String parameterChange = reader.readLine();
//			System.out.println("Introduce the new value: ");
//			if(parameterChange.equalsIgnoreCase("roomNumber")) {
//				System.out.println("Type the new roomNumber: ");
//			}else {
//				System.out.println("Type the new roomFloor: ");
//			}
//			String newParameter = reader.readLine();
//			operatingRoomManager.modifyOperatingRoom(roomId, parameterChange, newParameter);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void createVacation(String role) throws Exception
	{
		int surgId = 0;
		int nurseId = 0;
		if(role.equals("surgeon")) {
			surgId = surgeonManager.getIdByEmail(u.getEmail());
			if(surgeonVacationManager.countSurgeonVacations(surgId)==2) {
				System.out.println("You cannot add more vacations\n");
				surgeonMenu();
			}	
		} else if(role.equals("nurse")) {
			nurseId = nurseManager.getIdByEmail(u.getEmail());
			if(nurseVacationManager.countNurseVacations(nurseId)==2) {
				System.out.println("You cannot add more vacations\n");
  			NurseMenu();
			}	
		}
		Date start= selectStartDate();
		Date end= new Date (start.getTime() + (1000 * 60 * 60 * 24 * 14));
		if(role.equals("surgeon")) {
			SurgeonVacation sVac= new SurgeonVacation(start, end, surgId);
			surgeonVacationManager.addVacation(sVac);	
		} else if(role.equals("nurse")) {
			NurseVacation nVac= new NurseVacation(start, end, nurseId);
			nurseVacationManager.addVacation(nVac);
		}
		System.out.println("Vacation successfully saved!\n");
	}
	
	public static Date selectStartDate() throws Exception {
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
			switch(option) {
			case 1:
				start= new Date(year, 5, 1);
				break;
			case 2:
				start= new Date(year, 5, 16);
				break;
			case 3:
				start= new Date(year, 6, 1);
				break; 
			case 4:
				start= new Date(year, 6, 16);
				break;
			case 5:
				start= new Date(year, 7, 1);
				break;
			case 6:
				start= new Date(year, 7, 16);
				break;
			}
		} while (option< 1 || option> 6);
		return start;
	}
	
	public static Date getDate() throws Exception{
		Integer Year;
		Integer Month;
		Integer Day;
		do {
			System.out.println("Type the year:");
			Year= Integer.parseInt(reader.readLine());
		} while (Year.toString().length()!= 4);
		do {
			System.out.println("Type the month [1-12]:");
			Month= Integer.parseInt(reader.readLine());
		} while (Month<1 || Month>12);
		do {
			System.out.println("Type the day:");
			Day= Integer.parseInt(reader.readLine());
		} while (Day<1 || Month>31);
		Date date= new Date(Year-1900, Month-1, Day);
		return date;
	}
	
	public static void getSurgeonsOnVacation() throws Exception {
		System.out.println("Show all surgeons on vacation during any of these days");
		System.out.println("Type the start date:");
		Date start= getDate();
		System.out.println("Type the end date:");
		Date end= getDate();
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
		if(start.compareTo(end)>0) {
			throw new Exception ();
		}
		}catch (Exception e) {
			System.out.println("End date cannot be after start date");
			e.printStackTrace();
		}
		try {
			surgeons = surgeonVacationManager.getSurgeonsOnVacation(start, end);
			int i;
			for(i=0; i< surgeons.size(); i++)
			{
				System.out.println("Name: " + surgeons.get(i).getName() + " Id: " + surgeons.get(i).getSurgeonId());
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Surgeon> avaliableSurgeons(Date date){
		List<Surgeon> avaliable= surgeonManager.getListOfSurgeons();
		avaliable.removeAll(surgeonVacationManager.getSurgeonsOnVacation(date));
		return avaliable;
	}
	
	public static List<Nurse> avaliableNurses(Date date){
		List<Nurse> avaliable= nurseManager.getListOfNurses();
		avaliable.removeAll(nurseVacationManager.getNursesOnVacation(date));
		return avaliable;
	}
}