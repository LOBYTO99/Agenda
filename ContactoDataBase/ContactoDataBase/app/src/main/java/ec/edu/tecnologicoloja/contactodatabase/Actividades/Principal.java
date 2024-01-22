package ec.edu.tecnologicoloja.contactodatabase.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ec.edu.tecnologicoloja.contactodatabase.Adaptador.Adaptador;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.Contacto;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.ContactoDAO;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.ContactoLab;
import ec.edu.tecnologicoloja.contactodatabase.R;

public class Principal extends AppCompatActivity implements Adaptador.OnContactClickListener {

    private ArrayList<Contacto> listacontactos = new ArrayList<>();

    public RecyclerView lista;
    public Adaptador adapter;
    private ContactoLab contactoLab;

    private ContactoDAO bd;
    private static final int ADD_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        contactoLab = new ContactoLab(this);
        lista = findViewById(R.id.recycler_view_contacts);
        lista.setLayoutManager(new LinearLayoutManager(this));


        getAllPersonas();
        adapter= new Adaptador(this,listacontactos);
        lista.setAdapter(adapter);


        Button addButton = findViewById(R.id.btn_add_contact);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, AgregarContacto.class);
                startActivityForResult(intent, ADD_CONTACT_REQUEST);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllPersonas();
        adapter.notifyDataSetChanged();
    }

    public void getAllPersonas(){
        listacontactos.clear();
        listacontactos.addAll(contactoLab.getPersonas());
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pri, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.menuNuevo){
            Intent intent = new Intent();
            intent.setClass(this,
                    AgregarContacto.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}