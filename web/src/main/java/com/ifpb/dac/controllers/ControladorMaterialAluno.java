package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Material;
import com.ifpb.dac.interfaces.DropboxAPI;
import com.ifpb.dac.interfaces.MaterialDao;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodrigobento
 */
@RequestScoped
@Named
public class ControladorMaterialAluno {
    
    @Inject
    private MaterialDao mDao;
    @Inject
    private DropboxAPI dropbox;
    private List<Material> materiaisAluno = new ArrayList<>();
    private Aluno aluno = new Aluno();
    private HttpSession sessao;
    
    
    @PostConstruct
    public void init() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        aluno = (Aluno) sessao.getAttribute("aluno");
    }

    public List<Material> getMateriaisAluno() {
        return mDao.materiaisAluno(aluno.getId());
    }

    public void setMateriaisAluno(List<Material> materiaisAluno) {
        this.materiaisAluno = materiaisAluno;
    }
    
    public String downloadArquivo(Material m) throws FileNotFoundException{
        String link = dropbox.link("/"+m.getNomeArquivo());
        return link;
    }
    
}
