package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Material;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.interfaces.Dropbox;
import com.ifpb.dac.interfaces.MaterialDao;
import com.ifpb.dac.interfaces.TurmaDao;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author rodrigobento
 */
@Named
@SessionScoped
public class ControladorMaterial implements Serializable {

    @Inject
    private Dropbox drop;
    @Inject
    private MaterialDao mDao;
    @Inject
    private TurmaDao tDao;
    private HttpSession sessao;
    private Usuario usuario = new Usuario();
    private Professor professor = new Professor();
    private Part arquivo;
    private Material material = new Material();
    private Turma turma;
    private List<Material> materiaisProf = new ArrayList<>();
    private List<String> disciplinasProfessores = new ArrayList<>();
    private boolean visualizar = false;
    private String valorSelect;

    @PostConstruct
    public void init() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        professor = (Professor) sessao.getAttribute("usuario");
    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Material> getMateriaisProf() {
        List<Material> materiaisProfessor = mDao.materiaisProfessor(professor.getNome());
        if (materiaisProfessor == null) {
            return new ArrayList<>();
        } else {
            return materiaisProfessor;
        }
    }

    public void setMateriaisProf(List<Material> materiaisProf) {
        this.materiaisProf = materiaisProf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<String> getDisciplinasProfessores() {
        return tDao.disciplinaProfessores(professor.getNome());
    }

    public void setDisciplinasProfessores(List<String> disciplinasProfessores) {
        this.disciplinasProfessores = disciplinasProfessores;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public void listar() {
        setMateriaisProf(mDao.materiaisProfessor(professor.getNome()));
        visualizar = true;
    }

    public String upload() {
        turma = tDao.retornarDiscProf(valorSelect, professor.getNome());
        if (turma == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Turma não encontrada", "Turma não encontrada");
            FacesContext.getCurrentInstance().addMessage("Material", msg);
        } else {
            try {
                material.setNomeArquivo(arquivo.getSubmittedFileName());
                material.setArquivo(convertByteArray(arquivo.getInputStream()));
                material.setTurma(turma);
                drop.uploadArquivo(material);
            } catch (IOException ex) {
                Logger.getLogger(ControladorMaterial.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        material = new Material();
        return null;
    }

    public String removerMaterial(Material m) {
        mDao.remover(m);
        drop.removerArquivo(m);
        return null;
    }

    private byte[] convertByteArray(InputStream in) {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        try {
            int n = in.read();
            while (n != -1) {
                saida.write(n);
                n = in.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return saida.toByteArray();
    }
}
