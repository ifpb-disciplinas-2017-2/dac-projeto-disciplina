
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Coordenador;
import com.ifpb.dac.interfaces.CoordenadorDao;
import java.util.List;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lyndemberg
 */
@Stateless
@Remote(CoordenadorDao.class)
public class CoordenadorDaoImpl implements CoordenadorDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Coordenador coordenador) {
        coordenador.setEmail(coordenador.getEmail().toLowerCase());
        em.persist(coordenador);
    }

    @Override
    public void atualizar(Coordenador coordenador) {
        em.merge(coordenador);
    }

    @Override
    public List<Coordenador> listarTodos() {
        return em.createQuery("SELECT c FROM Coordenador c", Coordenador.class).getResultList();
    }

    @Override
    public Coordenador buscarPorCodigo(int codigo) {
        return em.find(Coordenador.class, codigo);
    }

    @Override
    public Coordenador autentica(String email, String senha) {
        TypedQuery<Coordenador> query = em.createQuery("SELECT c FROM Coordenador c WHERE c.email=:email AND c.senha=:senha",Coordenador.class);
        query.setParameter("email", email.toLowerCase());
        query.setParameter("senha", senha);
        Optional<Coordenador> retorno = query.getResultList().stream().findFirst();
        if(retorno.isPresent()){
            Coordenador c = retorno.get();
            return c;
        }else{
            return null;
        }
    }

    @Override
    public boolean verificarEmail(String email) {
        TypedQuery<Coordenador> query = em.createQuery("SELECT c FROM Coordenador c WHERE c.email=:email",Coordenador.class);
        query.setParameter("email", email.toLowerCase());
        Optional<Coordenador> retorno = query.getResultList().stream().findFirst();
        if(retorno.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void remover(Coordenador coordenador) {
        Coordenador aux = buscarPorCodigo(coordenador.getCodigo());
        em.remove(aux);
    }
    
}
