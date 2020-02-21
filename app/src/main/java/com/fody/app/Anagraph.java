package com.fody.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Anagraph extends AppCompatActivity implements OnClickListener {

     FirebaseFirestore db;
     EditText firstName;
     EditText lastName;
     EditText phone;
     Button confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagraph);

        db= FirebaseFirestore.getInstance();
        firstName=findViewById(R.id.editFirstName);
        lastName=findViewById(R.id.editLastName);
        phone=findViewById(R.id.editPhoneNumber);
        confirm=findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    String name = firstName.getText().toString().trim();
    String lname= lastName.getText().toString().trim();
    String numberPhone=phone.getText().toString().trim();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

        CollectionReference dbAnagraph =db.collection("anagraph");

        entityAnagraph anagraph = new entityAnagraph(name,lname,numberPhone,uid);
        dbAnagraph.add(anagraph)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                      Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
                      openPersonalData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });


    }

    public void openPersonalData(){

        Intent intent= new Intent(this,PersonalData.class);
        startActivity(intent);
    }
}
