/*
 * InvestorProviderRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.configurations.Configuration;
import acme.entities.investmentrounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.investor.id = ?1")
	Collection<Application> findManyByInvestorId(int eId);

	@Query("select i from Investor i where i.id = ?1")
	Investor findInvestorById(int investorId);

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select a.ticker from Application a")
	Collection<String> allReferences();
}
