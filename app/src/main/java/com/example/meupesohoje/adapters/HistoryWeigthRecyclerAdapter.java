package com.example.meupesohoje.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.databinding.ItemRvHistoryweigthBinding;
import com.example.meupesohoje.domain.model.PersonData;
import com.example.meupesohoje.R;

import java.util.List;

public class HistoryWeigthRecyclerAdapter extends RecyclerView.Adapter<HistoryWeigthRecyclerAdapter.MyViewHolder>{

    private List<PersonDataEntity> itemList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_historyweigth, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindItem(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        if(itemList != null){
            return itemList.size();
        }

        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ItemRvHistoryweigthBinding mDataBinding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mDataBinding = ItemRvHistoryweigthBinding.bind(itemView);
        }

        public void bindItem(PersonDataEntity personData){
            String weigthAndDate = personData.weight + " " + personData.date;
            mDataBinding.setTextDataWeigth(weigthAndDate);
        }

    }

    public List<PersonDataEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<PersonDataEntity> itemList) {
        this.itemList = itemList;
    }
}
