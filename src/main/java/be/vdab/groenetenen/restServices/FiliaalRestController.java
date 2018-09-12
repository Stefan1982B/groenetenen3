package be.vdab.groenetenen.restServices;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.exceptions.FiliaalNietGevondenException;
import be.vdab.groenetenen.services.FiliaalService;

@RestController
@RequestMapping("filialen")
@ExposesResourceFor(Filiaal.class)
class FiliaalRestController {
	private final FiliaalService filiaalService;
	private final EntityLinks entityLinks;

	FiliaalRestController(FiliaalService filiaalSerivce, EntityLinks entityLinks) {
		this.filiaalService = filiaalSerivce;
		this.entityLinks = entityLinks;
	}

	@GetMapping("{filiaal}")
	FiliaalResource read(@PathVariable Optional<Filiaal> filiaal) {
		if (filiaal.isPresent()) {
			return new FiliaalResource(filiaal.get(), entityLinks);
		}
		throw new FiliaalNietGevondenException();
	}

	@ExceptionHandler(FiliaalNietGevondenException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void filiaalNietGevonden() {

	}

	@DeleteMapping("{filiaal}")
	void delete(@PathVariable Optional<Filiaal> filiaal) {
		if (!filiaal.isPresent()) {
			throw new FiliaalNietGevondenException();
		}
		filiaalService.delete(filiaal.get().getId());
	}

	@ExceptionHandler(FiliaalHeeftNogWerknemersException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String FiliaalHeeftNogWerknemers() {
		return "Filiaal heeft nog werknemers";
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	HttpHeaders create(@RequestBody @Valid Filiaal filiaal) {
		filiaalService.create(filiaal);
		HttpHeaders headers = new HttpHeaders();
		Link link = entityLinks.linkToSingleResource(Filiaal.class, filiaal.getId());
		headers.setLocation(URI.create(link.getHref()));
		return headers;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String filiaalMetVerkeerdeProperties(MethodArgumentNotValidException ex) {
		StringBuilder fouten = new StringBuilder();
		ex.getBindingResult().getFieldErrors().forEach(
				error -> fouten.append(error.getField()).append(':').append(error.getDefaultMessage()).append('\n'));
		fouten.deleteCharAt(fouten.length() - 1);
		return fouten.toString();
	}

	@PutMapping("{id}")
	void update(@RequestBody @Valid Filiaal filiaal) {
		filiaalService.create(filiaal);
	}
	
	@GetMapping
	FilialenResource findAll() {
		return new FilialenResource(filiaalService.findAll(), entityLinks);
	}
}
