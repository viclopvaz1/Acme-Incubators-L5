
package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOftTechnologiesGroupedByActivitySector", "ratioOfOpenSourceTechnologies", "totalNumberOfToolsGroupedByActivitySector", "ratioOfOpenSourceToolsVersusClosedSourceTools", "totalTools", "totalTechnologies",
			"ratioOfInvestmentRoundsGroupedByKind", "ratioOfApplicationsGroupedByStatus", "totalApplications", "Accepted", "Pending", "Rejected", "Dias");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -15);
		Date moment = cal.getTime();

		Collection<Object[]> totalNumberOftTechnologiesGroupedByActivitySector = this.repository.totalNumberOftTechnologiesGroupedByActivitySector();
		Collection<Object[]> totalNumberOfToolsGroupedByActivitySector = this.repository.totalNumberOfToolsGroupedByActivitySector();
		Collection<Object[]> ratioOfOpenSourceTechnologies = this.repository.ratioOfOpenSourceTechnologies();
		Collection<Object[]> ratioOfOpenSourceToolsVersusClosedSourceTools = this.repository.ratioOfOpenSourceToolsVersusClosedSourceTools();
		Double totalTools = this.repository.totalTools();
		Double totalTechnologies = this.repository.totalTechnologies();
		Double totalApplications = this.repository.totalApplications();
		Collection<Object[]> ratioOfInvestmentRoundsGroupedByKind = this.repository.ratioOfInvestmentRoundsGroupedByKind();
		Collection<Object[]> ratioOfApplicationsGroupedByStatus = this.repository.ratioOfApplicationsGroupedByStatus();
		//		Collection<Object[]> accepted = this.repository.Accepted(moment);
		//		Collection<Object[]> pending = this.repository.Pending(moment);
		//		Collection<Object[]> rejected = this.repository.Rejected(moment);

		Dashboard result = new Dashboard();
		result.setTotalNumberOftTechnologiesGroupedByActivitySector(totalNumberOftTechnologiesGroupedByActivitySector);
		result.setTotalNumberOfToolsGroupedByActivitySector(totalNumberOfToolsGroupedByActivitySector);
		result.setRatioOfOpenSourceToolsVersusClosedSourceTools(ratioOfOpenSourceToolsVersusClosedSourceTools);
		result.setRatioOfOpenSourceTechnologies(ratioOfOpenSourceTechnologies);
		result.setTotalTools(totalTools);
		result.setTotalTechnologies(totalTechnologies);
		result.setTotalApplications(totalApplications);
		result.setRatioOfInvestmentRoundsGroupedByKind(ratioOfInvestmentRoundsGroupedByKind);
		result.setRatioOfApplicationsGroupedByStatus(ratioOfApplicationsGroupedByStatus);

		Date ahora = new Date();
		List<Integer> accepted = new ArrayList<Integer>();
		List<Integer> rejected = new ArrayList<Integer>();
		List<Integer> pending = new ArrayList<Integer>();

		for (int i = 14; i >= 0; i--) {

			Calendar anterior = Calendar.getInstance();
			anterior.setTime(ahora);
			anterior.add(Calendar.DAY_OF_MONTH, -i);

			Integer appAcc = this.repository.getApplicationsByStatus("accepted", anterior.getTime());

			accepted.add(14 - i, appAcc);
			rejected.add(14 - i, this.repository.getApplicationsByStatus("rejected", anterior.getTime()));
			pending.add(14 - i, this.repository.getApplicationsByStatus("pending", anterior.getTime()));
		}
		result.setPending(pending);
		result.setAccepted(accepted);
		result.setRejected(rejected);

		return result;

	}
}
