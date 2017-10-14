package com.ifpb.dac.conversores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rodrigobento
 */
@FacesConverter(value = "convert.ConversorData")
public class ConversorData implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        } else {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime data = LocalDateTime.parse(value, parser);
            return data;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            LocalDateTime valor = (LocalDateTime) value;
            return (valor).format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
        }
    }
}
