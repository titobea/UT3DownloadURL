package es.schooleando.ut3ejercicio2;

import android.graphics.Bitmap;

/**
 * Created by tito_ on 29/12/2016.
 */

public interface MetodosDownloadURLTask {
    void updateBar(int progreso);

    void finDescarga(Bitmap bitmap);

    void cancelaDescarga(String mensaje);

}
