
package acme.features.administrator.bookkeeperrequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookkeeperrequests.BookkeeperRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBookkeeperRequestRepository extends AbstractRepository {

	@Query("select a from BookkeeperRequest a where a.id=?1")
	BookkeeperRequest findOneById(int idRequest);

	@Query("select a from BookkeeperRequest a")
	Collection<BookkeeperRequest> findMany();

}
