package com.fody.app.ui.home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.BreakfastAdapter;
import com.fody.app.R;
import com.fody.app.entityBreakfast;
import com.fody.app.entityDinner;
import com.fody.app.entityLunch;
import com.fody.app.entitySnack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private Button colorScore;
    private FirebaseUser user;
    private TextView textscore;

    private List<entityDinner> listDinner = new ArrayList<>();
    private List<entityLunch> listLunch = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<entitySnack> listSnack = new ArrayList<>();
    final List<entityBreakfast> listBreakfast = new ArrayList<>();
    private List<Double> listScore = new ArrayList<>();
    final static String TAG ="breakfast";

    @Override
    public void onStart() {
        super.onStart();

        int result = 3;

         result = calculateScore(listBreakfast,listLunch,listSnack,listDinner);
       // int result = 3;
        if (result==0){
            colorScore.setBackgroundResource(R.drawable.shape_drawable0);
            textscore.setText("There are not enough data for color score");
        }


        if(result >=1 && result <2 ){

            colorScore.setBackgroundResource(R.drawable.shape_drawable);
            textscore.setText("Your color is red, you absolutly need to improve you habits - score : 1");

        }else if(result >=2 && result <3){
            colorScore.setBackgroundResource(R.drawable.shape_drawable1);
            textscore.setText("Your color is orange, we suggest you to start improve you lifestyle  - score : 2");

        }else if(result >=3 && result <4){
            colorScore.setBackgroundResource(R.drawable.shape_drawable2);
            textscore.setText("Your color is yellow, is not dangerous but try to better your lifestyle - score : 3");

        }else if(result >=4 && result <4){
            colorScore.setBackgroundResource(R.drawable.shape_drawable3);
            textscore.setText("Your color is light green, very well your lifestyle is pretty right but you can always get better - score : 4");

        }else if(result >=5){
            colorScore.setBackgroundResource(R.drawable.shape_drawable4);
            textscore.setText("Congratulations!,you have top score keep in this way  - score : 5");

        }






    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        colorScore= root.findViewById(R.id.circle_button);
        textscore=root.findViewById(R.id.textScore);

        user = FirebaseAuth.getInstance().getCurrentUser();
        getBreakfast();
        getLunch();
        getSnack();

        getDinner();








        return root;
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

    private int calculateScore ( List<entityBreakfast> b,List<entityLunch> l, List<entitySnack> s,List<entityDinner> d){
        int score = 0;
        listScore.clear();

        if(!b.isEmpty()){
            for(int i =0; i < b.size(); i++){
                listScore.add(b.get(i).getScore());
            }

        }
        if(!l.isEmpty()){
            for(int i =0; i < l.size(); i++){
                listScore.add(l.get(i).getScore());
            }
        }
        if(!s.isEmpty()){

            for(int i =0; i < s.size(); i++){
                listScore.add(s.get(i).getScore());
            }

        }
        if(!d.isEmpty()){
            for(int i =0; i < d.size(); i++){
                listScore.add(d.get(i).getScore());
            }
        }

        if(!listScore.isEmpty()){
            for(int i =0; i < listScore.size(); i++){
                score = score + listScore.get(i).intValue();


            }
            score = score/listScore.size();
        }



        return score;

    }


    private List<entityBreakfast> getBreakfast(){

        db.collection("breakfast").whereEqualTo("uid",user.getUid()).whereEqualTo("date",getCurrentDate()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        listBreakfast.clear();
                        listBreakfast.add(document.toObject(entityBreakfast.class));
                        onStart();


                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        return listBreakfast;

    }
    private List<entityLunch> getLunch(){
        db.collection("lunch").whereEqualTo("uid",user.getUid()).whereEqualTo("date",getCurrentDate()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        listLunch.clear();
                        listLunch.add(document.toObject(entityLunch.class));
                        onStart();

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        return listLunch;

    }
    private List<entitySnack> getSnack(){
        db.collection("snack").whereEqualTo("uid",user.getUid()).whereEqualTo("date",getCurrentDate()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        listSnack.clear();
                        listSnack.add(document.toObject(entitySnack.class));
                        onStart();

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        return listSnack;

    }
    private List<entityDinner> getDinner(){
        db.collection("dinner").whereEqualTo("uid",user.getUid()).whereEqualTo("date",getCurrentDate()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        listDinner.clear();
                        listDinner.add(document.toObject(entityDinner.class));
                        onStart();

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        return listDinner;

    }



}