
package acme.features.entrepreneur.workprogramme;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.roles.Entrepreneur;
import acme.entities.workprogrammes.WorkProgramme;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurWorkProgrammeUpdateService implements AbstractUpdateService<Entrepreneur, WorkProgramme> {

	@Autowired
	EntrepreneurWorkProgrammeRepository repository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		boolean result;
		int workProgrammeId;
		WorkProgramme workProgramme;
		Entrepreneur entrepreneur;
		Principal principal;

		workProgrammeId = request.getModel().getInteger("id");
		workProgramme = this.repository.findOneById(workProgrammeId);
		entrepreneur = workProgramme.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "deadline", "budget", "investmentRound.ticker");

	}

	@Override
	public WorkProgramme findOne(final Request<WorkProgramme> request) {
		assert request != null;

		WorkProgramme result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimunDeadline;
		Configuration configuration = this.repository.findConfiguration();
		String[] CustomisationParameter;
		Integer n = 0;

		if (!errors.hasErrors("title")) {

			Double limitePalabrasSpam = Double.valueOf(entity.getTitle().split(" ").length) * configuration.getSpamThreshold() / 100.0;

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getTitle().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "title", "entrepreneur.work-programme.form.error.spamWordsTitle");

				if (n > limitePalabrasSpam) {
					break;
				}
			}

		}

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, +1);
			minimunDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimunDeadline), "deadline", "entrepreneur.work-programme.form.error.tooClose");
		}

		if (!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getCurrency().equals("EUR") || entity.getBudget().getCurrency().equals("€"), "budget", "entrepreneur.work-programme.form.error.zoneEur");
		}

	}

	@Override
	public void update(final Request<WorkProgramme> request, final WorkProgramme entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
