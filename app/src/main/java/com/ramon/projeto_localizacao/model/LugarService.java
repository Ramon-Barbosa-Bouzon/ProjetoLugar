package com.ramon.projeto_localizacao.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LugarService {
    @GET("api/d753435cfe984e8e9de5ba4681b27733/lugar")
    Call<List<Lugar>> getAllLugares();

    @POST("api/d753435cfe984e8e9de5ba4681b27733/lugar")
    Call<ResponseBody> salvarLugar(
            @Body Lugar lugar);

}
