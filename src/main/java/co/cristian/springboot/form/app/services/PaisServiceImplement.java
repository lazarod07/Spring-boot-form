package co.cristian.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import co.cristian.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImplement implements PaiseService {
	
	private List<Pais> lista;

	@Override
	public List<Pais> listar() {
		
		return lista;
		
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		
		Pais resultado = null;
		
		for(Pais pais: this.lista) {
			
			if(pais.getId() == id) {
				
				resultado = pais;
				
				break;
			}
			
		}
		
		return resultado;
		
	}

	public PaisServiceImplement() {
		
		this.lista = Arrays.asList(new Pais(1, "CO", "Colombia"), new Pais(2, "PE" , "Peru"), new Pais(3, "CH" ,"Chile"), new Pais(4, "EC" ,"Ecuador"));
		
	}

}
