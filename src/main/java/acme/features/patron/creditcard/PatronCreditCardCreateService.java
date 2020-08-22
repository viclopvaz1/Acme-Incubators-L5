
package acme.features.patron.creditcard;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.configurations.Configuration;
import acme.entities.creditcards.CreditCard;
import acme.entities.roles.Patron;
import acme.features.patron.banner.PatronBannerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronCreditCardCreateService implements AbstractCreateService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository	repository;

	@Autowired
	PatronBannerRepository		bannerRepository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		boolean result;
		int bannerId;
		Banner banner;
		Patron patron;
		Principal principal;

		bannerId = request.getModel().getInteger("bannerid");
		banner = this.bannerRepository.findOneById(bannerId);
		patron = banner.getPatron();
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId() && this.repository.findCreditCardByBannerId(bannerId) == 0;
		return result;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holderName", "number", "brand", "monthExp", "yearExp", "cvv");
		model.setAttribute("bannerid", entity.getBanner().getId());
		model.setAttribute("finalmode", entity.getBanner().isFinalMode());
	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		assert request != null;
		CreditCard result = new CreditCard();
		Banner banner;
		int bannerid;

		bannerid = request.getModel().getInteger("bannerid");
		banner = this.bannerRepository.findOneById(bannerid);
		result.setBanner(banner);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar = Calendar.getInstance();
		Integer month = calendar.get(Calendar.MONTH) + 1;
		Integer year = calendar.get(Calendar.YEAR);

		Configuration configuration = this.bannerRepository.findConfiguration();
		String[] CustomisationParameter;
		Integer n = 0;

		if (!errors.hasErrors("brand")) {
			Double limitePalabrasSpam = Double.valueOf(entity.getBrand().split(" ").length) * configuration.getSpamThreshold() / 100.0;

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getBrand().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "brand", "patron.credit-card.form.error.spamWordsPatron");

				if (n > limitePalabrasSpam) {
					n = 0;
					break;
				}
			}

		}

		if (!errors.hasErrors("holderName")) {
			Double limitePalabrasSpam = Double.valueOf(entity.getHolderName().split(" ").length) * configuration.getSpamThreshold() / 100.0;

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getHolderName().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "holderName", "patron.credit-card.form.error.spamWordsPatron");

				if (n > limitePalabrasSpam) {
					break;
				}
			}

		}

		if (!errors.hasErrors("monthExp")) {
			errors.state(request, entity.getYearExp() > year || entity.getMonthExp() >= month && entity.getYearExp().equals(year), "monthExp", "patron.credit-card.form.error.monthExp");
		}

		if (!errors.hasErrors("yearExp")) {
			errors.state(request, entity.getYearExp() >= year, "yearExp", "patron.credit-card.form.error.yearExp");
		}
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
