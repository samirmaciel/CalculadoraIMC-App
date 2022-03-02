package com.example.meupesohoje.domain.model;

import java.time.LocalDate;

public class PersonData {

    private Integer weigth;
    private String date;

    public PersonData( Integer weigth, String date) {
        this.weigth = weigth;
        this.date = date;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public String getDate() {
        return date;
    }


}
