package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegMenu extends AppCompatActivity {

    private Button regVehicle, regStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_menu);

        // Initialize
        regStation = findViewById(R.id.regStationButton);
        regVehicle = findViewById(R.id.regVehicleBtn);

        regStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegMenu.this, RegisterStation.class));
                finish();
            }
        });

        regVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegMenu.this, RegisterVehicle.class));
                finish();
            }
        });
    }
}