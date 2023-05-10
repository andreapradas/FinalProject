package hospital.xml;


import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import hospital.ifaces.XMLManager;
import hospital.jdbc.JDBCManager;
import hospital.pojos.Nurse;
import hospital.pojos.Patient;

public class XMLManagerImpl implements XMLManager {
	JDBCManager manager;
	@Override
	public void nurse2xml(Integer id) {
		Nurse n = null;
		manager = new JDBCManager();
		try {
		//Do a sql query to get the nurse by the id
		Statement stmt = manager.getConnection().createStatement();
		String sql = "SELECT * from Nurse where id =" +id;
		ResultSet rs = stmt.executeQuery(sql);
		String name = rs.getString("nurseName");
		String surname = rs.getString("nurseSurname");
		String email = rs.getString("nurseEmail");
		
		n = new Nurse(id, name, surname, email);
		
		rs.close();
		stmt.close();
		
		//export the nurse to the xml by the id
		JAXBContext jaxbContext = JAXBContext.newInstance(Nurse.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File("./xmls/Nurse.xml");
		marshaller.marshal(n, file);
			
		}catch (Exception e)
		{
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
			
		}catch (Exception e)
		{
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
		}catch (Exception e)
		{
		e.printStackTrace();
		}
		return p;
	}

}
