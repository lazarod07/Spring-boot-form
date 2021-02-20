package co.cristian.springboot.form.app.services;

import java.util.List;

import co.cristian.springboot.form.app.models.domain.Pais;

public interface PaiseService {
	
	public List<Pais> listar();
	
	public Pais obtenerPorId(Integer id);

}
