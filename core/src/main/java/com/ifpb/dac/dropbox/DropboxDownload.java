package com.ifpb.dac.dropbox;

import com.ifpb.dac.entidades.Material;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/queueDropboxDownload")
})
public class DropboxDownload implements MessageListener {

    @EJB
    private DropboxAPI dropbox;
    
    @Override
    public void onMessage(Message message) {
        try {
            Material material = message.getBody(Material.class);
            dropbox.downloadArquivo(material);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
}
