package be.vdab.groenetenen.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.services.FiliaalService;

@Controller
@RequestMapping(path = "filialen", produces = MediaType.TEXT_HTML_VALUE)
class FiliaalController {

	private final FiliaalService filiaalService;

	FiliaalController(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}

	private static final String VAN_TOT_PRIJS_VIEW = "filialen/vantotpostcode";

	@GetMapping("vantotpostcode")
	ModelAndView vanTotPostcode() {
		return new ModelAndView(VAN_TOT_PRIJS_VIEW).addObject(new VanTotPostcodeForm());
	}

	@GetMapping(params = { "van", "tot" })
	ModelAndView vanTotPostcode(@Valid VanTotPostcodeForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView(VAN_TOT_PRIJS_VIEW).addObject(new VanTotPostcodeForm());
		}
		int aantalFilialen = filiaalService.findByPostcodeBetween(form.getVan(), form.getTot()).size();
		return new ModelAndView(VAN_TOT_PRIJS_VIEW)
				.addObject("filialen", filiaalService.findByPostcodeBetween(form.getVan(), form.getTot()))
				.addObject("aantalFilialen", aantalFilialen);
	}

	private static final String FILIAAL_VIEW = "filialen/filiaal";
	private static final String REDIRECT_FILIAAL_NIET_GEVONDEN = "redirect:/";

	@GetMapping("{filiaal}")
	ModelAndView read(@PathVariable Optional<Filiaal> filiaal, RedirectAttributes redirectAttributes) {
		if (filiaal.isPresent()) {
			return new ModelAndView(FILIAAL_VIEW, "filiaal", filiaal.get());
		}
		redirectAttributes.addAttribute("fout", "filiaal niet gevonden");
		return new ModelAndView(REDIRECT_FILIAAL_NIET_GEVONDEN);
	}

	private static final String PER_ID_VIEW = "filialen/perid";

	@GetMapping("perid")
	String findById() {
		return PER_ID_VIEW;
	}

	private static final String REDIRECT_NA_AFSCHRIJVEN = "redirect:/filialen/{id}";

	@PostMapping("{id}/afschrijven")
	String afschrijven(@PathVariable long id, RedirectAttributes redirectAttributes) {
		filiaalService.afschrijven(id);
//		redirectAttributes.addAttribute("id", id);
		return REDIRECT_NA_AFSCHRIJVEN;
	}
}
