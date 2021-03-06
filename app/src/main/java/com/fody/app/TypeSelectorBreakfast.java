package com.fody.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TypeSelectorBreakfast extends AppCompatActivity {

    private Button classic;
    private Button functional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selector_breakfast);

        classic=findViewById(R.id.btnClassic);
        functional=findViewById(R.id.btnFunctional);

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreakfast();
            }
        });
    }

    private void openBreakfast(){
        Intent intent = new Intent(this,Breakfast.class);
        startActivity(intent);
    }
}
