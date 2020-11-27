package com.ramon.projeto_localizacao.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramon.projeto_localizacao.R;

import java.util.List;

public class ListLocalizacaoAdapter extends RecyclerView.Adapter <ViewHolder> {

    private List<Lugar> lugar;
    private Context context;

    public ListLocalizacaoAdapter(List<Lugar> lugar, Context context) {
        this.lugar = lugar;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lugar l = lugar.get(position);

        holder.dataNomeTextView.setText(context.getString(R.string.data_nome,
                l.getNomeLugar(), DateHelper.format(l.getDataCadastro())));
        holder.lugarTextView.setText(context.getString(R.string.Lat_long,
                l.getLat(), l.getLong()));
        holder.descricaoLugar.setText(l.getDescricao());
    }

    @Override
    public int getItemCount() {
        return lugar.size();
    }

    public void setResults(List<Lugar> lugar) {
        this.lugar = lugar;
        notifyDataSetChanged();
    }

}
