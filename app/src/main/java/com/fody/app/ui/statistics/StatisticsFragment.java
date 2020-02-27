package com.fody.app.ui.statistics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.R;
import com.fody.app.entityPersonalData;
import com.fody.app.ui.sleep.SleepViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {
    private StatisticsViewModel statisticsViewModel;
    private TextView age;
    private TextView sex;
    private TextView height;
    private TextView wheight;
    private TextView BMI;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<entityPersonalData> retrieveData = new ArrayList<>();
    private static final String TAG= "Statistics";
    private entityPersonalData PData= new entityPersonalData();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statisticsViewModel =
                ViewModelProviders.of(this).get(StatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        user= FirebaseAuth.getInstance().getCurrentUser();
        age= root.findViewById(R.id.textRealAge);
        sex= root.findViewById(R.id.textRealSex);
        height= root.findViewById(R.id.textRealHeight);
        wheight= root.findViewById(R.id.textRealWheight);
        BMI=root.findViewById(R.id.textResultBMI);

        db.collection("PersonalData").whereEqualTo("uid",user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        if (document.getData()!=null)
                        {
                            retrieveData.add(document.toObject(entityPersonalData.class));
                            populateFields(retrieveData.get(0));
                            calculateBMI(retrieveData.get(0));




                        }


                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });


        return root;
    }

    private void populateFields(entityPersonalData data){
        age.setText(data.getAge().toString());
        sex.setText(data.getGender());
        wheight.setText(data.getWeight()+"Kg");
        height.setText(data.getHeight()+"cm");


    }
    private void calculateBMI(entityPersonalData data){

          DecimalFormat df2 = new DecimalFormat("#.##");

         Double kg;
         Double mt;
         Double result;


         kg= Double.parseDouble(data.getWeight());
         mt=Double.parseDouble(data.getHeight());

         result = kg/((mt/100)*(mt/100));
         df2.setRoundingMode(RoundingMode.UP);
         BMI.setText(df2.format(result));


    }
}
