
package acme.features.entrepreneur.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.forums.Forum;
import acme.entities.investmentrounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.entrepreneur.investmentround.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurForumCreateService implements AbstractCreateService<Entrepreneur, Forum> {

	@Autowired
	EntrepreneurForumRepository				repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


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

		model.setAttribute("investmentRoundid", entity.getInvestmentRound().getId());
		request.unbind(entity, model, "title", "investmentRound");

	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		assert request != null;
		Forum result = new Forum();
		Principal principal = request.getPrincipal();

		InvestmentRound investmentRound;
		int investmentRoundid = request.getModel().getInteger("investmentRoundid");

		investmentRound = this.investmentRoundRepository.findOneById(investmentRoundid);
		result.setInvestmentRound(investmentRound);

		result.setAuthenticated(this.repository.findAuthenticatedById(principal.getActiveRoleId()));

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration = this.repository.findConfiguration();
		String[] CustomisationParameter;
		Integer n = 0;
		Double limitePalabrasSpam = Double.valueOf(entity.getTitle().split(" ").length) * configuration.getSpamThreshold() / 100.0;

		if (!errors.hasErrors("title")) {

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getTitle().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "title", "entrepreneur.forum.form.error.spamWordsEntrepreneur");

				if (n > limitePalabrasSpam) {
					break;
				}
			}

		}
	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
