/*
 * EntrepreneurProviderRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.configurations.Configuration;
import acme.entities.forums.Forum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1")
	Collection<Application> findManyByEntrepreneurId(int eId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select count(a) from Forum a where a.investmentRound.id = ?1")
	int findTotalForumByEntrepreneur(int entrepreneurId);

	@Query("select a from Forum a where a.investmentRound.id = ?1")
	Forum findForumByInvestmentRound(int investmentRoundId);
}
