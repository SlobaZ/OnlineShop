package onlineshop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.model.Korisnik;
import onlineshop.model.Kupovina;
import onlineshop.model.Stavka;
import onlineshop.repository.KorisnikRepository;
import onlineshop.repository.KupovinaRepository;
import onlineshop.repository.StavkaRepository;
import onlineshop.service.KupovinaService;
import onlineshop.utils.PomocnaKlasa;

@Service
public class JpaKupovinaService implements KupovinaService{
	
	@Autowired
	private KupovinaRepository kupovinaRepository; 
	
	@Autowired
	private StavkaRepository stavkaRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public Kupovina getOne(Integer id) {
		return kupovinaRepository.getOne(id);
	}

	@Override
	public List<Kupovina> findAll() {
		return kupovinaRepository.findAll();
	}
	
	@Override
	public Page<Kupovina> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return kupovinaRepository.findAll(pageable);
	}

	@Override
	public Kupovina save(Kupovina kupovina) {
		return kupovinaRepository.save(kupovina);
	}

	@Override
	public Kupovina delete(Integer id) {
		Kupovina kupovina = kupovinaRepository.getOne(id);
		if(kupovina != null) {
			kupovinaRepository.delete(kupovina);
		}
		return kupovina;
	}

	
	@Override
	public Page<Kupovina> search(Integer korisnikId, String sifra, Double ukupnaCena, 
			String datumvremePocetak, String datumvremeKraj, int pageNum) {
		
		Timestamp datumVremePocetak = null;
		Timestamp datumVremeKraj = null;
		if( sifra != null) {
			sifra = '%' + sifra + '%';
		}
		if(datumvremePocetak != null) { 
		datumVremePocetak = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremePocetak);
		}
		if(datumvremeKraj !=null) {
			 datumVremeKraj = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremeKraj);
		}
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return kupovinaRepository.search(korisnikId, sifra, ukupnaCena, datumVremePocetak, datumVremeKraj,  pageable);
	}

	
	@Override
	public Kupovina kupi(Integer id) {
		Kupovina kupovina = kupovinaRepository.getOne(id);
		List<Stavka> stavke = stavkaRepository.findByIdKupovine(id);
		Double	x = 0.0 ;
		for (Stavka stavka: stavke) {
			x += stavka.getCenaStavke();
		  }
		if(kupovina.getKorisnik().getStanje()<x) {
			return null;
		}
		kupovina.setUkupnaCena(x);
		Korisnik korisnik = kupovina.getKorisnik();
		korisnik.setStanje(korisnik.getStanje()-x); 
		korisnikRepository.save(korisnik);
		kupovinaRepository.save(kupovina);
		return kupovina;
	}




}
