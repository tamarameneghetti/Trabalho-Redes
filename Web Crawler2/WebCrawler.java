
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int prof = Integer.parseInt(args[1]);
        String url=args[0];
        //String url = "https://www.google.com.br/";
        Crawle wc = new Crawle(url, prof);
        Thread tcrawler = new Thread(wc);
        tcrawler.start();

    }

}
