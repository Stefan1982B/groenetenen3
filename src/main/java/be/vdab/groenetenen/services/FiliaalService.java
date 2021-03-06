package be.vdab.groenetenen.services;

import java.util.List;

import be.vdab.groenetenen.entities.Filiaal;

public interface FiliaalService {
	List<Filiaal>findByPostcodeBetween(int van, int tot);
	void delete(long id);
	void create(Filiaal filiaal);
	void update(Filiaal filiaal);
	List<Filiaal> findAll();
	void afschrijven(long id); 

}
