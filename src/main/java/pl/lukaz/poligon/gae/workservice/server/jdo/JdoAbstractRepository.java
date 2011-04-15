package pl.lukaz.poligon.gae.workservice.server.jdo;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.PersistenceManagerFactoryUtils;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;

import pl.lukaz.poligon.gae.workservice.model.User;
import pl.lukaz.poligon.gae.workservice.server.AbstractRepository;

@Repository
public abstract class JdoAbstractRepository<T extends HasKey> implements
		AbstractRepository<T> {

	private Class<T> persistentClass;

	protected Class<T> getPersistentClass() {
		return persistentClass;
	}

	public JdoAbstractRepository() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	private PersistenceManagerFactory pmf;

	protected PersistenceManager getPersistenceManager() {
		return PersistenceManagerFactoryUtils.getPersistenceManager(pmf, true);
	}

	public void setPmf(final PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(T t) {
		PersistenceManager pm = getPersistenceManager();

			if (t.getKey() != null) {
				T atachedT = pm.getObjectById(getPersistentClass(), t.getKey());
				pm.deletePersistent(atachedT);
			}
	}

	@Override
	public void deleteAll() {
		PersistenceManager pm = getPersistenceManager();

			pm.deletePersistentAll(findAll());
	}

	@Override
	public List<T> findAll() {
		PersistenceManager pm = getPersistenceManager();

			Query query = pm.newQuery(getPersistentClass());
			List<T> all = (List<T>) query.execute();
			return all;
	}

	@Override
	public T findById(Key key) {
		PersistenceManager pm = getPersistenceManager();
		
		T t=pm.getObjectById(getPersistentClass(), key);
		
		return t;
	}
	

	@Override
	public void save(T t) {
		PersistenceManager pm = getPersistenceManager();

			if (t.getKey() == null) {
				pm.makePersistent(t);
			} else {
				T atachedT = pm.getObjectById(getPersistentClass(), t.getKey());
				rewrite(t, atachedT);
			}
	}

	abstract protected void rewrite(T src, T des);
}
