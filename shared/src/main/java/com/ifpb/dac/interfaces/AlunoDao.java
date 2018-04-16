package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Turma;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface AlunoDao {

    void adicionar(Aluno aluno);

    void remover(Aluno aluno);

    void atualizar(Aluno aluno);

    List<Aluno> listarTodos();

    Aluno buscarPorId(int id);

    Aluno autentica(String email, String senha);

    boolean verificarEmail(String email);

    List<Aluno> listarTodosOsAlunos(int id);

    List<Turma> listarTurmasAluno(Aluno a);

    List<Aluno> listarAlunosTurma(int id, int codTurma);
}
