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

package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.entities.participations.Participation;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipationRepository extends AbstractRepository {

	@Query("select t from Forum t  where t.id = ?1")
	Forum findOneForumById(int id);

	@Query("select t from Forum t where t.authenticated.id = ?1")
	Collection<Forum> findManyByAuthenticatedId(int authenticatedId);

	@Query("select p.authenticated from Participation p where p.forum.id = ?1")
	Collection<Authenticated> findManyAuthenticatedByForumId(int forumId);

	@Query("select a from Authenticated a")
	Collection<Authenticated> findAllAuthenticated();

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select t.authenticated.id from Forum t where t.id = ?1")
	Integer findCreatorUserByForumId(int forumId);

	@Query("select p from Participation p where p.forum.id= ?1 ")
	Collection<Participation> findAllParticipationByForumId(int forumId);

	@Query("select p from Participation p where p.id = ?1")
	Participation findParticipationById(int participationId);

	@Query("select p.forum.authenticated from Participation p where p.id = ?1")
	Authenticated findCreatorUserByParticipationId(int participationId);

	@Query("select p from Participation p where p.authenticated.id = ?1")
	Collection<Participation> findParticipationByAuthId(int authenticatedId);

	@Query("select u from UserAccount u where u.username = ?1")
	UserAccount findUserByName(String userName);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByAccountId(int id);

	@Query("select u from UserAccount u")
	Collection<UserAccount> findAllUserAccount();

	@Query("select u.username from UserAccount u")
	Collection<String> findAllUserNames();

	@Query("select u.username from UserAccount u where u.id in (select a.userAccount.id from Authenticated a where a.id in (select m.authenticated.id from Participation m where m.forum.id = ?1))")
	Collection<String> findAllUserInParticipation(Integer integer);
}
