package com.fody.app.ui.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.Breakfast;
import com.fody.app.Dinner;
import com.fody.app.Lunch;
import com.fody.app.R;
import com.fody.app.Snack;
import com.fody.app.TypeSelectorBreakfast;

public class DiaryFragment extends Fragment  {

     DiaryViewModel diaryViewModel;
     Button breakfast;
     Button lunch;
     Button snack;
     Button dinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_diary, container, false);
        diaryViewModel = ViewModelProviders.of(this).get(DiaryViewModel.class);

        breakfast = root.findViewById(R.id.btnBreakfast);
        lunch = root.findViewById(R.id.btnLunch);
        snack = root.findViewById(R.id.btnSnack);
        dinner = root.findViewById(R.id.btnDinner);

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), TypeSelectorBreakfast.class);
                startActivity(intent);
            }
        });
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Lunch.class);
                startActivity(intent);
            }
        });
        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Snack.class);
                startActivity(intent);
            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Dinner.class);
                startActivity(intent);
            }
        });


        return root;

    }




}