package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.model.Korisnik;
import onlineshop.web.dto.KorisnikDTO;


@Component
public class KorisnikToKorisnikDTO implements Converter<Korisnik, KorisnikDTO> {

	@Override
	public KorisnikDTO convert(Korisnik korisnik) {
		if(korisnik==null){
			return null;
		}
		
		KorisnikDTO dto = new KorisnikDTO();
		
		dto.setId(korisnik.getId());
		dto.setNaziv(korisnik.getNaziv());
		dto.setMesto(korisnik.getMesto());
		dto.setAdresa(korisnik.getAdresa());
		dto.setJmbg(korisnik.getJmbg());
		dto.setTelefon(korisnik.getTelefon());
		dto.setBrojracuna(korisnik.getBrojracuna());
		dto.setStanje(korisnik.getStanje());
		
		return dto;
	}
	
	public List<KorisnikDTO> convert(List<Korisnik> korisnici){
		List<KorisnikDTO> ret = new ArrayList<>();
		
		for(Korisnik k: korisnici){
			ret.add(convert(k));
		}
		
		return ret;
	}
}
