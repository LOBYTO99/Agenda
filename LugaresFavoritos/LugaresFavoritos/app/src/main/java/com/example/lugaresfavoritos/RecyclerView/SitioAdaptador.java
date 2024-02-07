package com.example.lugaresfavoritos.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lugaresfavoritos.BaseDeDatos.Sitio;
import com.example.lugaresfavoritos.R;
import com.example.lugaresfavoritos.Ventanas.DetallesDelSitio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SitioAdaptador  extends RecyclerView.Adapter<SitioAdaptador.SitioViewHolder> {
     private ArrayList<Sitio> datos;
    private ArrayList<Sitio> datosOriginales;


     private Context context;

     public SitioAdaptador(Context context , ArrayList<Sitio> datos){
         this.datos=datos;
         this.context=context;
         datosOriginales=new ArrayList<>();
         datosOriginales.addAll(datos);
     }


    public SitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sitio_item, null, false);
        return new SitioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SitioAdaptador.SitioViewHolder holder, int position) {
         holder.getNombre().setText(datos.get(position).getNombre());
         holder.getCategoria().setText(datos.get(position).getCategoria());

        Sitio sitio = datos.get(position);
        String rutaFoto = sitio.getImagen();
        Glide.with(holder.itemView.getContext())
                .load(new File(rutaFoto))
                .apply(new RequestOptions().centerCrop())
                .into(holder.foto);

    }

    public  void Filtrado(String txTBUSCAR){
         if (txTBUSCAR.length()==0){
             datos.clear();
             datos.addAll(datosOriginales);
         } else {
             List<Sitio> colleccion = datos.stream()
                     .filter(i ->i.getNombre().toLowerCase().contains(txTBUSCAR.toLowerCase()))
                     .collect(Collectors.toList());
             datos.clear();
             datos.addAll(colleccion);
         }
         notifyDataSetChanged();
    }


    public  void FiltrarCategorias(String OPCATEGORIA){

        if (OPCATEGORIA.length()==0){
            datos.clear();
            datos.addAll(datosOriginales);
        } else {
            List<Sitio> colleccion = datos.stream()
                    .filter(i ->i.getCategoria().toLowerCase().contains(OPCATEGORIA.toLowerCase()))
                    .collect(Collectors.toList());
            datos.clear();
            datos.addAll(colleccion);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  datos.size();

    }

    public class SitioViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;

        public TextView getNombre() {
            return nombre;
        }

        TextView categoria;
        public TextView getCategoria() {
            return categoria;
        }

        ImageView foto ;

        public ImageView getFoto() {
            return foto;
        }

        TextView latitud;

        public TextView getLatitud() {
            return latitud;
        }

        TextView longitud;

        public TextView getLongitud() {
            return longitud;
        }

        public SitioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.item_text_view_nombre);
            categoria= itemView.findViewById(R.id.item_text_view_categoria);
            foto = itemView.findViewById(R.id.item_image_view_);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetallesDelSitio.class);

                    intent.putExtra("ID",datos.get(getAdapterPosition()).getId());
                    intent.putExtra("RUTA",datos.get(getAdapterPosition()).getImagen());

                    context.startActivity(intent);
                }
            });

        }
    }
}
