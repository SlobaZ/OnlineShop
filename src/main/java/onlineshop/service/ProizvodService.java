package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.model.Proizvod;


public interface ProizvodService {

	Proizvod getOne(Integer id);
	List<Proizvod> findAll();
	Page<Proizvod> findAll(int pageNum);
	Proizvod save(Proizvod Proizvod);
	List<Proizvod> saveAll(List<Proizvod> proizvodi);
	Proizvod delete(Integer id);
	
	Page<Proizvod> search(
			@Param("naziv") String naziv, 
			@Param("kolicina") Integer kolicina,
			@Param("cena") Double cena,
			 int pageNum);
	
	

}
