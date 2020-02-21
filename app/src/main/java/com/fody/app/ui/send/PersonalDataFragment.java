package com.fody.app.ui.send;

import android.content.Intent;
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

import com.fody.app.FoodSelector;
import com.fody.app.Login;
import com.fody.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PersonalDataFragment extends Fragment {

    private PersonalDataViewModel personalDataViewModel;
    private FirebaseAuth auth= FirebaseAuth.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personalDataViewModel =
                ViewModelProviders.of(this).get(PersonalDataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personaldata, container, false);

        try {

            auth.signOut();

        }catch (Exception e){





        }
         openLogin();


        return root;
    }

    private void openLogin(){

        Intent intent = new Intent(getContext(), Login.class);

        startActivity(intent);
    }
}