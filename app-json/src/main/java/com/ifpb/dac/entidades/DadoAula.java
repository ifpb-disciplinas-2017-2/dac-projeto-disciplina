
package com.ifpb.dac.entidades;

import java.util.List;

/**
 *
 * @author rodrigobento
 */
public class DadoAula {
   
    private int code;
    private int status;
    private List<Aula> data;

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

    public List<Aula> getData() {
        return data;
    }

    public void setData(List<Aula> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DadoAula{" + "code=" + code + ", status=" + status + ", data=" + data + '}';
    }
    
}
