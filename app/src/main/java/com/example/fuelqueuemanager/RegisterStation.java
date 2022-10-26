package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelqueuemanager.Utils.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterStation extends AppCompatActivity {

    // Constant for REST API for User
    private final String BASE_URL = "https://fuel-queue-manager-be.azurewebsites.net/refillstation";
    // Constant for Logcat identification
    private final String TAG = "REG_STATION: ";

    // Variables
    private EditText stationName, address, email, password;
    private Button regBtn;

    private String station_name, station_address, station_mail, station_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_station);

        // Initialize
        stationName = findViewById(R.id.regStationName);
        address = findViewById(R.id.regStationAddress);
        email = findViewById(R.id.regStationEmail);
        password = findViewById(R.id.regStationPassword);
        regBtn = findViewById(R.id.regButton);

        // Reg button onclick
        regBtn.setOnClickListener(view -> {
            try {
                AddStation();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }

    // Validate user entered data
    private boolean isValid() {
        station_name = stationName.getText().toString().trim();
        station_address = address.getText().toString().trim();
        station_mail = email.getText().toString().trim();
        station_pwd = password.getText().toString().trim();

        if (TextUtils.isEmpty(station_name)) {
            stationName.setError("Station name cannot be empty");
            stationName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(station_address)) {
            address.setError("Address cannot be empty");
            address.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(station_mail)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(station_pwd)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
            return false;
        } else { return true; }

    }

    // INSERT data to db
    private void AddStation() throws JSONException {

        if (isValid()) {

            JSONObject station_obj = new JSONObject();
            station_obj.put("id", "3fas4564-57d7-4562-b3fc-2e963f66aff6");
            station_obj.put("stationName", "STATION NAME");
            station_obj.put("address", "LOCATION");
            station_obj.put("registrationNumber", "TEST-REG-123");
            Log.i(TAG, "AddStation: " + station_obj.get("address"));

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    BASE_URL,
                    station_obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(TAG, response.toString());

                            //redirect to Login
                            startActivity(new Intent(RegisterStation.this, Login.class));
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, error.toString());
                            Toast.makeText(RegisterStation.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                // Passing some request headers
//                @Override
//                public Map<String, String> getHeaders() {
//                    HashMap<String, String> headers = new HashMap<>();
//                    headers.put("Content-Type", "application/json");
//                    return headers;
//                }
            };

            // Add the request to the RequestQueue
            Volley.newRequestQueue(getApplicationContext()).add(request);

        }
    }
}