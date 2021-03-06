package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.SalaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorSala implements Serializable {

    @Inject
    private HorariosDao hDao;
    @Inject
    private SalaDao sDao;
    private String valorSelect;
    private boolean visualizar = false;
    private boolean msg = false;
    private List<HorariosDTO> horario = new ArrayList<>();
    private List<String> salas = new ArrayList<>();

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizarHorarios) {
        this.visualizar = visualizarHorarios;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public List<HorariosDTO> getHorario() {
        return hDao.listarHorarioSala(valorSelect);
    }

    public void setHorario(List<HorariosDTO> horario) {
        this.horario = horario;
    }

    public List<String> getSalas() {
        return sDao.listarNomeSalas();
    }

    public void setSalas(List<String> salas) {
        this.salas = salas;
    }

    public String visualizarHorarios() {
        if(getHorario().isEmpty()){
            msg = true;
        }
        if(getHorario() == null){
            msg = true;
        }
        visualizar = true;
        return null;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

}
