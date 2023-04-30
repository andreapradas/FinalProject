package hospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.Time;
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
		// TODO Auto-generated method stub

	jdbcManager = new JDBCManager();
	surgeonManager = new JDBCSurgeonManager(jdbcManager);
	surgeonVacationManager = new JDBCSurgeonVacationManager(jdbcManager);
	patientManager = new JDBCPatientManager(jdbcManager);
	userManager = new JPAUserManager();
	nurseManager = new JDBCNurseManager(jdbcManager);
	worksWithManager = new JDBCWorksWithManager(jdbcManager);
	surgeryManager = new JDBCSurgeryManager(jdbcManager);
	nurseVacationManager = new JDBCNurseVacationManager(jdbcManager);
	
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
					//System.out.println(userManager.getRole("surgeon").getUsers());
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
					break;
				}
			}while(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getUsers() {
		// TODO Auto-generated method stub
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
				System.out.println("Choose an option");
				System.out.println(" 1. Add new patient");
				System.out.println(" 2. Get list of patients");
				System.out.println(" 3. Add new vacation");
				System.out.println(" 4. Modify vacation");
				System.out.println(" 5. Delete vacation");
				System.out.println(" 6. Get all employee vacations");
				System.out.println(" 7. Get list of surgeons");
				System.out.println(" 8. Get list of nurses");
				System.out.println(" 9. Get list of all employees");	
				System.out.println("10. Assign nurse to surgeon");
				System.out.println("11. Change chief surgeon");
				System.out.println("12. Get surgeons on vacation any day of the given period");
		
				System.out.println("13. Log out");
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
					createVacation("surgeon"); //estamos dentro del menu de chiefSurgeons 
					//por lo que el que este aqui metido va a ser un chief y solo podra
					//añadir sus vacaciones
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
					Date date = null;//Completar
					createTeam(date);
					System.out.println(worksWithManager.getListOfWorksWith());
					break;
		 		case 11:
					changeChiefSurg();
					break;
				case 12: 
					getSurgeonsOnVacation();
					break;
				
				case 13:
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	private static void surgeonMenu() throws Exception{
		try {
			do {System.out.println("Choose an option");
			System.out.println(" 1. Add new patient");
			System.out.println(" 2. Get list of patients");
			System.out.println(" 3. Add new vacation");
			System.out.println(" 4. Modify vacation");
			System.out.println(" 5. Delete vacation");
			System.out.println(" 6. Get all employee vacations");
			System.out.println(" 7. Get list of surgeons");
			System.out.println(" 8. Get list of nurses");
			System.out.println(" 9. Get list of all employees");	
	
			System.out.println("10. Log out");
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
					createVacation("surgeon"); //estamos dentro del menu de sSurgeons 
					//por lo que el que este aqui metido va a ser un surgeon y solo podra
					//añadir sus vacaciones
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void NurseMenu() throws Exception{
		try {
			do {
				System.out.println("Choose an option");
				System.out.println(" 1. Get list of patients");
				System.out.println(" 2. Add new vacation");
				System.out.println(" 3. Modify vacation");
				System.out.println(" 4. Delete vacation");
				System.out.println(" 5. Get all employee vacations");
				System.out.println(" 6. Get list of surgeons");
				System.out.println(" 7. Get list of nurses");
				System.out.println(" 8. Get list of all employees");
				
				System.out.println(" 9. Log out");
				System.out.println(" 0. exit");
				
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void modifyVacation(String role) throws Exception {
		// TODO Auto-generated method stub
		if(role.equals("surgeon")) {
			List<SurgeonVacation> surgVac= surgeonVacationManager.getSurgeonReservedVacation(surgeonManager.getIdByEmail(u.getEmail()));
			System.out.println(surgVac);
			System.out.println("Select the vacation you want to modify");
			Integer vacId =  Integer.parseInt(reader.readLine());
			System.out.println("Select the new vacation dates");
			java.sql.Date start= selectStartDate();
			java.sql.Date end= new java.sql.Date (start.getTime() + (1000 * 60 * 60 * 24 * 15));
			surgeonVacationManager.modifySurgeonVacation(vacId, start, end);
		} else if (role.equals("nurse")) {
			List<NurseVacation> nurseVac= nurseVacationManager.getNurseReservedVacation(nurseManager.getIdByEmail(u.getEmail()));
			System.out.println(nurseVac);
			System.out.println("Select the vacation you want to modify");
			Integer vacId =  Integer.parseInt(reader.readLine());
			java.sql.Date start= selectStartDate();
			java.sql.Date end= new java.sql.Date (start.getTime() + (1000 * 60 * 60 * 24 * 15));
			nurseVacationManager.modifyNurseVacation(vacId, start, end);
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
			surgeonMenu(); //HAY QUE CREAR CHIEF SURGEON MENU
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
			List<User> users= new ArrayList<User>();
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
	

//	private static void deleteNurse() throws Exception {
//	// TODO Auto-generated method stub
//		System.out.println("Type the nurse id");
//		Integer nurseID =  Integer.parseInt(reader.readLine());
//		nurseManager.deleteNurseByID(nurseID);
//}


	private static void createTeam(Date date) throws Exception {
	// TODO Auto-generated method stub
		Integer nurseId;
		do {
			System.out.println(avaliableNurses(date).removeAll(nurseManager.getNursesAssignedThisDay(date)));
			System.out.println("Please enter the nurse ID to assign:");
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
			System.out.println(avaliableSurgeons(date));
			System.out.println(avaliableSurgeons(date).removeAll(surgeonManager.getSurgeonsAssignedThisDay(date)));
			System.out.println("Please enter the surgeon ID to assign:");
			surgeonId = Integer.parseInt(reader.readLine());
			if(!avaliableSurgeons(date).contains(surgeonManager.getSurgeonById(surgeonId))) {
				System.out.println("That surgeon is not avaliable");
			}else {
				break;
			}
		} while(true);
		
		
//		System.out.println("Please enter the nurse ID to assign:");
//		nurseId = Integer.parseInt(reader.readLine());
//		System.out.println("Please enter the surgeon ID to assign:");
//		Integer surgeonId = Integer.parseInt(reader.readLine());
//		//comprobra que los id que se meten existen
		
		worksWithManager.assign(nurseId, surgeonId, date);
		//crea el ww solo con los ids pero me falta meter la fecha tb
}


	private static void getAllNurses() throws Exception {
	// TODO Auto-generated method stub
		System.out.format("%-10s %-15s %-20s %-3s\n", "Name", "Surname", "Email", "Id");
		System.out.println("----------------------------------------------------");
		try {
			for(Nurse n: nurseManager.getListOfNurses()) {
				System.out.format("%-10s %-15s %-20s %-3d\n", n.getNurseName(), n.getNurseSurname(), n.getEmail(), n.getNurseId());
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}


//	private static void createNurse() throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("Type the name of the nurse:");
//		String name =  reader.readLine();
//		System.out.println("Type the email of the nurse:");
//		String email =  reader.readLine();
//		
//		Nurse n= new Nurse(name, email);
//		nurseManager.addNurse(n);
//	}

	private static void changeChiefSurg() {
	// TODO Auto-generated method stub
		System.out.println("The chief is going to be changed and you are going to be deleted as chief");
		int count=0;
//		for(count=0; count< surgeonManager.getListOfSurgeons().size(); count++) {
//			if(surgeonManager.getListOfSurgeons().get(count).equals(surgeonManager.getChiefSurgeon())){
//				continue;
//			}
//			System.out.println(surgeonManager.getListOfSurgeons().get(count).showSurgeon());
//		}
		for(Surgeon s: surgeonManager.getListOfSurgeons()){
			if(surgeonManager.getListOfSurgeons().get(count).equals(surgeonManager.getChiefSurgeon())){
				continue;
			}
            System.out.format("%-10s %-15s Id: %d\n", s.getName(), s.getSurname(), s.getSurgeonId());
        }
		System.out.println("Type the new chief id");
		Integer newChiefId = null;
		try {
			newChiefId = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		if(role.equals("surgeon")) {
			List<SurgeonVacation> surgVac= surgeonVacationManager.getSurgeonReservedVacation(surgeonManager.getIdByEmail(u.getEmail()));
			System.out.println(surgVac);
			System.out.println("Type the vacation id");
			Integer vacId =  Integer.parseInt(reader.readLine());
			surgeonVacationManager.deleteSurgeonVacationById(vacId);
		} else if (role.equals("nurse")) {
			List<NurseVacation> nurseVac= nurseVacationManager.getNurseReservedVacation(nurseManager.getIdByEmail(u.getEmail()));
			System.out.println(nurseVac);
			System.out.println("Type the vacation id");
			Integer vacId =  Integer.parseInt(reader.readLine());
			nurseVacationManager.deleteNurseVacationById(vacId);
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
			e.printStackTrace();
		}
	}
	
	public static void getAllSurgeons() throws Exception
	{
		System.out.format("%-10s %-15s %-20s %-3s %s\n", "Name", "Surname", "Email", "Id", "Chief");
		System.out.println("-----------------------------------------------------------");
		try {
			for(Surgeon s: surgeonManager.getListOfSurgeons()) {
				System.out.format("%-10s %-15s %-20s %-3d %s\n", s.getName(), s.getSurname(), s.getEmail(), s.getSurgeonId(), s.getChief());
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private static void createSurgery() throws Exception {
		System.out.println("Type the name of the patient: ");
		String patientName = reader.readLine();
		Surgery s = null;
		//Prove that exits and there is only one
		List<Patient> patients = new ArrayList<Patient>();
		patients = patientManager.getListPatientByName(patientName);
		if(patients.size()>1) {//quiere decir que hay dos o mas pacientes con el mismo nombre
			System.out.println("Seems that there are 2 patients or more with the same name ");
			List <String> surnames = new ArrayList<String>();
			System.out.println("Do you mean...");
			for(int i = 0; i<patients.size(); i++) {
				surnames.add(patients.get(i).getPatientSurname());
				System.out.println(patientName + surnames.get(i).toString());
			}
			String surname = reader.readLine();
			Patient p= patientManager.getPatientBySurname(surname,patientName);
			int patientId = p.getPatientId();
			System.out.println("Type the type of the surgery: ");
			String surgeryType =  reader.readLine();
			s= new Surgery(surgeryType, patientId);
		}else if(patients == null){//Si no existe ese nombre entre los pacientes
			System.out.println("There no such a patient with that name. Please enter another name.");
			//Volver al menu que no se cual es
		}else {//Si solo hay un paciente con ese nombre
			Patient p= patientManager.getPatientByName(patientName);
			int patientId = p.getPatientId();
			System.out.println("Type the type of the surgery: ");
			String surgeryType =  reader.readLine();
			s= new Surgery(surgeryType, patientId);
		}
		surgeryManager.addSurgery(s);
	}
	
	private static int assignRoom (int surgeryId) {
		List<OperatingRoom> rooms = new ArrayList<OperatingRoom>();
		rooms = operatingRoomManager.getListOfActiveOperatingRoom();
		int roomId;
		Surgery s = surgeryManager.getSurgeryById(surgeryId);
		for(int i=0; i<rooms.size();i++) {
			for(int j =0; j<4; j++) {
				if(rooms.get(i).getHoursAvailable().get(j)== true) {
					//Ya se encontró un hueco disponible
					roomId = rooms.get(i).getRoomId();
					rooms.get(i).changeHoursAvailable(j);//Cambiar el hueco a OCUPADO
					s.setStartHour(rooms.get(i).getStartHour(j));//Obtener la hora de la surgery
					return roomId;
				}
			}
		}
		return -1;
	}
	
	private static int assignSurgeon(int surgeryId) {
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		surgeons = surgeonManager.getListOfSurgeons();
		int surgeonId;
		Surgery s = surgeryManager.getSurgeryById(surgeryId);
		for(int i=0; i<surgeons.size();i++) {
			for(int j=0; j<4; j++) {
				
			}
		}
		return -1;
	}
	
	private static void manageSurgery(int surgeryId) {
		//Asignarle una room
		Surgery s = surgeryManager.getSurgeryById(surgeryId);
		int roomId = assignRoom(surgeryId);
		if(roomId==-1) {//No hay huecos disponibles
			System.out.println("There are not empty spaces");
			//No seguir 
		}
		s.setRoomId(roomId);
		//Asignarle un surgeon
		s.setSurgeonId(assignSurgeon(surgeryId));
	}
//Metodos para modificar la hab. y la cirugia 
	
	private static void modifySurgery() {
		try {
			System.out.println("Type the surgeryId you want to change: ");
			int surgeryId = Integer.parseInt(reader.readLine());
			System.out.println("Type the atribute you want to change(surgeryType, surgeryDate, startHour): ");
			String parameterChange = reader.readLine();
			System.out.println("Introduce the new value: ");
			if(parameterChange.equalsIgnoreCase("surgeryType")) {
				System.out.println("Type the new surgeryType: ");
			}else if(parameterChange.equalsIgnoreCase("surgeryDate")) {
				System.out.println("Type the new surgeryDate: ");
				/*System.out.println("Day: ");
				System.out.println("Month: ");
				System.out.println("Year: ");*/
			}else {
				System.out.println("Type the new startHour: ");
			}
			String newAtribute = reader.readLine();
			surgeryManager.modifySurgery(surgeryId, parameterChange, newAtribute);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void modifyOperatingRoom() {
		try {
			
			System.out.println("Type the roomId you want to change: ");
			int roomId = Integer.parseInt(reader.readLine());
			System.out.println("Type the atribute you want to change(roomNumber, roomFloor): ");
			String parameterChange = reader.readLine();
			System.out.println("Introduce the new value: ");
			if(parameterChange.equalsIgnoreCase("roomNumber")) {
				System.out.println("Type the new roomNumber: ");
			}else {
				System.out.println("Type the new roomFloor: ");
			}
			String newParameter = reader.readLine();
			operatingRoomManager.modifyOperatingRoom(roomId, parameterChange, newParameter);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

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
	
	@SuppressWarnings("deprecation")
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
			System.out.println(option);
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
		java.sql.Date start= new Date(startYear-1900, startMonth-1, startDay);
		java.sql.Date end= new Date(endYear-1900, endMonth-1, endDay);
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
			surgeons = surgeonVacationManager.getSurgeonsOnVacation(start, end);
			int i;
			for(i=0; i< surgeons.size(); i++)
			{
				System.out.println("Name: " + surgeons.get(i).getName() + " Id: " + surgeons.get(i).getSurgeonId());
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
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
