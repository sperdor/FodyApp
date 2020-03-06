package com.fody.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TypeSelectorLunch extends AppCompatActivity {

    private Button classic;
    private Button functional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selector_lunch);

        classic=findViewById(R.id.btnClassic);
        functional=findViewById(R.id.btnFunctional);

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLunch();
            }
        });
    }

    private void openLunch(){
        Intent intent = new Intent(this,Lunch.class);
        startActivity(intent);
    }
}
