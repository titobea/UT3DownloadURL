import java.io.*;
import java.net.*;

public class DemoDownloadURL {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
        // TODO code application logic here
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Indique la URL ");
        String unaUrl = consola.readLine();

        try {
            URL url = new URL(unaUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            con.connect();

            int resCode = con.getResponseCode();
            System.out.println("Response Code " + resCode);

            int resSize = con.getContentLength();
            System.out.println("Resource Size " + resSize);

            if (resCode == 200) {
                
                BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                
                File f = new File(url.toString());                
                OutputStream output = new FileOutputStream(f.getName());
                
                byte[] b = new byte[4096];
                int n=-1;
                while((n=bis.read(b))!= -1){
                    output.write(b, 0, n);
                }
            } else {
                System.out.println("Error en respuesto al recurso " + resCode);
            }
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }

    }

}