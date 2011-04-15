package pl.lukaz.poligon.gae.workservice.server;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface AbstractRepository<T> {
	T findById(Key key);
	void save(T t);
	void delete(T t);
	
	List<T> findAll();
	void deleteAll();
	
	long count();
}
