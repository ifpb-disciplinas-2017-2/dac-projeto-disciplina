/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.conversores;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lyndemberg
 */
@FacesConverter(value = "convert.ConversorCalendar")
public class ConversorCalendar implements Converter{
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        } else {
            try{
                LocalTime time = LocalTime.parse(value);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, time.getHour());
                calendar.set(Calendar.MINUTE, time.getMinute());
                return calendar;
            }catch(Exception ex){
                return null;
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }else{
            Calendar calendar = (Calendar) value;
            return sdf.format(calendar.getTime());
        }

    }
    
}
