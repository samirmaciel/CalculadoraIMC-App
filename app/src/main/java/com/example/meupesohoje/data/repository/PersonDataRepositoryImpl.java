package com.example.meupesohoje.data.repository;

import android.util.Log;

import com.example.meupesohoje.data.dao.PersonDataDao;
import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.domain.PersonDataRepository;
import com.example.meupesohoje.domain.model.PersonData;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class PersonDataRepositoryImpl implements PersonDataRepository {

    public PersonDataDao dao;

    public PersonDataRepositoryImpl(PersonDataDao dao){
        this.dao = dao;
    }

    @Override
    public Single<List<PersonDataEntity>> getAllPersonData() {
        return dao.getAllPersonData();
    }


    @Override
    public void insertPersonData(PersonDataEntity personData) {

        Log.d("MeuPesoHoje", "RegisterViewModel: " + personData.date);
        dao.insertPersonData(personData);
    }

    @Override
    public void deletePersonData(PersonDataEntity personData) {
        dao.deletePersonData(personData);
    }
}
