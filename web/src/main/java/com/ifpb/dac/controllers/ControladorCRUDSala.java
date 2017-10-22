package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.interfaces.SalaDao;
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
public class ControladorCRUDSala implements Serializable {
    
    @Inject
    private SalaDao salaDao; 
    private Sala sala = new Sala();
    private boolean editando = false;
    private List<Sala> salas = new ArrayList<>();

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public List<Sala> getSalas() {
        return salaDao.listarTodos();
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
    
    public String adicionar(){
        salaDao.adicionar(sala);
        sala = new Sala();
        return null;
    }
    
    public String editar(Sala s){
        editando = true;
        sala = s;
        return null;
    }
    
    public String atualizar(){
        salaDao.atualizar(sala);
        sala = new Sala();
        editando = false;
        return null;
    }
    
    public String remover(Sala s){
        salaDao.remover(s);
        return null;
    }
    
    
}
