package be.vdab.groenetenen.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
	private final static String VIEW = "index";

	private String begroeting() {
		int uur = LocalDateTime.now().getHour();
		if (uur < 12) {
			return "goedeMorgen";
		} else if (uur >= 12 && uur < 18) {
			return "goedeMiddag";
		} else {
			return "goedeAvond";
		}
	}

	@GetMapping
	ModelAndView index() {
		return new ModelAndView(VIEW, "begroeting", begroeting());
	}

}
