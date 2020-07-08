
package acme.features.anonymous.cordonbulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cordonbulletins.cordonBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCordonBulletinListService implements AbstractListService<Anonymous, cordonBulletin> {

	@Autowired
	AnonymousCordonBulletinRepository repository;


	@Override
	public boolean authorise(final Request<cordonBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<cordonBulletin> request, final cordonBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "author", "company", "description");

	}

	@Override
	public Collection<cordonBulletin> findMany(final Request<cordonBulletin> request) {
		assert request != null;
		Collection<cordonBulletin> result;
		result = this.repository.findMany();

		return result;
	}

}
