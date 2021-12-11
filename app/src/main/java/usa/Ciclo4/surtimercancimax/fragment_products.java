package usa.Ciclo4.surtimercancimax;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment_products extends Fragment {
    View v;
    Button btntent;
    Button btnfurniture;
    Button brnilumination;

    public fragment_products() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_products, container, false);
        btntent= (Button) v.findViewById(R.id.btnsmascarpa);
        btnfurniture= (Button) v.findViewById(R.id.btnsmasmobiliario);
        brnilumination= (Button) v.findViewById(R.id.btnsmasiluminacion);

        btntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_tent = new fragment_tent();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, fragment_tent);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        btnfurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_furniture = new fragment_furniture();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, fragment_furniture);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        brnilumination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_ilumination = new fragment_ilumination();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, fragment_ilumination);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return v;
    }
}