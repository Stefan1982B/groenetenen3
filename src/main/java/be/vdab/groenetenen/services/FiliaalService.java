package be.vdab.groenetenen.services;

import java.util.List;

import be.vdab.groenetenen.entities.Filiaal;

public interface FiliaalService {
	List<Filiaal>findByPostcodeBetween(int van, int tot);

}
