package com.ifpb.dac.conversores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lyndemberg
 */
@FacesConverter(value = "convert.LocalDate", forClass = LocalDate.class)
public class LocalDateConvert implements Converter {

    DateTimeFormatter padrao = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

//        true se a data possue so letras e ou espaços
//        boolean dataDesqualificada = value.matches("[a-zA-Z\\s]+");
       
        if (value == null) { //esta verificacao tem que vir primeiro
            return null;
        }
        if (isDateValid(value)) {
            return LocalDate.parse(value, padrao);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        LocalDate date = (LocalDate) value;
        return date.format(padrao);
    }

    public boolean isDateValid(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        try {
            df.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

}
