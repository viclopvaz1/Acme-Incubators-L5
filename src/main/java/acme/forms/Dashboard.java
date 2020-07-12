
package acme.forms;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Collection<Object[]>		totalNumberOftTechnologiesGroupedByActivitySector;

	Collection<Object[]>		ratioOfOpenSourceTechnologies;

	Collection<Object[]>		totalNumberOfToolsGroupedByActivitySector;

	Collection<Object[]>		ratioOfOpenSourceToolsVersusClosedSourceTools;

	Double						totalTools;

	Double						totalTechnologies;

	Double						totalApplications;

	Collection<Object[]>		ratioOfInvestmentRoundsGroupedByKind;

	Collection<Object[]>		ratioOfApplicationsGroupedByStatus;

	List<Integer>				Accepted;

	List<Integer>				Pending;

	List<Integer>				Rejected;

	String[]					Dias;

}
