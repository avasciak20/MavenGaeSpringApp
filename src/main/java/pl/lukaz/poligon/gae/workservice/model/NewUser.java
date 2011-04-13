package pl.lukaz.poligon.gae.workservice.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class NewUser {
	
	@NotNull
	@NotBlank
	private String firstName;
	@NotNull
	@NotBlank
	private String lastName;
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	@Size(min = 6)
	private String password;
	
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
}
