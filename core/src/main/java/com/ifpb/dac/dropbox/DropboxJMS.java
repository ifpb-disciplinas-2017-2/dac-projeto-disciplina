
package com.ifpb.dac.dropbox;

import com.ifpb.dac.entidades.Material;
import com.ifpb.dac.interfaces.Dropbox;
import com.ifpb.dac.jms.QueueRemove;
import com.ifpb.dac.jms.QueueUpload;
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
@Remote(Dropbox.class)
public class DropboxJMS implements Dropbox {

    @Inject @QueueUpload
    private Queue queueUpload;
    
    @Inject @QueueRemove
    private Queue queueRemove;
    @Inject
    private JMSContext contexto;
    
    
    @Override
    public void uploadArquivo(Material material) {
        ObjectMessage mensagem = contexto.createObjectMessage(material);
        contexto.createProducer().send(queueUpload, mensagem);
    }

    @Override
    public void removerArquivo(Material material) {
        ObjectMessage mensagem = contexto.createObjectMessage(material);
        contexto.createProducer().send(queueRemove, mensagem);
    }
    
}
