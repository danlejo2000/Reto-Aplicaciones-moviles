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


public class fragment_tent extends Fragment {


    int[] imagen= {R.drawable.dxd,R.drawable.txd,R.drawable.txt};
    String TAG ="fragment_tent";
    View v;
    int i=0;

    ListView listaProductosCarpas;
    Adapter adapter;
    DBMotorBase conectar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_tent, container, false);

        listaProductosCarpas= (ListView)v.findViewById(R.id.lista_productos_carpas);
        adapter= new Adapter(getTablaProductos(),getContext());
        listaProductosCarpas.setAdapter(adapter);

        return v;


    }

    private ArrayList<Entidad> getTablaProductos()
    {
        ArrayList<Entidad> lista_Productos_Carpas = new ArrayList<>();
        conectar= new DBMotorBase(getContext(),"TiendaProductos",null,1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        Cursor cursor = db_leer.rawQuery("SELECT * FROM productos_carpas", null);

        Log.v(TAG, "leyendo bas de datos");

        while(cursor.moveToNext()){
            Log.v(TAG, "dentro de while");
            lista_Productos_Carpas.add(new Entidad(imagen[i], cursor.getString(0), cursor.getString(1)));
            Log.v(TAG, "despues del while");
            i++;
        }


        return lista_Productos_Carpas;
    }

    private ArrayList<Entidad> GetListItems()
    {
        ArrayList<Entidad> listaitems= new ArrayList<>();
        listaitems.add(new Entidad(R.drawable.dxd,"Carpa 2x2", "aqui va la descripcion de carpa 1"));
        listaitems.add(new Entidad(R.drawable.txd,"Carpa 3x2", "aqui va la descripcion de carpa 2"));
        listaitems.add(new Entidad(R.drawable.txt,"Carpa 3x3", "aqui va la descripcion de carpa 3"));

        return listaitems;

    }
}