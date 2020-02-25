package com.fody.app.ui.personaldata;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.DinnerAdapter;
import com.fody.app.R;
import com.fody.app.entityBreakfast;
import com.fody.app.entityDinner;
import com.fody.app.entityFood;
import com.fody.app.entityPersonalData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PersonalDataFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText Age;
    private RadioButton male;
    private RadioButton female;
    private EditText wheight,target,height;
    private Spinner  country,diet,phisicsActivity,workingActivity;
    private TextView bust,waist,highHip,Hip;
    private List<entityPersonalData> retrieveData = new ArrayList<>();
    String Gender ="";
    private Button btnConfirm;

    private static final String TAG = "Personal Data";
    @Override
    public void onStart() {
        super.onStart();
        user=FirebaseAuth.getInstance().getCurrentUser();
        db.collection("PersonalData").whereEqualTo("uid",user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        if (document.getData()!=null)
                        {

                            retrieveData.add(document.toObject(entityPersonalData.class));
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }


                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personaldata, container, false);
        Age=root.findViewById(R.id.editTextAge);
        btnConfirm = root.findViewById(R.id.buttonConfirm);
        male=root.findViewById(R.id.radioM);

        female=root.findViewById(R.id.radioF);
        wheight=root.findViewById(R.id.editTextWeight);
        target=root.findViewById(R.id.editTextTarget);
        height=root.findViewById(R.id.editTextHeight);
        country=root.findViewById(R.id.country);
        diet=root.findViewById(R.id.diet);
        phisicsActivity=root.findViewById(R.id.PhisicsActivity);
        workingActivity=root.findViewById(R.id.WorkingActivity);
        bust=root.findViewById(R.id.editTextBust);
        waist=root.findViewById(R.id.editWaist);
        highHip=root.findViewById(R.id.editTextHighHip);
        Hip=root.findViewById(R.id.editHip);
        diet.setOnItemSelectedListener(this);
        country.setOnItemSelectedListener(this);
        phisicsActivity.setOnItemSelectedListener(this);
        workingActivity.setOnItemSelectedListener(this);





        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}