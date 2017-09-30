
package com.ifpb.dac.entidades;

import java.util.List;

/**
 *
 * @author rodrigobento
 */
public class DadoDisciplina {
    
    private int code;
    private int status;
    private List<Disciplina> data;

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

    public List<Disciplina> getData() {
        return data;
    }

    public void setData(List<Disciplina> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DadoDisciplina{" + "code=" + code + ", status=" + status + ", data=" + data + '}';
    }
    
    
    
}
