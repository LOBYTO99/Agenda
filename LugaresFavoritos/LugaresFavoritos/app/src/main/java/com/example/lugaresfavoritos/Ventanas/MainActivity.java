package com.example.lugaresfavoritos.Ventanas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lugaresfavoritos.BaseDeDatos.Sitio;
import com.example.lugaresfavoritos.BaseDeDatos.SitioLab;
import com.example.lugaresfavoritos.R;
import com.example.lugaresfavoritos.RecyclerView.SitioAdaptador;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ArrayList<Sitio> listasitios = new ArrayList<>();
    public RecyclerView lista;
    public SitioAdaptador adapter;
    SearchView Buscar;
    private SitioLab sitioLab;
    private static final int ADD_CONTACT_REQUEST = 1 ;

    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sitioLab = new SitioLab(this);
        lista = findViewById(R.id.recycler_view_sitios);
        Buscar = findViewById(R.id.txtBUSCAR);

        lista.setLayoutManager(new LinearLayoutManager(this));

        getAllSitios();
        adapter = new SitioAdaptador(this, listasitios);
        lista.setAdapter(adapter);

        Buscar.setOnQueryTextListener(this);
    }

    public void getAllSitios(){
        listasitios.clear();
        listasitios.addAll(sitioLab.getSitios());
    }

    public void NuevoSitio(View view){
        Intent intent= new Intent(this,AgregarSitio.class);
        startActivityForResult(intent,ADD_CONTACT_REQUEST);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getAllSitios();
        adapter.notifyDataSetChanged();
    }


    /**Metodos para implementar el menu y asignar la funcion a cada opcion**/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String categoria;

        if (id == R.id.opAgregarSitio) {
            Intent intent = new Intent(this, AgregarSitio.class);
            startActivity(intent);
        } else if (id == R.id.opRestaurants) {
            categoria="restaurant";
            adapter.FiltrarCategorias(categoria);
        } else if (id == R.id.ophoteles) {
            categoria="parque";
            adapter.FiltrarCategorias(categoria);
        } else if (id == R.id.opiglesias) {
            categoria="hotel";
            adapter.FiltrarCategorias(categoria);
        } else if (id == R.id.opparques) {
            categoria="parque";
            adapter.FiltrarCategorias(categoria);
        } else if (id == R.id.opinstituciones) {
            categoria="institucion";
            adapter.FiltrarCategorias(categoria);
        }else  if (id==R.id.optodas){
            getAllSitios();
            adapter=new SitioAdaptador(this,listasitios);
            lista.setAdapter(adapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.Filtrado(s);
        return false;
    }
}