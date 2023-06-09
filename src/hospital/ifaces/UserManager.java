package hospital.ifaces;

import java.util.List;

import hospital.pojos.Role;
import hospital.pojos.User;

public interface UserManager {
	
	public void disconnect();
	public void newUser(User u);
	public void newRole(Role r);
	public Role getRole(String roletype);
	public List<Role> getRoles();
	public User checkPassword(String email, String password);
	public List<User> getUsers();
	void changeChief(String email);
	User getUserByEmail(String email);
	User getChief();
	List<User> getSpecificUsers(String userRole);
	void deletUser(String email);

}
