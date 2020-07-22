
package acme.features.administrator.dashboard;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select sector, count(a) from TechnologyRecord a group by a.sector")
	Collection<Object[]> totalNumberOftTechnologiesGroupedByActivitySector();

	@Query("select sector, count(a) from ToolRecord a group by a.sector")
	Collection<Object[]> totalNumberOfToolsGroupedByActivitySector();

	@Query("select indication, count(a) from TechnologyRecord a group by a.indication")
	Collection<Object[]> ratioOfOpenSourceTechnologies();

	@Query("select indication, count(a) from ToolRecord a group by a.indication")
	Collection<Object[]> ratioOfOpenSourceToolsVersusClosedSourceTools();

	@Query("select count(a) from ToolRecord a")
	Double totalTools();

	@Query("select count(a) from TechnologyRecord a")
	Double totalTechnologies();

	@Query("select count(a) from Application a")
	Double totalApplications();

	@Query("select round, count(a) from InvestmentRound a group by a.round")
	Collection<Object[]> ratioOfInvestmentRoundsGroupedByKind();

	@Query("select status, count(a) from Application a group by a.status")
	Collection<Object[]> ratioOfApplicationsGroupedByStatus();

	//	@Query("select DATE(a.creationMoment), count(a) from Application a where a.status = 'accepted' and a.creationMoment >= ?1 group by DAY(a.creationMoment)")
	//	Collection<Object[]> Accepted(Date moment);
	//
	//	@Query("select DATE(a.creationMoment), count(a) from Application a where a.status = 'pending' and a.creationMoment >= ?1 group by DAY(a.creationMoment)")
	//	Collection<Object[]> Pending(Date moment);
	//
	//	@Query("select DATE(a.creationMoment), count(a) from Application a where a.status = 'rejected' and a.creationMoment >= ?1 group by DAY(a.creationMoment)")
	//	Collection<Object[]> Rejected(Date moment);

	@Query("select count(a) from Application a where a.status=?1 and a.creationMoment < ?2")
	Integer getApplicationsByStatus(String status, Date moment);

}
