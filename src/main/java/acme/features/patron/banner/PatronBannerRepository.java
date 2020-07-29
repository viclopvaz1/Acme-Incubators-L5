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

package acme.features.patron.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.configurations.Configuration;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findOneById(int id);

	@Query("select b from Banner b")
	Collection<Banner> findMany();

	@Query("select a from Banner a where a.patron.id = ?1")
	Collection<Banner> findManyByPatron(int patronId);

	@Query("select c.spamWords from Configuration c")
	String spamWords();

	@Query("select s from Patron s where s.id = ?1")
	Patron findPatronById(int patronId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

}
