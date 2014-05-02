package br.usp.sistemadeinformacao.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Willian on 28/04/2014.
 */
public class HttpUtils {

    public static String acessar(String endereco){
        try{
            URL url = new URL(endereco);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String conteudo = scanner.useDelimiter("\\A").next();
            scanner.close();
            return conteudo;
        }catch (Exception ex){
            return null;
        }
    }

}
