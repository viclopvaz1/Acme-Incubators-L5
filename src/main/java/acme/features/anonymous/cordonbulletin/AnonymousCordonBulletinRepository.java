
package acme.features.anonymous.cordonbulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cordonbulletins.cordonBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCordonBulletinRepository extends AbstractRepository {

	@Query("select s from cordonBulletin s")
	Collection<cordonBulletin> findMany();

}
