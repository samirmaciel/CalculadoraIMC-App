package com.example.meupesohoje.data.datasource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meupesohoje.data.dao.PersonDataDao;
import com.example.meupesohoje.data.model.PersonDataEntity;

@Database(entities = {PersonDataEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PersonDataDao personDataDao();

    public static volatile AppDataBase instance;
    public static String DATABASE_NAME = "person_db";

    public static AppDataBase getInstance(Context context){

        AppDataBase result = instance;

        if(result != null){
            return result;
        }

        synchronized (AppDataBase.class){
            if (instance == null){
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDataBase.class,
                        DATABASE_NAME
                ).allowMainThreadQueries().build();
            }

            return instance;
        }
    }



}
