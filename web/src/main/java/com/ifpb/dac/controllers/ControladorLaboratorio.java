package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.LaboratorioDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorLaboratorio implements Serializable {
    
    @Inject
    private HorariosDao hDao;
    @Inject
    private LaboratorioDao lDao;
    private String valorSelect;
    private boolean visualizar = false;
    private List<HorariosDTO> horario = new ArrayList<>();
    private List<String> laboratorios = new ArrayList<>();

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public List<HorariosDTO> getHorario() {
        return hDao.listarHorarioLab(valorSelect);
    }

    public void setHorario(List<HorariosDTO> horario) {
        this.horario = horario;
    }

    public List<String> getLaboratorios() {
        return lDao.listarNomeLaboratorios();
    }

    public void setLaboratorios(List<String> laboratorios) {
        this.laboratorios = laboratorios;
    }
    
    public String visualizarHorarios(){
        visualizar = true;
//        List<HorariosDTO> horarios = hDao.listarHorarioLab(valorSelect);
//        setHorario(horarios);
        return null;
    }
    
}
