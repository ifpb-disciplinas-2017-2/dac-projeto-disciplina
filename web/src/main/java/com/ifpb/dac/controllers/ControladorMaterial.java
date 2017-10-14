package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Material;
import com.ifpb.dac.interfaces.Dropbox;
import com.ifpb.dac.interfaces.MaterialDao;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorMaterial {

    @Inject
    private Dropbox drop;
    @Inject
    private MaterialDao mDao;
    private Part arquivo;
    private Material material = new Material();
    private List<Material> materiais = new ArrayList<>();

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

    public List<Material> getMateriais() {
        return mDao.listarTodos();
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }

    public String upload() {
        try {
            material.setNomeArquivo(arquivo.getSubmittedFileName());
            material.setArquivo(convertByteArray(arquivo.getInputStream()));
            drop.uploadArquivo(material);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        material = new Material();
        return null;
    }
    
    public String removerMaterial(Material m){
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
