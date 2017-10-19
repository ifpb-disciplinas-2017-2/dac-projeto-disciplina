package com.ifpb.dac.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.ifpb.dac.entidades.Material;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void downloadArquivo(Material material) {
        FileOutputStream downloadFile = null;
        try {
            DbxClientV2 cliente = cliente();
            String caminho = System.getProperty("user.home");
            System.out.println(caminho);
//            downloadFile = new FileOutputStream(caminho + "/Downloads/" + 
//                    material.getNomeArquivo());
            downloadFile = new FileOutputStream(caminho + "/" +material.getNomeArquivo());
            cliente.files().download(material.getId()).download(downloadFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (DbxException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                downloadFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
