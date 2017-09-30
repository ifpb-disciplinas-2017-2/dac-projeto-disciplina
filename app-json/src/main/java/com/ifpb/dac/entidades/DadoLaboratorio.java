
package com.ifpb.dac.entidades;

import java.util.List;

/**
 *
 * @author rodrigobento
 */
public class DadoLaboratorio {
    
    private int code;
    private int status;
    private List<Laboratorio> data;

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

    public List<Laboratorio> getData() {
        return data;
    }

    public void setData(List<Laboratorio> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DadoLaboratorio{" + "code=" + code + ", status=" + status + ", data=" + data + '}';
    }
    
}
