
package acme.features.administrator.bookkeeper;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.bookkeeperrequests.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorBookkeeperRepository extends AbstractRepository {

	@Query("select a from BookkeeperRequest a where a.id=?1")
	BookkeeperRequest findBookkeeperRequestById(int idRequest);

	@Query("select a from BookkeeperRequest a")
	Collection<BookkeeperRequest> findManyBookkeeperRequest();

	@Query("select a from Bookkeeper a where a.id=?1")
	Bookkeeper findBookkeeperById(int idRequest);
}
