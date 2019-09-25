package br.com.codersistemas.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.codersistemas.model.entity.IEntity;

@ManagedBean
@RequestScoped
@FacesValidator("entityValidator")
public class EntityValidator implements Validator {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {
		if(!(obj instanceof IEntity)) {
			FacesMessage msg = new FacesMessage("Entidade inválida.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	
}