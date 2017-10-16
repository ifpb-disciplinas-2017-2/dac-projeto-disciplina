package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

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
    private HttpSession sessao;
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
        return null;
    }
    
}
