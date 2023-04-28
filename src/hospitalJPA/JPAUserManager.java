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
	public User getUserByEmail(String email) {
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email= ?", User.class);
		q.setParameter(1, email);
		User u= (User) q.getSingleResult();
		return u;
	}
	
	@Override
	public User getChief() {
		Query q = em.createNativeQuery("SELECT users.* FROM users INNER JOIN "
				+ "roles ON roles.id= users.role_id WHERE roles.NAME= ?", User.class);
		q.setParameter(1, "chiefSurgeon");
		User u= (User) q.getSingleResult();
		return u;
	}
	
	@Override
	public void changeChief(String email) {
		// TODO Auto-generated method stub
		
		try{
			em.getTransaction().begin();
			User u= getUserByEmail(email);
			User u1= getChief();
			u.setRole(getRole("chiefSurgeon"));
			System.out.println(u1);
			u1.setRole(getRole("surgeon"));
			em.getTransaction().commit();
			em.close();
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
