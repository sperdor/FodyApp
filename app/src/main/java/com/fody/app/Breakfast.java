package com.fody.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Breakfast extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<entityBreakfast> breakfast = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    Date date = new Date();
    public static final String DATE_FORMAT_2 = "dd-MMM-yyyy";
    final static String TAG ="breakfast";

    @Override
    public void onStart() {
        super.onStart();
        user=FirebaseAuth.getInstance().getCurrentUser();
        db.collection("breakfast").whereEqualTo("uid",user.getUid()).whereEqualTo("date",getCurrentDate()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        breakfast.clear();
                        breakfast.add(document.toObject(entityBreakfast.class));
                        mAdapter = new BreakfastAdapter(breakfast);
                        recyclerView.setAdapter(mAdapter);

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openFoodSelector();

            }
        });



        mAuth = FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    private void openFoodSelector(){

        Intent intent = new Intent(this,FoodSelector.class);

        startActivity(intent);
    }


}
