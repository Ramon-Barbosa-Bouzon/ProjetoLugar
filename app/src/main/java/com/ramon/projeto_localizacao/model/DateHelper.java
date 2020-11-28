package com.ramon.projeto_localizacao.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");

    public static String format (Date date){
        return sdf.format(date);
    }

}
