package com.ifpb.dac.dropbox;

import com.ifpb.dac.entidades.Material;

/**
 *
 * @author rodrigobento
 */
public interface DropboxAPI {
    
   void uploadArquivo(Material material);
   void removerArquivo(Material material);
   
}
