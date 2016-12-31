package mx.grupogarcia.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetalleContactp extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView TvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contactp);

        //Recibimos los parametros
        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString(getResources().getString(R.string.Pnombre));
        String telefono = parametros.getString(getResources().getString(R.string.Ptelefono));
        String email = parametros.getString(getResources().getString(R.string.Pemail));
        //Aqui ya cachamos los datos

        /*
        TextView tvNombre =(TextView) findViewById(R.id.tvNombre);
        TextView tvTelefono =(TextView) findViewById(R.id.tvtelefono);
        TextView TvEmail =(TextView) findViewById(R.id.tvEmail);
        */
        //Vamos a comvertir los textview de arriba como variables globales
        //Ahora ya con el cambio mencionado queda como la parte de abajo

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvtelefono);
        TvEmail = (TextView) findViewById(R.id.tvEmail);

        //ya son valores accesibles

        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        TvEmail.setText(email);


    }

    //Nunca olvides este corchete
    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();
        //intent implicito
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel" + telefono)));
        //Aqui acaba el intent implicito

    }
    public void enviarMail(View v){
        String email= TvEmail.getText().toString();
        Intent emailIntent= new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto: "));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"E-mail"));

    }
}

