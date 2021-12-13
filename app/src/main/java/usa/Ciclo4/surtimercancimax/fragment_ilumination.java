package usa.Ciclo4.surtimercancimax;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class fragment_ilumination extends Fragment {
    int[] imagen= {R.drawable.llaser,R.drawable.ldiscoteca,R.drawable.lcadena};
    String TAG ="fragment_ilumination";
    View v;
    int i=0;

    ListView listaProductosIluminacion;
    Adapter adapter;
    DBMotorBase conectar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_ilumination, container, false);

        listaProductosIluminacion= (ListView)v.findViewById(R.id.lista_productos_iluminacion);
        adapter= new Adapter(getTablaProductos(),getContext());
        listaProductosIluminacion.setAdapter(adapter);


        return v;


    }

    private ArrayList<Entidad> getTablaProductos()
    {
        ArrayList<Entidad> lista_Productos_Iluminacion = new ArrayList<>();
        conectar= new DBMotorBase(getContext(),"TiendaProductos",null,1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        Cursor cursor = db_leer.rawQuery("SELECT * FROM productos_luces", null);

        Log.v(TAG, "leyendo bas de datos");

        while(cursor.moveToNext()){
            Log.v(TAG, "dentro de while");
            lista_Productos_Iluminacion.add(new Entidad(cursor.getInt(0), cursor.getString(0), cursor.getString(1)));
            Log.v(TAG, "despues del while");
            i++;
        }


        return lista_Productos_Iluminacion;
    }

    private ArrayList<Entidad> GetListItems()
    {
        ArrayList<Entidad> listaitems= new ArrayList<>();
        listaitems.add(new Entidad(R.drawable.llaser,"Luces laser", "aqui va la descripcion de luz laser"));
        listaitems.add(new Entidad(R.drawable.ldiscoteca,"Luces Discoteca", "aqui va la descripcion de luz discoteca"));
        listaitems.add(new Entidad(R.drawable.lcadena,"Luces laser", "aqui va la descripcion de luz cadena"));

        return listaitems;

    }
}