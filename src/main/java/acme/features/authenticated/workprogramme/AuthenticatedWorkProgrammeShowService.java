
package acme.features.authenticated.workprogramme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workprogrammes.WorkProgramme;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedWorkProgrammeShowService implements AbstractShowService<Authenticated, WorkProgramme> {

	@Autowired
	AuthenticatedWorkProgrammeRepository repository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "deadline", "budget", "investmentRound.ticker");
	}

	@Override
	public WorkProgramme findOne(final Request<WorkProgramme> request) {
		assert request != null;

		WorkProgramme result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
