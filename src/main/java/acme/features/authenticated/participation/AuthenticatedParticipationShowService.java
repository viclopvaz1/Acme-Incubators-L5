
package acme.features.authenticated.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participations.Participation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipationShowService implements AbstractShowService<Authenticated, Participation> {

	@Autowired
	AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {

		assert request != null;

		boolean result = false;
		Principal principal = request.getPrincipal();
		int participationId = request.getModel().getInteger("id");
		if (principal.getActiveRoleId() == this.repository.findCreatorUserByParticipationId(participationId).getId()) {
			result = true;
		}
		return result;

	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model);

		model.setAttribute("authenticated", entity.getAuthenticated().getUserAccount().getUsername());

	}

	@Override
	public Participation findOne(final Request<Participation> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Participation result = new Participation();
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findParticipationById(id);

		return result;
	}
}
