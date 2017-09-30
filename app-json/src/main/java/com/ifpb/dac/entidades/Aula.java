package com.ifpb.dac.entidades;

public class Aula {
    
    private int id;
    private int dia_abrev;
    private String dia_desc;
    private int hor_cod;
    private String hor_desc;
    private String hor_inicio;
    private String hor_fim;
    private int cur_cod;
    private int disc_cod;
    private int tur_cod;
    private char tur_id;
    private int prof_cod;
    private int sala;
    private int laboratorio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDia_abrev() {
        return dia_abrev;
    }

    public void setDia_abrev(int dia_abrev) {
        this.dia_abrev = dia_abrev;
    }

    public int getCur_cod() {
        return cur_cod;
    }

    public void setCur_cod(int cur_cod) {
        this.cur_cod = cur_cod;
    }

    public String getDia_desc() {
        return dia_desc;
    }

    public void setDia_desc(String dia_desc) {
        this.dia_desc = dia_desc;
    }

    public int getHor_cod() {
        return hor_cod;
    }

    public void setHor_cod(int hor_cod) {
        this.hor_cod = hor_cod;
    }
    
    public String getHor_desc() {
        return hor_desc;
    }

    public void setHor_desc(String hor_desc) {
        this.hor_desc = hor_desc;
    }

    public String getHor_inicio() {
        return hor_inicio;
    }

    public void setHor_inicio(String hor_inicio) {
        this.hor_inicio = hor_inicio;
    }

    public String getHor_fim() {
        return hor_fim;
    }

    public void setHor_fim(String hor_fim) {
        this.hor_fim = hor_fim;
    }

    public char getTur_id() {
        return tur_id;
    }

    public void setTur_id(char tur_id) {
        this.tur_id = tur_id;
    }

    public int getDisc_cod() {
        return disc_cod;
    }

    public void setDisc_cod(int disc_cod) {
        this.disc_cod = disc_cod;
    }

    public int getTur_cod() {
        return tur_cod;
    }

    public void setTum_cod(int turm_cod) {
        this.tur_cod = turm_cod;
    }

    public int getProf_cod() {
        return prof_cod;
    }

    public void setProf_cod(int prof_cod) {
        this.prof_cod = prof_cod;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public int getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(int laboratorio) {
        this.laboratorio = laboratorio;
    }
    
}
