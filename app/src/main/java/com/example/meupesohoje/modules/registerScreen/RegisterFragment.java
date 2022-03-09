package com.example.meupesohoje.modules.registerScreen;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.meupesohoje.data.datasource.AppDataBase;
import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.data.repository.PersonDataRepositoryImpl;
import com.example.meupesohoje.databinding.FragmentRegisterBinding;
import com.example.meupesohoje.domain.model.PersonData;
import com.example.meupesohoje.util.WeigthTextWatcher;
import com.example.meupesohoje.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding mDataBinding;
    private RegisterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = FragmentRegisterBinding.bind(inflater.inflate(R.layout.fragment_register, container, false));
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new RegisterViewModel.RegisterViewModelFacotry(
                new PersonDataRepositoryImpl(AppDataBase.getInstance(getContext()).personDataDao())
        )).get(RegisterViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();

        mDataBinding.edtWeigth.addTextChangedListener(new WeigthTextWatcher(mDataBinding.edtWeigth));

        mDataBinding.btnRegister.setOnClickListener((View v) -> {
            viewModel.insertPersonData(new PersonDataEntity(Integer.valueOf(mDataBinding.edtWeigth.getText().toString().replace("kg", "")), LocalDateTime.now().toString()));
            Navigation.findNavController(v).navigateUp();
        });

        mDataBinding.btnArrowBackRegister.setOnClickListener((View v) -> {
            Navigation.findNavController(v).navigateUp();
        });
    }
}
