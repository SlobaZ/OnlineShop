package onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.model.Korisnik;
import onlineshop.model.Kupovina;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{
	
	@Query("SELECT k FROM Korisnik k WHERE "
			+ "(:naziv IS NULL or k.naziv like :naziv ) AND "
			+ "(:mesto IS NULL OR k.mesto like :mesto) "
			)
	Page<Korisnik> search(
			@Param("naziv") String naziv, 
			@Param("mesto") String mesto,
			Pageable pageRequest);
	
	
	
	@Query("SELECT k FROM Kupovina k WHERE k.korisnik.id = :idK")
	Kupovina podatakKorisnika( @Param("idK") Integer idK);

}
