package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Mensagem;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.enums.TipoUsuarioMensagem;
import com.ifpb.dac.interfaces.MensagemDao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jozimar
 */
@Stateless
@Remote(MensagemDao.class)
public class MensagemDaoImpl implements MensagemDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void enviarMensagem(Mensagem mensagem) {
        em.persist(mensagem);
    }

    @Override
    public List<Mensagem> getHistoricoMensagens(int remetente, TipoUsuarioMensagem tipoRemetente, int destinatario, TipoUsuarioMensagem tipoDestinatario) {

        String querySql = "SELECT m FROM Mensagem m WHERE ("
                + "m.remetente=:rem AND m.tipoRemetente=:tiporem"
                + " AND m.destinatario=:dest AND m.tipoDestinatario=:tipodest"
                + " OR m.remetente=:dest AND m.tipoRemetente=:tipodest"
                + " AND m.destinatario=:rem AND m.tipoDestinatario=:tiporem)";

        TypedQuery<Mensagem> createQuery = em
                .createQuery(querySql, Mensagem.class);
        createQuery.setParameter("rem", remetente);
        createQuery.setParameter("dest", destinatario);
        createQuery.setParameter("tiporem", tipoRemetente);
        createQuery.setParameter("tipodest", tipoDestinatario);

        List<Mensagem> mensagens = createQuery.getResultList();

        if (mensagens == null) {
            return new ArrayList<>();
        }

        return mensagens;
    }
}
