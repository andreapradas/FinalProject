package hospital.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import hospital.xml.XMLManagerImpl;
import hospitalJPA.JPAUserManager;

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
	private static XMLManager xmlManager;
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
		xmlManager = new XMLManagerImpl();
		do {
			try {
				userManager.getRole("surgeon").setUsers(userManager.getSpecificUsers("surgeon"));
				userManager.getRole("nurse").setUsers(userManager.getSpecificUsers("nurse"));
				userManager.getRole("chiefSurgeon").setUsers(userManager.getSpecificUsers("chiefSurgeon"));
				System.out.println("Choose an option");
				System.out.println("1. Sign in");
				System.out.println("2. Log in");
				//System.out.println("3. List of users");
				System.out.println("0. Exit");
				int choice;
				do {
					try {
						choice = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);

				switch (choice) {
				case 1:
					signIn();
					break;
				case 2:
					logIn();
					break;
//				case 3:
//					getUsers();
//					break;
				case 0:
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				default:
					System.out.println("Please choose one of the options");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Incorrect value");
			}
		} while (true);
	}

	private static void getUsers() {
		System.out.format("%-30s %s\n", "Email", "Role");
		System.out.println("------------------------------------");
		for (User u : userManager.getUsers()) {
			System.out.format("%-30s %s\n", u.getEmail(), u.getRole().getName());
		}
		System.out.println();
	}

	private static void chiefSurgeonMenu() throws Exception {
		do {
			try {
				System.out.println("\nChoose an option");
				System.out.println(" 1. Register patient");
				System.out.println(" 2. Modify patient");
				System.out.println(" 3. List of patients");
				System.out.println(" 4. List of surgeons");
				System.out.println(" 5. List of nurses");
				System.out.println(" 6. List of all employees");
				System.out.println(" 7. Request days off");
				System.out.println(" 8. Modify vacation");
				System.out.println(" 9. Delete vacation");
				System.out.println("10. My vacations");
				System.out.println("11. Surgeons on vacation (specific period)");
				System.out.println("12. Nurses on vacation (specific period)");
				System.out.println("13. All employee vacations");
				System.out.println("14. Add new operating room");
				System.out.println("15. Create surgery");
				System.out.println("16. Delete surgery");
				System.out.println("17. List of surgeries");
				System.out.println("18. Change chief surgeon");
				System.out.println("19. Create schedule");
				System.out.println("20. Show schedule");
				System.out.println("21. Print patient to html and xml document");
				System.out.println("22. Load patient from xml");
				System.out.println("23. Load nurse from xml");
				System.out.println("24. Log out");
				System.out.println("25. Delete account");
				System.out.println(" 0. Exit");
				int choice;
				do {
					try {
						choice = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				switch (choice) {
				case 1:
					createPatient();
					break;
				case 2:
					updatePhoneNumber();
					break;
				case 3:
					getPatients();
					break;
				case 4:
					getAllSurgeons();
					break;
				case 5:
					getAllNurses();
					break;
				case 6:
					getUsers();
					break;
				case 7:
					createVacation("surgeon");
					break;
				case 8:
					modifyVacation("surgeon");
					break;
				case 9:
					deleteVacations("surgeon");
					break;
				case 10:
					getMyVacations();
					break;
				case 11:
					getSurgeonsOnVacation();
					break;
				case 12:
					getNursesOnVacation();
					break;
				case 13:
					getAllVacations();
					break;
				case 14:
					newOperatingRoom();
					break;
				case 15:
					createSurgery();
					break;
				case 16:
					deleteSurgery();
					break;
				case 17:
					getAllSurgeries();
					break;
				case 18:
					changeChiefSurg();
					break;
				case 19:
					createSchedule();
					break;
				case 20:
					Date date = getDate();
					showSchedule(date);
					break;
				case 21:
					printPatient();
					break;
				case 22:
					loadPatient();
					break;
				case 23:
					loadNurse();
					break;
				case 24:
					main(null);
					break;
				case 25:
					deleteAccount("surgeon", surgeonManager.getIdByEmail(u.getEmail()), u.getEmail());
					main(null);
				case 0:
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);
	}

	private static void surgeonMenu() throws Exception {
		do {
			try {
				System.out.println("\nChoose an option");
				System.out.println(" 1. Register patient");
				System.out.println(" 2. Modify patient");
				System.out.println(" 3. List of patients");
				System.out.println(" 4. List of surgeons");
				System.out.println(" 5. List of all employees");
				System.out.println(" 6. Request days off");
				System.out.println(" 7. Modify vacation");
				System.out.println(" 8. Delete vacation");
				System.out.println(" 9. My vacations");
				System.out.println("10. All employee vacations");
				System.out.println("11. Create surgery");
				System.out.println("12. List of surgeries");
				System.out.println("13. Show schedule");
				System.out.println("14. Log out");
				System.out.println("15. Delete account");
				System.out.println(" 0. Exit");
				int choice;
				do {
					try {
						choice = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				switch (choice) {
				case 1:
					createPatient();
					break;
				case 2:
					updatePhoneNumber();
					break;
				case 3:
					getPatients();
					break;
				case 4:
					getAllSurgeons();
					break;
				case 5:
					getUsers();
					break;
				case 6:
					createVacation("surgeon");
				case 7:
					modifyVacation("surgeon");
					break;
				case 8:
					deleteVacations("surgeon");
					break;
				case 9:
					getMyVacations();
					break;
				case 10:
					getAllVacations();
					break;
				case 11:
					createSurgery();
					break;
				case 12:
					getAllSurgeries();
					break;
				case 13:
					Date date = getDate();
					showSchedule(date);
					break;
				case 14:
					main(null);
					break;
				case 15:
					deleteAccount("surgeon", surgeonManager.getIdByEmail(u.getEmail()), u.getEmail());
					main(null);
					break;
				case 0:
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);
	}

	private static void NurseMenu() throws Exception {
		do {
			try {
				System.out.println("\nChoose an option");
				System.out.println(" 1. List of patients");
				System.out.println(" 2. List of nurses");
				System.out.println(" 3. List of all employees");
				System.out.println(" 4. Request days off");
				System.out.println(" 5. Modify vacation");
				System.out.println(" 6. Delete vacation");
				System.out.println(" 7. My vacations");
				System.out.println(" 8. All employee vacations");
				System.out.println(" 9. List of surgeries");
				System.out.println("10. Show schedule");
				System.out.println("11. Print me to html xml");
				System.out.println("12. Log out");
				System.out.println("13. Delete account");
				System.out.println(" 0. Exit");
				int choice;
				do {
					try {
						choice = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);

				switch (choice) {
				case 1:
					getPatients();
					break;
				case 2:
					getAllNurses();
					break;
				case 3:
					getUsers();
					break;
				case 4:
					createVacation("nurse");
					break;
				case 5:
					modifyVacation("nurse");
					break;
				case 6:
					deleteVacations("nurse");
					break;
				case 7:
					getMyVacations();
					break;
				case 8:
					getAllVacations();
					break;
				case 9:
					getAllSurgeries();
					break;
				case 10:
					Date date = getDate();
					showSchedule(date);
					break;
				case 11:
					printMeNurse(nurseManager.getIdByEmail(u.getEmail()));
					break;
				case 12:
					main(null);
					break;
				case 13:
					deleteAccount("nurse", nurseManager.getIdByEmail(u.getEmail()), u.getEmail());
					main(null);
					break;
				case 0:
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);
	}

	private static void loadNurse() {
		Nurse n = null;
		File file = new File(".\\src\\xmls\\ExternalNurse.xml");
		n = xmlManager.xml2Nurse(file);
		System.out.println(n);
	}

	private static void loadPatient() {
		Patient p = null;
		File file = new File(".\\src\\xmls\\ExternalPatient.xml");
		p = xmlManager.xml2Patient(file);
		System.out.println(p);
	}

	private static void printMeNurse(Integer nurseId) {
		xmlManager.nurse2xml(nurseId);
		xmlManager.simpleTransform("./src/xmls/Nurse.xml", "./src/xmls/Nurse-style.xslt", "./src/xmls/Nurse.html");
		System.out.println("You have been printed to html and xml document");
	}

	private static void printPatient() {
		int patientId;
		do {
			System.out.println("Select the patient you want to print");
			List<Patient> patients = patientManager.getListOfPatients();
			System.out.format("%-18s %-15s %-3s\n", "Name", "Surname", "Id");
			System.out.println("------------------------------------------");
			for (Patient p : patients) {
				System.out.format("%-18s %-15s %-3d\n", p.getPatientName(), p.getPatientSurname(), p.getPatientId());
			}
			do {
				try {
					patientId = Integer.parseInt(reader.readLine());
					break;
				} catch (Exception e) {
					System.out.println("Not valid input");
				}
			} while (true);
			if (patientManager.getListOfPatients().contains(patientManager.getPatientById(patientId))) {
				break;
			} else {
				System.out.println("Incorrect id, type id again: ");
			}
		} while (true);
		xmlManager.patient2xml(patientManager.getPatientById(patientId));
		xmlManager.simpleTransform("./src/xmls/Patient.xml", "./src/xmls/Patient-style.xslt", "./src/xmls./Patient.html");
		System.out.println("The patient has been printed to html an xml document");
	}

	private static void updatePhoneNumber() throws Exception, phoneException {
		try {
			patientManager.getListOfPatients();
		} catch (Exception e) {
			System.out.println("No patients in the data base");
		}
		System.out.println("Type the name of the patient: ");
		int patientId;
		do {
			String patientName = reader.readLine();
			// Prove that exits and there is only one
			List<Patient> patients = new ArrayList<Patient>();
			patients = patientManager.getListPatientByName(patientName);
			if (patients.size() == 0) {// If there is no patient with that name
				System.out.println("There no such a patient with that name. Please enter another name: ");
				System.out.println("These are the patients\n");
				System.out.format("%-18s %-15s\n", "Name", "Surname");
				System.out.println("------------------------------");
				for (Patient p : patientManager.getListOfPatients()) {
					System.out.format("%-18s %-15s\n", p.getPatientName(), p.getPatientSurname());
				}
			} else if (patients.size() > 1) {//two or more patients with that name
				System.out.println("\nSeems that there are 2 patients or more with the same name ");
				System.out.println("Do you mean (type the Id)...");

				do {
					System.out.format("%-18s %-15s %-3s\n", "Name", "Surname", "Id");
					System.out.println("---------------------------------------");
					for (Patient p : patients) {
						System.out.format("%-18s %-15s %-3d\n", p.getPatientName(), p.getPatientSurname(),
								p.getPatientId());
					}
					do {
						try {
							patientId = Integer.parseInt(reader.readLine());
							break;
						} catch (Exception e) {
							System.out.println("Not valid input");
						}
					} while (true);
					if (patientManager.getListOfPatients().contains(patientManager.getPatientById(patientId))) {
						break;
					} else {
						System.out.println("Incorrect id, type id again: ");
					}
				} while (true);
			} else {//if there is just one patient with that name
				Patient p = patientManager.getPatientByName(patientName);
				patientId = p.getPatientId();
				break;
			}
		} while (true);
		do {
			try {
				Patient patient = patientManager.getPatientById(patientId);
				System.out.println("Type the phone number");
				int phone;
				do {
					try {
						phone = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				if (patient.checkPhoneNumber(phone)) {
					patientManager.updatePhoneNumber(patientId, phone);
					patient.setPhoneNumber(phone);
					System.out.println("Patient updated successfully\n");
					break;
				}
				;
			} catch (Exception e) {
				System.out.println("Error while updating the patient phone number");
			}
		} while (true);
	}

	private static void modifyVacation(String role) throws Exception {
		if (role.equals("surgeon")) {
			modifyS: do {
				List<SurgeonVacation> surgVac = surgeonVacationManager
						.getSurgeonReservedVacation(surgeonManager.getIdByEmail(u.getEmail()));
				if (surgVac.size() < 1) {
					System.out.println("\nYou do not have any vacations yet");
					break;
				}
				int vacId;
				do {
					try {
						for (SurgeonVacation sv : surgVac) {
							System.out.println(sv.toString());
						}
						System.out.println("Select the id of the vacation you want to modify");
						vacId = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				for (SurgeonVacation sv : surgVac) {
					if (sv.getVacationId() == vacId) {
						System.out.println("Select the new vacation dates");
						@SuppressWarnings("deprecation")
						Date start = selectStartDate(role, sv.getStartDate().getYear() + 1900);
						Date end = new Date(start.getTime() + (1000 * 60 * 60 * 24 * 15));
						surgeonVacationManager.modifySurgeonVacation(vacId, start, end);
						System.out.println("Your vacation has been modified");
						break modifyS;
					}
				}
				System.out.println("Incorrect vacationId\n");
			} while (true);
		} else if (role.equals("nurse")) {
			modifyN: do {
				List<NurseVacation> nurseVac = nurseVacationManager
						.getNurseReservedVacation(nurseManager.getIdByEmail(u.getEmail()));
				if (nurseVac.size() < 1) {
					System.out.println("\nYou do not have any vacations yet");
					break;
				}
				int vacId;
				do {
					try {
						for (NurseVacation nv : nurseVac) {
							System.out.println(nv.toString());
						}
						System.out.println("Select the id of the vacation you want to modify");
						vacId = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				for (NurseVacation nv : nurseVac) {
					if (nv.getVacationId() == vacId) {
						System.out.println("Select the new vacation dates");
						@SuppressWarnings("deprecation")
						Date start = selectStartDate(role, nv.getStartDate().getYear() + 1900);
						Date end = new Date(start.getTime() + (1000 * 60 * 60 * 24 * 15));
						nurseVacationManager.modifyNurseVacation(vacId, start, end);
						System.out.println("Your vacation has been modified");
						break modifyN;
					}
				}
				System.out.println("Incorrect vacationId\n");
			} while (true);
		}
	}

	private static void logIn() throws Exception {
		System.out.println("Email: ");
		String email = reader.readLine();
		System.out.println("Password: ");
		String password = reader.readLine();
		u = userManager.checkPassword(email, password);
		if (u != null && u.getRole().getName().equals("surgeon")) {
			System.out.println("Surgeon Login Successful!\n");
			surgeonMenu();
		} else if (u != null && u.getRole().getName().equals("nurse")) {
			System.out.println("Nurse Login Successful!\n");
			NurseMenu();
		} else if (u != null && u.getRole().getName().equals("chiefSurgeon")) {
			System.out.println("ChiefSurgeon Login Successful!\n");
			chiefSurgeonMenu();
		} else {
			System.out.println("Incorrect user or password\n");
		}
	}

	private static void signIn() throws Exception {
		try {
			System.out.println("Choose your role: ");
			System.out.println("1. Surgeon");
			System.out.println("2. Nurse");
			System.out.println("3. Chief surgeon");
			int option;
			do {
				try {
					option = Integer.parseInt(reader.readLine());
					break;
				} catch (Exception e) {
					System.out.println("Not valid input");
				}
			} while (true);

			Role role = null;
			Boolean chief = null;
			switch (option) {
			case 1:
				role = userManager.getRole("surgeon");
				chief = false;
				break;
			case 2:
				role = userManager.getRole("nurse");
				break;
			case 3:
				if (userManager.getRole("chiefSurgeon").getUsers().size() != 0) {
					System.out.println("There is already one chief, no more chief avaliables");
					main(null);
				} else {
					role = userManager.getRole("chiefSurgeon");
				}
				chief = true;
				break;
			}
			System.out.println("Type your email: ");
			String email = reader.readLine();
			User u;
			if (nurseManager.getListOfNurses().contains(nurseManager.getNurseById(nurseManager.getIdByEmail(email)))) {
				System.out.println("Type the password: ");
				String password = reader.readLine();
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] hash = md.digest();
				u = new User(email, hash, userManager.getRole("nurse"));
				u.getRole().addUser(u);
			} else {
				System.out.println("Type the password: ");
				String password = reader.readLine();
				System.out.println("Type your name:");
				String name = reader.readLine();
				System.out.println("Type your surname:");
				String surname = reader.readLine();
				Surgeon s;
				Nurse n;
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] hash = md.digest();
				u = new User(email, hash, role);
				u.getRole().addUser(u);
				if (role.getName().equals("surgeon") || role.getName().equals("chiefSurgeon")) {
					s = new Surgeon(name, surname, email, chief);
					surgeonManager.addSurgeon(s);
				} else if (role.getName().equals("nurse")) {
					n = new Nurse(name, surname, email);
					nurseManager.addNurse(n);
				}
			}
			userManager.newUser(u);
			System.out.println("User has been created correctly");
		} catch (Exception e) {
			System.out.println("It was not created");
			main(null);
		}
	}

	private static void deleteAccount(String role, int id, String email) {
		try {

			do {
				System.out.println("Are you sure you want to delete your account? (Y/N)");
				String conf = reader.readLine();
				if (conf.equalsIgnoreCase("Y")) {
					User u = userManager.getUserByEmail(email);
					userManager.deletUser(email);
					if (role.equals("surgeon") || role.equals("chiefSurgeon")) {
						surgeonManager.deleteSurgeonByID(id);
						userManager.getRole(role).getUsers().remove(u);

					} else if (role.equals("nurse")) {
						userManager.getRole(role).getUsers().remove(u);
						nurseManager.deleteNurseByID(id);
					}
					System.out.println("ACCOUNT DELETED\n\n");
					break;
				} else if (conf.equalsIgnoreCase("N")) {
					System.out.println("ACCOUNT NOT DELETED\n\n");
					break;
				}
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createTeams(Date date) throws Exception {
		List<Nurse> nurses = avaliableNurses(date);
		List<Surgeon> surgeons = avaliableSurgeons(date);
		createteam: if (nurses.size() > 0 && surgeons.size() > 0) {
			do {
				int nurseId;
				do {
					System.out.println("\nThese are the avaliable nurses:");
					System.out.format("%-10s %-15s %-3s\n", "Name", "Surname", "Id");
					System.out.println("-----------------------------------------------------------");
					nurses.removeAll(nurseManager.getNursesAssignedThisDay(date));
					for (Nurse n : nurses) {
						System.out.format("%-10s %-15s %-3d\n", n.getNurseName(), n.getNurseSurname(), n.getNurseId());
					}
					System.out.print("\nPlease enter the nurse ID to assign: ");
					do {
						try {
							nurseId = Integer.parseInt(reader.readLine());
							break;
						} catch (Exception e) {
							System.out.println("Not valid input");
						}
					} while (true);
					if (!avaliableNurses(date).contains(nurseManager.getNurseById(nurseId))) {
						System.out.println("That nurse is not avaliable\n");
					} else {
						break;
					}
				} while (true);
				int surgeonId;
				do {
					System.out.println("\nThese are the avaliable surgeons:");
					System.out.format("%-10s %-15s %-3s\n", "Name", "Surname", "Id");
					System.out.println("-----------------------------------------------------------");
					surgeons.removeAll(surgeonManager.getSurgeonsAssignedThisDay(date));
					for (Surgeon s : surgeons) {
						System.out.format("%-10s %-15s %-3d\n", s.getName(), s.getSurname(), s.getSurgeonId());
					}
					System.out.print("\nPlease enter the surgeon ID to assign: ");
					do {
						try {
							surgeonId = Integer.parseInt(reader.readLine());
							break;
						} catch (Exception e) {
							System.out.println("Not valid input");
						}
					} while (true);
					if (!avaliableSurgeons(date).contains(surgeonManager.getSurgeonById(surgeonId))) {
						System.out.println("That surgeon is not avaliable");
					} else {
						break;
					}
				} while (true);
				worksWithManager.assign(nurseId, surgeonId, date);
				nurses.removeAll(nurseManager.getNursesAssignedThisDay(date));
				surgeons.removeAll(surgeonManager.getSurgeonsAssignedThisDay(date));
				while (true) {
					System.out.print("\nDo you want to create another team (Y/N): ");
					String confirmation = reader.readLine();
					if (confirmation.equalsIgnoreCase("y") && nurses.size() > 0 && surgeons.size() > 0) {
						createTeams(date);
					} else if (confirmation.equalsIgnoreCase("n")) {
						break createteam;
					} else if (confirmation.equalsIgnoreCase("y") && (nurses.size() == 0 || surgeons.size() == 0)) {
						System.out.println("No surgeons or nurses avaliable\n");
						break createteam;
					} else {
						continue;
					}
					break createteam;
				}
			} while (true);
		}
	}

	private static void getAllNurses() throws Exception {
		System.out.format("%-10s %-15s %-30s %-3s\n", "Name", "Surname", "Email", "Id");
		System.out.println("----------------------------------------------------");
		try {
			for (Nurse n : nurseManager.getListOfNurses()) {
				System.out.format("%-10s %-15s %-30s %-3d\n", n.getNurseName(), n.getNurseSurname(), n.getEmail(),
						n.getNurseId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	private static void changeChiefSurg() {
		System.out.println("The chief is going to be changed and you are going to be deleted as chief");
		for (Surgeon s : surgeonManager.getListOfSurgeons()) {
			if (s.equals(surgeonManager.getChiefSurgeon())) {
				continue;
			}
			System.out.format("%-10s %-15s Id: %d\n", s.getName(), s.getSurname(), s.getSurgeonId());
		}
		System.out.println("\nType the new chief id");
		Integer newChiefId = null;
		do {
			try {
				newChiefId = Integer.parseInt(reader.readLine());
				break;
			} catch (Exception e) {
				System.out.println("Not valid input");
			}
		} while (true);
		if (userManager.getChief().getEmail().equals(surgeonManager.getEmailById(newChiefId))) {
			System.out.println("That's the actual chief, no changes done\n");

		} else if (surgeonManager.getListOfSurgeons().contains(surgeonManager.getSurgeonById(newChiefId))) {
			surgeonManager.changeChief(newChiefId);
			String email = surgeonManager.getEmailById(newChiefId);
			userManager.changeChief(email);
			System.out.println("You will have to log in again in order to have the appropiate menu to your new role");
			main(null);
		} else {
			System.out.println("Not valid Id\n");
		}
	}

	private static void deleteVacations(String role) throws Exception {
		try {
			if (role.equals("surgeon")) {
				do {
					List<SurgeonVacation> surgVac = surgeonVacationManager
							.getSurgeonReservedVacation(surgeonManager.getIdByEmail(u.getEmail()));
					if (surgVac.size() < 1) {
						System.out.println("\nYou do not have any vacations yet");
						break;
					}
					System.out.println(surgVac);
					System.out.println("Type the vacation id");
					int vacId;
					do {
						try {
							vacId = Integer.parseInt(reader.readLine());
							break;
						} catch (Exception e) {
							System.out.println("Not valid input");
						}
					} while (true);
					if (surgVac.get(0).getVacationId() == vacId || surgVac.get(1).getVacationId() == vacId) {
						surgeonVacationManager.deleteSurgeonVacationById(vacId);
						break;
					} else {
						System.out.println("Incorrect vacationId\n");
					}
				} while (true);
			} else if (role.equals("nurse")) {
				do {
					List<NurseVacation> nurseVac = nurseVacationManager
							.getNurseReservedVacation(nurseManager.getIdByEmail(u.getEmail()));
					if (nurseVac.size() < 1) {
						System.out.println("\nYou do not have any vacations yet");
						break;
					}
					System.out.println(nurseVac);
					System.out.println("Type the vacation id");
					int vacId;
					do {
						try {
							vacId = Integer.parseInt(reader.readLine());
							break;
						} catch (Exception e) {
							System.out.println("Not valid input");
						}
					} while (true);
					if (nurseVac.get(0).getVacationId() == vacId || nurseVac.get(1).getVacationId() == vacId) {
						nurseVacationManager.deleteNurseVacationById(vacId);
						break;
					} else {
						System.out.println("Incorrect vacationId\n");
					}
				} while (true);

			}
		} catch (Exception e) {
		}
	}

	private static void deleteSurgery() {
		try {
			//SURGERIES that can be deleted, NOT DONE and NOT SCHEDULED
			List<Surgery> surgeries = surgeryManager.getListOfSurgeriesNotDone();
			boolean checkId = false;//checks if the id introduced is in the surgeries
			if (surgeries.size() > 0) {
				System.out.format("%-15s %-18s %-13s %-15s %s\n", "Patient name", "Patient surname", "Patient id",
						"Surgery type", "Surgery Id");
				System.out.println("-------------------------------------------------------------------------");
				for (Surgery s : surgeries) {
					System.out.format("%-15s %-18s %-13d %-15s %d\n",
							patientManager.getPatientById(s.getPatientId()).getPatientName(),
							patientManager.getPatientById(s.getPatientId()).getPatientSurname(), s.getPatientId(),
							s.getSurgeryType(), s.getSurgeryId());
				}
				System.out.println("\nType the id of the surgery you want to delete");
				int surgeryId;
				do {
					try {
						surgeryId = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				for (int i = 0; i < surgeries.size(); i++) {
					if (surgeryId == surgeries.get(i).getSurgeryId()) {
						surgeryManager.deleteSurgery(surgeryId);
						checkId = true;
						System.out.println("Surgery deleted succesfully!");
						break;
					}
				}
				if (checkId == false) {
					System.out.println("Wrong id, please enter it again.");
					deleteSurgery();
				}
			} else {
				System.out.println("No surgeries added yet");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getAllVacations() throws Exception {
		List<SurgeonVacation> sVacations = new ArrayList<SurgeonVacation>();
		List<NurseVacation> nVacations = new ArrayList<NurseVacation>();
		try {
			sVacations = surgeonVacationManager.getAllVacations();
			nVacations = nurseVacationManager.getAllVacations();
			int i;
			System.out.println("Surgeons:");
			for (i = 0; i < sVacations.size(); i++) {
				System.out.format("%-20s", surgeonManager.getNameById(sVacations.get(i).getSurgeonId()) + " "
						+ surgeonManager.getSurgeonById(sVacations.get(i).getSurgeonId()).getSurname() + ": ");
				System.out.println(sVacations.get(i).toString());
			}
			System.out.println("\nNurses:");
			for (i = 0; i < nVacations.size(); i++) {
				System.out.format("%-20s", nurseManager.getNameById(nVacations.get(i).getNurseId()) + " "
						+ nurseManager.getNurseById(nVacations.get(i).getNurseId()).getNurseSurname() + ": ");
				System.out.println(nVacations.get(i).toString());
			}
		} catch (Exception e) {
		}
	}

	public static void getMyVacations() throws Exception {
		try {
			List<SurgeonVacation> sVacations;
			List<NurseVacation> nVacations;
			if (u.getRole().getName().equals("surgeon") || u.getRole().getName().equals("chiefSurgeon")) {
				sVacations = new ArrayList<SurgeonVacation>();
				sVacations = surgeonVacationManager.getMyVacationsSurgeon(surgeonManager.getIdByEmail(u.getEmail()));
				if (sVacations.size() < 1) {
					System.out.println("\nYou do not have any vacations yet");
				}
				for (SurgeonVacation sv : sVacations) {
					System.out.println(sv.toString());
				}
			} else if (u.getRole().getName().equals("nurse")) {
				nVacations = new ArrayList<NurseVacation>();
				nVacations = nurseVacationManager.getMyVacationsNurse(nurseManager.getIdByEmail(u.getEmail()));
				if (nVacations.size() < 1) {
					System.out.println("\nYou do not have any vacations yet");
				}
				for (NurseVacation nv : nVacations) {
					System.out.println(nv.toString());
				}
			}
		} catch (Exception e) {
		}
	}

	public static void createPatient() throws phoneException, Exception {
		try {
			System.out.println("Type the name of the patient:");
			String name = reader.readLine();
			System.out.println("Type the surname of the patient:");
			String surname = reader.readLine();
			System.out.println("Type the phone number");
			int phone = Integer.parseInt(reader.readLine());
			System.out.print("Do you want to add a photo? (Y/N): ");
			do {
				String yesNo = reader.readLine();
				if (yesNo.equalsIgnoreCase("N")) {
					Patient patient = new Patient(name, surname, phone);
					patientManager.addPatient(patient);
					System.out.println("Patient created successfully\n");
					break;
				} else if (yesNo.equalsIgnoreCase("Y")) {
					System.out.print("Type the file name as it appears in folder /photos, including extension: ");
					String fileName = reader.readLine();
					File photo = new File(".\\photos\\" + fileName);
					InputStream streamBlob = new FileInputStream(photo);
					byte[] bytesBlob = new byte[streamBlob.available()];
					streamBlob.read(bytesBlob);
					streamBlob.close();
					Patient patient = new Patient(name, surname, phone, bytesBlob);
					patientManager.addPatient(patient, fileName);
					System.out.println("Patient created successfully\n");
					break;
				}
				System.out.println("Write a valid input please (Y/N)");
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while creating the patient");
			createPatient();
		}
	}

	public static void getPatients() throws Exception {
		try {
			System.out.format("%-18s %-15s %-10s %-3s\n", "Name", "Surname", "PhoneNumber", "Id");
			System.out.println("----------------------------------------------------");
			for (Patient p : patientManager.getListOfPatients()) {
				System.out.format("%-18s %-15s %-12d %-3d\n", p.getPatientName(), p.getPatientSurname(),
						p.getPhoneNumber(), p.getPatientId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getAllSurgeons() throws Exception {
		System.out.format("%-10s %-15s %-30s %-3s %s\n", "Name", "Surname", "Email", "Id", "Chief");
		System.out.println("-----------------------------------------------------------");
		try {
			for (Surgeon s : surgeonManager.getListOfSurgeons()) {
				System.out.format("%-10s %-15s %-30s %-3d %s\n", s.getName(), s.getSurname(), s.getEmail(),
						s.getSurgeonId(), s.getChief());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	private static void getAllSurgeries() {
		System.out.format("%-15s %-18s %-13s %-20s %s\n", "Patient name", "Patient surname", "Patient id",
				"Surgery type", "Surgery Id");
		System.out.println("-----------------------------------------------------------------------------------");
		try {
			//System.out.println(surgeryManager.getListOfSurgeries());
			for (Surgery s : surgeryManager.getListOfSurgeries()) {
				System.out.format("%-15s %-18s %-13d %-20s %d\n",
						patientManager.getPatientById(s.getPatientId()).getPatientName(),
						patientManager.getPatientById(s.getPatientId()).getPatientSurname(), s.getPatientId(),
						s.getSurgeryType(), s.getSurgeryId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	private static void createSurgery() throws Exception {
		try {
			patientManager.getListOfPatients();
		} catch (Exception e) {
			System.out.println("No patients in the data base");
		}
		System.out.println("\nType the name of the patient: ");
		Surgery s = null;
		do {
			String patientName = reader.readLine();
			// Prove that exits and there is only one
			List<Patient> patients = new ArrayList<Patient>();
			patients = patientManager.getListPatientByName(patientName);
			if (patients.size() == 0) {
				System.out.println("There no such a patient with that name. Please enter another name: ");
				System.out.println("These are the patients\n");
				System.out.format("%-10s %-15s\n", "Name", "Surname");
				System.out.println("-----------------------");
				for (Patient p : patientManager.getListOfPatients()) {
					System.out.format("%-10s %-15s\n", p.getPatientName(), p.getPatientSurname());
				}
			} else if (patients.size() > 1) {
				System.out.println("\nSeems that there are 2 patients or more with the same name ");
				System.out.println("\nDo you mean (type the Id)...");
				int patientId;
				do {
					System.out.format("%-10s %-15s %-3s\n", "Name", "Surname", "Id");
					System.out.println("-----------------------------");
					for (Patient p : patients) {
						System.out.format("%-10s %-15s %-3d\n", p.getPatientName(), p.getPatientSurname(),
								p.getPatientId());
					}
					do {
						try {
							patientId = Integer.parseInt(reader.readLine());
							break;
						} catch (Exception e) {
							System.out.println("Not valid input");
						}
					} while (true);
					if (patientManager.getListOfPatients().contains(patientManager.getPatientById(patientId))) {
						break;
					} else {
						System.out.println("Incorrect id, type id again: ");
					}
				} while (true);
				System.out.println("\nType the type of the surgery: ");
				String surgeryType = reader.readLine();
				s = new Surgery(surgeryType, patientId);
				break;

			} else {
				Patient p = patientManager.getPatientByName(patientName);
				int patientId = p.getPatientId();
				System.out.println("\nType the type of the surgery: ");
				String surgeryType = reader.readLine();
				s = new Surgery(surgeryType, patientId);
				break;
			}
		} while (true);
		surgeryManager.addSurgery(s);
	}

	private static void createSchedule() {
		try {
			List<Surgery> surgeries = surgeryManager.getListOfSurgeriesNotDone();
			if (surgeries.size() != 0) {
				Date date;
				int surgeriesCount;
				do {
					System.out.println("\nChoose the date you want to do the schedule: ");
					date = getDate();
					Date today = Date.valueOf(LocalDate.now());
					if (date.compareTo(today) < 0) {
						System.out.println("You cannot create a schedule for a passed day");
					} else if (date.compareTo(today) == 0) {
						System.out.println("You cannot create a schedule for today");
					} else {
						break;
					}
				} while (true);
				if (surgeryManager.getListOfSurgeries(date).size() != 0) {
					System.out.println("Surgery already programmed for that day");
				} else {
					System.out.println("Choose the surgeon-nurse who are going to work together the whole day");
					createTeams(date);
					chooseActiveRooms();
					List<OperatingRoom> rooms = operatingRoomManager.getListOfActiveOperatingRoom();
					List<WorksWith> ww = worksWithManager.getListOfWorksWith(date);
					for (Surgery s : surgeries) {
						s.setRoomId(-1);
					}
					for (Surgery s : surgeries) {
						s.setSurgeonId(-1);
					}
					for (surgeriesCount = 0; surgeriesCount < surgeries.size(); surgeriesCount++) {
						rooms:
						for (OperatingRoom room : rooms) {
							for (int i = 0; i < room.getHoursAvailable().size(); i++) {
								if (room.getHoursAvailable().get(i) == true) {
									surgeries.get(surgeriesCount).setRoomId(room.getRoomId());
									surgeries.get(surgeriesCount).setStartHour(room.getStartHour(i));
									surgeryManager.updateRoomHourDate(surgeries.get(surgeriesCount).getSurgeryId(),
											date, room.getStartHour(i), room.getRoomId());
									room.getHoursAvailable().set(i, false);
									break rooms;
								}
							}
						}
						if (surgeries.get(surgeriesCount).getRoomId() == -1) {
							System.out.println("No rooms");
							break;
						}
						teams:
						for (WorksWith team : ww) {
							for (int i = 0; i < team.getHoursAvailable().size(); i++) {
								if (team.getHoursAvailable().get(i) == true) {
									surgeries.get(surgeriesCount).setSurgeonId(team.getSurgeonID());
									team.changeHoursAvailable(i);
									surgeryManager.updateSurgeonId(surgeries.get(surgeriesCount).getSurgeryId(),
											team.getSurgeonID());
									break teams;
								}
							}
						}
						if (surgeries.get(surgeriesCount).getSurgeonId() == -1) {
							System.out.println("No teams");
							surgeryManager.deleteRoomHourDate(surgeries.get(surgeriesCount).getSurgeryId());
							break;
						}
						surgeryManager.updateDone(surgeries.get(surgeriesCount).getSurgeryId());
					}
					if (surgeries.size() - surgeriesCount == 0) {
						System.out.println("All surgeries are programmed");
					} else {
						System.out.println("Note: " + (surgeries.size() - surgeriesCount)
								+ " surgeries could not be programmed for this day, will pass for the next\n");
					}
					showSchedule(date);
				}
			} else {
				System.out.println("No surgeries in the data base, you must create surgeries before");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showSchedule(Date date) {
		if (surgeryManager.getListOfSurgeries(date).size() != 0) {
			List<Surgery> surgeries = surgeryManager.getListOfSurgeries(date);
			System.out.format("%-5s %-25s %-15s %-15s %-23s %-25s %-25s %-15s %-5s\n", "Id", "Patient", "Date",
					"StartHour", "Surgery Type", "Surgeon", "Nurse", "Room Number", "Room Floor");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			try {

				for (Surgery s : surgeries) {
					String sCompleteName = surgeonManager.getSurgeonById(s.getSurgeonId()).getName() + " "
							+ surgeonManager.getSurgeonById(s.getSurgeonId()).getSurname();
					int nurseId = worksWithManager.getNurseIdAssignedSurgeonDate(s.getSurgeonId(), date);
					String nCompleteName = nurseManager.getNurseById(nurseId).getNurseName() + " "
							+ nurseManager.getNurseById(nurseId).getNurseSurname();

					System.out.format("%-5d %-25s %-15s %-15s %-23s %-25s %-25s %-15d %-15d\n", s.getSurgeryId(),
							patientManager.getPatCompleteNametById(s.getPatientId()),
							new SimpleDateFormat("dd-MM-yyyy").format(s.getSurgeryDate()), s.getStartHour(),
							s.getSurgeryType(), sCompleteName, nCompleteName,
							operatingRoomManager.getRoomById(s.getRoomId()).getRoomNumber(),
							operatingRoomManager.getRoomById(s.getRoomId()).getRoomFloor());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No surgeries programmed for that date");
		}

	}

	private static void chooseActiveRooms() {
		try {
			if (operatingRoomManager.getListOfActiveOperatingRoom().size() != 0) {
				while (true) {
					System.out.println("These are the active rooms, do you want to make any modification?");
					for (OperatingRoom oR : operatingRoomManager.getListOfActiveOperatingRoom()) {
						System.out.println(oR);
					}
					System.out.println("1. Activate new room");
					System.out.println("2. Disable a room");
					System.out.println("3. No more modifications");
					int option;
					do {
						try {
							option = Integer.parseInt(reader.readLine());
							break;
						} catch (Exception e) {
							System.out.println("Not valid input");
						}
					} while (true);
					switch (option) {
					case 1:
						List<OperatingRoom> rooms = operatingRoomManager.getListOfOperatingRoom();
						rooms.removeAll(operatingRoomManager.getListOfActiveOperatingRoom());
						update: do {
							if (rooms.size() == 0) {
								System.out.println("All rooms are activated");
								break;
							}
							for (OperatingRoom oR : rooms) {
								System.out.println(oR);
							}
							System.out.print("Activate room: ");
							int roomId;
							do {
								try {
									roomId = Integer.parseInt(reader.readLine());
									break;
								} catch (Exception e) {
									System.out.println("Not valid input");
								}
							} while (true);
							for (OperatingRoom r : rooms) {
								if (r.getRoomId() == roomId) {
									operatingRoomManager.updateActivity(roomId, true);
									break update;
								}
							}
							System.out.println("Incorrect Id");
						} while (true);
						break;
					case 2:
						update: do {
							for (OperatingRoom oR : operatingRoomManager.getListOfActiveOperatingRoom()) {
								System.out.println(oR);
							}
							System.out.print("Disable room: ");
							int rId;
							do {
								try {
									rId = Integer.parseInt(reader.readLine());
									break;
								} catch (Exception e) {
									System.out.println("Not valid input");
								}
							} while (true);
							for (OperatingRoom r : operatingRoomManager.getListOfActiveOperatingRoom()) {
								if (r.getRoomId() == rId) {
									operatingRoomManager.updateActivity(rId, false);
									break update;
								}
							}
							System.out.println("Incorrect Id");
						} while (true);
						break;
					case 3:
						break;
					}
					if (option == 3) {
						break;
					}
				}
			} else {
				System.out.println("Choose the active rooms: ");
				List<OperatingRoom> rooms = operatingRoomManager.getListOfOperatingRoom();
				while (true) {
					rooms.removeAll(operatingRoomManager.getListOfActiveOperatingRoom());
					update: do {
						for (OperatingRoom oR : rooms) {
							System.out.println(oR);
						}
						System.out.print("Activate room: ");
						int roomId;
						do {
							try {
								roomId = Integer.parseInt(reader.readLine());
								break;
							} catch (Exception e) {
								System.out.println("Not valid input");
							}
						} while (true);
						for (OperatingRoom r : rooms) {
							if (r.getRoomId() == roomId) {
								operatingRoomManager.updateActivity(roomId, true);
								break update;
							}
						}
						System.out.println("Incorrect Id");
					} while (true);
					System.out.print("Do you want to activate another room (Y/N): ");
					String confirmation = reader.readLine();
					System.out.println(operatingRoomManager.getListOfActiveOperatingRoom());
					if (confirmation.equalsIgnoreCase("y")) {
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
		int roomNumber;
		do {
			try {
				roomNumber = Integer.parseInt(reader.readLine());
				break;
			} catch (Exception e) {
				System.out.println("Not valid input");
			}
		} while (true);
		System.out.println("Type the room floor: ");
		int roomFloor;
		do {
			try {
				roomFloor = Integer.parseInt(reader.readLine());
				break;
			} catch (Exception e) {
				System.out.println("Not valid input");
			}
		} while (true);
		OperatingRoom r = new OperatingRoom(roomNumber, roomFloor);
		operatingRoomManager.addOperatingRoom(r);
		System.out.println("OperatingRoom added successfully\n");
	}

	public static void createVacation(String role) throws Exception {
		int surgId = 0;
		int nurseId = 0;
		if (role.equals("surgeon")) {
			surgId = surgeonManager.getIdByEmail(u.getEmail());
		} else if (role.equals("nurse")) {
			nurseId = nurseManager.getIdByEmail(u.getEmail());
		}
		Date start = selectStartDate(role);
		Date end = new Date(start.getTime() + (1000 * 60 * 60 * 24 * 15));
		if (role.equals("surgeon")) {
			SurgeonVacation sVac = new SurgeonVacation(start, end, surgId);
			surgeonVacationManager.addVacation(sVac);
		} else if (role.equals("nurse")) {
			NurseVacation nVac = new NurseVacation(start, end, nurseId);
			nurseVacationManager.addVacation(nVac);
		}
		System.out.println("Vacation successfully saved!\n");
	}

	@SuppressWarnings("deprecation")
	public static Date selectStartDate(String role) throws Exception {
		Date start = null;
		Integer year;
		while (true) {
			try {
				System.out.println("Type the year:");
				do {
					try {
						year = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				if (year.toString().length() == 4) {
					break;
				}
				System.out.println("Not valid year");
			} catch (Exception e) {
				System.out.println("Not valid input");
			}
		}
		int surgId = 0;
		int nurseId = 0;
		if (role.equals("surgeon")) {
			surgId = surgeonManager.getIdByEmail(u.getEmail());
			if (surgeonVacationManager.countSurgeonVacations(surgId, year) == 2) {
				System.out.println("You cannot add more vacations these year\n");
				do {
					System.out.println("Do you want to select a new date? (Y/N)");
					String conf = reader.readLine();
					if (conf.equalsIgnoreCase("Y")) {
						selectStartDate(role);
						break;
					} else if (conf.equalsIgnoreCase("N")) {
						System.out.println("Vacation not saved");
						surgeonMenu();
						break;
					}
				} while (true);
			}
		} else if (role.equals("nurse")) {
			nurseId = nurseManager.getIdByEmail(u.getEmail());
			if (nurseVacationManager.countNurseVacations(nurseId, year) == 2) {
				System.out.println("You cannot add more vacations these year\n");
				do {
					System.out.println("Do you want to select a new date? (Y/N)");
					String conf = reader.readLine();
					if (conf.equalsIgnoreCase("Y")) {
						selectStartDate(role);
						break;
					} else if (conf.equalsIgnoreCase("N")) {
						System.out.println("Vacation not saved");
						NurseMenu();
						break;
					}
				} while (true);
			}
		}
		int option;
		do {
			System.out.println("These are the vacation periods, please choose one of them:");
			System.out.println("1) 1 june to 15 june");
			System.out.println("2) 16 june to 30 june");
			System.out.println("3) 1 july to 15 july");
			System.out.println("4) 16 july to 30 july");
			System.out.println("5) 1 august to 15 august");
			System.out.println("6) 16 august to 30 august");
			do {
				try {
					option = Integer.parseInt(reader.readLine());
					break;
				} catch (Exception e) {
					System.out.println("Not valid input");
				}
			} while (true);
			year = year - 1900;
			switch (option) {
			case 1:
				start = new Date(year, 5, 1);
				break;
			case 2:
				start = new Date(year, 5, 16);
				break;
			case 3:
				start = new Date(year, 6, 1);
				break;
			case 4:
				start = new Date(year, 6, 16);
				break;
			case 5:
				start = new Date(year, 7, 1);
				break;
			case 6:
				start = new Date(year, 7, 16);
				break;
			}
		} while (option < 1 || option > 6);
		Date today = Date.valueOf(LocalDate.now());
		if (start.compareTo(today) < 0) {
			System.out.println("You cannot add a vacation for a passed day");
			selectStartDate(role);
		}
		return start;
	}

	@SuppressWarnings("deprecation")
	public static Date selectStartDate(String role, int vacYear) throws Exception {
		Date start = null;
		Integer year;
		System.out.println(vacYear);
		while (true) {
			try {
				System.out.println("Type the year:");
				do {
					try {
						year = Integer.parseInt(reader.readLine());
						break;
					} catch (Exception e) {
						System.out.println("Not valid input");
					}
				} while (true);
				if (year.toString().length() == 4) {
					break;
				}
				System.out.println("Not valid year");
			} catch (Exception e) {
				System.out.println("Not valid input");
			}
		}
		int surgId = 0;
		int nurseId = 0;
		if (role.equals("surgeon")) {
			surgId = surgeonManager.getIdByEmail(u.getEmail());
			if (surgeonVacationManager.countSurgeonVacations(surgId, year) == 2 && year != vacYear) {
				System.out.println("You cannot add more vacations these year\n");
				do {
					System.out.println("Do you want to select a new date? (Y/N)");
					String conf = reader.readLine();
					if (conf.equalsIgnoreCase("Y")) {
						selectStartDate(role, vacYear);
						break;
					} else if (conf.equalsIgnoreCase("N")) {
						System.out.println("Vacation not saved");
						surgeonMenu();
						break;
					}
				} while (true);
			}
		} else if (role.equals("nurse")) {
			nurseId = nurseManager.getIdByEmail(u.getEmail());
			if (nurseVacationManager.countNurseVacations(nurseId, year) == 2 && year != vacYear) {
				System.out.println("You cannot add more vacations these year\n");
				do {
					System.out.println("Do you want to select a new date? (Y/N)");
					String conf = reader.readLine();
					if (conf.equalsIgnoreCase("Y")) {
						selectStartDate(role, vacYear);
						break;
					} else if (conf.equalsIgnoreCase("N")) {
						System.out.println("Vacation not saved");
						NurseMenu();
						break;
					}
				} while (true);
			}
		}
		int option;
		do {
			System.out.println("These are the vacation periods, please choose one of them:");
			System.out.println("1) 1 june to 15 june");
			System.out.println("2) 16 june to 30 june");
			System.out.println("3) 1 july to 15 july");
			System.out.println("4) 16 july to 30 july");
			System.out.println("5) 1 august to 15 august");
			System.out.println("6) 16 august to 30 august");
			do {
				try {
					option = Integer.parseInt(reader.readLine());
					break;
				} catch (Exception e) {
					System.out.println("Not valid input");
				}
			} while (true);
			year = year - 1900;
			switch (option) {
			case 1:
				start = new Date(year, 5, 1);
				break;
			case 2:
				start = new Date(year, 5, 16);
				break;
			case 3:
				start = new Date(year, 6, 1);
				break;
			case 4:
				start = new Date(year, 6, 16);
				break;
			case 5:
				start = new Date(year, 7, 1);
				break;
			case 6:
				start = new Date(year, 7, 16);
				break;
			}
		} while (option < 1 || option > 6);
		Date today = Date.valueOf(LocalDate.now());
		if (start.compareTo(today) < 0) {
			System.out.println("You cannot add a vacation for a passed day");
			selectStartDate(role);
		}
		return start;
	}

	public static Date getDate() throws Exception {
		Integer Year;
		Integer Month;
		Integer Day;
		do {
			System.out.println("Type the year:");
			do {
				try {
					Year = Integer.parseInt(reader.readLine());
					break;
				} catch (Exception e) {
					System.out.println("Not valid input");
				}
			} while (true);

		} while (Year.toString().length() != 4);
		do {
			System.out.println("Type the month [1-12]:");
			do {
				try {
					Month = Integer.parseInt(reader.readLine());
					break;
				} catch (Exception e) {
					System.out.println("Not valid input");
				}
			} while (true);
		} while (Month < 1 || Month > 12);
		do {
			System.out.println("Type the day:");
			do {
				try {
					Day = Integer.parseInt(reader.readLine());
					break;
				} catch (Exception e) {
					System.out.println("Not valid input");
				}
			} while (true);
		} while (Day < 1 || Month > 31);
		@SuppressWarnings("deprecation")
		Date date = new Date(Year - 1900, Month - 1, Day);
		return date;
	}

	public static void getSurgeonsOnVacation() throws Exception {
		System.out.println("Show all surgeons on vacation during any of these days");
		System.out.println("Type the start date:");
		Date start = getDate();
		System.out.println("Type the end date:");
		Date end = getDate();
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
			if (start.compareTo(end) > 0) {
				Date aux = start;
				start = end;
				end = aux;
			}
			surgeons = surgeonVacationManager.getSurgeonsOnVacation(start, end);
			List<SurgeonVacation> sVacations = new ArrayList<>();
			for (int i = 0; i < surgeons.size(); i++) {
				sVacations.addAll(surgeonVacationManager.getMyVacationsSurgeon(surgeons.get(i).getSurgeonId()));
			}
			for (int i = 0; i < sVacations.size(); i++) {
				System.out.print(surgeonManager.getNameById(sVacations.get(i).getSurgeonId()) + ": ");
				System.out.println(sVacations.get(i).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getNursesOnVacation() throws Exception {
		System.out.println("Show all nurses on vacation during any of these days");
		System.out.println("Type the start date:");
		Date start = getDate();
		System.out.println("Type the end date:");
		Date end = getDate();
		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			if (start.compareTo(end) > 0) {
				Date aux = start;
				start = end;
				end = aux;
			}
			nurses = nurseVacationManager.getNursesOnVacation(start, end);
			List<NurseVacation> nVacations = new ArrayList<>();
			for (int i = 0; i < nurses.size(); i++) {
				nVacations.addAll(nurseVacationManager.getMyVacationsNurse(nurses.get(i).getNurseId()));
			}
			for (int i = 0; i < nVacations.size(); i++) {
				System.out.print(nurseManager.getNameById(nVacations.get(i).getNurseId()) + ": ");
				System.out.println(nVacations.get(i).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Surgeon> avaliableSurgeons(Date date) {
		List<Surgeon> avaliable = surgeonManager.getListOfSurgeons();
		avaliable.removeAll(surgeonVacationManager.getSurgeonsOnVacation(date));
		return avaliable;
	}

	public static List<Nurse> avaliableNurses(Date date) {
		List<Nurse> avaliable = nurseManager.getListOfNurses();
		avaliable.removeAll(nurseVacationManager.getNursesOnVacation(date));
		return avaliable;
	}
}