package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.model.Korisnik;

public interface KorisnikService {
	
	Korisnik getOne(Integer id);
	List<Korisnik> findAll();
	Page<Korisnik> findAll(int pageNum);
	Korisnik save(Korisnik Korisnik);
	Korisnik delete(Integer id);
	
	Page<Korisnik> search(
			@Param("naziv") String naziv, 
			@Param("mesto") String mesto, 
			 int pageNum);
	


}
