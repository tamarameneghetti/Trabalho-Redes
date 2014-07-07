/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

/**
 *
 * @author Erick
 */
public class Crawle {

    public void urlCrawle(String url, int prof) {

        //this.insertCrawledUrl(url);

        StringBuffer objBuffer = new StringBuffer("");

        try {
            //Biblioteca URL usada apenas para separar host e path, para não usar tanta memória com contains
            URL objURL = new URL(url);

            String host = objURL.getHost();

            String path = objURL.getPath();

            if (path.length() == 0) {

                path = "/";

            }

            String outQuery = "GET "+path+" HTTP/1.1\n";

            InetAddress r = InetAddress.getByName(host);
            Socket s = new Socket(r, 80);
            OutputStream os = s.getOutputStream();
            PrintWriter writer = new PrintWriter(os, true);
            String a = r.getHostName();
            writer.println("GET / HTTP/1.0");
            writer.println("Host: " + a);
            writer.println("Connection: close");
            writer.println();

            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader instream = new BufferedReader(isr);
            while (true) {
                String line = instream.readLine();
                if (line == null) {
                    break; // Terminou de ler o documento 
                }
                
                System.out.println(line);
            }

            s.close();



        } catch (Exception ex) {
        }


        //System.out.println(objBuffer.toString());


    }

    
}
