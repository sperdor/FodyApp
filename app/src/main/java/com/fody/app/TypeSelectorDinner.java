package com.fody.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TypeSelectorDinner extends AppCompatActivity {

    private Button classic;
    private Button functional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selector_dinner);

        classic=findViewById(R.id.btnClassic);
        functional=findViewById(R.id.btnFunctional);

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDinner();
            }
        });
    }

    private void openDinner(){
        Intent intent = new Intent(this,Dinner.class);
        startActivity(intent);
    }
}
