package usa.Ciclo4.surtimercancimax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    Fragment servicesf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragment_splash

        servicesf = new fragment_services();
        bottomNavigationView=findViewById(R.id.bottomnav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, servicesf).commit();
    }
    /*barra de navegacion inferior*/
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment= null;

            switch (item.getItemId())
            {
                case R.id.ItemServicios:
                    fragment=new fragment_services();
                    break;
                case R.id.ItemProducto:
                    fragment=new fragment_products();
                    break;
                case R.id.ItemSucursales:
                    fragment=new fragment_branch();
                    break;
                case R.id.ItemFavoritos:
                    fragment=new fragment_favorites();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fragment).commit();
            return true;
        }
    };
}