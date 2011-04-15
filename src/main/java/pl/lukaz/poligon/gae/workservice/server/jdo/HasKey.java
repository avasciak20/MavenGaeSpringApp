package pl.lukaz.poligon.gae.workservice.server.jdo;

import com.google.appengine.api.datastore.Key;

public interface HasKey {
	Key getKey();
	String getStringKey();
}
