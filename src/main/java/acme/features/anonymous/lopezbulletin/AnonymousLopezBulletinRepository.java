
package acme.features.anonymous.lopezbulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.lopezbulletins.lopezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousLopezBulletinRepository extends AbstractRepository {

	@Query("select s from lopezBulletin s")
	Collection<lopezBulletin> findMany();

}
