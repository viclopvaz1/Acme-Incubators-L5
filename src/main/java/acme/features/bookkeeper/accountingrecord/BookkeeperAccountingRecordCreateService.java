
package acme.features.bookkeeper.accountingrecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingrecords.AccountingRecord;
import acme.entities.investmentrounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.features.authenticated.investmentround.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository	repository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "investmentRound");

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moneyOffer", "status", "creationMoment", "bookkeeper.identity.fullName");
		model.setAttribute("investmentRoundId", entity.getInvestmentRound().getId());

	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		InvestmentRound investmentRound;
		int investmentRoundId;

		result = new AccountingRecord();
		investmentRoundId = request.getModel().getInteger("investmentRoundId");
		investmentRound = this.investmentRoundRepository.findOneById(investmentRoundId);
		result.setInvestmentRound(investmentRound);

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		Principal principal;
		Bookkeeper bookkeeper;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getActiveRoleId();
		bookkeeper = this.repository.findBookkeeperById(userAccountId);
		result.setBookkeeper(bookkeeper);

		return result;
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
