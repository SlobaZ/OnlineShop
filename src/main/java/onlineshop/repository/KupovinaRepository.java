package onlineshop.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.model.Kupovina;


@Repository
public interface KupovinaRepository extends JpaRepository<Kupovina, Integer>{

//	Page<Kupovina> findByKorisnikId(Integer korisnikId, Pageable page);
	
	@Query("SELECT k FROM Kupovina k WHERE "
			+ "(:korisnikId IS NULL or k.korisnik.id = :korisnikId ) AND "
			+ "(:sifra IS NULL OR k.sifra like :sifra) AND "
			+ "(:ukupnaCena IS NULL or k.ukupnaCena = :ukupnaCena ) AND "
			+ "(:datumVremePocetak IS NULL or k.datumVreme >= :datumVremePocetak ) AND "
			+ "(:datumVremeKraj IS NULL or k.datumVreme <= :datumVremeKraj ) "
			)
	Page<Kupovina> search(
			@Param("korisnikId") Integer korisnikId, 
			@Param("sifra") String sifra, 
			@Param("ukupnaCena") Double ukupnaCena,
			@Param("datumVremePocetak") Timestamp datumVremePocetak,
			@Param("datumVremeKraj") Timestamp datumVremeKraj,
			Pageable pageRequest);
	
}
