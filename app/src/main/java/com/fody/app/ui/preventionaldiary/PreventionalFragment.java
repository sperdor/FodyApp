package com.fody.app.ui.preventionaldiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.R;
import com.fody.app.ui.anamnesis.AnamnesisViewModel;

public class PreventionalFragment extends Fragment {
    private PreventionalViewModel preventionalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        preventionalViewModel =
                ViewModelProviders.of(this).get(PreventionalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_preventional, container, false);




        return root;
    }
}
