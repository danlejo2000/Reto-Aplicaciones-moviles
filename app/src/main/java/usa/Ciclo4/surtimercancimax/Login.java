package usa.Ciclo4.surtimercancimax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    EditText user, pass;
    Button btniniciarsesion,btnregistrar;
    daouser dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user=(EditText) findViewById(R.id.txtNombreUsuario);
        pass=(EditText) findViewById(R.id.txtContrasenia);

        btniniciarsesion=(Button) findViewById(R.id.btniniciarsesion);
        btnregistrar=(Button) findViewById(R.id.btnregistrarse);

        dao=new daouser(this);

        btniniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u=user.getText().toString();
                String p=pass.getText().toString();

                if(u.equals("")&&p.equals(""))
                {
                    Toast.makeText(Login.this, "Complete todos los campos.", Toast.LENGTH_LONG).show();
                }
                else if(dao.login(u,p)==1)
                {
                    user ux=dao.getusuario(u,p);

                    Toast.makeText(Login.this, "Bienvenido "+u+".", Toast.LENGTH_LONG).show();
                    Notificacion();
                    Intent i= new Intent(getApplicationContext(), splash.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(Login.this, "Datos incorrectos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Login.this, Registro.class);
                startActivity(i);
            }
        });


    }
    private void Notificacion()
    {
       /* NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Festejando Juntos")
                .setContentText("Las ofertas han llegado :)");*/

       /* Intent NotificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(this,0,NotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);*/

        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=26)
        {
            String id="channel_1";
            String description = "143";
            int importance =NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel= new NotificationChannel(id,description,importance);
            //                     channel.enableLights(true);
//                     channel.enableVibration(true);
            manager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(Login.this, id)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Festejando Juntos")
                    .setContentText("Las ofertas han llegado :)")
                    .setAutoCancel(true)
                    .build();
            manager.notify(1, notification);
        }
        else
        {
            //When sdk version is less than26
            Notification notification = new NotificationCompat.Builder(Login.this)
                    .setContentTitle("Festejando Juntos")
                    .setContentText("Las ofertas han llegado :)")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
            manager.notify(1,notification);
        }


    }
}
