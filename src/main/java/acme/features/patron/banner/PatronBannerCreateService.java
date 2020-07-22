
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.configurations.Configuration;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronBannerCreateService implements AbstractCreateService<Patron, Banner> {

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "finalMode", "patron");

	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetUrl");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		Banner result;
		Principal principal;
		Patron patron;
		int patronId;

		principal = request.getPrincipal();
		patronId = principal.getActiveRoleId();
		patron = this.repository.findPatronById(patronId);

		result = new Banner();
		result.setFinalMode(false);
		result.setPatron(patron);
		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration = this.repository.findConfiguration();
		String[] CustomisationParameter;
		Integer n = 0;
		Double limitePalabrasSpam = Double.valueOf(entity.getSlogan().split(" ").length) * configuration.getSpamThreshold() / 100.0;

		if (!errors.hasErrors("slogan")) {

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getSlogan().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "slogan", "patron.banner.form.error.spamWordsPatron");

				if (n > limitePalabrasSpam) {
					break;
				}
			}

		}

	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {

		this.repository.save(entity);

	}

}
