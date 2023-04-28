package hospitalJPA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospital.ifaces.UserManager;
import hospital.pojos.Role;
import hospital.pojos.User;

public class JPAUserManager implements UserManager{

	private EntityManager em;
	
	public JPAUserManager() {
		this.connect();
	}

	public void connect() {
		// TODO Auto-generated method stub
		em = Persistence.createEntityManagerFactory("hospital-surgeries").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key= ON").executeUpdate();
		em.getTransaction().commit();
		System.out.println(this.getRoles().toString());
		if(this.getRoles().isEmpty()) {
			//Query q = em.createNativeQuery("ALTER TABLE roles AUTO_INCREMENT=0");
			//q.executeUpdate();
			Role surgeon= new Role("surgeon");
			Role nurse= new Role("nurse");
			Role chiefSurgeon= new Role("chiefSurgeon");
			this.newRole(surgeon);
			this.newRole(nurse);
			this.newRole(chiefSurgeon);
		}
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		em.close();
	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRoleByEmail(String email) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public Role getRole(String roletype) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE NAME= ?", Role.class);
		q.setParameter(1, roletype);
		Role role= (Role) q.getSingleResult();
		return role;
	}
	
	@Override
	public void changeChief(String email) {
		// TODO Auto-generated method stub
		
		try{
			Query q = em.createNativeQuery("UPDATE user INNER JOIN role "
					+ "SET user.role_id= (SELECT id FROM roles WHERE name= ?) WHERE"
					+ "user.email= ?");
			q.setParameter(1, "surgeon");
			q.setParameter(2, email);
			
			q.executeUpdate();
			
			Query q2 = em.createNativeQuery("UPDATE user INNER JOIN role "
					+ "SET user.role_id= roles.id WHERE"
					+ "roles.name= ? AND user.email= ?");
			q2.setParameter(1, "chiefSurgeon");
			q2.setParameter(2, email);
			
			q2.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM users", User.class);
		List<User> users = (List<User>) q.getResultList();
		return users;
	}

	@Override
	public User checkPassword(String email, String password) {
		// TODO Auto-generated method stub
		User u= null;
		Query q= em.createNativeQuery("SELECT * FROM users WHERE email=? AND password=?", User.class);
		q.setParameter(1, email);
		try {
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash= md.digest();
			q.setParameter(2, hash);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		try {
			u= (User) q.getSingleResult();
		}catch(NoResultException e){
		}
		
		return u;
	}

}
