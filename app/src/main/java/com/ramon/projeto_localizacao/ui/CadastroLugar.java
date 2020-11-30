package com.ramon.projeto_localizacao.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ramon.projeto_localizacao.R;
import com.ramon.projeto_localizacao.model.Lugar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroLugar extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;


    private double latitudeAtual;
    private double longitudeAtual;
    private EditText nomeLugar;
    private EditText descricaoLugar;
    private TextView locationTextView;
    private TextView dataLugar;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String data;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lugar);


        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        data = dateFormat.format(calendar.getTime());
        dataLugar = findViewById(R.id.dataTextView);
        dataLugar.setText(data);


        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationTextView = findViewById(R.id.locationTextView);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                latitudeAtual = lat;
                longitudeAtual = lon;
                locationTextView.setText(String.format("Lat: %f, Long: %f", lat, lon));


            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
            @Override
            public void onProviderEnabled(String provider) {

            }
            @Override
            public void onProviderDisabled(String provider) {

            }

        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener );

        }
        else{
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_GPS);

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GPS){
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, locationListener);
                }
            }
            else{

                Toast.makeText(this, getString(R.string.no_gps_no_app),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }


    public void onClick(View view){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Lugar");
        String erro = "";


        descricaoLugar = findViewById(R.id.editTextDescricaoLugar);
        nomeLugar = findViewById(R.id.editTextNomeLugar);

        Lugar lugar = new Lugar();
        lugar.setNomeLugar(nomeLugar.getText().toString());
        lugar.setDescricao(descricaoLugar.getText().toString());
        lugar.setLat(latitudeAtual);
        lugar.setLong(longitudeAtual);

        lugar.setDataCadastro(dateFormat.format(calendar.getTime()));

        String id = nomeLugar.getText().toString() + calendar.getTimeInMillis();


        if(nomeLugar.getText().toString().equals(erro)){
            Toast.makeText(this, "Nome é obrigatório", Toast.LENGTH_SHORT).show();

        }else{
            mDatabase.child(id).setValue(lugar);
            mDatabase.push();

            Toast.makeText(CadastroLugar.this, "Dados Salvos com sucesso", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }

    public void voltar(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}