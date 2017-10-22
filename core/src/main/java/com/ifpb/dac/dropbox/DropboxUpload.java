package com.ifpb.dac.dropbox;

import com.ifpb.dac.interfaces.DropboxAPI;
import com.ifpb.dac.entidades.Material;
import com.ifpb.dac.interfaces.MaterialDao;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/queueDropboxUpload")
})
public class DropboxUpload implements MessageListener {

    @EJB
    private DropboxAPI dropbox;
    @EJB
    private MaterialDao mDao;

    @Override
    public void onMessage(Message message) {
        try {
            Material material = message.getBody(Material.class);
            String id = dropbox.uploadArquivo(material);
            List<Material> materiais = mDao.listarTodos();
            boolean existe = false;
            for (Material mat : materiais) {
                if (mat.getId().equals(id)) {
                    existe = true;
                }
            }
            if (!existe) {
                material.setId(id);
                mDao.adicionar(material);
            }            
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}
