package es.schooleando.ut3ejercicio2;

import android.content.Context;
import android.graphics.*;
import android.net.*;
import android.os.AsyncTask;

import java.io.*;
import java.net.*;



/**
 * Created by ruben on 18/11/16.
 */

public class DownloadURLTask extends AsyncTask<String,Integer,Bitmap> {

    private DownloadActivity clase;
    private String error;

    public DownloadURLTask(DownloadActivity clase){
        this.clase=clase;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        ConnectivityManager cm = (ConnectivityManager) clase.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            URL url= null;
            try {
                url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("HEAD");
                con.connect();

                if (con.getContentType().contains("image/")) {

                    int size = con.getContentLength();
                    InputStream in = url.openStream();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();

                    byte[] b = new byte[1024];

                    for (int i; (i = in.read(b)) != -1; ) {
                        out.write(b, 0, i);
                        if (size>0) {
                            publishProgress(out.size() * 100 / size);
                        }else{
                            publishProgress(i*-1);
                        }
                    }
                    bmp = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
                    out.close();
                    in.close();
                }else{
                    cancelar("No es una imagen");
                }

            } catch (Exception e) {
                cancelar("URL incorrecta"+e.getMessage());
            }

        } else {
            cancelar("No hay conexión");
        }
        return bmp;
    }

    private void cancelar(String mensaje){
        error = mensaje;
        cancel(true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap b) {
        clase.finDescarga(b);//fin de la descarga
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        clase.updateBar(values[0]);//actualización en onProgress de la barra y texto
    }

    @Override
    protected void onCancelled() {
        clase.cancelaDescarga(error);//cancelado de la descarga
    }
}
