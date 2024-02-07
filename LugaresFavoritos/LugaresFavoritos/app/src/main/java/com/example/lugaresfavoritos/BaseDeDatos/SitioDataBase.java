package com.example.lugaresfavoritos.BaseDeDatos;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Sitio.class},version = 1)
public abstract class SitioDataBase extends RoomDatabase{

    public abstract SitioDAO daoSitio();
}
