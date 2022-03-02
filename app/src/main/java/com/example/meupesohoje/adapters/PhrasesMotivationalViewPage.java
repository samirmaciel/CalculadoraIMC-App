package com.example.meupesohoje.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.meupesohoje.R;

public class PhrasesMotivationalViewPage extends PagerAdapter {

    private Context mContext;

    public String[] itemList = new String[]{};

    public PhrasesMotivationalViewPage(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return itemList.length;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item_phrases_motivational, null);

        TextView mPhrase = itemView.findViewById(R.id.tvPhraseText);

        mPhrase.setText(itemList[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public String[] getItemList() {
        return itemList;
    }

    public void setItemList(String[] itemList) {
        this.itemList = itemList;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
