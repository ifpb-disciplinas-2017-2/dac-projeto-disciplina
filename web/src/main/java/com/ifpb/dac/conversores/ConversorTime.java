/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.conversores;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lyndemberg
 */
@FacesConverter(value = "convert.ConversorTime")
public class ConversorTime implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        } else {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime data = LocalTime.parse(value, parser);
            return data;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            LocalTime valor = (LocalTime) value;
            return (valor).format(
                    DateTimeFormatter.ofPattern("HH:mm"));
        }
    }
    
}
