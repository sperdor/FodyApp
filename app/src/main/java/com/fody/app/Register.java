package com.fody.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ProgressBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    private EditText editemail, editpassword;
    private Button buttonLogin;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


       buttonLogin=findViewById(R.id.buttonLogin);
       buttonLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


            registerUser();
           }
       });

        editpassword = findViewById(R.id.editTextPassword);
        editemail = findViewById(R.id.editTextEmail);

        mAuth = FirebaseAuth.getInstance();

    }

    public void registerUser(){
        String email = editemail.getText().toString();
        String password = editpassword.getText().toString();

        if(email.isEmpty()){
            editemail.setError("Inserire una mail");
            editemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editpassword.setError("Inserire una password");
            editpassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Please enter a valid email");
            editemail.requestFocus();
            return;
        }
        if(password.length()< 6){
            editpassword.setError("Inserire una password di almeno 6 caratteri");
            editpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(Register.this, PersonalData.class));
                    openAnagraph();
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }





    private void openAnagraph() {
        Intent intent = new Intent(this,Anagraph.class);
        startActivity(intent);

    }




}
