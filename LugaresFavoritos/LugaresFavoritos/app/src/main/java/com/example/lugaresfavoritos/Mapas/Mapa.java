package com.example.lugaresfavoritos.Mapas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lugaresfavoritos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {


    GoogleMap mMap;
    LatLng ubicacionsend;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng miUbicacion = new LatLng(-3.985649, -79.236195);
        //mMap.addMarker(new MarkerOptions().position(miUbicacion).title("Mi ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
            Toast.makeText(this, "Manten pulsado para agregar una ubicacion", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        mMap.clear();
        LatLng UbiSitio = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(UbiSitio).title("ESTA ES LA Ubi?"));
        Toast.makeText(this,"Latitud: "+latLng.latitude +" \n Longitud: "+latLng.longitude, Toast.LENGTH_SHORT).show();
        ubicacionsend= latLng;
    }

    public void  CapturarEstaUbicacion(View V){

        if (ubicacionsend != null) {
            mostrarDialogoConfirmacion();
        }else {
            Toast.makeText(this, "Selecciona una ubicación primero", Toast.LENGTH_SHORT).show();
        }
    }
    private void mostrarDialogoConfirmacion() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("¿Estás seguro de guardar esta ubicación?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Envía los datos de ubicación a la actividad principal
                if (ubicacionsend != null) {
                    Intent intent = new Intent();
                    intent.putExtra("key latitud", ubicacionsend.latitude);
                    intent.putExtra("key longitud", ubicacionsend.longitude);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}