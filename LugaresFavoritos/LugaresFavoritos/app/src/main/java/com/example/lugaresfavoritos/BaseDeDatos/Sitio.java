package com.example.lugaresfavoritos.BaseDeDatos;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "sitio")
public class Sitio {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "imagen")
    @NonNull
    private String imagen;

    @ColumnInfo(name = "nombre")
    @NonNull
    private String nombre;

    @ColumnInfo(name = "categoria")
    @NonNull
    private String categoria;

    @ColumnInfo(name = "latitud")
    @NonNull
    private Double latitud;

    @ColumnInfo(name = "longitud")
    @NonNull
    private Double longitud;

    @ColumnInfo(name = "descripcion")
    @NonNull
    private String descripcion;

    public Sitio(String imagen,String nombre,String categoria,Double latitud,Double longitud,String descripcion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.categoria = categoria;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getImagen() {
        return imagen;
    }

    public void setImagen(@NonNull String imagen) {
        this.imagen = imagen;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NonNull String categoria) {
        this.categoria = categoria;
    }

    @NonNull
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(@NonNull Double latitud) {
        this.latitud = latitud;
    }

    @NonNull
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(@NonNull Double longitud) {
        this.longitud = longitud;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }
}

