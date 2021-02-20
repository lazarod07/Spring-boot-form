package co.cristian.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.cristian.springboot.form.app.models.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	private List<Role> roles;

	@Override
	public List<Role> listar() {
		return this.roles;
	}

	@Override
	public Role obtenetPorId(Integer id) {
		Role resultado = null;
		
		for (Role role: roles) {
			if(id == role.getId()) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}

	public RoleServiceImpl() {
		
		this.roles = new ArrayList<>();
		
		this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		
		this.roles.add(new Role(2, "Usuario", "ROLE_USUARIO"));
		
		this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
		
	}

}
