package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Coordenador;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author lyndemberg
 */

@Named
@SessionScoped
public class ControladorCoordenador implements Serializable{
    @Inject
    private PedidoDao pedidoDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private AlunoDao alunoDao;
    
    
    private String filtroPedidos;
    
    public List<Pedido> getPedidos() {
        return pedidoDao.listarTodosFiltro(Tipo.valueOf(filtroPedidos));
    }
    public List<String> getItensFiltro(){
        return Arrays.asList("Professor", "Aluno");
    }
    public String filtrar(){
        return null;
    }

    public String liberarAcesso(Pedido p) {
        if (p.getTipo().equals(Tipo.Aluno)) {
            Aluno alunoLib = alunoDao.autentica(p.getEmail(), p.getSenha());
            if (alunoLib != null) {
                alunoLib.setLogado(true);
                alunoDao.atualizar(alunoLib);
                pedidoDao.remover(p);
            }
        }else{
            Professor prof = professorDao.autentica(p.getEmail(), p.getSenha());
            if (prof != null) {
                prof.setLogado(true);
                professorDao.atualizar(prof);
                pedidoDao.remover(p);
            }
        }        
        return null;
    }

    public String getFiltroPedidos() {
        return filtroPedidos;
    }

    public void setFiltroPedidos(String filtroPedidos) {
        this.filtroPedidos = filtroPedidos;
    }
    
    
    
}
