package com.ifpb.dac.googleAgenda;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(GoogleAPI.class)
public class GoogleAgendaAPI implements GoogleAPI {

    private static final String CALENDAR_ID = "projetodacfinal@projetodac"
            + "-182622.iam.gserviceaccount.com";
//        private static final String CALENDAR_ID2 = "projeto-dac@projeto-dac.iam.gserviceaccount.com";
//        private static final String CALENDAR_ID = "dac-project@projeto-dac.iam.gserviceaccount.com";


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

}
