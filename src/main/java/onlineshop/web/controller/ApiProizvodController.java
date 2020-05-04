package onlineshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import onlineshop.model.Kupovina;
import onlineshop.model.Proizvod;
import onlineshop.model.Stavka;
import onlineshop.service.KupovinaService;
import onlineshop.service.ProizvodService;
import onlineshop.service.StavkaService;
import onlineshop.support.ProizvodDTOToProizvod;
import onlineshop.support.ProizvodToProizvodDTO;
import onlineshop.web.dto.ProizvodDTO;



@RestController
@RequestMapping(value="/proizvodi")
public class ApiProizvodController {
	
	@Autowired
	private ProizvodService proizvodService;
	
	@Autowired
	private ProizvodToProizvodDTO toDTO;
	
	@Autowired
	private ProizvodDTOToProizvod toProizvod;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private StavkaService stavkaService;
	
		
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<ProizvodDTO>> getAll(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Integer kolicina,
			@RequestParam(required=false) Double cena,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Proizvod> proizvodPage = null;
		
		if(naziv != null || kolicina != null || cena != null) {
			proizvodPage = proizvodService.search(naziv, kolicina, cena, pageNum);
		}
		else {
			proizvodPage = proizvodService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(proizvodPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(proizvodPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<ProizvodDTO> getOne(@PathVariable Integer id){
		Proizvod proizvod = proizvodService.getOne(id);
		if(proizvod==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(proizvod), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<ProizvodDTO> delete(@PathVariable Integer id){
		Proizvod deleted = proizvodService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ProizvodDTO> add( @Validated @RequestBody ProizvodDTO newProizvodDTO){
		
		if(newProizvodDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Proizvod savedProizvod = proizvodService.save(toProizvod.convert(newProizvodDTO));
		List<Kupovina> kupovine = kupovinaService.findAll();
		for( Kupovina kupovina : kupovine ) {
			Stavka stavka = new Stavka();
			stavka.setProizvod(savedProizvod);
			stavkaService.save(stavka);
			kupovina.addStavka(stavka);
			kupovinaService.save(kupovina);
		}
		return new ResponseEntity<>( toDTO.convert(savedProizvod), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<ProizvodDTO> edit(
			@Validated @RequestBody ProizvodDTO proizvodDTO,
			@PathVariable Integer id){
		
		if(!id.equals(proizvodDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Proizvod persisted = proizvodService.save(toProizvod.convert(proizvodDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	
	
}
