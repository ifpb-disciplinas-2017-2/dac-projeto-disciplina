package com.ifpb.dac.dropbox;

import com.ifpb.dac.interfaces.DropboxAPI;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.ifpb.dac.entidades.Material;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(DropboxAPI.class)
public class DropboxAPIImpl implements DropboxAPI {

    private static DbxClientV2 cliente() {
        DbxRequestConfig configuracoes = new DbxRequestConfig(
                "Projeto-DAC");
        DbxClientV2 cliente = new DbxClientV2(configuracoes,
                "h9TYlOFcV2AAAAAAAAAACFyLSZIZaaEoE9qWWLxHT10MATSYT4rcwie5GeyjRACP");
        return cliente;
    }

    @Override
    public String uploadArquivo(Material material) {
        DbxClientV2 cliente = cliente();
        InputStream leitorArquivo = new ByteArrayInputStream(material.getArquivo());
        try {
            FileMetadata arquivoUpload = cliente.files().
                    upload("/" + material.getNomeArquivo()).uploadAndFinish(leitorArquivo);
            return arquivoUpload.getId();
        } catch (DbxException | IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void removerArquivo(Material material) {
        DbxClientV2 cliente = cliente();
        try {
//            cliente.files().deleteV2("/"+material.getNomeArquivo());
            cliente.files().deleteV2(material.getId());
        } catch (DbxException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String link(String path){
        DbxClientV2 cliente = cliente();
        try {
            return cliente.files().getTemporaryLink(path).getLink();
        } catch (DbxException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
