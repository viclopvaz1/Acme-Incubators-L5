
package acme.features.anonymous.cordonbulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cordonbulletins.cordonBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCordonBulletinCreateService implements AbstractCreateService<Anonymous, cordonBulletin> {

	@Autowired
	AnonymousCordonBulletinRepository repository;


	@Override
	public boolean authorise(final Request<cordonBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<cordonBulletin> request, final cordonBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<cordonBulletin> request, final cordonBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "company", "description");

	}

	@Override
	public cordonBulletin instantiate(final Request<cordonBulletin> request) {
		assert request != null;

		cordonBulletin result;

		result = new cordonBulletin();
		result.setAuthor("Alberto Cordon");
		result.setCompany("Youtube");
		result.setDescription("Me gusta la empresa.");
		return result;
	}

	@Override
	public void validate(final Request<cordonBulletin> request, final cordonBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<cordonBulletin> request, final cordonBulletin entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
