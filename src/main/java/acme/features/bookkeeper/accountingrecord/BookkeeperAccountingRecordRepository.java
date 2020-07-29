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

package acme.features.bookkeeper.accountingrecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingrecords.AccountingRecord;
import acme.entities.configurations.Configuration;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select ar from AccountingRecord ar where ar.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select ar from AccountingRecord ar where ar.status = 1")
	Collection<AccountingRecord> findMany();

	@Query("select ar from AccountingRecord ar where ar.bookkeeper.id =?1")
	Collection<AccountingRecord> findManyByBookkeeperId(int entrepreneurId);

	@Query("select a from Bookkeeper a where a.id = ?1")
	Bookkeeper findBookkeeperById(int bookkeeperId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
