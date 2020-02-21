package com.fody.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.logging.Logger;

public class Login extends AppCompatActivity {
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar progressBar;
    private Button buttonRegister;
    private EditText editemail;
    private EditText editpassword;
    private TextView txt;
    private final static String TAG  = "Login";

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
           openHome();
        }



    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        buttonLogin =  findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginUser();
            }
        });


        mAuth = FirebaseAuth.getInstance();

        buttonRegister =  findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        editemail=  findViewById(R.id.editTextEmail);
        editpassword= findViewById(R.id.editTextPassword);

    }

    private void openRegister() {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);

    }
    private void openHome() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);

    }


    private void loginUser(){

        String email = editemail.getText().toString().trim();
        String password = editpassword.getText().toString().trim();

        if(email.isEmpty()){
            editemail.setError("inserire una mail ");
            editemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editpassword.setError("inserire una password");
            editpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editpassword.setError("inserire almeno 6 caratteri");
            editpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        Logger.getLogger("pre source");

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user =mAuth.getCurrentUser();
                    openHome();
                }
                else {
                    Log.e(TAG, "Sign-in Failed: " + task.getException().getMessage());
                    // Or if you don't use Log:
                     System.out.println("Sign-in Failed: " + task.getException().getMessage());
                    Toast.makeText(Login.this,"Erorr Login",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"auth failed",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
