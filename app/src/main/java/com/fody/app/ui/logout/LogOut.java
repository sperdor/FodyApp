package com.fody.app.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.Login;
import com.fody.app.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogOut extends Fragment {

    private LogOutViewModel logOutViewModel;
    private FirebaseAuth auth= FirebaseAuth.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModel =
                ViewModelProviders.of(this).get(LogOutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

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