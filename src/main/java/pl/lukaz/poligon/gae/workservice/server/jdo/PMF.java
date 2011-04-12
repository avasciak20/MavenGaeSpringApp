package pl.lukaz.poligon.gae.workservice.server.jdo;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.FactoryBean;

public class PMF implements FactoryBean<PersistenceManagerFactory> {

	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	@Override
	public PersistenceManagerFactory getObject() throws Exception {
		return pmfInstance;
	}

	@Override
	public Class<?> getObjectType() {
		return PersistenceManagerFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
