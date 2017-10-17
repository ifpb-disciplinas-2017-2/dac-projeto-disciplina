package com.ifpb.dac.notificacao;

import com.ifpb.dac.interfaces.EnviarEmail;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.jms.QueueEmail;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(EnviarEmail.class)
public class EnviarEmailImpl implements EnviarEmail {

    @Inject @QueueEmail
    private Queue queueEnviar;
    @Inject
    private JMSContext contexto;
    
    @Override
    public void enviar(Atividade a) {
        ObjectMessage mensagem = contexto.createObjectMessage(a);
        contexto.createProducer().send(queueEnviar, mensagem);
    }
    
}
