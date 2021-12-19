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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class fragment_furniture extends Fragment {

    int[] imagen= {R.drawable.mesabanquetera,R.drawable.sillafiesta};
    String TAG ="fragment_furniture";
    View v;
    int i=0;

    ListView listaProductosMuebles;
    Adapter adapter;
    //DBMotorBase conectar;
    TextView prueba;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_furniture, container, false);

        listaProductosMuebles= (ListView)v.findViewById(R.id.lista_productos_muebleria);
        adapter= new Adapter(getTablaProductos(),getContext());
        listaProductosMuebles.setAdapter(adapter);
        prueba = (TextView) v.findViewById(R.id.prueba2);
        return v;
    }


    private ArrayList<Entidad> getTablaProductos()
    {
        ArrayList<Entidad> listaProductosMuebles = new ArrayList<>();

        String url="https://g3c9316d4414cae-db202112170848.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/surtimercancimax/productos_Muebles";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //***********************************************************
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String titulo = jsonObject.getString("titulo");
                        String descripcion = jsonObject.getString("descripcion");

                        listaProductosMuebles.add(new Entidad(imagen[i], titulo, descripcion));
                        prueba.append(titulo + '\n');

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //***********************************************************
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        /* ================================================================================================== */

        return listaProductosMuebles;
    }

/*
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

    }*/
}