package pl.lukaz.poligon.gae.workservice.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.lukaz.poligon.gae.workservice.server.jdo.HasKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Position implements HasKey{
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String companyName;
	@Persistent
	private String positionName;
	
	public Position() {
	}
	
	public Position(String companyName, String positionName) {
		this.companyName = companyName;
		this.positionName = positionName;
	}
	
	public Position(Key key, String companyName, String positionName) {
		this.key=key;
		this.companyName = companyName;
		this.positionName = positionName;
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
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
	
	
}
