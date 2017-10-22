
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Laboratorio;
import com.ifpb.dac.interfaces.LaboratorioDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@SessionScoped
public class ControladorCRUDLab implements Serializable {
    
    @Inject
    private LaboratorioDao labDao;
    private Laboratorio laboratorio = new Laboratorio();
    private List<Laboratorio> laboratorios = new ArrayList<>();
    private boolean editando = false;

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public List<Laboratorio> getLaboratorios() {
        return labDao.listarTodos();
    }

    public void setLaboratorios(List<Laboratorio> laboratorios) {
        this.laboratorios = laboratorios;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    
    public String cadastrar(){
        labDao.adicionar(laboratorio);
        laboratorio = new Laboratorio();
        return null;
    }
    
    public String editar(Laboratorio lab){
        editando = true;
        laboratorio = lab;
        laboratorio.setDescricao(lab.getDescricao());
        return null;
    }
    
    public String atualizar(){
        labDao.atualizar(laboratorio);
        laboratorio = new Laboratorio();
        editando = false;
        return null;
    }
    
    public String remover(Laboratorio lab){
        labDao.remover(lab);
        return null;
    }
    
    
}
