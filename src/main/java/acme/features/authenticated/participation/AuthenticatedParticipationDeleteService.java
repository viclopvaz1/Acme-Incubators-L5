
package acme.features.authenticated.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participations.Participation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipationDeleteService implements AbstractDeleteService<Authenticated, Participation> {

	@Autowired
	AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {

		assert request != null;
		boolean result = false;
		Principal principal = request.getPrincipal();
		if (this.repository.findCreatorUserByParticipationId(request.getModel().getInteger("id")).getId() == principal.getActiveRoleId()) {
			result = true;
		}

		return result;
	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "authenticated.id");//authenticated es una clase entera deberia traer solo el id
	}

	@Override
	public void bind(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "thread");

	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public Participation findOne(final Request<Participation> request) {
		Participation result;

		result = new Participation();
		result = this.repository.findParticipationById(request.getModel().getInteger("id"));
		return result;
	}

	@Override
	public void delete(final Request<Participation> request, final Participation entity) {

		assert request != null;
		assert entity != null;
		this.repository.delete(entity);

	}
}
