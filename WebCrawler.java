public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int prof = 1;
        String url = "http://windowsphonebrasil.com.br/";
        urlCrawle wc = new urlCrawle();
        wc.urlCrawle(url, prof);

    }

}