package com.fody.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.base.Predicate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class FoodSelector extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<entityFood> food = new ArrayList<>();
    private ArrayList<String> strings = new ArrayList<String>();
    private FirebaseAuth mAuth;
    private entityFood classFood;
    private AutoCompleteTextView selectFood;
    private String input;
    final static String TAG ="Food Selector";
    private Button btn;
    private String uid;
    private Integer qty;
    private FirebaseUser user;
    private TextView displayedFood;
    private EditText quantity,calories,fat,proteins,carbo;
    public static final String DATE_FORMAT_2 = "dd-MMM-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         user = FirebaseAuth.getInstance().getCurrentUser();
         uid = user.getUid();
        setContentView(R.layout.activity_food_selector);
        selectFood = findViewById(R.id.autoCompleteTextView);
        displayedFood = findViewById(R.id.textFood);
        quantity=findViewById(R.id.editTextQuantity);
        calories=findViewById(R.id.editTextCalories);
        fat=findViewById(R.id.editTextFat);
        proteins=findViewById(R.id.editTextProteins);
        carbo =findViewById(R.id.editTextCarbo);
        btn= findViewById(R.id.btnSelectFood);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entityBreakfast breakfast = new entityBreakfast();
                breakfast.setUid(uid);

                breakfast.setFood(classFood.id);

                breakfast.setDate(getCurrentDate());

                breakfast.setQuantity(qty);
                breakfast.setNameFood(classFood.name);
                breakfast.setScore(classFood.score);

                db.collection("breakfast")
                        .add(breakfast)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                openBreakfast();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });




        db.collection("T_Food").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        food.add(document.toObject(entityFood.class));
                        strings = toStringArray(food);
                        onStart();




                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });



        selectFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                input = selectFood.getText().toString();
                classFood= stringtoFood(input,food);
                populate(classFood);
            }
        });


        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                populate(classFood);

            }
        });



    }

    public void openBreakfast(){

        Intent intent= new Intent(this,Breakfast.class);
        startActivity(intent);
    }
    public static Date getCurrentDate() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();


        return today;
    }

    @Override
    public void onStart() {
        super.onStart();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strings);
        selectFood.setAdapter(adapter);


    }
    public ArrayList<String> toStringArray (List<entityFood> list){

        ArrayList<String> stringlist = new ArrayList<>();

        if(!list.isEmpty()){
            for(int i=0; i<list.size(); i++){
                stringlist.add(list.get(i).name);

            }

        }

        return stringlist;
    }

    public entityFood stringtoFood(String selectedFood, List<entityFood> entityFoods){

        for(int i=0; i<entityFoods.size(); i++){

            if(entityFoods.get(i).name.equals(selectedFood)){
                return entityFoods.get(i);
            }

        }
        return new entityFood();
    }

    public void populate(entityFood entityFood){


        if(entityFood != null){

            if(quantity.getText().toString().matches("")){
                carbo.setText(entityFood.getCarbo().toString());
                proteins.setText(entityFood.getProteins().toString());
                fat.setText(entityFood.getGrassi().toString());
                calories.setText(entityFood.getCalories().toString());
                displayedFood.setText(entityFood.getName());
                quantity.setText("100");
            }else{
                 qty = Integer.parseInt(quantity.getText().toString());

               entityFood updated = calculator(entityFood,qty);
                carbo.setText(updated.getCarbo().toString());
                proteins.setText(updated.getProteins().toString());
                fat.setText(updated.getGrassi().toString());
                calories.setText(updated.getCalories().toString());
            }

        }
    }



    public entityFood calculator (entityFood entityFood, double qty){


        double cal= 0;
        double car= 0;
        double pro= 0;
        double fat= 0;
        cal= entityFood.getCalories().doubleValue();
        car= entityFood.getCarbo().doubleValue();
        pro= entityFood.getProteins().doubleValue();
        fat= entityFood.getGrassi().doubleValue();
        cal=cal/100*qty;
        car=car/100*qty;
        pro=pro/100*qty;
        fat=fat/100*qty;
        entityFood.setCalories(cal);
        entityFood.setCarbo(car);
        entityFood.setProteins(pro);
        entityFood.setGrassi(fat);


        return entityFood;
    }





}
