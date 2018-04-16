/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.conversores;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author lyndemberg
 */
@Converter(autoApply = false)
public class LocalDateToDate implements AttributeConverter<LocalDate,Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDate data) {
        if(data == null) return null;
        return Date.valueOf(data);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        if(date == null) return null;
        return date.toLocalDate();
    }
    
}
