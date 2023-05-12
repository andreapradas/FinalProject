package hospital.xml;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import hospital.ifaces.NurseManager;
import hospital.ifaces.XMLManager;
import hospital.jdbc.JDBCManager;
import hospital.jdbc.JDBCNurseManager;
import hospital.pojos.Nurse;
import hospital.pojos.Patient;

public class XMLManagerImpl implements XMLManager {
	JDBCManager manager;
	private static JDBCManager jdbcManager;
	private static NurseManager nurseManager;


	@Override
	public void nurse2xml(Integer id) {
		Nurse n = null;

		try {
			// Do a sql query to get the nurse by the id
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * from Nurse where id =" + id;
			ResultSet rs = stmt.executeQuery(sql);
			String name = rs.getString("nurseName");
			String surname = rs.getString("nurseSurname");
			String email = rs.getString("nurseEmail");

			n = new Nurse(id, name, surname, email);

			rs.close();
			stmt.close();

			// export the nurse to the xml by the id
			JAXBContext jaxbContext = JAXBContext.newInstance(Nurse.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Nurse.xml");
			marshaller.marshal(n, file);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void patient2xml(Patient p) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Patient.xml");
			marshaller.marshal(p, file);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Patient xml2Patient(File xml) {
		Patient p = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			p = (Patient) unmarshaller.unmarshal(xml);

			// JDBC code to insert patient to table patients
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Nurse xml2Nurse(File xml) {
		Nurse n = null;
		try {

			nurseManager = new JDBCNurseManager(jdbcManager);
			JAXBContext jaxbContext = JAXBContext.newInstance(Nurse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			n = (Nurse) unmarshaller.unmarshal(xml);

			// JDBC code to insert patient to table patients
			nurseManager.addNurse(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

}
