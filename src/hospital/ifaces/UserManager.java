package hospital.ifaces;

import java.util.List;

import hospital.pojos.Role;
import hospital.pojos.User;

public interface UserManager {
	
	public void disconnect();
	public void newUser(User u);
	public void newRole(Role r);
	public Role getRole(String email);
	public List<Role> getRoles();
	public User checkPassword(String email, String password);

}
