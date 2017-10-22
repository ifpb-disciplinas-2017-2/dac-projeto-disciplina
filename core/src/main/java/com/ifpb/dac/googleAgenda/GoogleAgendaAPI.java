package com.ifpb.dac.googleAgenda;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.interfaces.AtividadeDao;
import com.ifpb.dac.interfaces.EnviarEmail;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(GoogleAPI.class)
public class GoogleAgendaAPI implements GoogleAPI {

    @EJB
    private AtividadeDao atividadeDao;
    @EJB
    private EnviarEmail email;
    
    private static final String CALENDAR_ID = "projetodacfinal@projetodac"
            + "-182622.iam.gserviceaccount.com";

    private static Calendar getCalendarClient() {
        try {
            ClassLoader carregarCredenciais = Thread.currentThread().
                    getContextClassLoader();
            InputStream leitor = carregarCredenciais.
                    getResourceAsStream("ProjetoDACFinal.json");
            GoogleCredential credenciais = GoogleCredential.
                    fromStream(leitor).
                    createScoped(Arrays.
                            asList("https://www.googleapis.com/auth/calendar"));
            Calendar client = new Calendar.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    new JacksonFactory(), credenciais)
                    .setApplicationName("ProjetoDAC")
                    .build();
            return client;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (GeneralSecurityException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String adicionarEvento(Event evento) {
        System.out.println(evento.toString());
        Calendar c = getCalendarClient();
        try {
            return c.events().insert(CALENDAR_ID,
                    evento).execute().getId();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void removerEvento(String idEvento) {
        Calendar c = getCalendarClient();
        try {
            c.events().delete(CALENDAR_ID, idEvento).execute();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Schedule(hour = "23", minute = "00", second = "00")
//    @Schedule(hour = "23", minute = "59", second = "59")
//    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void notificarDiaAnteriorEvento() {
        System.out.println("Executando o agendador...");
        List<Atividade> atividades = atividadeDao.listarTodos();
        LocalDate dataPosterior = LocalDate.now().plusDays(1);
        for (Atividade auxiliar : atividades) {
            System.out.println("Buscando atividades com data de entrega");
            LocalDate dataFimBanco = auxiliar.getFim().toLocalDate();
            if (dataPosterior.equals(dataFimBanco)) {
                auxiliar.setNotDiaAnterior(true);
                email.enviar(auxiliar);
                System.out.println("Email enviado...");
            }
        }
    }

}
