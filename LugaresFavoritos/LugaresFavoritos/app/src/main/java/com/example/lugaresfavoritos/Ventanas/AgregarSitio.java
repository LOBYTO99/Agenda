package com.example.lugaresfavoritos.Ventanas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.lugaresfavoritos.BaseDeDatos.Sitio;
import com.example.lugaresfavoritos.BaseDeDatos.SitioLab;
import com.example.lugaresfavoritos.Mapas.Mapa;
import com.example.lugaresfavoritos.R;

import java.io.File;
import java.io.IOException;

public class AgregarSitio extends AppCompatActivity {
    private Spinner mSpinner;
    ImageView mFoto;
    double latitudrecibida,longitudrecibida;
    String mCategoridaEscogida;
    EditText mEditTextNombre, mEditTextLatitud, mEditTextLongitud, mEditTextDescripcion;
    String nombre,  descripcion,rutaImagen;
    Double latitud = null;
    Double longitud= null;
    File imagenArchivo= null;
    private SitioLab sitioLab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sitio);
        mSpinner = findViewById(R.id.spinner);

        mFoto = findViewById(R.id.imageViewAniadir);
        mEditTextNombre = findViewById(R.id.editTextNombre);
        mEditTextLatitud = findViewById(R.id.editTextLatitud);
        mEditTextLongitud = findViewById(R.id.editTextLongitud);
        mEditTextDescripcion = findViewById(R.id.editTextDescripcion);
        sitioLab = new SitioLab(this);

        String[] opciones = {"Restaurant", "Parque", "Hotel", "Iglesia", "Institucion", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        mSpinner.setAdapter(adapter);
    }

    public void InsertarNuevoRegistro(View v) {

        mCategoridaEscogida = mSpinner.getSelectedItem().toString();
        nombre = mEditTextNombre.getText().toString();
        descripcion = mEditTextDescripcion.getText().toString();

        if (nombre.isEmpty() ||descripcion.isEmpty() || imagenArchivo == null) {
            Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            latitud = Double.valueOf(mEditTextLatitud.getText().toString());
            longitud = Double.valueOf(mEditTextLongitud.getText().toString());
            sitioLab.addSitio(new Sitio(rutaImagen,nombre,mCategoridaEscogida,latitud,longitud,descripcion));
            Toast.makeText(getApplicationContext(), "Agregado Correctamente", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    public void FinalizarVentanaAgregar(View v) {
        finish();
    }

    public void AbrirMapa(View v) {
        Intent intent = new Intent(this, Mapa.class);
        startActivityForResult(intent, 2);
    }


   /**Metodo para abrir la camara**/
    public void AbrirCamara(View v) {
        Intent intent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));

        try {
            imagenArchivo = crearImagen();
        }catch (IOException ex){
            Log.e("Error",ex.toString());
        }
        if(imagenArchivo != null){
            Uri fotoUri = FileProvider.getUriForFile(this,"com.example.lugaresfavoritos.fileprovider",imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 3);
        }
    }

    private File crearImagen() throws IOException {

        File directorio= getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile("FOTO_",".jpg",directorio);
        rutaImagen = imagen.getAbsolutePath();

        return imagen;

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {

                //Bundle extras = data.getExtras();
                //Bitmap imgBitMap = (Bitmap) extras.get("data");
                Bitmap imgBitMap = BitmapFactory.decodeFile(rutaImagen);
                mFoto.setImageBitmap(imgBitMap);

            } else if (requestCode == 2) {
                Bundle datos = data.getExtras();
                if (datos != null) {

                    latitudrecibida = datos.getDouble("key latitud");
                    longitudrecibida = datos.getDouble("key longitud");
                    mEditTextLatitud.setText(String.valueOf(latitudrecibida));
                    mEditTextLongitud.setText(String.valueOf(longitudrecibida));
                }
            }
        }
    }
}