package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class StationDetails extends AppCompatActivity {

    // Constant for REST API for User
//    private final String BASE_URL = "https://fuel-queue-manager-be.azurewebsites.net/refillstation/getstationbyid?id=";
    private final String BASE_URL = "https://fuel-queue-manager-be.azurewebsites.net/queue/updateexsistingqueue";
    // Constant for Logcat identification
    private final String TAG = "STATION_DETAILS: ";

    private TextView name, address, waitTime, petrolAvailable, dieselAvailable, petrolQ, dieselQ;
    private Button checkin, checkout;
    private ImageButton back;
    private String station_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);


        station_id = getIntent().getStringExtra("key");

        // Initialize
        name = findViewById(R.id.stationName);
        address = findViewById(R.id.stationAddress);
        waitTime = findViewById(R.id.waitingTime);
        petrolAvailable = findViewById(R.id.petrolAvailability);
        dieselAvailable = findViewById(R.id.dieselAvailability);
        petrolQ = findViewById(R.id.petrolVehicles);
        dieselQ = findViewById(R.id.dieselVehicles);
        checkin = findViewById(R.id.checkin_btn);
        checkout = findViewById(R.id.checkout_btn);
        back = findViewById(R.id.backBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StationDetails.this, VehicleOwnerMain.class));
            }
        });

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    checkin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    checkout();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // check in method
    private void checkin() throws JSONException {
        // Generate Id
        UUID id = UUID.randomUUID();

        JSONObject queue = new JSONObject();
        queue.put("id", id);
        queue.put("fuelStationReference", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
        queue.put("vehicalOwnerId", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
        queue.put("fuelType", "Petrol");
        queue.put("qInTime", "2022-10-26T16:23:57.061Z");
        queue.put("qOutTime", "");
        queue.put("isFuelAvailable", "true");
        queue.put("numberOfHoursInQueue", 0);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                BASE_URL,
                queue,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        Toast.makeText(getApplicationContext(), "You checked in successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                        Toast.makeText(getApplicationContext(), "Error checking in", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    // check out method
    private void checkout() throws JSONException {
        // Generate Id
        UUID id = UUID.randomUUID();

        JSONObject queue = new JSONObject();
        queue.put("id", id);
        queue.put("fuelStationReference", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
        queue.put("vehicalOwnerId", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
        queue.put("fuelType", "Petrol");
        queue.put("qInTime", "");
        queue.put("qOutTime", "2022-10-26T16:23:57.061Z");
        queue.put("isFuelAvailable", "true");
        queue.put("numberOfHoursInQueue", 0);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                BASE_URL,
                queue,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        Toast.makeText(getApplicationContext(), "You checked out successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                        Toast.makeText(getApplicationContext(), "Error checking out", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

}