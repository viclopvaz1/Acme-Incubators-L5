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

package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMany(int id);

	@Query("select m.forum from Message m where m.id=?1")
	Forum findForumByMessageId(int id);

	@Query("select f from Forum f where f.id=?1")
	Forum findForum(int id);

	@Query("select p.authenticated from Participation p where p.forum.id = ?1")
	Collection<Authenticated> findManyByForumId(int threadId);

	@Query("select a from Authenticated a where  a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedByUserAccountId(int id);

	@Query("select p.authenticated from Participation p where p.authenticated.userAccount.id = ?1")
	Authenticated findAuthenticatedByAuthenticatedId(int activeRoleId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
