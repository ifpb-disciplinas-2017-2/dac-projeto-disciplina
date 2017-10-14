
package com.ifpb.dac.googleAgenda;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.interfaces.AtividadeDao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author rodrigobento
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/queueAgendaAdd")
})
public class GoogleAgendaAdicionar implements MessageListener {

    @EJB
    private GoogleAPI api;
    @EJB
    private AtividadeDao dao;
    
    @Override
    public void onMessage(Message message) {
        try {
            Atividade a = message.getBody(Atividade.class);
            Event eventUsu = evento(a);
            String id = api.adicionarEvento(eventUsu);
            a.setId(id);
            dao.adicionar(a);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    // Tratamento para criar evento
    private static Event evento(Atividade a){
        Event event = new Event()
                .setSummary(a.getResumo())
                .setLocation("Cajazeiras")
                .setDescription(a.getDescricao());
        
        LocalDateTime ini = a.getInicio().plusHours(3);
        LocalDateTime fm = a.getFim().plusHours(3);
        
        String dataInicioFormatada = ini.format(
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String dataFinalFormatada = fm.format(
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        String dataInicioConvertida = DateTime.parseRfc3339(dataInicioFormatada).toString();
        String dataFimConvertida = DateTime.parseRfc3339(dataFinalFormatada).toString();
        DateTime startDateTime = new DateTime(dataInicioConvertida);
//        DateTime startDateTime = new DateTime("2017-10-12T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime);
        event.setStart(start);
//        DateTime endDateTime = new DateTime("2017-10-15T17:00:00-07:00");
        DateTime endDateTime = new DateTime(dataFimConvertida);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime);
        event.setEnd(end);

        // Seta para quem vai ser enviado 
        EventAttendee[] attendees = new EventAttendee[]{
            new EventAttendee().setEmail("rodrigobentor2014@gmail.com")};
        event.setAttendees(Arrays.asList(attendees));
        return event;
    }
    
}
