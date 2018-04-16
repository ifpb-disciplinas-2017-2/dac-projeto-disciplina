/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Professor;
import java.util.List;

/**
 *
 * @author natan
 */
public interface DuvidaDao {
    void adicionar(Duvida duvida);
    void remover(Duvida duvida);
    void atualizar(Duvida duvida);
    List<Duvida> listarTodos();
    Duvida buscarPorId(int id);    
    List<Duvida> listarDuvidasFeitasPorAluno(Aluno aluno);
    List<Duvida> listarDuvidasNaoRespondidasTurmasAluno(Aluno aluno);
    List<Duvida> listarDuvidasNaoRespondidasTurmasProfessor(Professor aluno);
    
}
