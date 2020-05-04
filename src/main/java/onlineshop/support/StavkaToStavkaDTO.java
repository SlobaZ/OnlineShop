package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.model.Stavka;
import onlineshop.web.dto.StavkaDTO;



@Component
public class StavkaToStavkaDTO implements Converter<Stavka, StavkaDTO> {

	@Override
	public StavkaDTO convert(Stavka stavka) {
		if(stavka==null){
			return null;
		}
		
		StavkaDTO dto = new StavkaDTO();
			
		dto.setId(stavka.getId());
		dto.setKolicinaStavke(stavka.getKolicinaStavke());
		dto.setCenaStavke(stavka.getCenaStavke());
		
		dto.setKupovinaId(stavka.getKupovina().getId());
		dto.setKupovinaSifra(stavka.getKupovina().getSifra());
		dto.setKupovinaUkupnaCena(stavka.getKupovina().getUkupnaCena());
		dto.setKupovinaDatumVreme(stavka.getKupovina().getDatumVreme());
		dto.setKupovinaDateTime(stavka.getKupovina().getDateTime());
		
		dto.setProizvodId(stavka.getProizvod().getId());
		dto.setProizvodNaziv(stavka.getProizvod().getNaziv());
		dto.setProizvodKolicina(stavka.getProizvod().getKolicina());
		dto.setProizvodCena(stavka.getProizvod().getCena());
		
		return dto;
	}
	
	public List<StavkaDTO> convert(List<Stavka> transakcije){
		List<StavkaDTO> ret = new ArrayList<>();
		
		for(Stavka s: transakcije){
			ret.add(convert(s));
		}
		
		return ret;
	}

}
