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

package acme.features.entrepreneur.investmentround;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.entities.investmentrounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.workprogrammes.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from InvestmentRound i where i.entrepreneur.id =?1")
	Collection<InvestmentRound> findManyByEntrepreneurId(int entrepreneurId);

	@Query("select count(ar) from AccountingRecord ar where ar.investmentRound.id = ?1")
	int findAccountingRecordByInvestmentRoundId(int investmentRoundId);

	@Query("select count(a) from Application a where a.investmentRound.id = ?1")
	int findApplicationByInvestmentRoundId(int investmentRoundId);

	@Query("select sum(wp.budget.amount) from WorkProgramme wp where wp.investmentRound.id = ?1")
	Double sumBudgetWorkProgramme(int id);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select wp from WorkProgramme wp where wp.investmentRound.id = ?1")
	Collection<WorkProgramme> findAllWorkProgrammeByInvestmentRoundId(int investmentRoundId);

	@Query("select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findEntrepreneurById(int entrepreneurId);

	@Query("select e.ticker from InvestmentRound e")
	Collection<String> allTickers();

}
