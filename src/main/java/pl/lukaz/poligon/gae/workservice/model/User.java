package pl.lukaz.poligon.gae.workservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.lukaz.poligon.gae.workservice.server.jdo.HasKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class User implements Serializable, HasKey {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String lastName;
	@Persistent
	private String firstName;
	@Persistent
	private String email;
	@Persistent
	private String password;
	
	@Persistent
	private List<Position> positions;
	
	public User() {
		positions=new ArrayList<Position>();
	}
	
	public User(String firstName, String lastName, String email, String password, List<Position> positions) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.positions=positions;
	}
	
	public User(Key key, String lastName, String firstName, String email,
			String password, List<Position> positions) {
		super();
		this.key = key;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.positions = positions;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	@Override
	public String getStringKey() {
		if(key==null)return "";
		return KeyFactory.keyToString(key);
	}
}
