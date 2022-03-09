package com.example.meupesohoje.modules.homeScreen;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.meupesohoje.domain.PersonDataRepository;

public class HomeViewModel extends ViewModel {

    private PersonDataRepository repository;
    public MutableLiveData<Boolean> hasData = new MutableLiveData<Boolean>();

    public HomeViewModel(PersonDataRepository repository){
        this.repository = repository;
    }

    public void checkData(){
        repository.getAllPersonData().subscribe(personDataEntities -> {
            if(personDataEntities.size() > 0){
                hasData.postValue(true);
            }else{
                hasData.postValue(false);
            }
        });
    }

    static class HomeViewModelFactory implements ViewModelProvider.Factory{

        private final PersonDataRepository repository;

        public HomeViewModelFactory(PersonDataRepository repository){
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new HomeViewModel(repository);
        }
    }
}
