package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.model.Korisnik;
import onlineshop.service.KorisnikService;
import onlineshop.web.dto.KorisnikDTO;



@Component
public class KorisnikDTOToKorisnik implements Converter<KorisnikDTO, Korisnik> {
	
	@Autowired
	private KorisnikService korisnikService;

	@Override
	public Korisnik convert(KorisnikDTO dto) {
		Korisnik korisnik = null;
		
		if(dto.getId()!=null){
			korisnik = korisnikService.getOne(dto.getId());
			
			if(korisnik == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant Korisnik");
			}
		}
		else {
			korisnik = new Korisnik();
		}
		
		korisnik.setId(dto.getId());
		korisnik.setNaziv(dto.getNaziv());
		korisnik.setMesto(dto.getMesto());
		korisnik.setAdresa(dto.getAdresa());
		korisnik.setJmbg(dto.getJmbg());
		korisnik.setTelefon(dto.getTelefon());
		korisnik.setBrojracuna(dto.getBrojracuna());
		korisnik.setStanje(dto.getStanje());
		
		return korisnik;
	}
	
	public List<Korisnik> convert (List<KorisnikDTO> dtoKorisniks){
		List<Korisnik> korisniks = new ArrayList<>();
		
		for(KorisnikDTO dto : dtoKorisniks){
			korisniks.add(convert(dto));
		}
		
		return korisniks;
	}
}
