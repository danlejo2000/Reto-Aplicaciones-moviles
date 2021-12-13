package usa.Ciclo4.surtimercancimax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {

    ArrayList<Entidad> lista_items;
    Context context;
    DBMotorBase conectar;


    public Adapter(ArrayList<Entidad> lista_items, Context context) {
        this.lista_items = lista_items;
        this.context = context;
    }



    @Override
    public int getCount() {

        return lista_items.size();
    }

    @Override
    public Object getItem(int position) {

        return lista_items.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup viewGroup) {


        Entidad datosItem = (Entidad) getItem(position);

        v = LayoutInflater.from(context).inflate(R.layout.item, null);
        //-------------------------------------------------------------------

        ImageView imagen = (ImageView)v.findViewById(R.id.imagen1_item);
        TextView titulo = (TextView)v.findViewById(R.id.titulo_item);
        TextView descripcion = (TextView)v.findViewById(R.id.descripcion_item);

        conectar = new DBMotorBase(context,"Productos", null, 1);
        SQLiteDatabase db_escribir = conectar.getWritableDatabase();
        conectar.onUpgrade(db_escribir, 1, 2);

        Button boton1 = (Button) v.findViewById(R.id.boton1_item);
        Button favoritos = (Button) v.findViewById(R.id.favoritos_item);

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Guardado en favoritos", Toast.LENGTH_LONG).show();
                db_escribir.execSQL("INSERT INTO favoritos VALUES (1, '"+datosItem.getTitulo()+"', '"+datosItem.getTitulo()+"')");


                //db_escribir.execSQL("INSERT INTO favoritos VALUES ('datosItem.getImagen()','datosItem.getTitulo()','datosItem.getDescripcion()')");

            }
        });





        /*
        Pongo los datos de cada item desde la clase Entidad dentro de cada elemento xml
         */
        imagen.setImageResource(datosItem.getImagen());
        titulo.setText(datosItem.getTitulo());
        descripcion.setText(datosItem.getDescripcion());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "item:" + titulo.getText(), Toast.LENGTH_LONG ).show();
            }
        });

        //-------------------------------------------------------------------
        return v;
    }
}
