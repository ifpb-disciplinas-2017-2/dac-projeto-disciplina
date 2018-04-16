package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.rs.interfaces.ProfessorDaoLocal;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@Remote(ProfessorDao.class)
@Local(ProfessorDaoLocal.class)
public class ProfessorDaoImpl implements ProfessorDao, ProfessorDaoLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Professor prof) {
        prof.setEmail(prof.getEmail().toLowerCase());
        em.persist(prof);
    }

    @Override
    public void remover(Professor prof) {
        Professor auxiliar = buscarPorId(prof.getCodigo());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Professor prof) {
        em.merge(prof);
    }

    @Override
    public List<Professor> listarTodos() {
        return em.createQuery("SELECT p FROM Professor p",
                Professor.class).getResultList();
    }

    @Override
    public Professor buscarPorId(int id) {
        return em.find(Professor.class, id);
    }

    @Override
    public List<String> listarNomeProfessores() {
        return em.createQuery("SELECT p.nome FROM Professor p",
                String.class).getResultList();
    }

    @Override
    public Professor buscarPorNome(String nomeProfessor) {
        TypedQuery<Professor> createQuery = em.createQuery("select p from Professor p where p.nome like :nomeProfessor", Professor.class);
        createQuery.setParameter("nomeProfessor", nomeProfessor);
        Optional<Professor> resultado = createQuery.getResultList().stream().findFirst();
        if (resultado.isPresent()) {
            Professor prof = resultado.get();
            return prof;
        } else {
            return null;
        }
    }

    @Override
    public Professor autentica(String email, String senha) {
        TypedQuery<Professor> createQuery = em.createQuery("SELECT p FROM Professor p WHERE p.email =:email "
                + "AND p.senha =:senha", Professor.class);
        createQuery.setParameter("email", email);
        createQuery.setParameter("senha", senha);
        Optional<Professor> findFirst = createQuery.getResultList().stream().findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarEmail(String email) {
        TypedQuery<Professor> createQuery = em.createQuery("SELECT p FROM Professor p "
                + "WHERE p.email =:email", Professor.class);
        createQuery.setParameter("email", email);
        Optional<Professor> findFirst = createQuery.getResultList().stream().findFirst();
        if(findFirst.isPresent()){
            return true;
        } else {
            return false;
        }
    }
    
}
