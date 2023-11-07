package com.example.tareaunidad2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    GoogleMap mMap;
    String[] sucursales = {"Pedro de Valdivia","Manuel Rodriguez"};

    AutoCompleteTextView autoCompleteTxt;

    ArrayAdapter<String> adapterSucursales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterSucursales = new ArrayAdapter<String>(this, R.layout.list_sucursales, sucursales);

        autoCompleteTxt.setAdapter(adapterSucursales);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(position == 0){
                    LatLng Arica = new LatLng(-38.7344998595073, -72.60197790354697);
                    mMap.addMarker(new MarkerOptions().position(Arica).title("Neumatex 1"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Arica));
                } else if (position == 1) {
                    LatLng Iquique = new LatLng(-38.73165431933473, -72.60373743264442);
                    mMap.addMarker(new MarkerOptions().position(Iquique).title("Neumatex 2"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Iquique));
                }
            }
        });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        LatLng Chile = new LatLng(-38.7344998595073, -72.60197790354697);
        mMap.addMarker(new MarkerOptions().position(Chile).title("Neumatex 1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Chile));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng){

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng){

    }

    public void goVistaMenu(View view){
        Intent vista = new Intent(this,Menu.class);
        startActivity(vista);
    }
}