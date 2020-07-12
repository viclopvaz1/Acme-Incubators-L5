
package acme.features.patron.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/patron/banner/")
public class PatronBannerController extends AbstractController<Patron, Banner> {

	@Autowired
	private PatronBannerListMineService	listService;

	@Autowired
	private PatronBannerShowService		showService;

	@Autowired
	private PatronBannerCreateService	createService;

	@Autowired
	private PatronBannerDeleteService	deleteService;

	@Autowired
	private PatronBannerUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
