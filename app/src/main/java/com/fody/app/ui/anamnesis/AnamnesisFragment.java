package com.fody.app.ui.anamnesis;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fody.app.R;
import com.fody.app.entityAnamnesis;
import com.fody.app.entityPersonalData;
import com.fody.app.ui.statistics.StatisticsViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AnamnesisFragment extends Fragment  {
    private AnamnesisViewModel anamnesisViewModel;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private entityAnamnesis anamnesis = new entityAnamnesis();
    private Button submit;


    private RadioButton smokeY,smokeN, diseasY,diseasN,surgeryY,surgeryN,geneticY,geneticN,drugsY,drugsN;
    private EditText editDiseas,editSurgery,editGenetic,editDrugs;

    private static final String TAG = "Personal Data";
    @Override
    public void onStart() {
        super.onStart();



    }





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        anamnesisViewModel =
                ViewModelProviders.of(this).get(AnamnesisViewModel.class);
        View root = inflater.inflate(R.layout.fragment_anamnesis, container, false);


        smokeY=root.findViewById(R.id.radioY);
        smokeN=root.findViewById(R.id.radioN);
        diseasY=root.findViewById(R.id.radioDiseasY);
        diseasN=root.findViewById(R.id.radioDiseasN);
        surgeryY=root.findViewById(R.id.radioSurgeryY);
        surgeryN=root.findViewById(R.id.radioSurgeryN);
        geneticY=root.findViewById(R.id.radioGeneticY);
        geneticN=root.findViewById(R.id.radioGeneticN);
        drugsY=root.findViewById(R.id.radioDrugsY);
        drugsN=root.findViewById(R.id.radioDrugsN);
        editDiseas=root.findViewById(R.id.editDiseas);
        editSurgery=root.findViewById(R.id.editSurgery);
        editGenetic=root.findViewById(R.id.editGenetic);
        editDrugs=root.findViewById(R.id.editDrugs);


        smokeY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setSmoke(1);
            }
        });
        smokeN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setSmoke(5);
            }
        });
        diseasY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setDisease(1);
                editDiseas.setEnabled(true );
            }
        });
        diseasN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setDisease(5);
                editDiseas.setEnabled(false );
            }
        });

        surgeryY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setSurgery(3);
                editSurgery.setEnabled(true );
            }
        });
        surgeryN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setSurgery(5);
                editSurgery.setEnabled(false );
            }
        });
        geneticY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setGenetic(3);
                editGenetic.setEnabled(true );
            }
        });
        geneticN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setGenetic(5);
                editGenetic.setEnabled(false );
            }
        });
        drugsY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setDrugs(2);
                editDrugs.setEnabled(true );
            }
        });
        drugsN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesis.setDrugs(5);
                editDrugs.setEnabled(false );
            }
        });
        user= FirebaseAuth.getInstance().getCurrentUser();
        anamnesis.setUid(user.getUid());



        submit=root.findViewById(R.id.btnAnamnesis);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CollectionReference dbuser =db.collection("Anamnesis");


                dbuser.document(user.getUid()).set(anamnesis).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(),"added",Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });


        return root;
    }



    public void submit(){

    }


}
