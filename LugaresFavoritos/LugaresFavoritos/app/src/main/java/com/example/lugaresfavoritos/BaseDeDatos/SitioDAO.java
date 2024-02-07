package com.example.lugaresfavoritos.BaseDeDatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SitioDAO {

    @Query("select * from sitio")
    List<Sitio> getSitio();

    @Query("select * from sitio where id = :uuid")
    Sitio getSitio(String uuid);



    @Insert
    void insertSitio(Sitio sitio);

    @Update
    void updateSitio(Sitio sitio);
    @Delete
    void deleteSitio(Sitio sitio);
}
