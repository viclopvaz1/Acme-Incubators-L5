
package acme.features.anonymous.lopezbulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lopezbulletins.lopezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousLopezBulletinCreateService implements AbstractCreateService<Anonymous, lopezBulletin> {

	@Autowired
	AnonymousLopezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<lopezBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<lopezBulletin> request, final lopezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<lopezBulletin> request, final lopezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "nombre", "dni", "descripcion");

	}

	@Override
	public lopezBulletin instantiate(final Request<lopezBulletin> request) {
		assert request != null;

		lopezBulletin result;

		result = new lopezBulletin();
		result.setNombre("Vicente");
		result.setDni("30265027A");
		result.setDescripcion("Me gusta la empresa.");
		return result;
	}

	@Override
	public void validate(final Request<lopezBulletin> request, final lopezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<lopezBulletin> request, final lopezBulletin entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
