package com.example.meupesohoje.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.time.LocalDate;

@Entity(tableName = "person_data")
public class PersonDataEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "weigth")
    public Integer weight;

    @ColumnInfo(name = "date")
    public String date;

    public PersonDataEntity(Integer weight, String date){
        this.weight = weight;
        this.date = date;
    }
}
