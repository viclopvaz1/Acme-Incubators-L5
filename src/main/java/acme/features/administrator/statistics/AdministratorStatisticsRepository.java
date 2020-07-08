
package acme.features.administrator.statistics;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorStatisticsRepository extends AbstractRepository {

	@Query("select count(*) from Notice")
	Double findNotice();

	@Query("select count(*) from TechnologyRecord")
	Double findTechnologyRecord();

	@Query("select count(*) from ToolRecord")
	Double findToolRecord();

	@Query("select i.minMoney from Inquirie i where datediff(current_date(), i.deadline)<0 ")
	List<Money> minInquirie();

	@Query("select i.maxMoney from Inquirie i where datediff(current_date(), i.deadline)<0")
	List<Money> maxInquirie();

	@Query("select i.minMoney from Overture i where datediff(current_date(), i.deadline)<0 ")
	Money minOverture();

	@Query("select i.maxMoney from Overture i where datediff(current_date(), i.deadline)<0")
	List<Money> maxOverture();

	@Query("select avg(minMoney.amount) from Inquirie o where o.deadline >= current_date")
	Double avgMinActiveInquirie();

	@Query("select avg(maxMoney.amount) from Inquirie o where o.deadline >= current_date")
	Double avgMaxActiveInquirie();

	@Query("select avg(minMoney.amount) from Overture o where o.deadline >= current_date")
	Double avgMinActiveOverture();

	@Query("select avg(maxMoney.amount) from Overture o where o.deadline >= current_date")
	Double avgMaxActiveOverture();

	@Query("select stddev(maxMoney.amount) from Inquirie o where o.deadline >= current_date")
	Double stDevMaxActiveInquirie();

	@Query("select stddev(minMoney.amount) from Inquirie o where o.deadline >= current_date")
	Double stDevMinActiveInquirie();

	@Query("select stddev(maxMoney.amount) from Overture o where o.deadline >= current_date")
	Double stDevMaxActiveOverture();

	@Query("select stddev(minMoney.amount) from Overture o where o.deadline >= current_date")
	Double stDevMinActiveOverture();

	@Query("select avg(select count(a) from InvestmentRound a where a.entrepreneur.id = e.id) from Entrepreneur e")
	Double averageNumberOfInvestmentRoundsPerEntrepreneur();

	@Query("select avg(select count(a) from Application a where exists(select j from InvestmentRound j where j.entrepreneur.id = e.id)) from Entrepreneur e")
	Double averageNumberOfApplicationsPerEntrepreneur();

	@Query("select avg(select count(a) from Application a where a.investor.id = e.id) from Investor e")
	Double averageNumberOfApplicationPerInvestor();

}
