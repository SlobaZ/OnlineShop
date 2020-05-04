package onlineshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import onlineshop.model.Stavka;

@Repository
public interface StavkaRepository extends JpaRepository<Stavka, Integer>{
	

	@Query("SELECT s FROM Stavka s WHERE s.kupovina.id = :kupovinaId ")
	List<Stavka> findByIdKupovine(Integer kupovinaId);
	
}
