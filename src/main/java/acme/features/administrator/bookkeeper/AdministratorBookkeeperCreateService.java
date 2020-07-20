
package acme.features.administrator.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperrequests.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBookkeeperCreateService implements AbstractCreateService<Administrator, Bookkeeper> {

	@Autowired
	AdministratorBookkeeperRepository repository;


	@Override
	public boolean authorise(final Request<Bookkeeper> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Bookkeeper> request, final Bookkeeper entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "firm", "responsabilityStatement");
	}

	@Override
	public Bookkeeper instantiate(final Request<Bookkeeper> request) {
		Bookkeeper result;
		BookkeeperRequest bkRequest;

		int idRequest = request.getModel().getInteger("id");
		bkRequest = this.repository.findBookkeeperRequestById(idRequest);
		result = new Bookkeeper();
		result.setName(bkRequest.getFirm());
		result.setResponsibilityStatement(bkRequest.getResponsabilityStatement());
		result.setUserAccount(bkRequest.getAuthenticated().getUserAccount());

		return result;
	}

	@Override
	public void validate(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Bookkeeper> request, final Bookkeeper entity) {

		this.repository.save(entity);
		int idRequest = request.getModel().getInteger("id");
		BookkeeperRequest auditorRequest = this.repository.findBookkeeperRequestById(idRequest);
		this.repository.delete(auditorRequest);
	}

	@Override
	public void onSuccess(final Request<Bookkeeper> request, final Response<Bookkeeper> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.GET)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
