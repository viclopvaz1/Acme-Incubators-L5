
package acme.features.patron.creditcard;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditcards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class PatronCreditCardUpdateService implements AbstractUpdateService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
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
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

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

		if (!errors.hasErrors("monthExp")) {
			errors.state(request, entity.getYearExp() > year || entity.getMonthExp() >= month && entity.getYearExp().equals(year), "monthExp", "patron.credit-card.form.error.monthExp");
		}

		if (!errors.hasErrors("yearExp")) {
			errors.state(request, entity.getYearExp() >= year, "yearExp", "patron.credit-card.form.error.yearExp");
		}

	}

	@Override
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
