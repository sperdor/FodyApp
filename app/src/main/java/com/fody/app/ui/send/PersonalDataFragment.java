package com.fody.app.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.R;

public class PersonalDataFragment extends Fragment {

    private PersonalDataViewModel personalDataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personalDataViewModel =
                ViewModelProviders.of(this).get(PersonalDataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personaldata, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        personalDataViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}