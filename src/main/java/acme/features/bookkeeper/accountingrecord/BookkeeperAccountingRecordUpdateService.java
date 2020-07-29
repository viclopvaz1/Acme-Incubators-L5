
package acme.features.bookkeeper.accountingrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingrecords.AccountingRecord;
import acme.entities.configurations.Configuration;
import acme.entities.investmentrounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class BookkeeperAccountingRecordUpdateService implements AbstractUpdateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		boolean result;
		int investmentRoundId;
		AccountingRecord accountingRecord;
		InvestmentRound investmentRound;

		investmentRoundId = request.getModel().getInteger("id");
		accountingRecord = this.repository.findOneById(investmentRoundId);
		investmentRound = accountingRecord.getInvestmentRound();
		result = accountingRecord.getInvestmentRound().getId() == investmentRound.getId();

		return result;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "status", "creationMoment");
		request.bind(entity, errors, "bookkeeper.identity.fullName", "investmentRound.title", "body");
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment");
		request.unbind(entity, model, "bookkeeper.identity.fullName", "investmentRound.title", "moneyOffer");

	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		AccountingRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		// TODO Auto-generated method stu
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration = this.repository.findConfiguration();
		String[] CustomisationParameter;
		Integer n = 0;
		Double limitePalabrasSpam = Double.valueOf(entity.getTitle().split(" ").length) * configuration.getSpamThreshold() / 100.0;

		if (!errors.hasErrors("title")) {

			CustomisationParameter = configuration.getSpamWords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getTitle().toLowerCase();
				int indice = l.indexOf(s);
				while (indice != -1) {
					n++;
					l = l.substring(indice + 1);
					indice = l.indexOf(s);
				}
				errors.state(request, n <= limitePalabrasSpam, "title", "bookkeeper.accountingRecord.form.error.spamWordsTitle");

				if (n > limitePalabrasSpam) {
					break;
				}
			}

			if (!errors.hasErrors("body")) {

				CustomisationParameter = configuration.getSpamWords().split(",");

				for (String s : CustomisationParameter) {
					String l = entity.getTitle().toLowerCase();
					int indice = l.indexOf(s);
					while (indice != -1) {
						n++;
						l = l.substring(indice + 1);
						indice = l.indexOf(s);
					}
					errors.state(request, n <= limitePalabrasSpam, "body", "bookkeeper.accountingRecord.form.error.spamWordsBody");

					if (n > limitePalabrasSpam) {
						break;
					}
				}
			}
		}
	}

	@Override
	public void update(final Request<AccountingRecord> request, final AccountingRecord entity) {
		// TODO Auto-generated method stub

		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
