package com.fody.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TypeSelectorSnack extends AppCompatActivity {

    private Button classic;
    private Button functional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selector_snack);

        classic=findViewById(R.id.btnClassic);
        functional=findViewById(R.id.btnFunctional);

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSnack();
            }
        });
    }

    private void openSnack(){
        Intent intent = new Intent(this,Snack.class);
        startActivity(intent);
    }
}
