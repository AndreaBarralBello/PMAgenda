package com.example.agenda.amigos;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.Amigo;
import com.example.agenda.Amigos.AmigosEditar;
import com.example.agenda.R;

import java.util.List;

public class AmigosAdaptador extends RecyclerView.Adapter <AmigosAdaptador.ViewHolder> {

    List<Amigo> listaAmigos;

    public AmigosAdaptador(List<Amigo> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }


    @NonNull
    @Override
    public AmigosAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amigos_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmigosAdaptador.ViewHolder holder, int position) {
        Amigo amigo = listaAmigos.get(position);

        holder.tvNombre.setText(amigo.getNombre());
        holder.tvTelefono.setText(amigo.getTelefono());


        //Añadimos el click al botón de borrar
        holder.ivBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listaAmigos.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listaAmigos.size());

            }


            //botón Editar
            holder.btnEditar.setOnClickListener(new View.OnClickListener(){
                @Override
                        public void onClick(View view){
                    Intent intent = new Intent(context, AmigosEditar.class);
                    intent.putExtra("id", String.valueOf(amigo.getId()));

                    context.startActivity(intent);
                }

            }


        });
    }

    @Override
    public int getItemCount() {
        return listaAmigos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvTelefono;
        ImageView ivEditar;
        ImageView ivBorrar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.itemNombre);
            tvTelefono = itemView.findViewById(R.id.itemTelefono);
            ivEditar = itemView.findViewById(R.id.itemEditar);
            ivBorrar = itemView.findViewById(R.id.ItemBorrar);
        }
    }
}

