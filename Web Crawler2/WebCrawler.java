/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




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
        int prof = Integer.parseInt(args[1]);
        String url=args[0];
        if(url.contains("http://")){
            Crawle wc = new Crawle(url, prof);
            Thread tcrawler = new Thread(wc);
            tcrawler.start();
        }else if(url.contains("https://")){
            Crawlehttps wc = new Crawlehttps(url, prof);
            Thread tcrawler = new Thread(wc);
            tcrawler.start();
        }
        

    }

}
