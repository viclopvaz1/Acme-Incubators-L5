
package acme.features.administrator.bookkeeperrequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bookkeeperrequests.BookkeeperRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@RequestMapping("/administrator/bookkeeper-request/")
@Controller
public class AdministratorBookkeeperRequestController extends AbstractController<Administrator, BookkeeperRequest> {

	@Autowired
	AdministratorBookkeeperRequestListService	listService;

	@Autowired
	AdministratorBookkeeperRequestShowService	showService;

	@Autowired
	AdministratorBookkeeperRequestDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
