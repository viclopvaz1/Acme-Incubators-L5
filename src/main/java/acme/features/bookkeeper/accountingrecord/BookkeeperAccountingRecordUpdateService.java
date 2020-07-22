
package acme.features.bookkeeper.accountingrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingrecords.AccountingRecord;
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
		request.bind(entity, errors, "bookkeeper.identity.fullName", "investmentRound.title", "moneyOffer");
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

	}

	@Override
	public void update(final Request<AccountingRecord> request, final AccountingRecord entity) {
		// TODO Auto-generated method stub

		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
