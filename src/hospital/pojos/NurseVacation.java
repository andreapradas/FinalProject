package hospital.pojos;

import java.time.LocalDate;
import java.util.Objects;

public class NurseVacation {
	private Integer vacationID;
	private Integer nurseID;
	private LocalDate starts;
	private LocalDate ends;
	
	
	public NurseVacation() {
		super();
	}
	
	public NurseVacation(Integer vacationID, Integer nurseID, LocalDate starts, LocalDate ends) {
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
	
	public LocalDate getStarts() {
		return starts;
	}

	public void setStarts(LocalDate starts) {
		this.starts = starts;
	}
	
	public LocalDate getEnds() {
		return ends;
	}

	public void setEnds(LocalDate ends) {
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
