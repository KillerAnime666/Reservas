package com.example.reservas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewDataHelper> {

    ArrayList<ModeloProducto> lista;

    public AdaptadorProducto(ArrayList<ModeloProducto> lista) {
        this.lista = lista;
    }

    public void setListaDeProductos(ArrayList<ModeloProducto> listaDeProductos) {
        this.lista = listaDeProductos;
    }

    @NonNull
    @Override
    public ViewDataHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_producto, parent, false);
        return new ViewDataHelper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDataHelper holder, int position) {
        holder.tvIdRes.setText(String.valueOf(lista.get(position).getId()));
        holder.tvNombreRes.setText(lista.get(position).getNombre());
        holder.tvCarnetRes.setText(lista.get(position).getCarnet());
        holder.tvTelefonoRes.setText(String.valueOf(lista.get(position).getTelefono()));
        holder.tvDepartamentoRes.setText(lista.get(position).getDepartamento());
        holder.tvMontoRes.setText(String.valueOf(lista.get(position).getMonto()));
        holder.tvSaldoRes.setText(String.valueOf(lista.get(position).getSaldo()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHelper extends RecyclerView.ViewHolder{

        TextView tvIdRes, tvNombreRes, tvCarnetRes, tvTelefonoRes, tvDepartamentoRes, tvMontoRes, tvSaldoRes;

        public ViewDataHelper(@NonNull View itemView) {
            super(itemView);
            tvIdRes = itemView.findViewById(R.id.tvIdItem);
            tvNombreRes = itemView.findViewById(R.id.tvNombreItem);
            tvCarnetRes = itemView.findViewById(R.id.tvCarnetItem);
            tvTelefonoRes = itemView.findViewById(R.id.tvTelefonoItem);
            tvDepartamentoRes = itemView.findViewById(R.id.tvDepartamentoItem);
            tvMontoRes = itemView.findViewById(R.id.tvMontoItem);
            tvSaldoRes = itemView.findViewById(R.id.tvSaldoItem);
        }
    }
}
