package pl.lukaz.poligon.gae.workservice.server;

import java.util.List;

import pl.lukaz.poligon.gae.workservice.model.HasIdAsLong;

public interface AbstractRepository<T extends HasIdAsLong> {
	T findById(Long id);
	void save(T t);
	void delete(T t);
	
	List<T> findAll();
	void deleteAll();
	
	long count();
}
