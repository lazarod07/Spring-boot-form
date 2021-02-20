package co.cristian.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.cristian.springboot.form.app.services.RoleService;

@Component
public class RolesEditor extends PropertyEditorSupport {

	@Autowired
	private RoleService service;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		try {
			
			Integer id = Integer.parseInt(text);
			
			setValue(service.obtenetPorId(id));
			
		} catch (NumberFormatException e) {
			
			setValue(null);
			
		}
		
	}

}
