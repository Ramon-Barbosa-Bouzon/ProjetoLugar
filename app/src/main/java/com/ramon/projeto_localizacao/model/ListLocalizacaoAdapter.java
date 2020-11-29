package com.ramon.projeto_localizacao.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramon.projeto_localizacao.R;
import com.ramon.projeto_localizacao.ui.CadastroLugar;
import com.ramon.projeto_localizacao.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EventListener;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ListLocalizacaoAdapter extends RecyclerView.Adapter {

    //private Context context;
    private List<Lugar> lugar;
    AlertDialog dg;



    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String data;
    private DatabaseReference mDatabase;

    public ListLocalizacaoAdapter(List<Lugar> lugar) {
        this.lugar = lugar;
        //this.context = context;

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
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

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            mDatabase = FirebaseDatabase.getInstance().getReference("Lugar");

        }

        @Override
        public void onClick(View v) {
            String key = lugar.get(getAdapterPosition()).getId();

        }

        @Override
        public boolean onLongClick(View v) {

            String key = lugar.get(getAdapterPosition()).getId();

                lugar.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                mDatabase.child(key).removeValue();
                mDatabase.push();

            return false;
        }

    }

}
