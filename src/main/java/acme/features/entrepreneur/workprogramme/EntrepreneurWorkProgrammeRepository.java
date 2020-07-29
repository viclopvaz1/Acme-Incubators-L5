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

package acme.features.entrepreneur.workprogramme;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.entities.workprogrammes.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurWorkProgrammeRepository extends AbstractRepository {

	@Query("select wp from WorkProgramme wp where wp.id = ?1")
	WorkProgramme findOneById(int id);

	@Query("select wp from WorkProgramme wp where wp.investmentRound.id = ?1")
	Collection<WorkProgramme> findManyByInvestmentRoundId(int investmentRoundId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
