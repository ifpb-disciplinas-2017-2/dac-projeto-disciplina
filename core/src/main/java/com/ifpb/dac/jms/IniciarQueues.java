package com.ifpb.dac.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;

/**
 *
 * @author rodrigobento
 */
@JMSDestinationDefinitions(
        value = {
            @JMSDestinationDefinition(
                    name = "java:global/jms/queueAgendaAdd",
                    resourceAdapter = "jmsra",
                    interfaceName = "javax.jms.Queue",
                    destinationName = "queueAgendaAdd",
                    description = "Utilizada para adicionar evento com a API do Google Agenda")
            ,
        @JMSDestinationDefinition(
                    name = "java:global/jms/queueAgendaRmv",
                    resourceAdapter = "jmsra",
                    interfaceName = "javax.jms.Queue",
                    destinationName = "queueAgendaRmv",
                    description = "Utilizada para remover evento com a API do Google Agenda"),
        @JMSDestinationDefinition(
                    name = "java:global/jms/queueDropboxUpload",
                    resourceAdapter = "jmsra",
                    interfaceName = "javax.jms.Queue",
                    destinationName = "queueDropboxUpload",
                    description = "Utilizada para realizar upload com a API do Dropbox"),
        @JMSDestinationDefinition(
                    name = "java:global/jms/queueDropboxRemove",
                    resourceAdapter = "jmsra",
                    interfaceName = "javax.jms.Queue",
                    destinationName = "queueDropboxRemove",
                    description = "Utilizada para remover arquivo com a API do Dropbox")
        }
)
@Singleton
@Startup
public class IniciarQueues {

    @Resource(lookup = "java:global/jms/queueAgendaAdd")
    private Queue queueAdd;
    @Resource(lookup = "java:global/jms/queueAgendaRmv")
    private Queue queueRmv;
    @Resource(lookup = "java:global/jms/queueDropboxUpload")
    private Queue queueUpload;
    @Resource(lookup = "java:global/jms/queueDropboxRemove")
    private Queue queueRemove;    

    public IniciarQueues() {
        System.out.println("Criando as queues");
    }

    @Produces @QueueAdd
    public Queue getQueueAdd() {
        return this.queueAdd;
    }

    @Produces @QueueRmv
    public Queue getQueueRmv() {
        return this.queueRmv;
    }
    
    @Produces @QueueUpload
    public Queue getQueueUpload() {
        return this.queueUpload;
    }

    @Produces @QueueRemove
    public Queue getQueueRemove() {
        return this.queueRemove;
    }

}
