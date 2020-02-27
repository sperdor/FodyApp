package com.fody.app.ui.anamnesis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.R;
import com.fody.app.ui.statistics.StatisticsViewModel;

public class AnamnesisFragment extends Fragment {
    private AnamnesisViewModel anamnesisViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        anamnesisViewModel =
                ViewModelProviders.of(this).get(AnamnesisViewModel.class);
        View root = inflater.inflate(R.layout.fragment_anamnesis, container, false);




        return root;
    }

}
