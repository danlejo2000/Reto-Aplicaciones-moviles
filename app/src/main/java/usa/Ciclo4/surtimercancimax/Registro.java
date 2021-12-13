package usa.Ciclo4.surtimercancimax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText us, pass, nombre,apellido;
    Button btnReg,btnCancelar;
    daouser dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        us=(EditText) findViewById(R.id.txtUsuarioRegistro);
        pass=(EditText) findViewById(R.id.txtContraseniaRegistro);
        nombre=(EditText) findViewById(R.id.txtNombreRegistro);
        apellido=(EditText) findViewById(R.id.txtApellidoRegistro);

        btnReg=(Button) findViewById(R.id.btnRegistrarUsuario);
        btnCancelar=(Button) findViewById(R.id.btnCancelarRegistro);
        dao=new daouser(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user u=new user();
                u.setUsuario(us.getText().toString());
                u.setNombre(nombre.getText().toString());
                u.setApellidos(apellido.getText().toString());
                u.setClave(pass.getText().toString());

                if(!u.isNull())
                {
                    Toast.makeText(Registro.this, "Complete todos los campos.", Toast.LENGTH_LONG).show();
                }
                else if(dao.insertUser(u))
                {
                    Toast.makeText(Registro.this, "Registro existoso.", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Registro.this, Login.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(Registro.this, "Usuario Registrado anteriormente.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Registro.this, Login.class);
                startActivity(i);
            }
        });
    }
}