
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "deadline", "description", "title");

	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimunDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, +1);
			minimunDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimunDeadline), "deadline", "administrator.challenge.form.error.tooClose");
		}

		if (!errors.hasErrors("expertReward")) {
			errors.state(request, entity.getExpertReward().getCurrency().equals("EUR") || entity.getExpertReward().getCurrency().equals("€"), "expertReward", "administrator.challenge.form.error.zoneEurE");
		}

		if (!errors.hasErrors("averageReward")) {
			errors.state(request, entity.getAverageReward().getCurrency().equals("EUR") || entity.getAverageReward().getCurrency().equals("€"), "averageReward", "administrator.challenge.form.error.zoneEurA");
			errors.state(request, entity.getExpertReward().getAmount() > entity.getAverageReward().getAmount(), "averageReward", "administrator.challenge.form.error.expertBaverage");
		}

		if (!errors.hasErrors("rookieReward")) {
			errors.state(request, entity.getRookieReward().getCurrency().equals("EUR") || entity.getRookieReward().getCurrency().equals("€"), "rookieReward", "administrator.challenge.form.error.zoneEurR");
			errors.state(request, entity.getRookieReward().getAmount() < entity.getAverageReward().getAmount(), "rookieReward", "administrator.challenge.form.error.averageBrookie");
		}

	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
