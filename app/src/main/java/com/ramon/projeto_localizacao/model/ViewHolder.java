package com.ramon.projeto_localizacao.model;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ramon.projeto_localizacao.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView dataNomeTextView;
    TextView lugarTextView;
    TextView descricaoLugar;


    ViewHolder(View v) {
        super(v);

        this.dataNomeTextView =
                v.findViewById(R.id.dataNomeTextView);
        this.lugarTextView =
                v.findViewById(R.id.lugarTextView);
        this.descricaoLugar =
                v.findViewById(R.id.descricaoLugar);
    }

}
