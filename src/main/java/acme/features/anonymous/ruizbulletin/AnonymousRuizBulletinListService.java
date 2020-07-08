
package acme.features.anonymous.ruizbulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ruizbulletins.ruizBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousRuizBulletinListService implements AbstractListService<Anonymous, ruizBulletin> {

	@Autowired
	AnonymousRuizBulletinRepository repository;


	@Override
	public boolean authorise(final Request<ruizBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<ruizBulletin> request, final ruizBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "job", "company", "moment");

	}

	@Override
	public Collection<ruizBulletin> findMany(final Request<ruizBulletin> request) {
		assert request != null;
		Collection<ruizBulletin> result;
		result = this.repository.findMany();

		return result;
	}

}
