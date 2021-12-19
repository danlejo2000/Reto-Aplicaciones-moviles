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


public class fragment_tent extends Fragment {


    int[] imagen= {R.drawable.dxd,R.drawable.txd,R.drawable.txt};
    String TAG ="fragment_tent";
    View v;

    ListView lista_Productos_Carpas;
    Adapter adapter;
    TextView prueba;
    //DBMotorBase conectar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_tent, container, false);

        lista_Productos_Carpas= (ListView)v.findViewById(R.id.lista_productos_carpas);
        adapter= new Adapter(getTablaProductos(),getContext());
        lista_Productos_Carpas.setAdapter(adapter);
        prueba = (TextView) v.findViewById(R.id.prueba);
        return v;


    }

    private ArrayList<Entidad> getTablaProductos()
    {
        ArrayList<Entidad> listaProductosCarpas = new ArrayList<>();

        String url="https://g3c9316d4414cae-db202112170848.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/surtimercancimax/productos_carpas";

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

                        listaProductosCarpas.add(new Entidad(imagen[i], titulo, descripcion));
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

        return listaProductosCarpas;
    }
}
/*conectar= new DBMotorBase(getContext(),"TiendaProductos",null,1);
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

    }*/
