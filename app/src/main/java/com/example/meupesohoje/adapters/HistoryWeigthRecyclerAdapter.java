package com.example.meupesohoje.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.databinding.ItemRvHistoryweigthBinding;
import com.example.meupesohoje.domain.model.PersonData;
import com.example.meupesohoje.R;
import com.example.meupesohoje.util.FormatterDateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class HistoryWeigthRecyclerAdapter extends RecyclerView.Adapter<HistoryWeigthRecyclerAdapter.MyViewHolder>{

    private final Context context;
    private List<PersonDataEntity> itemList;

    public HistoryWeigthRecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_historyweigth, parent, false);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindItem(itemList.get(position), position);
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

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bindItem(PersonDataEntity personData, Integer position){
            String date = FormatterDateUtil.getStringFomattedFromDateTime(personData.date);
            String finalText = personData.weight.toString() + "kg" + " " + date;
            mDataBinding.tvLastWeigth.setTextColor(context.getResources().getColor(R.color.white));

            if(position == 0){
                mDataBinding.cardViewWeight.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                mDataBinding.tvLastWeigth.setTextColor(context.getResources().getColor(R.color.orange));
                mDataBinding.tvLastWeigth.setTextSize(25f);
            }

            mDataBinding.setTextDataWeigth(finalText);

            Integer sizeOfIndextItemList = itemList.size() - 1;

            if(sizeOfIndextItemList > position){
                if(itemList.get(position + 1).weight < personData.weight){
                    mDataBinding.setTextDataWeigth(finalText + "+");
                    mDataBinding.tvLastWeigth.setTextColor(context.getColor(R.color.red));
                }

                if(itemList.get(position + 1).weight > personData.weight){
                    mDataBinding.setTextDataWeigth(finalText + "-");
                    mDataBinding.tvLastWeigth.setTextColor(context.getColor(R.color.green));
                }
            }
        }
    }

    public List<PersonDataEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<PersonDataEntity> itemList) {
        this.itemList = itemList;
    }


}
