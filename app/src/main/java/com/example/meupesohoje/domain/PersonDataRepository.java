package com.example.meupesohoje.domain;

import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.domain.model.PersonData;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PersonDataRepository {

    Single<List<PersonDataEntity>> getAllPersonData();

    void insertPersonData(PersonDataEntity personData);

    void deletePersonData(PersonDataEntity personData);

    void cleanDatabase();
}
