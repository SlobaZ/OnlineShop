package onlineshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import onlineshop.model.Stavka;
import onlineshop.service.StavkaService;
import onlineshop.support.StavkaDTOToStavka;
import onlineshop.support.StavkaToStavkaDTO;
import onlineshop.web.dto.StavkaDTO;



@RestController
@RequestMapping(value="/kupovine/{kupovinaId}/stavke")
public class ApiStavkaController {
	
	@Autowired
	private StavkaService stavkaService;
	
	@Autowired
	private StavkaToStavkaDTO toDTO;
	 
	@Autowired
	private StavkaDTOToStavka toStavka;
		
	
	
	@RequestMapping( method=RequestMethod.GET)
	ResponseEntity<List<StavkaDTO>> getAllsByKupovinaId(@PathVariable Integer kupovinaId) {
		List<Stavka> stavke = stavkaService.findByIdKupovine(kupovinaId);
	return new ResponseEntity<>( toDTO.convert(stavke) , HttpStatus.OK);
	}	

	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<StavkaDTO> getOne(@PathVariable Integer id){
		Stavka stavka = stavkaService.getOne(id);
		if(stavka==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(stavka), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<StavkaDTO> delete(@PathVariable Integer id){
		Stavka deleted = stavkaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StavkaDTO> add( @Validated @RequestBody StavkaDTO newStavkaDTO){
		if(newStavkaDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Stavka savedStavka = stavkaService.save(toStavka.convert(newStavkaDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedStavka), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<StavkaDTO> edit(
			@Validated @RequestBody StavkaDTO stavkaDTO,
			@PathVariable Integer id){
		
		if(!id.equals(stavkaDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Stavka persisted = stavkaService.save(toStavka.convert(stavkaDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	@RequestMapping(value="/{id}/{kolicinaStavke}/kupiStavku" , method=RequestMethod.POST)
	public ResponseEntity<StavkaDTO> kupiStavku(@PathVariable Integer id, @PathVariable int kolicinaStavke){

		Stavka stavka = stavkaService.kupiStavku(id,kolicinaStavke);
		if(stavka==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>( toDTO.convert(stavka), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/{id}/resetujStavku" , method=RequestMethod.POST)
	public ResponseEntity<StavkaDTO> resetujStavku(@PathVariable Integer id){

		Stavka stavka = stavkaService.resetujStavku(id);
		if(stavka==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>( toDTO.convert(stavka), HttpStatus.CREATED);
	}
	

}
