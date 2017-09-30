package com.ifpb.dac.entidades;

import java.util.List;

/**
 *
 * @author rodrigobento
 */
public class DadoProfessor {
    
    private int code;
    private int status;
    private List<Professor> data;

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

    public List<Professor> getData() {
        return data;
    }

    public void setData(List<Professor> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DadoProfessor{" + "code=" + code + ", status=" + status + ", data=" + data + '}';
    }
    
}
