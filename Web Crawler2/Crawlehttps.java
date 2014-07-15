/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Erick
 */
public class Crawlehttps implements Runnable {  //implementa runnable para uso das threads

    String url;
    int prof;

    public Crawlehttps(String url, int prof) {
        this.url = url;
        this.prof = prof;
    }

    @Override
    public void run() {
        try {
            URL end = new URL(url);
            HttpsURLConnection url2 = (HttpsURLConnection) end.openConnection();    //abre conexão com o site https
            InputStream is =  url2.getInputStream();                                //recebe o html em binario
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader instream = new BufferedReader(isr);                      //usado para passar pra string
            while (true) {
                String line = instream.readLine();                                  //recebe a linha do html
                if (line == null) {
                    break; // Terminou de ler o documento 
                }
                if (prof != 0) {
                    if (line.contains("<a")) {
                        line = line.substring(line.indexOf("<a"));
                        if (line.contains("http://")) {
                            line = line.substring(line.indexOf("http://"));
                            
                                String link = line.substring(0, line.indexOf("\""));  //pega o link da linha
                                System.out.println(link);
                                if (prof > 0) {
                                    Crawle urlCrawle = new Crawle(link, prof - 1);    //cria uma nova thread
                                    Thread tnew = new Thread(urlCrawle);
                                    tnew.start();                                       //inicia a thread
                                }
                            

                        }else if(line.contains("https://")){
                            line = line.substring(line.indexOf("https://"));                            
                                String link = line.substring(0, line.indexOf("\""));
                                System.out.println(link);
                                if (prof > 0) {
                                    Crawle urlCrawle = new Crawle(link, prof - 1);
                                    Thread tnew = new Thread(urlCrawle);
                                    tnew.start();
                                }
                        }
                    }
                }
                
            }
            is.close();  //fecha a conexão
        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawlehttps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crawlehttps.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
