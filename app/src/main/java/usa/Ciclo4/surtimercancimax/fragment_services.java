package usa.Ciclo4.surtimercancimax;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment_services extends Fragment {
    View v;
    Button btnCumpleaños;
    Button btnPrimeraComunion;
    Button btnBodas;

    public fragment_services() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_services, container, false);

        btnCumpleaños= (Button) v.findViewById(R.id.btnExplorarCumpleanios);
        btnPrimeraComunion= (Button) v.findViewById(R.id.btnExplorarPrimComunion);
        btnBodas=(Button) v.findViewById(R.id.btnExplorarBodas);
        btnCumpleaños.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_birthday = new fragment_birthday();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, fragment_birthday);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        btnPrimeraComunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_fc = new fragment_first_communion();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, fragment_fc);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });


        btnBodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_m = new fragment_marriage();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, fragment_m);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return v;
    }


}
