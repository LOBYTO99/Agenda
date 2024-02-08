package ec.edu.tecnologicoloja.contactodatabase.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.List;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.Contacto;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.ContactoDb;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.ContactoLab;
import ec.edu.tecnologicoloja.contactodatabase.R;

public class AgregarContacto extends AppCompatActivity {
    private TextView nom,cell,city, email;
    private ContactoLab contactoLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarcontacto);

        nom = findViewById(R.id.et_nombre);
        cell = findViewById(R.id.et_cell);
        city= findViewById(R.id.et_city);
        email = findViewById(R.id.et_email);

        contactoLab =new ContactoLab(this);

    }
    public void GuardarContactos(View v){

        String nombree = String.valueOf(nom.getText());
        String telefonoo = String.valueOf(cell.getText());
        String ciudadd = String.valueOf(city.getText());
        String correoo = String.valueOf(email.getText());

        if (nombree.isEmpty()) {
            nom.setError("Campo obligatorio");
        } if (telefonoo.isEmpty()) {
            cell.setError("Campo obligatorio");
        } if (ciudadd.isEmpty()) {
            city.setError("Campo obligatorio");
        } if(correoo.isEmpty()){
            email.setError("Campo obligatorio");
        } else {
            contactoLab.addPersona(new Contacto( nombree,telefonoo,ciudadd,correoo));
            String no = nom.getText().toString();
            String num = cell.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nom", no);
            resultIntent.putExtra("num", num);
            setResult(RESULT_OK, resultIntent);
            finish();

            Toast.makeText(this, "Contacto agregado con éxito",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ListarContactos(View v){
        ContactoDb contactoDb = Room.databaseBuilder(
                getApplicationContext(),
                ContactoDb.class, "ContactoDb"
        ).allowMainThreadQueries().build();
        List<Contacto> listContactos;
        listContactos =  contactoDb.daoContacto().getContacto();
        String texto = " ";

        for (Contacto dato : listContactos) {
            Log.d("datos","Nombre: "
                    +dato.getNombre()+",Celular: "
                    +dato.getTelefono()+",ciudad: "
                    +dato.getCiudad()+",Correo: "
                    +dato.getCorreo()+"\n");
        }
    }
}
