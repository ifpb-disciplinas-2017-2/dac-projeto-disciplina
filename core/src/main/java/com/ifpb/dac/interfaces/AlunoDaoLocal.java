package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.rs.model.AlunoRest;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lyndemberg
 */
public interface AlunoDaoLocal {
    void adicionar(Aluno aluno);
    AlunoRest buscarPorIdRest(int id);
    AlunoRest autenticacaoRest(String email, String senha);
    boolean verificarEmail(String email);
}
