package com.ramon.projeto_localizacao.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ramon.projeto_localizacao.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ListLocalizacaoAdapter extends RecyclerView.Adapter {

    private List<Lugar> lugar;
    private Context context;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String data;


    public ListLocalizacaoAdapter(List<Lugar> lugar) {
        this.lugar = lugar;
       // , Context context this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Lugar l = lugar.get(position);
        ViewHolder viewHolder = (ViewHolder)holder;

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        data = dateFormat.format(calendar.getTime());

        String nomel = l.getNomeLugar()+" data de registro: "+data;

        String latLong = "Latitude: "+l.getLat()+" Longitude: "+l.getLong();

        viewHolder.dataNomeTextView.setText(nomel);
        viewHolder.lugarTextView.setText(latLong);
        viewHolder.descricaoLugar.setText(l.getDescricao());

    }

    @Override
    public int getItemCount() {
        return lugar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dataNomeTextView;
        TextView lugarTextView;
        TextView descricaoLugar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

                this.dataNomeTextView =
                        itemView.findViewById(R.id.dataNomeTextView);
                this.lugarTextView =
                        itemView.findViewById(R.id.lugarTextView);
                this.descricaoLugar =
                        itemView.findViewById(R.id.descricaoLugar);
            }
        }

}
