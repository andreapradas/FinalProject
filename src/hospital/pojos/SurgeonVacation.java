package hospital.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SurgeonVacation implements Serializable{

	private static final long serialVersionUID = -6939843271309580766L;

	private int vacationId;
	private LocalDate starts;
	private LocalDate ends;
	private int surgeonId;
	
	public SurgeonVacation(int vacationId, LocalDate starts, int surgeonId) {
		this.vacationId= vacationId;
		this.starts= starts;
		this.ends= starts.plusDays(15);
		this.surgeonId= surgeonId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.vacationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurgeonVacation other = (SurgeonVacation) obj;
		return Objects.equals(this.vacationId, other.vacationId);
	}

	@Override
	public String toString() {
		//select surgeon name where Surgeon.surgeonid= SurgeonVacation.surgeonId
		return "Surgeon vacation [name= " + surgeonId + ", start date= " + starts + ", end date= " + ends + "]";
	}
	
	public LocalDate getStartDate() {
		return starts;
	}
	
	public void setStartDate(LocalDate start) {
		this.starts= start;
	}
	
	public LocalDate getEndDate() {
		return ends;
	}
	
	public void setEndDate(LocalDate end) {
		this.ends= end;
	}
	
	public int getVacationId() {
		return vacationId;
	}
		
	public int getSurgeonOnVacationId() {
		return surgeonId;
	}
}
