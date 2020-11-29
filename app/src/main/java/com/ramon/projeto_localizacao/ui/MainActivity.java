package com.ramon.projeto_localizacao.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramon.projeto_localizacao.R;
import com.ramon.projeto_localizacao.model.ListLocalizacaoAdapter;
import com.ramon.projeto_localizacao.model.Lugar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Lugar> lugar;
    private ListLocalizacaoAdapter adapter;
    private RecyclerView lugarRecyclerView;
    private DatabaseReference mDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lugarRecyclerView = findViewById(R.id.recyclerViewLugar);
        lugarRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lugar = new ArrayList<>();


        mDatabase = FirebaseDatabase.getInstance().getReference("Lugar");


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Lugar l = ds.getValue(Lugar.class);
                    l.setId(ds.getKey());
                    lugar.add(l);

                }
                adapter = new ListLocalizacaoAdapter(lugar);
                lugarRecyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CadastroLugar.class);
        startActivity(intent);
    }
}