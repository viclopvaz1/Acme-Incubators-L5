
package acme.features.anonymous.lopezbulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lopezbulletins.lopezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousLopezBulletinListService implements AbstractListService<Anonymous, lopezBulletin> {

	@Autowired
	AnonymousLopezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<lopezBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<lopezBulletin> request, final lopezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "nombre", "dni", "descripcion");

	}

	@Override
	public Collection<lopezBulletin> findMany(final Request<lopezBulletin> request) {
		assert request != null;
		Collection<lopezBulletin> result;
		result = this.repository.findMany();

		return result;
	}

}
