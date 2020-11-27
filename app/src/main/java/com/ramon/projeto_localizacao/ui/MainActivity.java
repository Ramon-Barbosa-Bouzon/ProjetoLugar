package com.ramon.projeto_localizacao.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ramon.projeto_localizacao.R;
import com.ramon.projeto_localizacao.model.ListLocalizacaoAdapter;
import com.ramon.projeto_localizacao.model.Lugar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListLocalizacaoAdapter adapter;
    private RecyclerView lugarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        lugarRecyclerView = findViewById(R.id.recyclerViewLugar);
        lugarRecyclerView.setLayoutManager(layoutManager);
        List<Lugar> lugar = new ArrayList<>();


        adapter = new ListLocalizacaoAdapter(lugar, this);
        lugarRecyclerView.setAdapter(adapter);


    }

    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CadastroLugar.class);
        startActivity(intent);
    }
}