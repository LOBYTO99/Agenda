package ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;
public class ContactoLab {
    @SuppressLint("StaticFieldLeak")
    private static ContactoLab contactoLab;
    private ContactoDAO ContactoDao;
    public ContactoLab(Context context){
        Context appContext=context.getApplicationContext();
        ContactoDb database=
                Room.databaseBuilder(
                                appContext, ContactoDb.class,"persona")
                        .allowMainThreadQueries().build();
        ContactoDao=database.daoContacto();
    }
    public static ContactoLab get(Context context){
        if (contactoLab ==null){
            contactoLab =new ContactoLab(context);
        }
        return contactoLab;
    }
    public List<Contacto> getPersonas(){
        return  ContactoDao.getContacto();
    }
    public Contacto getPersona(String id){
        return ContactoDao.getContacto(id);
    }
    public void addPersona(Contacto contacto){
        ContactoDao.insertContacto(contacto);
    }
    public void deleteContacto(Contacto contacto){
        ContactoDao.deleteContacto(contacto);
    }
    public void updateContacto(Contacto contacto)
    {ContactoDao.updateContacto(contacto);
    }
}
