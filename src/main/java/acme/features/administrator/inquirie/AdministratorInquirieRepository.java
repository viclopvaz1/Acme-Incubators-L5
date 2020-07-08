
package acme.features.administrator.inquirie;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiries.Inquirie;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInquirieRepository extends AbstractRepository {

	@Query("select a from Inquirie a where a.id = ?1")
	Inquirie findOneById(int id);

	@Query("select a from Inquirie a where a.deadline > CURRENT_TIMESTAMP")
	Collection<Inquirie> findMany();

}
