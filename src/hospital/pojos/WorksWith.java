package hospital.pojos;

import java.sql.Date;
import java.util.Objects;

public class WorksWith {
	private Integer teamID;
	private Integer surgeonID;
	private Integer nurseID;
	private Date date;
	
	
	public WorksWith() {
		super();
	}
	
	public WorksWith(Integer teamID, Integer surgeonID, Integer nurseID, Date date) {
		super();
		this.teamID = teamID;
		this.surgeonID = surgeonID;
		this.nurseID = nurseID;
		this.date = date;	
	}
	
	@Override
	public String toString() {
		return 
				"WorksWith [teamID=" + teamID + ", surgeonID=" + surgeonID + ", nurseID=" + nurseID + ", date=" + date + "]";
	}

	public Integer getTeamID() {
		return teamID;
	}

	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	
	public Integer getSurgeonID() {
		return surgeonID;
	}

	public void setSurgeonID(Integer surgeonID) {
		this.surgeonID = surgeonID;
	}
	
	public Integer getNurseID() {
		return nurseID;
	}

	public void setNurseID(Integer nurseID) {
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
