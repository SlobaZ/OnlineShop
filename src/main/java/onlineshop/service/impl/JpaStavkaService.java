package onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.model.Kupovina;
import onlineshop.model.Proizvod;
import onlineshop.model.Stavka;
import onlineshop.repository.ProizvodRepository;
import onlineshop.repository.StavkaRepository;
import onlineshop.service.StavkaService;

@Service
public class JpaStavkaService implements StavkaService {
	
	@Autowired
	private StavkaRepository stavkaRepository;
	
	@Autowired
	private ProizvodRepository proizvodRepository;

	@Override
	public Stavka getOne(Integer id) {
		return stavkaRepository.getOne(id);
	}

	@Override
	public List<Stavka> findAll() {
		return stavkaRepository.findAll();
	}

	@Override
	public Page<Stavka> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return stavkaRepository.findAll(pageable);
	}

	@Override
	public Stavka save(Stavka stavka) {
		return stavkaRepository.save(stavka);
	}

	@Override
	public Stavka delete(Integer id) {
		Stavka stavka = stavkaRepository.getOne(id);
		if(stavka != null) {
			stavkaRepository.delete(stavka);
		}
		return stavka;
	}


	@Override
	public List<Stavka> findByIdKupovine(Integer kupovinaId) {
		return stavkaRepository.findByIdKupovine(kupovinaId);
	}

	@Override
	public Stavka kupiStavku(Integer id, int kolicinaStavke) {
		Stavka stavka = stavkaRepository.getOne(id);
		Proizvod proizvod = stavka.getProizvod();
		Kupovina kupovina = stavka.getKupovina();
		if( proizvod.getKolicina()- kolicinaStavke >= 0   &&  proizvod.getKolicina() >= kolicinaStavke 
			&& kupovina.getKorisnik().getStanje() >= (stavka.getCenaStavke() + (proizvod.getCena()*kolicinaStavke))  ) {
			proizvod.setKolicina( proizvod.getKolicina() - kolicinaStavke ); 
			stavka.setKolicinaStavke(stavka.getKolicinaStavke() + kolicinaStavke);
			stavka.setCenaStavke(stavka.getCenaStavke() + (proizvod.getCena()*kolicinaStavke) );
			}
		else {
			return null;
		}
		proizvodRepository.save(proizvod);
		stavkaRepository.save(stavka);
		
		return stavka;
	}
	
	@Override
	public Stavka resetujStavku(Integer id) {
		Stavka stavka = stavkaRepository.getOne(id);
		Proizvod proizvod = stavka.getProizvod();
		proizvod.setKolicina( proizvod.getKolicina() + stavka.getKolicinaStavke() ); 
		stavka.setCenaStavke(0.0);
		stavka.setKolicinaStavke(0);
		proizvodRepository.save(proizvod);
		stavkaRepository.save(stavka);
		return stavka;
	}
	

}
