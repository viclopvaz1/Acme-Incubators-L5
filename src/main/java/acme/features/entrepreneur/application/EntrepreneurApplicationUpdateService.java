
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.configurations.Configuration;
import acme.entities.participations.Participation;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.participation.AuthenticatedParticipationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository		repository;

	@Autowired
	AuthenticatedParticipationRepository	participationRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Entrepreneur entrepreneur;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		entrepreneur = application.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "statement", "moneyOffer", "investmentRound.ticker", "investor.identity.fullName", "status", "reason");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration = this.repository.findConfiguration();
		String[] CustomisationParameter;
		Integer n = 0;

		if (!errors.hasErrors("reason")) {

			Double limitePalabrasSpam = Double.valueOf(entity.getReason().split(" ").length) * configuration.getSpamThreshold() / 100.0;

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getReason().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "reason", "entrepreneur.application.form.error.spamWordsReason");

				if (n > limitePalabrasSpam) {
					break;
				}
			}

		}

		if (!errors.hasErrors("reason")) {
			errors.state(request, entity.getReason() != null && entity.getReason() != "" || !entity.getStatus().equals("rejected"), "reason", "entrepreneur.application.error.reason");

		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Participation participation = new Participation();

		if (entity.getStatus().equals("accepted") && this.repository.findTotalForumByEntrepreneur(entity.getInvestmentRound().getId()) == 1) {
			Authenticated auth = this.participationRepository.findAuthenticatedByAccountId(entity.getInvestor().getUserAccount().getId());
			participation.setAuthenticated(auth);
			participation.setForum(this.repository.findForumByInvestmentRound(entity.getInvestmentRound().getId()));
			this.participationRepository.save(participation);
		}

		this.repository.save(entity);

	}

}
