package com.example.meupesohoje.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatterDateUtil {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getStringFomattedFromDateTime(String dateTimeString){

            String onlyDate = dateTimeString.split("T")[0];
            LocalDate localdate = LocalDate.parse(onlyDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            String date = formatter.format(localdate);

            return date;
    }
}
