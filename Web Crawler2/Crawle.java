

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.jws.WebParam.Mode;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HandshakeCompletedEvent;
import java.security.cert.CertificateFactory;
import javax.net.ssl.SSLSession;


/**
 *
 * @author Erick
 */
public class Crawle implements Runnable{
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

            String outQuery = "GET " + path + " HTTP/1.1\n";

            InetAddress r = InetAddress.getByName(host);
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket s =  (SSLSocket) factory.createSocket(r, 80);
            HandshakeCompletedEvent go = new HandshakeCompletedEvent(s,s.getHandshakeSession());
            go.getPeerCertificateChain();
            OutputStream os = s.getOutputStream();
            PrintWriter writer = new PrintWriter(os, true);
            String a = r.getHostName();
            writer.println("GET "+path+" HTTP/1.0");
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
                if (prof != 0) {
                    if (line.contains("<a")) {
                        line = line.substring(line.indexOf("<a"));
                        if (line.contains("http://")) {
                            line = line.substring(line.indexOf("http://"));
                            
                                String link = line.substring(0, line.indexOf("\""));
                                System.out.println(link);
                                if (prof > 0) {
                                    Crawle urlCrawle = new Crawle(link, prof - 1);
                                    Thread tnew = new Thread(urlCrawle);
                                    tnew.start();
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
                //System.out.println(line);
            }

            s.close();



        } catch (Exception ex) {
        }


        //System.out.println(objBuffer.toString());
    }

}
