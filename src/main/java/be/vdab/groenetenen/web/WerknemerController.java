package be.vdab.groenetenen.web;

import org.springframework.data.domain.Pageable;
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
	ModelAndView werknemerLijst(Pageable pageable) {
		return new ModelAndView(WERKNEMER_VIEW, "page", werknemerService.findAll(pageable));
	}

}
