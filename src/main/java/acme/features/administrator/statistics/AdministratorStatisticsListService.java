
package acme.features.administrator.statistics;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.statistics.Statistics;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorStatisticsListService implements AbstractListService<Administrator, Statistics> {

	@Autowired
	AdministratorStatisticsRepository repository;


	@Override
	public boolean authorise(final Request<Statistics> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Statistics> request, final Statistics entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "nombre", "valor");

	}

	@Override
	public Collection<Statistics> findMany(final Request<Statistics> request) {
		assert request != null;

		Collection<Statistics> result = this.createAll();
		;
		return result;

	}

	private Collection<Statistics> createAll() {

		Collection<Statistics> res = new HashSet<>();

		Statistics s = new Statistics();

		s.setNombre("Total number of Notice");
		s.setValor(this.repository.findNotice());

		res.add(s);

		Statistics cr = new Statistics();//***************
		cr.setNombre("Total number of technology records");
		cr.setValor(this.repository.findTechnologyRecord());

		res.add(cr);

		Statistics inve = new Statistics();
		inve.setNombre("Total number of tool records");
		inve.setValor(this.repository.findToolRecord());

		res.add(inve);

		List<Money> todosM = this.repository.maxInquirie();
		List<Double> todos = todosM.stream().map(x -> x.getAmount()).collect(Collectors.toList());
		List<Money> minM = this.repository.minInquirie();
		List<Double> min = minM.stream().map(x -> x.getAmount()).collect(Collectors.toList());
		todos.addAll(min);
		Integer c = todos.size();
		Double minV = todos.stream().sorted().findFirst().orElse(null);
		List<Double> maxV2 = todos.stream().sorted().collect(Collectors.toList());
		Double maxV = maxV2.get(maxV2.size() - 1);
		Double suma = todos.stream().mapToDouble(x -> x).sum();
		Double r = 0.;

		for (Double x : todos) {
			Double z = suma / x;
			Double v = (x - z) * (x - z);
			r = r + v;
		}
		Double t = r / (c - 1);

		t = Math.sqrt(t);
		Statistics maxO = new Statistics();
		maxO.setNombre("Maximum of Inquiries");
		maxO.setValor(maxV);

		res.add(maxO);

		Statistics minO = new Statistics();
		minO.setNombre("Minimum of Inquiries");
		minO.setValor(minV);

		res.add(minO);

		Double avgMaxActiveInquirie = this.repository.avgMaxActiveInquirie();
		Double avgMinActiveInquirie = this.repository.avgMinActiveInquirie();

		Double avgMaxActiveOverture = this.repository.avgMaxActiveOverture();
		Double avgMinActiveOverture = this.repository.avgMinActiveOverture();

		Double stDevMinActiveInquirie = this.repository.stDevMinActiveInquirie();
		Double stDevMaxActiveInquirie = this.repository.stDevMaxActiveInquirie();

		Double stDevMinActiveOverture = this.repository.stDevMinActiveOverture();
		Double stDevMaxActiveOverture = this.repository.stDevMaxActiveOverture();

		Statistics avgO = new Statistics();
		avgO.setNombre("Average of minMoney Inquirie");
		avgO.setValor(avgMinActiveInquirie);
		res.add(avgO);

		Statistics avg1 = new Statistics();
		avg1.setNombre("Average of maxMoney Inquirie");
		avg1.setValor(avgMaxActiveInquirie);
		res.add(avg1);

		Statistics avg2 = new Statistics();
		avg2.setNombre("Average of minMoney Overture");
		avg2.setValor(avgMinActiveOverture);
		res.add(avg2);

		Statistics avg3 = new Statistics();
		avg3.setNombre("Average of maxMoney Overture");
		avg3.setValor(avgMaxActiveOverture);
		res.add(avg3);

		Statistics sdO = new Statistics();
		sdO.setNombre("Standard derivation of minMoney Inquirie");
		sdO.setValor(stDevMinActiveInquirie);
		res.add(sdO);

		Statistics sd1 = new Statistics();
		sd1.setNombre("Standard derivation of maxMoney Inquirie");
		sd1.setValor(stDevMaxActiveInquirie);
		res.add(sd1);

		Statistics sd2 = new Statistics();
		sd2.setNombre("Standard derivation of minMoney Overture");
		sd2.setValor(stDevMinActiveOverture);
		res.add(sd2);

		Statistics sd3 = new Statistics();
		sd3.setNombre("Standard derivation of maxMoney Overture");
		sd3.setValor(stDevMaxActiveOverture);
		res.add(sd3);

		Statistics av1 = new Statistics();
		av1.setNombre("Average number of Investment rounds per Entrepreneur");//****************
		Double prueba = this.repository.averageNumberOfInvestmentRoundsPerEntrepreneur();
		av1.setValor(prueba);

		res.add(av1);

		Statistics av2 = new Statistics();
		av2.setNombre("Average number of Applications per Entrepreneur");//********************
		av2.setValor(this.repository.averageNumberOfApplicationsPerEntrepreneur());
		res.add(av2);

		Statistics av3 = new Statistics();
		av3.setNombre("Average number of Applications per Investor");
		av3.setValor(this.repository.averageNumberOfApplicationPerInvestor());

		res.add(av3);

		return res;

	}
}
