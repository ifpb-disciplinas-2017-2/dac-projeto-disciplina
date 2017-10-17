package com.ifpb.dac.notificacao;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.entidades.Turma;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author rodrigobento
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    ,
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/queueEnviarEmail")
})
public class EnviarEmailListener implements MessageListener {

    private static final String EMAIL = "projetodacq@gmail.com";
    private static final String SENHA = "projetodac123";

    @Override
    public void onMessage(Message message) {
        Atividade ativ;
        try {
            ativ = message.getBody(Atividade.class);
            enviarEmail(ativ);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    private void enviarEmail(Atividade ativ) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
            email.setTLS(true);
            email.setSSL(true);
            Turma turmaVinculada = ativ.getTurma();
            String nomeProf = turmaVinculada.getProfessor().getNome();
            email.setSubject("Nova atividade cadastrada");            
            List<Aluno> alunosTurma = turmaVinculada.getAlunos();
            email.setFrom(EMAIL);
            if(alunosTurma.isEmpty()){
                System.out.println("Não possui alunos...");                
                email.setMsg("O professor "+ nomeProf +" adicionou uma nova atividade "
                    + "a turma " + turmaVinculada.getNome_disciplina());
                email.addTo(EMAIL);                
            } else {
                email.setMsg("O professor "+ nomeProf +" adicionou uma nova atividade "
                    + "a turma " + turmaVinculada.getNome_disciplina() + "\n"
                            + "Verifique sua Agenda Google para mais instruções...");
                for(Aluno aux: alunosTurma){
//                    email.setFrom(aux.getEmail());
                    email.addTo(aux.getEmail());                    
                }                
            }
            email.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
        }
    }

}
