package com.example.meupesohoje.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.domain.model.PersonData;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PersonDataDao {

    @Query("SELECT * FROM person_data")
    public Single<List<PersonDataEntity>> getAllPersonData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPersonData(PersonDataEntity personData);

    @Delete
    public void deletePersonData(PersonDataEntity personData);

    @Query("DELETE FROM person_data")
    public void deleteAll();
}
