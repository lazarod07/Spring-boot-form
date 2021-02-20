package co.cristian.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import co.cristian.springboot.form.app.editors.NombreMayusculaEditor;
import co.cristian.springboot.form.app.editors.PaisPropertyEditor;
import co.cristian.springboot.form.app.editors.RolesEditor;
import co.cristian.springboot.form.app.models.domain.Pais;
import co.cristian.springboot.form.app.models.domain.Role;
import co.cristian.springboot.form.app.models.domain.Usuario;
import co.cristian.springboot.form.app.services.PaiseService;
import co.cristian.springboot.form.app.services.RoleService;
import co.cristian.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaiseService paisService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBindir(WebDataBinder binder) {
		
		binder.addValidators(validador);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		dateFormat.setLenient(false);
		
		binder.registerCustomEditor(Date.class, "fechaNacimiento" ,new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		
		binder.registerCustomEditor( Pais.class, "pais", paisEditor);
		
		binder.registerCustomEditor( Role.class, "roles", roleEditor);
		
	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		
		return Arrays.asList("Hombre","Mujer");
		
	}
	
	@ModelAttribute("paises")
	public List<String> paises(){
		
		return Arrays.asList("Colombia", "Chile", "Ecuador", "Peru");
		
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		
		return paisService.listar();
		
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		
		roles.add("ROLE_ADMIN");
		
		roles.add("ROLE_USER");
		
		roles.add("ROLE_MODERATOR");
		
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap(){
		
		Map<String, String> roles = new HashMap<String, String>();
		
		roles.put("ROLE_ADMIN", "Administrador");
		
		roles.put("ROLE_USER", "Usuario");
		
		roles.put("ROLE_MODERATOR", "Moderador"); 
		
		return roles;
		
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		
		Map<String, String> paises = new HashMap<String, String>();
		
		paises.put("CO", "Colombia");
		
		paises.put("PE", "Peru");
		
		paises.put("CH", "Chile"); 
		
		paises.put("Bo", "Bolivia");
		
		return paises;
		
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		
		return this.roleService.listar();
		
	}
	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Cristian");
		
		usuario.setApellido("Zapata");
		
		usuario.setHabilitar(true);
		
		usuario.setPais(new Pais(1, "CO", "Colombia"));
		
		usuario.setRoles(Arrays.asList(new Role(1, "Administrador", "ROLE_ADMIN")));
		
		usuario.setValorSecreto("Algun valor");
		
		model.addAttribute("titulo", "Formulario usuarios");
		
		model.addAttribute("usuario", usuario);
		
		return "form";
		
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
		
//		validador.validate(usuario, result);
		
		if(result.hasErrors()) {
			
			model.addAttribute("titulo", "Resultado formulario");
			
			return "form";
			
		}
		
		return "redirect:/ver";
		
	}
	
	@GetMapping("/ver")
	public String  ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
		
		if(usuario == null) {
			
			return "redirect:/form";
					
		}
		
		model.addAttribute("titulo", "Resultado formulario");
		
		status.setComplete();
		
		return "resultado";
		
	}

}
