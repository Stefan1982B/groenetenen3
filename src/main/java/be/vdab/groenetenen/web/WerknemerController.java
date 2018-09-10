package be.vdab.groenetenen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.services.WerknemerService;

@Controller
@RequestMapping("werknemers")
class WerknemerController {
	private final WerknemerService werknemerService;

	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}
	
	private static final String WERKNEMER_VIEW = "werknemers/werknemers";
	
	@GetMapping
	ModelAndView werknemerLijst() {
		return new ModelAndView(WERKNEMER_VIEW, "werknemers", werknemerService.findAll());
	}

}
