package com.example.meupesohoje.modules.registerScreen;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.domain.PersonDataRepository;
import com.example.meupesohoje.domain.model.PersonData;

import java.util.List;

public class RegisterViewModel extends ViewModel {

    private PersonDataRepository repository;
    public MutableLiveData<List<PersonDataEntity>> worList = new MutableLiveData();

    public RegisterViewModel(PersonDataRepository repository){
        this.repository = repository;

    }


    public void insertPersonData(PersonDataEntity personData){
        repository.insertPersonData(personData);
    }

    public void getAllPersonData(){
        repository.getAllPersonData().subscribe( personList -> {
           worList.postValue(personList);
        });
    }

    static class RegisterViewModelFacotry implements ViewModelProvider.Factory {

        private PersonDataRepository repository;

        public RegisterViewModelFacotry(PersonDataRepository repository){
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RegisterViewModel(repository);
        }
    }
}
