package es.schooleando.ut3ejercicio2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class DownloadActivity extends AppCompatActivity implements MetodosDownloadURLTask{

    private ProgressBar pb;
    private EditText et;
    private TextView tv;
    private DownloadURLTask dut;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        //recoger variables del layout
        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        et = (EditText)findViewById(R.id.textURL);
        tv = (TextView)findViewById(R.id.barPercent);
        iv = (ImageView)findViewById(R.id.imageView);

    }

    public void btnDescargar(View view) {
        pb.setVisibility(View.VISIBLE);
        dut= new DownloadURLTask(this);
        dut.execute(et.getText().toString());
    }

    public void updateBar(int progreso){//función para actualizar la descarga
        pb.setIndeterminate(progreso<0);
        if (progreso>0) tv.setText(""+progreso+"%");
        pb.setProgress(progreso);
    }

    public void finDescarga(Bitmap bitmap){//función de fin de descarga
        pb.setVisibility(View.INVISIBLE);
        tv.setText("");
        iv.setImageBitmap(bitmap);
    }

    public void cancelaDescarga(String mensaje){//función para avisar que se ha cancelado
        pb.setVisibility(View.INVISIBLE);
        tv.setText("");
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
}
