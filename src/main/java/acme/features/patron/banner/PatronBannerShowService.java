
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.features.patron.creditcard.PatronCreditCardRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class PatronBannerShowService implements AbstractShowService<Patron, Banner> {

	@Autowired
	PatronBannerRepository		repository;

	@Autowired
	PatronCreditCardRepository	creditCardRepository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean result;
		int bannerId;
		Banner banner;
		Patron patron;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneById(bannerId);
		patron = banner.getPatron();
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int id = request.getModel().getInteger("id");
		int numCreditCard = this.creditCardRepository.findCreditCardByBannerId(id);
		model.setAttribute("numCreditCard", numCreditCard);
		request.unbind(entity, model, "picture", "slogan", "targetUrl", "finalMode", "patron.identity.fullName");

	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		//		int num;
		//		num = this.creditCardRepository.findCreditCardByBannerId(id);
		//		result.setNumCreditCard(num);

		return result;
	}

}
