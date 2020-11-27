package com.ramon.projeto_localizacao.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Lugar implements Comparable <Lugar>, Serializable {
    private int id;
    private String nomeLugar;
    private Date DataCadastro;
    private String Descricao;
    private double Lat;
    private double Long;

    public Date getDataCadastro() {

        return DataCadastro;
    }

    @SerializedName("_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataCadastro(Date dataCadastro) {

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
