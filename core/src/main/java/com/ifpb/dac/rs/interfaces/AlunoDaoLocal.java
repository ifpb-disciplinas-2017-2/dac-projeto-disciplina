package com.ifpb.dac.rs.interfaces;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.rs.model.AlunoRest;
import com.ifpb.dac.rs.model.TurmaRest;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lyndemberg
 */
public interface AlunoDaoLocal {
    void adicionar(Aluno aluno);
    Aluno buscarPorEmail(String email);
    Aluno buscarPorId(int id);
    List<TurmaRest> listTurmasRestAluno(int idAluno);
    AlunoRest buscarPorIdRest(int id);
    AlunoRest autenticacaoRest(String email, String senha);
    boolean verificarEmail(String email);
}
