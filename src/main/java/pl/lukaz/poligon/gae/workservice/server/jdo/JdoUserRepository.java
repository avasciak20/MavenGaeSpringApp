package pl.lukaz.poligon.gae.workservice.server.jdo;

import org.springframework.stereotype.Repository;

import pl.lukaz.poligon.gae.workservice.model.User;
import pl.lukaz.poligon.gae.workservice.server.UserRepository;

@Repository
public class JdoUserRepository extends JdoAbstractRepository<User> implements UserRepository {

	@Override
	protected void rewrite(User src, User des) {
		des.setId(src.getId()); 
		des.setFirstName(src.getFirstName()); 
		des.setLastName(src.getLastName());
		des.setEmail(src.getEmail());
		des.setPassword(src.getPassword());
	}

}
