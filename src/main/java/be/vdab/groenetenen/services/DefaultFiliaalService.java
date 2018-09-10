package be.vdab.groenetenen.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.repositories.FiliaalRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultFiliaalService implements FiliaalService {
	private final FiliaalRepository filiaalRepository;

	DefaultFiliaalService(FiliaalRepository filiaalRepository) {
		this.filiaalRepository = filiaalRepository;
	}

	@Override
	public List<Filiaal> findByPostcodeBetween(int van, int tot) {
		return filiaalRepository.findByAdresPostcodeBetweenOrderByAdresPostcode(van, tot);
	}

}
