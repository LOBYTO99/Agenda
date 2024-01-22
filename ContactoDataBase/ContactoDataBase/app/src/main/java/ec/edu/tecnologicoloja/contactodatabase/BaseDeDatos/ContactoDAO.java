package ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ContactoDAO {
    @Query("select * from Contacto")
    List<Contacto> getContacto();
    @Query("select * from Contacto where id = :uuid")
    Contacto getContacto(String uuid);
    @Insert
    void insertContacto(Contacto contacto);
    @Update
    void updateContacto(Contacto contacto);
    @Delete
    void deleteContacto(Contacto contacto);
}
