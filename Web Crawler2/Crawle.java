package webcrawler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

/**
 *
 * @author Erick
 */
public class Crawle implements Runnable{    //implementa Runnable para usar as threads
    String url;
    int prof;
    public Crawle(String url, int prof) {
        this.url=url;
        this.prof=prof;
    }

    

    @Override
    public void run() {
        StringBuffer objBuffer = new StringBuffer("");

        try {
            //Biblioteca URL usada apenas para separar host e path, para não usar tanta memória com contains
            URL objURL = new URL(url);

            String host = objURL.getHost();

            String path = objURL.getPath();

            if (path.length() == 0) {

                path = "/";

            }


            InetAddress r = InetAddress.getByName(host);
            Socket s = new Socket(r, 80);           //cria o socket
            OutputStream os = s.getOutputStream();  
            PrintWriter writer = new PrintWriter(os, true); //cria writer para comunicação com o servidor
            String a = r.getHostName();
            writer.println("GET "+path+" HTTP/1.0");
            writer.println("Host: " + a);
            writer.println("Connection: close");
            writer.println();

            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader instream = new BufferedReader(isr);  //Resposta do servidor
            while (true) {
                String line = instream.readLine(); //pega uma linha do html uma linha
                if (line == null) {
                    break; // Terminou de ler o documento 
                }
                if (prof != 0) {
                    if (line.contains("<a")) {      //Se contém <a na linha
                        line = line.substring(line.indexOf("<a"));      //cria uma substring a partir de <a
                        if (line.contains("http://")) {         //continua se contém http://
                            line = line.substring(line.indexOf("http://"));     //cria uma substring a partir de http://
                            
                                String link = line.substring(0, line.indexOf("\""));    //pega o link da linha
                                System.out.println(link);                               //imprime o link
                                if (prof > 0) {
                                    Crawle urlCrawle = new Crawle(link, prof - 1);  //cria um novo crawle
                                    Thread tnew = new Thread(urlCrawle);
                                    tnew.start();                       //inicia a thread
                                }
                            

                        }
                    }
                }
                //System.out.println(line);
            }

            s.close();              //fecha o socket



        } catch (Exception ex) {
        }


        //System.out.println(objBuffer.toString());
    }

}
