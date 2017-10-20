package com.ifpb.dac.notificacao;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.entidades.Turma;
import java.util.List;
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
            System.out.println("Boolean: " + ativ.isNotDiaAnterior());
            Turma turmaVinculada = ativ.getTurma();
            String nomeProf = turmaVinculada.getProfessor().getNome();
            if (!ativ.isNotDiaAnterior()) {
                email.setSubject("Nova atividade cadastrada");
                List<Aluno> alunosTurma = turmaVinculada.getAlunos();
                email.setFrom(EMAIL);
                if (alunosTurma.isEmpty()) {
                    email.setMsg("O professor " + nomeProf + " adicionou uma nova atividade "
                            + "a turma " + turmaVinculada.getNome_disciplina());
                    email.addTo(EMAIL);
                } else {
                    email.setMsg("O professor " + nomeProf + " adicionou uma nova atividade "
                            + "a turma " + turmaVinculada.getNome_disciplina() + "\n"
                            + "Verifique sua Agenda Google para mais instruções...");
                    for (Aluno aux : alunosTurma) {
                        email.addTo(aux.getEmail());
                    }
                }
            } else {
                email.setSubject("Voce possui uma atividade para entregar hoje");
                List<Aluno> alunosTurma = turmaVinculada.getAlunos();
                email.setFrom(EMAIL);
                String mensagem = "A atividade: " + ativ.getResumo() + 
                        ", passada pelo professor: " + nomeProf + 
                        ", na turma: " + turmaVinculada.getNome_disciplina() + 
                        " tem data de entrega para hoje.\n"
                        + "Não se esqueça de enviar, caso tenha feito. Tenha um ótimo dia!!!";
                email.setMsg(mensagem);
                if (alunosTurma.isEmpty()) {                    
                    email.addTo(EMAIL);
                } else {
                    for (Aluno aux : alunosTurma) {
                        email.addTo(aux.getEmail());
                    }
                }
            }
            email.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
        }
    }

}
