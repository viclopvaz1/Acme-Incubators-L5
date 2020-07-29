
package acme.features.administrator.bookkeeper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@RequestMapping("/administrator/bookkeeper/")
@Controller
public class AdministratorBookkeeperController extends AbstractController<Administrator, Bookkeeper> {

	@Autowired
	AdministratorBookkeeperUpdateService	updateService;

	@Autowired
	AdministratorBookkeeperCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
