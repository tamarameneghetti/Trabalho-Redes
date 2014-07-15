/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.util.Scanner;

/**
 *
 * @author Erick
 */
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int prof = Integer.parseInt(args[1]);   //Pega a profundidade dos argumentos
        String url = args[0];                     //Pega o endereço dos argumentos
        System.out.println("Deseja usar o Web Crawler versão 1 ou versão 2?[1 / 2]");
        Scanner entrada = new Scanner(System.in);   //Scanner para a leitura do valor
        String escolha = entrada.nextLine();        //Leitura
        while (true) {                              //While para se digitar um valor errado
            if ("1".equals(escolha)) {              //executa o web crawler 1
                Crawle wc = new Crawle(url, prof);
                Thread tcrawler = new Thread(wc);
                tcrawler.start();
                break;
            } else if ("2".equals(escolha)) {       //executa o web crawler 2
                Crawlehttps wc = new Crawlehttps(url, prof);
                Thread tcrawler = new Thread(wc);
                tcrawler.start();
                break;
            } else {
                System.out.println("Digite 1 ou 2");
            }
        }

    }
}
