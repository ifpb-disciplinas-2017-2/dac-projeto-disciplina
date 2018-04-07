/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.rs.model.CursoRest;
import java.util.List;

/**
 *
 * @author lyndemberg
 */
public interface CursoDaoLocal {
    Curso buscarPorId(int id);
    List<CursoRest> listCursos();
    Curso retornarPorNome(String curso);
}
