package pl.lukaz.poligon.gae.workservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.google.appengine.api.datastore.Key;

public class UserFormBean {
	
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Email
	private String email;
	@Size(min = 6)
	private String password;
	
	private Key key;
	
	@NotNull
	@Valid
	private List<PositionFormBean> positions;
	
	public UserFormBean() {
		positions=new ArrayList<PositionFormBean>();
	}
	
	public UserFormBean(User user) {
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setPositions(buildPositionFormBeansList(user.getPositions()));
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
	public List<PositionFormBean> getPositions() {
		return positions;
	}
	public void setPositions(List<PositionFormBean> positions) {
		this.positions = positions;
	}
	
	public User buildUserClass(){
		return new User(key, lastName, firstName, email, password, buildPositionsList());
	}
	private List<Position> buildPositionsList() {
		List<Position> positionsList=new ArrayList<Position>();
		
		for(PositionFormBean positionFormBean: positions)
			positionsList.add(positionFormBean.buildPositionClass());
		
		return positionsList;
	}
	
	private List<PositionFormBean> buildPositionFormBeansList(List<Position> list) {
		List<PositionFormBean> positionsList=new ArrayList<PositionFormBean>();
		
		for(Position position: list)
			positionsList.add(new PositionFormBean(position));
		
		return positionsList;
	}
}
