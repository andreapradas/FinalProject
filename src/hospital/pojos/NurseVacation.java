package hospital.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class NurseVacation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3514210278458069620L;
	private Integer vacationID;
	private Integer nurseID;
	private Date starts;
	private Date ends;
	
	
	public NurseVacation() {
		super();
	}
	
	public NurseVacation(Integer vacationID, Integer nurseID, Date starts, Date ends) {
		super();
		this.vacationID = vacationID;
		this.nurseID = nurseID;
		this.starts = starts;
		this.ends = ends;	
	}
	
	@Override
	public String toString() {
		return 
				"NurseVacation [VacationID=" + vacationID + ", nurseID=" + nurseID + ", starts=" + starts + ", ends=" + ends + "]";
	}

	public Integer getVacationID() {
		return vacationID;
	}

	public void setVacationID(Integer vacationID) {
		this.vacationID = vacationID;
	}
	
	public Integer getNurseID() {
		return nurseID;
	}

	public void setNurseId(Integer nurseID) {
		this.nurseID = nurseID;
	}
	
	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}
	
	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(vacationID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NurseVacation other = (NurseVacation) obj;
		return Objects.equals(vacationID, other.vacationID);
	}
}
