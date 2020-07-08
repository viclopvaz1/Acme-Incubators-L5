
package acme.features.administrator.dashboard;

import java.util.Collection;

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
			"ratioOfInvestmentRoundsGroupedByKind", "ratioOfApplicationsGroupedByStatus", "totalApplications");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Collection<Object[]> totalNumberOftTechnologiesGroupedByActivitySector = this.repository.totalNumberOftTechnologiesGroupedByActivitySector();
		Collection<Object[]> totalNumberOfToolsGroupedByActivitySector = this.repository.totalNumberOfToolsGroupedByActivitySector();
		Collection<Object[]> ratioOfOpenSourceTechnologies = this.repository.ratioOfOpenSourceTechnologies();
		Collection<Object[]> ratioOfOpenSourceToolsVersusClosedSourceTools = this.repository.ratioOfOpenSourceToolsVersusClosedSourceTools();
		Double totalTools = this.repository.totalTools();
		Double totalTechnologies = this.repository.totalTechnologies();
		Double totalApplications = this.repository.totalApplications();
		Collection<Object[]> ratioOfInvestmentRoundsGroupedByKind = this.repository.ratioOfInvestmentRoundsGroupedByKind();
		Collection<Object[]> ratioOfApplicationsGroupedByStatus = this.repository.ratioOfApplicationsGroupedByStatus();

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

		return result;

	}
}
