package onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.model.Proizvod;


@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>{
	
	@Query("SELECT p FROM Proizvod p WHERE "
			+ "(:naziv IS NULL or p.naziv like :naziv ) AND "
			+ "(:kolicina IS NULL OR p.kolicina = :kolicina) AND "
			+ "(:cena IS NULL OR p.cena = :cena) "
			)
	Page<Proizvod> search(
			@Param("naziv") String naziv, 
			@Param("kolicina") Integer kolicina,
			@Param("cena") Double cena,
			Pageable pageRequest);
	
	


}
