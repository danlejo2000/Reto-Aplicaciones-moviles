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

public class fragment_favorites extends Fragment {

    int[] imagen= {R.drawable.dxd,R.drawable.txd,R.drawable.txt,R.drawable.mesabanquetera,R.drawable.sillafiesta,R.drawable.llaser,R.drawable.ldiscoteca,R.drawable.lcadena};
    String TAG ="fragment_favorites";
    View v;
    int i=0;

    ListView ListaFavoritos;
    Adapter adapter;
    DBMotorBase conectar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_favorites, container, false);

        ListaFavoritos= (ListView)v.findViewById(R.id.lista_favoritos);
        adapter= new Adapter(getTablaProductos(),getContext());
        ListaFavoritos.setAdapter(adapter);


        return v;


    }

    private ArrayList<Entidad> getTablaProductos()
    {
        ArrayList<Entidad> lista_Favoritos = new ArrayList<>();
        conectar= new DBMotorBase(getContext(),"TiendaProductos",null,1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        Cursor cursor = db_leer.rawQuery("SELECT * FROM favoritos", null);

        Log.v(TAG, "leyendo bas de datos");

        while(cursor.moveToNext()){
            Log.v(TAG, "dentro de while");
            lista_Favoritos.add(new Entidad(imagen[i], cursor.getString(0), cursor.getString(1)));
            Log.v(TAG, "despues del while");
            i++;
        }


        return lista_Favoritos;
    }


}