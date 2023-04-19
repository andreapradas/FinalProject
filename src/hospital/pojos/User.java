package hospital.pojos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity 
@Table(name= "users")

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2004663877998019879L;
	private Integer id;
	private String email;
	private byte[] password;
	private  Role role;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String email, byte[] password, Role role) {
		super();
		@Id
		@GeneratedValue(generator= "users")
		@TableGenerator(names= "users", table = "sqlite_sequences", pkColumnName = "name", valueColumnName= "seq", pkColumnVale= "users")
		this.id = id;
		this.email = email;
		@Lob
		this.password = password;
		@ManyToOne
		@JoinColumn(name= "role_id")
		this.role = role;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + Objects.hash(email, id, role);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Arrays.equals(password, other.password) && Objects.equals(role, other.role);
	}	
}