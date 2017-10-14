package com.ifpb.dac.googleAgenda;

import com.ifpb.dac.jms.QueueAdd;
import com.ifpb.dac.jms.QueueRmv;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.interfaces.GoogleAgenda;
import com.ifpb.dac.entidades.Usuario;
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
@Remote(GoogleAgenda.class)
public class GoogleAgendaJMS implements GoogleAgenda {
    
    @Inject @QueueAdd
    private Queue queueAdd;
    
    @Inject @QueueRmv
    private Queue queueRmv;
    
    @Inject
    private JMSContext contexto;
    
    @Override
    public void cadastrarEvento(Atividade atividade){
        ObjectMessage mensagem = contexto.createObjectMessage(atividade);
        contexto.createProducer().send(queueAdd, mensagem);
    }

    @Override
    public void atualizarEvento(Atividade atividade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removerEvento(Atividade atividade) {
        ObjectMessage mensagem = contexto.createObjectMessage(atividade);
        contexto.createProducer().send(queueRmv, mensagem);
    }
    
}
