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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private TextView mDisplayDate;
    private EditText Age;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private RadioGroup sex;
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
                dbuser.add(personalData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
                                openHome();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });



            }
        });

        db=FirebaseFirestore.getInstance();

        //region Old Calendar
      /*  mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PersonalData.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };*/
        //endregion
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
    //region OldCheckBox
   /* public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked){
                    Gender = "male";
                }

            else
                    male.setClickable(false);
                    female.setClickable(true);
                break;
            case R.id.female:
                if (checked){
                    Gender = "female";
                }
                // Cheese me
            else
               female.setClickable(false);
                male.setClickable(true);
                break;
        }

    }*/
   //endregion


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
