package pl.lukaz.poligon.gae.workservice.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.appengine.api.datastore.Key;

public class PositionFormBean {
	@NotEmpty
	private String companyName;
	@NotEmpty
	private String positionName;
	
	private Key key;
	
	public PositionFormBean() {
	}
	
	public PositionFormBean(String companyName, String positionName) {
		this.companyName = companyName;
		this.positionName = positionName;
	}
	
	public PositionFormBean(String companyName, String positionName, Key key) {
		this.companyName = companyName;
		this.positionName = positionName;
		this.key=key;
	}
	
	public PositionFormBean(Position position) {
		this.companyName = position.getCompanyName();
		this.positionName = position.getPositionName();
		this.key = position.getKey();
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	public Position buildPositionClass(){
		return new Position(key, companyName, positionName);
	}
}
