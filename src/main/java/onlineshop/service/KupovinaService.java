package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.model.Kupovina;

public interface KupovinaService {
	
	Kupovina getOne(Integer id);
	List<Kupovina> findAll();
	Page<Kupovina> findAll(int pageNum);
	Kupovina save(Kupovina kupovina);
	Kupovina delete(Integer id);
		
	Page<Kupovina> search(
			@Param("korisnikId") Integer korisnikId, 
			@Param("sifra") String sifra, 
			@Param("ukupnaCena") Double ukupnaCena,
			@Param("datumvremePocetak") String datumvremePocetak,
			@Param("datumvremeKraj") String datumvremeKraj,
			 int pageNum);

	
	Kupovina kupi(Integer id);


}
