package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Info;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.rs.interfaces.AlunoDaoLocal;
import com.ifpb.dac.rs.model.AlunoRest;
import com.ifpb.dac.rs.model.CursoRest;
import com.ifpb.dac.rs.model.TurmaRest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(AlunoDao.class)
@Local(AlunoDaoLocal.class)
public class AlunoDaoImpl implements AlunoDao, AlunoDaoLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Aluno aluno) {
        aluno.setEmail(aluno.getEmail().toLowerCase());
        em.persist(aluno);
    }

    @Override
    public void remover(Aluno aluno) {
        em.remove(buscarPorId(aluno.getId()));
    }

    @Override
    public void atualizar(Aluno aluno) {
        em.merge(aluno);
    }

    @Override
    public List<Aluno> listarTodos() {
        return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }

    @Override
    public List<Aluno> listarTodosOsAlunos(int id) {

        String querySql = "SELECT * FROM aluno a WHERE a.id <> " + id;

        Query createNativeQuery = em
                .createNativeQuery(querySql, Aluno.class);

        List<Aluno> alunos = createNativeQuery.getResultList();

        if (alunos == null) {
            return new ArrayList<>();
        }

        return alunos;
    }

    @Override
    public Aluno buscarPorId(int id) {
        return em.find(Aluno.class, id);
    }

    @Override
    public Aluno autentica(String email, String senha) {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a WHERE "
                + "a.email =:email AND a.senha =:senha", Aluno.class);
        query.setParameter("email", email.toLowerCase());
        query.setParameter("senha", senha);
        Optional<Aluno> resultado = query.getResultList().stream().findFirst();
        if (resultado.isPresent()) {
            Aluno usuario = resultado.get();
            return usuario;
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarEmail(String email) {
        TypedQuery<Aluno> createQuery = em.createQuery("SELECT a FROM "
                + "Aluno a WHERE a.email =:email", Aluno.class);
        createQuery.setParameter("email", email);
        Optional<Aluno> findFirst = createQuery.getResultList().stream().findFirst();
        if (findFirst.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AlunoRest autenticacaoRest(String email, String senha) {
        AlunoRest aluno = null;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
        Root<Aluno> root = criteria.from(Aluno.class);
        criteria.multiselect(root.get("id"), root.get("nome"),
                root.get("email"),
                root.get("logado"),
                root.get("curso").get("codigo_curso"),
                root.get("curso").get("info"));
        Predicate predicadoEmail = builder.equal(root.get("email"), email);
        Predicate predicadoSenha = builder.equal(root.get("senha"), senha);
        criteria.where(predicadoEmail, predicadoSenha);
        TypedQuery<Tuple> query = em.createQuery(criteria);
        List<Tuple> result = query.getResultList();
        if (!result.isEmpty()) {
            for (Tuple tupla : result) {
                Integer codigo_curso = tupla.get(4, Integer.class);
                Info info = tupla.get(5, Info.class);
                CursoRest curso = new CursoRest(codigo_curso, info);
                Integer id = tupla.get(0, Integer.class);
                String nome = tupla.get(1, String.class);
                String emailRetornado = tupla.get(2, String.class);
                Boolean logado = tupla.get(3, Boolean.class);
                aluno = new AlunoRest(id, nome, emailRetornado, logado, curso);
            }
        }
        return aluno;
    }

    @Override
    public AlunoRest buscarPorIdRest(int id) {
        AlunoRest aluno = null;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
        Root<Aluno> root = criteria.from(Aluno.class);
        criteria.multiselect(root.get("id"), root.get("nome"),
                root.get("email"),
                root.get("logado"),
                root.get("curso").get("codigo_curso"),
                root.get("curso").get("info"));
        Predicate predicadoEmail = builder.equal(root.get("id"), id);
        criteria.where(predicadoEmail);
        TypedQuery<Tuple> query = em.createQuery(criteria);
        List<Tuple> result = query.getResultList();
        if (!result.isEmpty()) {
            for (Tuple tupla : result) {
                Integer codigo_curso = tupla.get(4, Integer.class);
                Info info = tupla.get(5, Info.class);
                CursoRest curso = new CursoRest(codigo_curso, info);
                Integer idRetornado = tupla.get(0, Integer.class);
                String nome = tupla.get(1, String.class);
                String emailRetornado = tupla.get(2, String.class);
                Boolean logado = tupla.get(3, Boolean.class);
                aluno = new AlunoRest(id, nome, emailRetornado, logado, curso);
            }
        }
        return aluno;
    }

    @Override
    public Aluno buscarPorEmail(String email) {
        TypedQuery<Aluno> createQuery = em.createQuery("SELECT a FROM "
                + "Aluno a WHERE a.email =:email", Aluno.class);
        createQuery.setParameter("email", email);
        Optional<Aluno> findFirst = createQuery.getResultList().stream().findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get();
        } else {
            return null;
        }
    }

    @Override
    public List<TurmaRest> listTurmasRestAluno(int idAluno) {
        TypedQuery<TurmaRest> createQuery = em.createQuery("SELECT NEW com.ifpb.dac.rs.model.TurmaRest(t.codigo_turma,t.identificacao,t.nome_disciplina,t.professor.nome) FROM Aluno a, IN (a.turmas) t WHERE a.id =:id", TurmaRest.class);
        createQuery.setParameter("id", idAluno);
        return createQuery.getResultList();
    }

    @Override
    public List<Turma> listarTurmasAluno(Aluno a) {
        TypedQuery<Turma> createQuery = em.createQuery("SELECT t FROM Aluno a, IN (a.turmas) t WHERE a.id =:id", Turma.class);
        createQuery.setParameter("id", a.getId());
        return createQuery.getResultList();
    }

    @Override
    public List<Aluno> listarAlunosTurma(int id, int codTurma) {
        TypedQuery<Aluno> createQuery = em.createQuery("SELECT a FROM Turma t, IN (t.alunos) a WHERE a.id <>:idAluno AND t.codigo_turma =:codTurma", Aluno.class);
        createQuery.setParameter("idAluno", id);
        createQuery.setParameter("codTurma", codTurma);
        return createQuery.getResultList();
    }

}
