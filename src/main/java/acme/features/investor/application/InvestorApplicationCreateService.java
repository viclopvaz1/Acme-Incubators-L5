
package acme.features.investor.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.configurations.Configuration;
import acme.entities.investmentrounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
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

		model.setAttribute("investmentRoundid", entity.getInvestmentRound().getId());
		request.unbind(entity, model, "ticker", "creationMoment", "statement", "moneyOffer", "investmentRound.ticker", "investor.identity.fullName");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();
		Date moment;
		Principal principal;
		Investor investor;
		int userAccountId;
		int investmentRoundId;
		InvestmentRound investmentRound;

		principal = request.getPrincipal();
		userAccountId = principal.getActiveRoleId();
		investor = this.repository.findInvestorById(userAccountId);
		result.setInvestor(investor);

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		result.setStatus("pending");

		investmentRoundId = request.getModel().getInteger("investmentRoundid");
		investmentRound = this.repository.findInvestmentRoundById(investmentRoundId);
		result.setInvestmentRound(investmentRound);

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

		Collection<String> references = this.repository.allReferences();

		if (!errors.hasErrors("ticker")) {
			errors.state(request, !references.contains(entity.getTicker()), "ticker", "investor.application.form.error.tickerRepeat");
		}

		if (!errors.hasErrors("statement")) {

			Double limitePalabrasSpam = Double.valueOf(entity.getStatement().split(" ").length) * configuration.getSpamThreshold() / 100.0;

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getStatement().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "statement", "investor.application.form.error.spamWordsStatement");

				if (n > limitePalabrasSpam) {
					break;
				}
			}

		}

		if (!errors.hasErrors("ticker")) {
			List<String> res = new ArrayList<>();
			Date moment = new Date(System.currentTimeMillis() - 1);
			Integer year = moment.getYear() + 1900;
			res.add(entity.getTicker().substring(0, entity.getTicker().indexOf("-")));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 1, entity.getTicker().indexOf("-") + 3));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 4, entity.getTicker().length()));
			boolean result = res.get(0).matches("[A-Z ]+") && (res.get(0).equals(entity.getInvestor().getSector().substring(0, 1).toUpperCase()) || res.get(0).equals(entity.getInvestor().getSector().substring(0, 2).toUpperCase())
				|| res.get(0).equals(entity.getInvestor().getSector().substring(0, 3).toUpperCase())) && res.get(1).equals(year.toString().substring(2)) && res.get(2).matches("^[0-9]{6}$");
			errors.state(request, result, "ticker", "investor.application.form.error.ticker");
		}

		if (!errors.hasErrors("moneyOffer")) {
			errors.state(request, entity.getMoneyOffer().getCurrency().equals("EUR") || entity.getMoneyOffer().getCurrency().equals("€"), "moneyOffer", "investor.application.form.error.zoneEur");
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);

	}

}
