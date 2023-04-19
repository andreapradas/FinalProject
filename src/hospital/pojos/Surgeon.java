package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Surgeon implements Serializable{
	private static final long serialVersionUID = 5459002349058385563L;
	
	private int surgeonId;
	private String name;
	private boolean chief;
	private boolean avaliable;
	
	public Surgeon() {
		super();
	}
	
	public Surgeon(String name, boolean chief){
		super();
		this.name= name;
		this.chief= chief;
		this.avaliable= true;
	}
	
	public Surgeon(int surgeonId, String name, boolean chief){
		this.surgeonId= surgeonId;
		this.name= name;
		this.chief= chief;
		this.avaliable= true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.surgeonId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Surgeon other = (Surgeon) obj;
		return Objects.equals(this.surgeonId, other.surgeonId);
	}

	@Override
	public String toString() {
		return "Surgeon [id= " + surgeonId + ", name= " + name +"]";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public int getSurgeonId() {
		return surgeonId;
	}
	
	public boolean getChief() {
		return chief;
	}
	
	public boolean getAvaliable() {
		return avaliable;
	}
	
	public void setChief(boolean chief) {
		this.chief= chief;
	}
	
	public void setAvaliable(boolean avaliable) {
		this.avaliable= avaliable;
	}
}
