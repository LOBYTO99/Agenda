package ec.edu.tecnologicoloja.contactodatabase.Actividades;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.Contacto;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.ContactoLab;
import ec.edu.tecnologicoloja.contactodatabase.R;

public class DetallesContacto extends AppCompatActivity {
EditText txtnombre,txtnumero,txtciudad,txtcorreo;
Button guardar;
Contacto contacto;

private ContactoLab contactoLab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallescontacto);

        txtnombre=findViewById(R.id.et_detalle_contacto);
        txtnumero=findViewById(R.id.et_detalle_celular);
        txtciudad=findViewById(R.id.et_detalle_city);
        txtcorreo=findViewById(R.id.et_detalle_email);
        guardar=findViewById(R.id.btn_save);

        Bundle extras = getIntent().getExtras();
         int id=extras.getInt("ID");

         ContactoLab contactoLab1 = new ContactoLab(this);
            contacto = contactoLab1.getPersona(String.valueOf(id));

            txtnombre.setText(contacto.getNombre());
            txtnumero.setText(contacto.getTelefono());
            txtciudad.setText(contacto.getCiudad());
            txtcorreo.setText(contacto.getCorreo());

    }
}
