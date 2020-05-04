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

import onlineshop.model.Korisnik;
import onlineshop.service.KorisnikService;
import onlineshop.support.KorisnikDTOToKorisnik;
import onlineshop.support.KorisnikToKorisnikDTO;
import onlineshop.web.dto.KorisnikDTO;



@RestController
@RequestMapping(value="/korisnici")
public class ApiKorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnikToKorisnikDTO toDTO;
	
	@Autowired
	private KorisnikDTOToKorisnik toKorisnik;
	
	
	@RequestMapping(value="/sve", method=RequestMethod.GET)
	ResponseEntity<List<KorisnikDTO>> getAlls() {
		List<Korisnik> korisnikPage = null;
		korisnikPage = korisnikService.findAll();
		return new ResponseEntity<>( toDTO.convert(korisnikPage) , HttpStatus.OK);
	}	
	
		
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<KorisnikDTO>> getAll(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String mesto,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Korisnik> korisnikPage = null;
		
		if(naziv != null || mesto != null ) {
			korisnikPage = korisnikService.search(naziv, mesto, pageNum);
		}
		else {
			korisnikPage = korisnikService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(korisnikPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(korisnikPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<KorisnikDTO> getOne(@PathVariable Integer id){
		Korisnik korisnik = korisnikService.getOne(id);
		if(korisnik==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(korisnik), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<KorisnikDTO> delete(@PathVariable Integer id){
		Korisnik deleted = korisnikService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KorisnikDTO> add( @Validated @RequestBody KorisnikDTO newKorisnikDTO){
		
		if(newKorisnikDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Korisnik savedKorisnik = korisnikService.save(toKorisnik.convert(newKorisnikDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedKorisnik), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<KorisnikDTO> edit(
			@Validated @RequestBody KorisnikDTO korisnikDTO,
			@PathVariable Integer id){
		
		if(!id.equals(korisnikDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Korisnik persisted = korisnikService.save(toKorisnik.convert(korisnikDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	

	
	
}
