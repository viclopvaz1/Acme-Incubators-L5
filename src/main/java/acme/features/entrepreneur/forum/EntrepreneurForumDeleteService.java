
package acme.features.entrepreneur.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.entities.participations.Participation;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.features.authenticated.participation.AuthenticatedParticipationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurForumDeleteService implements AbstractDeleteService<Entrepreneur, Forum> {

	@Autowired
	EntrepreneurForumRepository				repository;

	@Autowired
	AuthenticatedParticipationRepository	participationRepository;

	@Autowired
	AuthenticatedMessageRepository			messageRepository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "investmentRound");
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;
		Forum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		int id;

		id = request.getModel().getInteger("id");

		Collection<Message> messages = this.repository.findMessagesByForumId(id);
		for (Message m : messages) {
			this.messageRepository.delete(m);

		}
		Collection<Participation> participations = this.repository.findParticipationsByForumId(id);
		for (Participation p : participations) {
			this.participationRepository.delete(p);

		}
		this.repository.delete(entity);

	}

}
