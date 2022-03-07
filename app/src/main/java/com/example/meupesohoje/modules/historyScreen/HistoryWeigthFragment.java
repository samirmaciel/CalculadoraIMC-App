package com.example.meupesohoje.modules.historyScreen;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.meupesohoje.adapters.HistoryWeigthRecyclerAdapter;
import com.example.meupesohoje.data.datasource.AppDataBase;
import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.data.repository.PersonDataRepositoryImpl;
import com.example.meupesohoje.databinding.FragmentHistoryweigthBinding;
import com.example.meupesohoje.domain.model.PersonData;
import com.example.meupesohoje.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryWeigthFragment extends Fragment {

    private FragmentHistoryweigthBinding mDataBinding;
    private HistoryWeigthRecyclerAdapter mRecyclerAdapter;
    private HistoricViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = FragmentHistoryweigthBinding.bind(inflater.inflate(R.layout.fragment_historyweigth, container, false));
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new HistoricViewModel.HistoricViewModelFactory(
           new PersonDataRepositoryImpl(AppDataBase.getInstance(getContext()).personDataDao())
        )).get(HistoricViewModel.class);
        initRecycler();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();

        viewModel.getAllPersonData();

        viewModel.workList.observe(getViewLifecycleOwner(), new Observer<List<PersonDataEntity>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<PersonDataEntity> personDataEntities) {
                Log.d("MeuPesoHoje", "onChanged: " + personDataEntities.size());
                mRecyclerAdapter.setItemList(personDataEntities);
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });

        mDataBinding.btnArrowBackHistory.setOnClickListener((View v) -> {
            Navigation.findNavController(v).navigateUp();
        });
    }

    private void initRecycler(){
        mRecyclerAdapter = new HistoryWeigthRecyclerAdapter();
        mDataBinding.rvHistoryWeigth.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mDataBinding.rvHistoryWeigth.setAdapter(mRecyclerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<PersonData> getMockListPersonData(){
        List<PersonData> tempList = new ArrayList<>();
        tempList.add(new PersonData(55, LocalDate.now().toString()));
        tempList.add(new PersonData(80, LocalDate.now().toString()));
        tempList.add(new PersonData(65, LocalDate.now().toString()));
        tempList.add(new PersonData(74, LocalDate.now().toString()));
        tempList.add(new PersonData(45, LocalDate.now().toString()));

        return tempList;
    }

}
