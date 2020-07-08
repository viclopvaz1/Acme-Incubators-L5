
package acme.features.anonymous.ruizbulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.ruizbulletins.ruizBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousRuizBulletinRepository extends AbstractRepository {

	@Query("select s from ruizBulletin s")
	Collection<ruizBulletin> findMany();

}
