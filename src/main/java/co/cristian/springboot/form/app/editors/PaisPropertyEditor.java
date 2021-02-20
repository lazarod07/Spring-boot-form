package co.cristian.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.cristian.springboot.form.app.services.PaiseService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaiseService service;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (text != null && text.length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(text);
				
				this.setValue(service.obtenerPorId(id));
				
			}catch (NumberFormatException e) {
				
				this.setValue(null);
				
			}
			
		}else {
			
			this.setValue(null);
			
		}

	}

}
