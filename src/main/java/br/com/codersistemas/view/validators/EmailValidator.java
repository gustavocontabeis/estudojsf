package br.com.codersistemas.view.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@ManagedBean
@RequestScoped
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {
		String email = (String) obj;
		if (StringUtils.isNotBlank(email) && !validateEmail(email)) {
			FacesMessage msg = new FacesMessage("Email inválido");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	
	public boolean validateEmail(final String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9_\\-.]*[a-zA-Z0-9]@[a-zA-Z0-9][a-zA-Z0-9.]*\\.[a-zA-Z0-9.]+$");
		Matcher mailMatcher = mailPattern.matcher(email);
		return mailMatcher.matches();
	}
}