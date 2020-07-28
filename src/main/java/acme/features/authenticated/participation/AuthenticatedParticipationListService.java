
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.participations.Participation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipationListService implements AbstractListService<Authenticated, Participation> {

	@Autowired
	AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {

		assert request != null;
		boolean result = false;
		Principal principal = request.getPrincipal();
		int forumId = request.getModel().getInteger("forumId");
		if (principal.getActiveRoleId() == this.repository.findCreatorUserByForumId(forumId)) {
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

		model.setAttribute("authenticated.name", entity.getAuthenticated().getUserAccount().getUsername());

	}

	@Override
	public Collection<Participation> findMany(final Request<Participation> request) {

		assert request != null;

		Collection<Participation> result;

		Forum forum;
		forum = this.repository.findOneForumById(request.getModel().getInteger("forumId"));
		Authenticated au = forum.getAuthenticated();

		Collection<Participation> participation = this.repository.findParticipationByAuthId(au.getId());

		result = this.repository.findAllParticipationByForumId(request.getModel().getInteger("forumId"));

		result.removeAll(participation);

		return result;
	}
}
