package ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Contacto")
public class Contacto {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "nombre")
    @NonNull
    private String nombre;

    @ColumnInfo(name = "telefono")
    private String telefono;

    @ColumnInfo(name = "ciudad")
    private String ciudad;

    @ColumnInfo(name = "correo")
    private String correo;

   public Contacto(String nombre, String telefono, String ciudad, String correo) {
       this.nombre=nombre;
        this.telefono=telefono;
        this.ciudad=ciudad;
        this.correo=correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}




