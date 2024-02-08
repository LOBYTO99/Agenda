package ec.edu.tecnologicoloja.contactodatabase.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ec.edu.tecnologicoloja.contactodatabase.R;
import ec.edu.tecnologicoloja.contactodatabase.Actividades.DetallesContacto;
import ec.edu.tecnologicoloja.contactodatabase.BaseDeDatos.Contacto;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ContactViewHolder> {

    private ArrayList<Contacto> datos;

    private Context context;

    public TextView textView;



    private static OnContactClickListener onContactClickListener;
    public void setOnContactClickListener(OnContactClickListener listener) {
        onContactClickListener = listener;
    }
    public Adaptador(Context context , ArrayList<Contacto> datos) {
        this.datos=datos;
        this.context=context;
    }

    public interface OnContactClickListener {
        default void onContactClick(Contacto contacto) {
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,null,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.getNameTextView().setText(datos.get(position).getNombre());
        holder.getPhoneTextView().setText(datos.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        public TextView getNameTextView() {
            return nameTextView;
        }
        TextView phoneTextView;
        public TextView getPhoneTextView() {
            return phoneTextView;
        }
        ImageView contactImageView;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            phoneTextView = itemView.findViewById(R.id.text_view_phone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetallesContacto.class);
                    intent.putExtra("ID",datos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
    public TextView getTextView() {
        return textView;
    }
}

