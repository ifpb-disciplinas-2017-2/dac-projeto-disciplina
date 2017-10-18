package com.ifpb.dac.googleAgenda;

import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.interfaces.AtividadeDao;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/queueAgendaRmv")
})
public class GoogleAgendaRemover implements MessageListener {

    @EJB
    private GoogleAPI api;
    @EJB
    private AtividadeDao aDao;

    @Override
    public void onMessage(Message message) {
        try {
            Atividade a = message.getBody(Atividade.class);
            api.removerEvento(a.getId());
//            aDao.remover(a);
            System.out.println("Evento removido...");
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}
