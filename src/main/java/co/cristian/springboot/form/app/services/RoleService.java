package co.cristian.springboot.form.app.services;

import java.util.List;

import co.cristian.springboot.form.app.models.domain.Role;

public interface RoleService {
	
	public List<Role> listar();
	
	public Role obtenetPorId(Integer id);

}
