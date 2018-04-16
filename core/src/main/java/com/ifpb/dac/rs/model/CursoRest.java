
package com.ifpb.dac.rs.model;

import com.ifpb.dac.entidades.Info;
import java.io.Serializable;

/**
 *
 * @author lyndemberg
 */
public class CursoRest implements Serializable{
    private int id;
    private Info info;

    public CursoRest() {
    }

    public CursoRest(int id, Info info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
    
    
    
}
