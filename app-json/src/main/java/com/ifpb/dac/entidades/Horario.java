package com.ifpb.dac.entidades;

public class Horario {
    
    private int hor_cod;
    private String hor_desc;
    private String hor_inicio;
    private String hor_fim;

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

    @Override
    public String toString() {
        return "Horario{" + "hor_cod=" + hor_cod + ", hor_desc=" + hor_desc + ", hor_inicio=" + hor_inicio + ", hor_fim=" + hor_fim + '}';
    }
    
}
