package pl.lukaz.poligon.gae.workservice.server.jdo;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import pl.lukaz.poligon.gae.workservice.model.HasIdAsLong;
import pl.lukaz.poligon.gae.workservice.server.AbstractRepository;

public abstract class JdoAbstractRepository<T extends HasIdAsLong> implements AbstractRepository<T> {

	@Autowired
	PersistenceManagerFactory pmfInstance;
	
	private Class<T> persistentClass;
	
	protected Class<T> getPersistentClass() {
		return persistentClass;
	}

	public JdoAbstractRepository() {
		persistentClass=(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(T t) {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		
		try{
			if(t.getId()!=null){
				T atachedT=pm.getObjectById(getPersistentClass(), t.getId());
				pm.deletePersistent(atachedT);
			}
		}finally{
			pm.close();	
		}
	}

	@Override
	public void deleteAll() {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		
		try{
			pm.deletePersistentAll(findAll());
		}finally{
			pm.close();	
		}
	}

	@Override
	public List<T> findAll() {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		
		try{
			List<T> all=new ArrayList<T>();
			Extent<T> extent = pm.getExtent(getPersistentClass());
			for(T t : extent){
				all.add(t);
			}
			extent.closeAll();
			
			return all;
		}finally{
			pm.close();	
		}
	}

	@Override
	public T findById(Long id) {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		
		try{
			T t=pm.getObjectById(getPersistentClass(), id);
			
			return t;
		}finally{
			pm.close();	
		}
	}

	@Override
	public void save(T t) {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		
		try{
			if(t.getId()==null){
				pm.makePersistent(t);
			}else{
				T atachedT=pm.getObjectById(getPersistentClass(), t.getId());
				rewrite(t,atachedT);
			}
		}finally{
			pm.close();	
		}
	}

	abstract protected void rewrite(T src, T des);
}
