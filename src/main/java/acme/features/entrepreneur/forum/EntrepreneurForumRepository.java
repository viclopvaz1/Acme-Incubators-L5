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

package acme.features.entrepreneur.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.entities.participations.Participation;
import acme.entities.roles.Investor;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurForumRepository extends AbstractRepository {

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneById(int id);

	@Query("select f from Forum f where f.investmentRound.entrepreneur.id = ?1")
	Collection<Forum> findMany(int id);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select m from Message m  where m.forum.id =?1")
	Collection<Message> findMessagesByForumId(int forumid);

	@Query("select p from Participation p  where p.forum.id =?1")
	Collection<Participation> findParticipationsByForumId(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByAccountId(int authenticatedId);

	@Query("select count(a) from Forum a where a.investmentRound.id = ?1")
	int findTotalForumByEntrepreneur(int entrepreneurId);

	@Query("select a.investor from Application a where a.investmentRound.id = ?1 and a.status ='accepted'")
	Collection<Investor> findInvestorByInvestmentRoundId(int investmentRoundId);
}
