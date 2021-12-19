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

public class fragment_first_communion extends Fragment {


    int[]imagen={R.drawable.fcbasico,R.drawable.bpro};
    String TAG ="fragment_first_communion";
    View v;

    ListView listaServiciosPC;
    Adapter adapter;
    //DBMotorBase conectar;
    TextView prueba;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_first_communion, container, false);

        listaServiciosPC= (ListView)v.findViewById(R.id.lista_servicios_Prim_com);
        adapter= new Adapter(getTablaServicios(),getContext());
        listaServiciosPC.setAdapter(adapter);
        prueba = (TextView) v.findViewById(R.id.prueba5);
        return v;
    }


    private ArrayList<Entidad> getTablaServicios() {
        ArrayList<Entidad> lista_Servicios_PC = new ArrayList<>();

        String url = "https://g3c9316d4414cae-db202112170848.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/surtimercancimax/servicios_Primeras_C";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //***********************************************************
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String titulo = jsonObject.getString("titulo");
                        String descripcion = jsonObject.getString("descripcion");

                        lista_Servicios_PC.add(new Entidad(imagen[i], titulo, descripcion));
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

        return lista_Servicios_PC;
    }
/*
    private ArrayList<Entidad> getTablaServicios()
    {
        ArrayList<Entidad> lista_Servicios_PC = new ArrayList<>();
        conectar= new DBMotorBase(getContext(),"TiendaServicios",null,1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        Cursor cursor = db_leer.rawQuery("SELECT * FROM servicios_Primeras_C", null);

        Log.v(TAG, "leyendo bas de datos");

        while(cursor.moveToNext()){
            Log.v(TAG, "dentro de while");
            lista_Servicios_PC.add(new Entidad(imagen[i], cursor.getString(0), cursor.getString(1)));
            Log.v(TAG, "despues del while");
            i++;
        }


        return lista_Servicios_PC;
    }

    private ArrayList<Entidad> GetListItems()
    {
        ArrayList<Entidad> listaitems= new ArrayList<>();
        listaitems.add(new Entidad(R.drawable.fcbasico,"Paquete Basico", "aqui va la descripcion de Paquete B"));
        listaitems.add(new Entidad(R.drawable.bpro,"Paquete Pro", "aqui va la descripcion de Paquete P"));

        return listaitems;

    }*/
}