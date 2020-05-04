package onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.model.Korisnik;
import onlineshop.repository.KorisnikRepository;
import onlineshop.service.KorisnikService;


@Service
public class JpaKorisnikService implements KorisnikService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public Korisnik getOne(Integer id) {
		return korisnikRepository.getOne(id);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public Page<Korisnik> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return korisnikRepository.findAll(pageable);
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	@Override
	public Korisnik delete(Integer id) {
		Korisnik korisnik = korisnikRepository.getOne(id);
		if(korisnik != null) {
			korisnikRepository.delete(korisnik);
		}
		return korisnik;
	}

	@Override
	public Page<Korisnik> search(String naziv, String mesto, int pageNum) {
		if( naziv != null) {
			naziv = '%' + naziv + '%';
		}
		if(mesto != null) {
			mesto = '%' + mesto + '%';
		}
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return korisnikRepository.search(naziv, mesto, pageable);
	}



}
