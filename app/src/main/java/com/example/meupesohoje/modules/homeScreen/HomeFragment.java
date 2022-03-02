package com.example.meupesohoje.modules.homeScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.meupesohoje.adapters.PhrasesMotivationalViewPage;
import com.example.meupesohoje.R;
import com.example.meupesohoje.data.datasource.AppDataBase;
import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.databinding.FragmentHomeBinding;

import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    FragmentHomeBinding mDataBinding;
    PhrasesMotivationalViewPage mViewPager;
    Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDataBinding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false));
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();
        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderPhrases(this), 2000, 4000);
    }

    private void initComponents(){
        mViewPager = new PhrasesMotivationalViewPage(getContext());
        mViewPager.setItemList(getResources().getStringArray(R.array.motivational_phrases));
        mDataBinding.vpPhrases.setAdapter(mViewPager);
    }

    @Override
    public void onResume() {
        super.onResume();



        mDataBinding.btnHistory.setOnClickListener((View v) -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_historyWeigthFragment);
        });

        mDataBinding.btnStart.setOnClickListener((View v) -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_registerFragment);
        });
    }

    public class SliderPhrases extends TimerTask{

        private Fragment fragment;

        public SliderPhrases(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void run() {
            fragment.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mViewPager.getItemList().length > 0){

                        Integer currentItem = mDataBinding.vpPhrases.getCurrentItem();
                        Integer listSize = mViewPager.getItemList().length;
                        if( currentItem < listSize - 1){
                            mDataBinding.vpPhrases.setCurrentItem(currentItem + 1);
                        }else{
                            mDataBinding.vpPhrases.setCurrentItem(0);

                        }
                    }
                }
            });
        }
    }
}