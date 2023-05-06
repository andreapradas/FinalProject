package hospital.pojos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorksWith {
	private int teamID;
	private int surgeonID;
	private int nurseID;
	private Date date;
	private List<Boolean> hoursAvailable;
	
	
	public WorksWith() {
		super();
	}
	
	public WorksWith(int teamID, int surgeonID, int nurseID, Date date) {
		super();
		this.teamID = teamID;
		this.surgeonID = surgeonID;
		this.nurseID = nurseID;
		this.date = date;	
		this.hoursAvailable = createHoursRange(4);
	}
	
	public WorksWith(int surgeonID, int nurseID) {
		super();
		this.surgeonID = surgeonID;
		this.nurseID = nurseID;
		this.hoursAvailable = createHoursRange(4);
	}
	
	private List<Boolean> createHoursRange(int rangeAmount){
		List<Boolean> hoursAvailable = new ArrayList<Boolean>();
		for(int i=0; i<rangeAmount; i++) {
			hoursAvailable.add(true); 
		}
		return hoursAvailable;
	}
	
	public void changeHoursAvailable(int hourRange) {//Cambia el hueco que le pasa a OCUPADO
		this.hoursAvailable.set(hourRange, false);
	}
	
	@Override
	public String toString() {
		return 
				"WorksWith [teamID=" + teamID + ", surgeonID=" + surgeonID + ", nurseID=" + nurseID + ", date=" + date + "]";
	}

	public List<Boolean> getHoursAvailable() {
		return hoursAvailable;
	}

	public void setHoursAvailable(List<Boolean> hoursAvailable) {
		this.hoursAvailable = hoursAvailable;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	
	public int getSurgeonID() {
		return surgeonID;
	}

	public void setSurgeonID(int surgeonID) {
		this.surgeonID = surgeonID;
	}
	
	public int getNurseID() {
		return nurseID;
	}

	public void setNurseID(int nurseID) {
		this.nurseID = nurseID;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(teamID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorksWith other = (WorksWith) obj;
		return Objects.equals(teamID, other.teamID);
	}
}
