package com.ifpb.dac.googleAgenda;

import com.google.api.services.calendar.model.Event;

/**
 *
 * @author rodrigobento
 */
public interface GoogleAPI {
    
    String adicionarEvento(Event evento);
    void removerEvento(String idEvento);
    
}
