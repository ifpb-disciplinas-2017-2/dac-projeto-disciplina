package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.ProfessorDao;
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
public class ControladorProfessor implements Serializable {
    
    @Inject
    private HorariosDao hDao;
    @Inject
    private ProfessorDao pDao;
    private String valorSelect;
    private boolean visualizar = false;
    private List<HorariosDTO> horario = new ArrayList<>();
    private List<String> professores = new ArrayList<>();

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
        return hDao.listarHorarioProf(valorSelect);
    }

    public void setHorario(List<HorariosDTO> horarios) {
        this.horario = horarios;
    }

    public List<String> getProfessores() {
        return pDao.listarNomeProfessores();
    }

    public void setProfessores(List<String> professores) {
        this.professores = professores;
    }
    
    public String visualizarHorarios(){
        visualizar = true;
//        List<HorariosDTO> lista = (hDao.listarHorarioProf(valorSelect));
//        for (HorariosDTO h: lista){
//            System.out.println("Dia: " + h.getDia());
//            System.out.println("Inicio: " + h.getValorInicio());
//            System.out.println("Fim: " + h.getValorFim());
//            System.out.println("Sala: " + h.getDescricaoSala());
//            System.out.println("Laboratorio: " + h.getDescricaoLab());
//            System.out.println("Local: " + h.getLocal());
//        }
        return null;
    }
    
}
