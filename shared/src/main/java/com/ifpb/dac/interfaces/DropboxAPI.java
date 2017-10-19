package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Material;

/**
 *
 * @author rodrigobento
 */
public interface DropboxAPI {

    String uploadArquivo(Material material);

    void removerArquivo(Material material);
    
    String link(String path);

}
