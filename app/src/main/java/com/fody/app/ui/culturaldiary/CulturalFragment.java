package com.fody.app.ui.culturaldiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.R;
import com.fody.app.ui.anamnesis.AnamnesisViewModel;

public class CulturalFragment extends Fragment {

    private CulturalViewModel culturalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        culturalViewModel =
                ViewModelProviders.of(this).get(CulturalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cultural, container, false);




        return root;
    }
}
