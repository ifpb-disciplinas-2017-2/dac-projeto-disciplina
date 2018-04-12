/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.rs.interfaces;

import com.ifpb.dac.entidades.Turma;
import java.util.List;

/**
 *
 * @author lyndemberg
 */
public interface TurmaDaoLocal {
    List<String> professoresDisciplina(String disciplina);
    Turma buscarPorId(int id);
    Turma retornarDiscProf(String disciplina, String professor);
    int verificarAlunoTurma(int idTurma, int idAluno);
    void atualizar(Turma t);
}
