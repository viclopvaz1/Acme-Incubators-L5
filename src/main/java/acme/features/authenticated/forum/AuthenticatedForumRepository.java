/*
 * AuthenticatedProviderRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.entities.forums.Forum;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneById(int id);

	@Query("select f from Forum f where f.authenticated.id = ?1")
	Collection<Forum> findMany(int id);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select distinct p.forum from Participation p where p.authenticated.id = ?1")
	Collection<Forum> findManyForumByAuthenticatedId(int activeRoleId);

	@Query("select p.authenticated from Participation p where p.authenticated.userAccount.id = ?1")
	Authenticated findAuthenticatedByAuthenticatedId(int activeRoleId);

	@Query("select a from Authenticated a WHERE exists (select f from Forum f where f.authenticated.id = a.id) AND a.userAccount.id = ?1")
	Authenticated findUser(int id);

}
