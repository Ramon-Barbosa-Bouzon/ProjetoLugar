package com.ramon.projeto_localizacao.model;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Lugar implements Comparable <Lugar>, Serializable {

    private String nomeLugar;
    private String DataCadastro;
    private String Descricao;
    private double Lat;
    private double Long;
    private String id;


    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getDataCadastro() {

        return DataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {

        DataCadastro = dataCadastro;
    }

    public String getDescricao() {

        return Descricao;
    }

    public String getNomeLugar() {
        return nomeLugar;
    }

    public void setNomeLugar(String nomeLugar) {
        this.nomeLugar = nomeLugar;
    }

    public void setDescricao(String descricao) {

        Descricao = descricao;
    }


    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }


    @Override
    public int compareTo(Lugar lugar) {
        return this.DataCadastro.compareTo(lugar.DataCadastro);
    }

}
