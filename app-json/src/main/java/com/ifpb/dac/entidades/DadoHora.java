
package com.ifpb.dac.entidades;

import java.util.List;

public class DadoHora {
    
    private int code;
    private int status;
    private List<Horario> data;

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

    public List<Horario> getData() {
        return data;
    }

    public void setData(List<Horario> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DadoHora{" + "code=" + code + ", status=" + status + ", data=" + data + '}';
    }
    
    
    
}
