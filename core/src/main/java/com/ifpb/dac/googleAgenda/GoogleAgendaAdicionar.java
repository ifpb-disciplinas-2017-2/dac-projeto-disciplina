package com.ifpb.dac.googleAgenda;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.interfaces.AtividadeDao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
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
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    ,
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
    private static Event evento(Atividade a) {
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

        // Seta para quem vai ser enviado (Com lembrete)
//        EventReminder reminder = new EventReminder();
//
//        Long convert = TimeUnit.MINUTES.convert(1, TimeUnit.DAYS);
//        int notificacaoDia = Integer.parseInt(convert.toString());
//        System.out.println("Tempo: " + notificacaoDia);
//        System.out.println("Tempo chute: " + 1440);
//
//        reminder.setMinutes(1440);
//        reminder.setMethod("email");
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(
                        new EventReminder().setMethod("email").setMinutes(1440),
                        new EventReminder().setMethod("popup").setMinutes(1440)));

//        Event.Reminders lembrete = new Event.Reminders();
//        lembrete.setUseDefault(false);
//
//        List<EventReminder> listEventReminder = new ArrayList<>();
//        listEventReminder.add(reminder);
//
//        lembrete.setOverrides(listEventReminder);
//        event.setReminders(lembrete);
        event.setReminders(reminders);

        EventAttendee[] attendees;
        
        List<Aluno> alunosTurma = a.getTurma().getAlunos();
        if (alunosTurma.isEmpty()) {
            attendees = new EventAttendee[2];
            EventAttendee setEmail = new EventAttendee().setEmail("rodrigobentor2014@gmail.com");
            EventAttendee setEmail2 = new EventAttendee().setEmail("projetodacq@gmail.com");
            attendees[0] = setEmail;
            attendees[1] = setEmail2;            
        } else {
            int cont = 0;
            int emails = alunosTurma.size();
            System.out.println("Numero de usuarios cadastrados: " + emails);
            attendees = new EventAttendee[emails];
            for(Aluno auxiliar: alunosTurma){
                attendees[cont] = new EventAttendee().setEmail(auxiliar.getEmail());
                cont++;
            }
        }
        event.setColorId("5");
        event.setAttendees(Arrays.asList(attendees));
        return event;
    }

}
