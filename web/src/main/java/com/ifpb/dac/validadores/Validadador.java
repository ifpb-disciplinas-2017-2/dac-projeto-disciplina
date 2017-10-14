
package com.ifpb.dac.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("validator.ValidadorArquivo")
public class Validadador implements Validator {

    private static final int TAMANHO_MAX = 2 * 1024 * 1024;
    
    @Override
    public void validate(FacesContext context, UIComponent component, 
            Object value) throws ValidatorException {
        Part arq = (Part) value;
        if(arq.getSize() > TAMANHO_MAX){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Arquivo muito grande", "Não é possivel fazer o upload");
            throw new ValidatorException(msg);
        } else if(!arq.getContentType().equals("text/plain") && !arq.getContentType().equals("application/pdf")){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Arquivo não suportado", "Informe um arquivo do tipo de texto");
            throw new ValidatorException(msg);
        }
    }
    
}
