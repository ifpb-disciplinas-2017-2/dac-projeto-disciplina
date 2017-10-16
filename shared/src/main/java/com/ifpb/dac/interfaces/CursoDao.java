package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Curso;
import java.util.List;

public interface CursoDao {
    
    void adicionar(Curso curs);
    void remover(Curso curs);
    void atualizar(Curso curs);
    List<Curso> listarTodos();
    Curso buscarPorId(int id);
    List<String> listarNomeCursos();
    Curso retornarPorNome(String curso);
    
}
