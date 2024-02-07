package com.example.lugaresfavoritos.Ventanas;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lugaresfavoritos.BaseDeDatos.Sitio;
import com.example.lugaresfavoritos.BaseDeDatos.SitioLab;
import com.example.lugaresfavoritos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.Random;

public class DetallesDelSitio extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap mMap;
    String nombreubi;
    Sitio sitio;
    EditText nombre, categoria;
    TextView descripcion;
    double longitud,latitud;
    ImageView logo;

    private SitioLab sitioLab;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_del_sitio);
        nombre=findViewById(R.id.editTextNombreVER);
        categoria=findViewById(R.id.editTextCategoriaVER);
        descripcion=findViewById(R.id.editTextDESCRIPCIONVER);
        logo=findViewById(R.id.imageViewVER);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        int id=extras.getInt("ID");
        String rutaimg = extras.getString("RUTA");

        sitioLab = new SitioLab(this);
        sitio=sitioLab.getSitio(String.valueOf(id));

        nombre.setText(sitio.getNombre());
        nombreubi= sitio.getNombre();
        categoria.setText(sitio.getCategoria());
        descripcion.setText(sitio.getDescripcion());

        latitud = sitio.getLatitud();
        longitud = sitio.getLongitud();

        Glide.with(this)
                .load(new File(rutaimg))
                .apply(RequestOptions.centerCropTransform()) // Opcional: transformación de la imagen
                .into(logo);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Random r = new Random();
        double xd = r.nextDouble();
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng miUbicacion = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(miUbicacion).title(nombreubi));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
    }

    public  void EliminarSitio(View view){
        mostrarDialogoConfirmacion();
    }


    private void mostrarDialogoConfirmacion() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("¿Estás seguro de borrar este sitio?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Bundle extras = getIntent().getExtras();
                int id=extras.getInt("ID");
                sitioLab = new SitioLab(DetallesDelSitio.this);
                sitio=sitioLab.getSitio(String.valueOf(id));
                sitioLab.deleteSitio(sitio);
                Toast.makeText(DetallesDelSitio.this,"Eliminacion Completa ",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void SalirDetalles(View view) {
        finish();
    }
}