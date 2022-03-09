package com.example.meupesohoje.modules.historicScreen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meupesohoje.adapters.HistoryWeigthRecyclerAdapter;
import com.example.meupesohoje.data.datasource.AppDataBase;
import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.data.repository.PersonDataRepositoryImpl;
import com.example.meupesohoje.databinding.FragmentHistoricweigthBinding;
import com.example.meupesohoje.domain.model.PersonData;
import com.example.meupesohoje.R;
import com.example.meupesohoje.util.FormatterDateUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class HistoricWeigthFragment extends Fragment {

    private FragmentHistoricweigthBinding mDataBinding;
    private HistoryWeigthRecyclerAdapter mRecyclerAdapter;
    private HistoricViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = FragmentHistoricweigthBinding.bind(inflater.inflate(R.layout.fragment_historicweigth, container, false));
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

        LocalDate dt = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        new PersonDataEntity(65, dt.format(formatter));

        viewModel.getAllPersonData();

//        viewModel.lastPersonData.observe(getViewLifecycleOwner(), new Observer<PersonDataEntity>() {
//            @Override
//            public void onChanged(PersonDataEntity personDataEntity) {
//                String date = FormatterDateUtil.getStringFomattedFromDateTime(personDataEntity.date);
//                mDataBinding.tvLastWeigth.setText(personDataEntity.weight+ "kg" + " " + date);
//            }
//        });

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
        mRecyclerAdapter = new HistoryWeigthRecyclerAdapter(getContext());
        mDataBinding.rvHistoryWeigth.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mDataBinding.rvHistoryWeigth);
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

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            alertDialog.setTitle("Deseja excluir esse registro?");
            alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    viewModel.deletePersonData(mRecyclerAdapter.getItemList().get(viewHolder.getLayoutPosition()));
                    mRecyclerAdapter.getItemList().remove(viewHolder.getLayoutPosition());
                    mRecyclerAdapter.notifyDataSetChanged();
                }
            });
            alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mRecyclerAdapter.notifyDataSetChanged();
                }
            });

            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    mRecyclerAdapter.notifyDataSetChanged();
                }
            });

            alertDialog.create().show();
        }
    };

}
