package com.ifpb.dac.entidades;

import java.util.List;

/**
 *
 * @author rodrigobento
 */
public class DadoCurso {

    private int code;
    private int status;
    private List<Curso> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Curso> getData() {
        return data;
    }

    public void setData(List<Curso> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DadoCurso{" + "code=" + code + ", status=" + status + ", data=" + data + '}';
    }
    
    
    
}
