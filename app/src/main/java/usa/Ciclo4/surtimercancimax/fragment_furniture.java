package usa.Ciclo4.surtimercancimax;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class fragment_furniture extends Fragment {

    int[] imagen= {R.drawable.mesabanquetera,R.drawable.sillafiesta};
    String TAG ="fragment_furniture";
    View v;
    int i=0;

    ListView listaProductosMuebles;
    Adapter adapter;
    DBMotorBase conectar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_furniture, container, false);

        listaProductosMuebles= (ListView)v.findViewById(R.id.lista_productos_muebleria);
        adapter= new Adapter(getTablaProductos(),getContext());
        listaProductosMuebles.setAdapter(adapter);

        return v;
    }


    private ArrayList<Entidad> getTablaProductos()
    {
        ArrayList<Entidad> lista_Productos_muebleria = new ArrayList<>();
        conectar= new DBMotorBase(getContext(),"TiendaProductos",null,1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        Cursor cursor = db_leer.rawQuery("SELECT * FROM productos_Muebles", null);

        Log.v(TAG, "leyendo bas de datos");

        while(cursor.moveToNext()){
            Log.v(TAG, "dentro de while");
            lista_Productos_muebleria.add(new Entidad(imagen[i], cursor.getString(0), cursor.getString(1)));
            Log.v(TAG, "despues del while");
            i++;
        }


        return lista_Productos_muebleria;
    }

    private ArrayList<Entidad> GetListItems()
    {
        ArrayList<Entidad> listaitems= new ArrayList<>();
        listaitems.add(new Entidad(R.drawable.mesabanquetera,"Mesa banquetera", "aqui va la descripcion de mesa"));
        listaitems.add(new Entidad(R.drawable.sillafiesta,"Silla", "aqui va la descripcion de silla"));

        return listaitems;

    }
}