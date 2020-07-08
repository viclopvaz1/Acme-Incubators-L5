
package acme.features.anonymous.ruizbulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ruizbulletins.ruizBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousRuizBulletinCreateService implements AbstractCreateService<Anonymous, ruizBulletin> {

	@Autowired
	AnonymousRuizBulletinRepository repository;


	@Override
	public boolean authorise(final Request<ruizBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<ruizBulletin> request, final ruizBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ruizBulletin> request, final ruizBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "job", "company", "moment");

	}

	@Override
	public ruizBulletin instantiate(final Request<ruizBulletin> request) {
		assert request != null;

		ruizBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new ruizBulletin();
		result.setJob("Youtuber");
		result.setCompany("Youtube");
		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<ruizBulletin> request, final ruizBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<ruizBulletin> request, final ruizBulletin entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
