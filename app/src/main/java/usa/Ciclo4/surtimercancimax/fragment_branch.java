package usa.Ciclo4.surtimercancimax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;
import org.osmdroid.library.BuildConfig;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import java.util.ArrayList;

public class fragment_branch extends Fragment {

    View v;
    private MapView myOpenMapView;
    private MapController myMapController;

    GeoPoint Bogota, PatioBonito,Carvajal,Marsella,NuevaCandelaria;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_branch, container, false);


        myOpenMapView = (MapView) v.findViewById(R.id.openmapview);


        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);


        Bogota = new GeoPoint(4.6351, -74.0703);
        PatioBonito = new GeoPoint(4.6466418130225895, -74.16866185526897);
        Carvajal = new GeoPoint(4.615302, -74.133052);
        Marsella = new GeoPoint(4.630505, -74.130272);
        NuevaCandelaria = new GeoPoint(4.578111, -74.157764);

        myOpenMapView.setBuiltInZoomControls(true);

        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(Bogota);
        myMapController.setZoom(6);

        myOpenMapView.setMultiTouchControls(true);


        final MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), myOpenMapView);
        myOpenMapView.getOverlays().add(myLocationoverlay);
        myLocationoverlay.enableMyLocation();

        myLocationoverlay.runOnFirstFix(new Runnable() {
            public void run() {
                myMapController.animateTo(myLocationoverlay.getMyLocation());
            }
        });

        ArrayList<OverlayItem> puntos = new ArrayList<OverlayItem>();
        //puntos.add(new OverlayItem("Bogota", "Capital de Colombia", Bogota));
        puntos.add(new OverlayItem("Patio Bonito", "Tienda 1", PatioBonito));
        puntos.add(new OverlayItem("Carvajal","Tienda 2",Carvajal));
        puntos.add(new OverlayItem("Marsella","Tienda 3",Marsella));
        puntos.add(new OverlayItem("Nueva Candelaria","Tienda 4",NuevaCandelaria));


        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                return false;
            }
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }
        };

        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<OverlayItem>(getContext(), puntos, tap);
        capa.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);



        return v;
    }
}