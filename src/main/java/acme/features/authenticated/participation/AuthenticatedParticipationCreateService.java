
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participations.Participation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipationCreateService implements AbstractCreateService<Authenticated, Participation> {

	@Autowired
	AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {

		assert request != null;
		boolean result = false;
		Principal principal = request.getPrincipal();

		result = this.repository.findCreatorUserByForumId(request.getModel().getInteger("forumId")) == principal.getActiveRoleId();

		return result;
	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Integer forumId = request.getModel().getInteger("forumId");
		model.setAttribute("forumId", forumId);
		Collection<String> allUserNames = this.repository.findAllUserNames();
		allUserNames.remove("anonymous");
		Collection<String> allUserNamesInParticipation = this.repository.findAllUserInParticipation(request.getModel().getInteger("forumId"));
		allUserNames.removeAll(allUserNamesInParticipation);
		model.setAttribute("allAuthenticated", allUserNames);
		model.setAttribute("nUsers", allUserNames.size());
		request.unbind(entity, model);
	}

	@Override
	public void bind(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "forum", "authenticated");
	}

	@Override
	public Participation instantiate(final Request<Participation> request) {
		assert request != null;

		Participation result;

		result = new Participation();

		Integer forumId = request.getModel().getInteger("forumId");
		result.setForum(this.repository.findOneForumById(forumId));
		result.setAuthenticated(this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId()));

		return result;
	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		String userName = request.getModel().getString("authenticated");

		boolean check = false;

		if (this.repository.findAllUserInParticipation(request.getModel().getInteger("forumId")).contains(userName)) {
			check = true;
		}
		errors.state(request, !check, "*", "authenticated.participant.error.name");

	}

	@Override
	public void create(final Request<Participation> request, final Participation entity) {

		String userName = request.getModel().getString("authenticated");
		UserAccount aux = this.repository.findUserByName(userName);
		Authenticated auth = this.repository.findAuthenticatedByAccountId(aux.getId());
		entity.setAuthenticated(auth);
		this.repository.save(entity);

	}
}
