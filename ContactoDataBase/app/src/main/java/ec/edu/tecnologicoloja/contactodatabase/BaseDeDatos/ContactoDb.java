package ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Contacto.class},version = 1)
public abstract class ContactoDb extends RoomDatabase {
    public abstract ContactoDAO daoContacto();
}
