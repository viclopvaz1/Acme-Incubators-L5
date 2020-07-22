
package acme.features.administrator.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperUpdateService implements AbstractUpdateService<Administrator, Bookkeeper> {

	@Autowired
	AdministratorBookkeeperRepository repository;


	@Override
	public boolean authorise(final Request<Bookkeeper> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Bookkeeper> request, final Bookkeeper entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "text", "moreInfo");

	}

	@Override
	public Bookkeeper findOne(final Request<Bookkeeper> request) {
		// TODO Auto-generated method stub
		assert request != null;
		Bookkeeper result;
		Principal principal;
		int id;

		principal = request.getPrincipal();
		id = principal.getAccountId();
		result = this.repository.findBookkeeperById(id);

		return result;

	}

	@Override
	public void validate(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Bookkeeper> request, final Bookkeeper entity) {
		// TODO Auto-generated method stub

		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	@Override
	public void onSuccess(final Request<Bookkeeper> request, final Response<Bookkeeper> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
