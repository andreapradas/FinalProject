package hospital.ifaces;

import hospital.pojos.Surgeon;

public interface SurgeonManager {
	String getNameById(int id);
	void addSurgeon(Surgeon surg);
	
}
