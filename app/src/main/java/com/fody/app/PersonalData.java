package com.fody.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonalData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //region Declared Variables
    private EditText Age;
    private RadioButton male;
    private RadioButton female;
    private EditText wheight,target,height;
    private Spinner  country,diet,phisicsActivity,workingActivity;
    private TextView bust,waist,highHip,Hip;
    String Gender ="";
    private Button btnConfirm;
    private FirebaseFirestore db ;
    private static final String TAG = "Personal Data";
    //endregion



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        //region Casting
        Age=findViewById(R.id.editTextAge);
        btnConfirm = findViewById(R.id.buttonConfirm);
        male=findViewById(R.id.radioM);

        female=findViewById(R.id.radioF);
        wheight=findViewById(R.id.editTextWeight);
        target=findViewById(R.id.editTextTarget);
        height=findViewById(R.id.editTextHeight);
        country=findViewById(R.id.country);
        diet=findViewById(R.id.diet);
        phisicsActivity=findViewById(R.id.PhisicsActivity);
        workingActivity=findViewById(R.id.WorkingActivity);
        bust=findViewById(R.id.editTextBust);
        waist=findViewById(R.id.editWaist);
        highHip=findViewById(R.id.editTextHighHip);
        Hip=findViewById(R.id.editHip);
        diet.setOnItemSelectedListener(this);
        country.setOnItemSelectedListener(this);
        phisicsActivity.setOnItemSelectedListener(this);
        workingActivity.setOnItemSelectedListener(this);
        //endregion

       btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //region Casting pre insert
                SimpleDateFormat format = new SimpleDateFormat("" + "dd-MM-yyyy'T'HH:mm:ss'Z'");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                String gender = Gender;
                Integer age = Integer.parseInt(Age.getText().toString());
                String Wheigh = wheight.getText().toString();
                String Target = target.getText().toString();
                String Height = wheight.getText().toString();

                String Diet =diet.getSelectedItem().toString();
                String Country =country.getSelectedItem().toString();
                String Physics =phisicsActivity.getSelectedItem().toString();
                String working =workingActivity.getSelectedItem().toString();

                String Bust = bust.getText().toString();
                String Waist = waist.getText().toString();
                String HighHip = highHip.getText().toString();
                String hip = Hip.getText().toString();
                //endregion


                CollectionReference dbuser =db.collection("PersonalData");

                entityPersonalData personalData = new entityPersonalData(uid,gender,age,Wheigh,Target,Height,Diet,Country,Physics,working,Bust,Waist,HighHip,hip);
                dbuser.document(user.getUid()).set(personalData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
                        openHome();
                    }
                });



            }
        });

        db=FirebaseFirestore.getInstance();


    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioM:
                if (checked)
                    Gender="";
                    Gender = "male";
                    break;
            case R.id.radioF:
                if (checked)
                    Gender="";
                    Gender = "female";
                    break;
        }
    }



    private void openHome() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
