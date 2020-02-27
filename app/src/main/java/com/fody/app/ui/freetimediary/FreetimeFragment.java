package com.fody.app.ui.freetimediary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.R;
import com.fody.app.ui.anamnesis.AnamnesisViewModel;

public class FreetimeFragment extends Fragment {
    private FreetimeViewModel freetimeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        freetimeViewModel =
                ViewModelProviders.of(this).get(FreetimeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_freetime, container, false);




        return root;
    }
}
