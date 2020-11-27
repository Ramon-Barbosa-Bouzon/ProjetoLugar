package com.ramon.projeto_localizacao.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class LugarRepository {
    private static final String LUGAR_SERVICE_BASE_URL = "https://crudcrud.com";
    private LugarService lugarService;
    private MutableLiveData<List<Lugar>> lugarResponseMutableLiveData;
    private MutableLiveData<Boolean> salvoSucessoMutableLiveData;


    public LugarRepository() {
        lugarResponseMutableLiveData = new MutableLiveData<>();
        salvoSucessoMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        lugarService = new retrofit2.Retrofit.Builder()
                .baseUrl(LUGAR_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LugarService.class);
    }

    public void getLugares() {
        lugarService.getAllLugares().enqueue(new Callback<List<Lugar>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Lugar>> call, retrofit2.Response<List<Lugar>> response) {
                if (response.body() != null) {
                    Log.d("RESPOSTA", "tenho resultato-->"+response.body());
                    lugarResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Lugar>> call, Throwable t) {
                Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
                lugarResponseMutableLiveData.postValue(null);
            }
        });
    }

    public LiveData<List<Lugar>> getAllLugares() {
        return lugarResponseMutableLiveData;
    }
    public LiveData<Boolean> getSalvoSucesso() {
        return salvoSucessoMutableLiveData;
    }

    public void salvarLugar(Lugar lugar){
        lugarService.salvarLugar(lugar)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.body() != null) {
                            Log.d("RESPOSTA", "tenho resultato-->"+response.body());
                            salvoSucessoMutableLiveData.postValue(new Boolean(true));
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
                        salvoSucessoMutableLiveData.postValue(new Boolean(false));
                    }
                });
    }
}
