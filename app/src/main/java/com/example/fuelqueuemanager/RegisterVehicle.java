package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelqueuemanager.Utils.DBHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class RegisterVehicle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Constant for REST API for User
    private final String BASE_URL = "https://fuel-queue-manager-be.azurewebsites.net/vehicleuser";
    // Constant for Logcat identification
    private final String TAG = "REG_VEHICLE: ";

    // Variables
    private EditText vehicleOwnerName, number, vehicleAddress,  email, password;
    private Button regBtn;
    private Spinner spinner;

    private String v_owner_name, v_number, v_mail, v_pwd, v_fuelTYpe, v_address;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);

        dbHelper = new DBHelper(this);

        // Initialize
        vehicleOwnerName = findViewById(R.id.regVehicleOwnerName);
        number = findViewById(R.id.regVehicleNo);
        vehicleAddress = findViewById(R.id.regVLocation);
        email = findViewById(R.id.regVOwnerEmail);
        password = findViewById(R.id.regVOwnerPassword);
        regBtn = findViewById(R.id.regButton);
        spinner = findViewById(R.id.spinnerFuelType);

        // Created ArrayAdapter with custom spinner layout
        ArrayAdapter adapterCategory = ArrayAdapter.createFromResource(this, R.array.fuel_types, R.layout.custom_spinner);

        // Specify the custom layout to use when the list of choices appears
        adapterCategory.setDropDownViewResource(R.layout.custom_spinner);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapterCategory);
        spinner.setOnItemSelectedListener(this);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegUser();
//                try {
//                    AddVehicle();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    // Validate user entered data
    private boolean isValid() {
        v_owner_name = vehicleOwnerName.getText().toString().trim();
        v_number = number.getText().toString().trim();
        v_address = vehicleAddress.getText().toString().trim();
        v_mail = email.getText().toString().trim();
        v_pwd = password.getText().toString().trim();
        v_fuelTYpe = spinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(v_owner_name)) {
            vehicleOwnerName.setError("Owner name cannot be empty");
            vehicleOwnerName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(v_number)) {
            number.setError("Vehicle Number cannot be empty");
            number.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(v_address)) {
            vehicleAddress.setError("Vehicle location cannot be empty");
            vehicleAddress.requestFocus();
            return false;
        } else if (v_fuelTYpe.equals("Fuel Type")) {
            Toast.makeText(RegisterVehicle.this, "Please select the fuel type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(v_mail)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(v_pwd)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
            return false;
        } else { return true; }

    }

    // INSERT data to db
    private void AddVehicle() throws JSONException {

        if (isValid()) {

            // Generate Id
            UUID id = UUID.randomUUID();
            JSONObject station_obj = new JSONObject();
//            station_obj.put("id", "3fas4564-57d7-4562-b3fc-2e963f66aff6");
//            station_obj.put("username", "NAME");
//            station_obj.put("address", "LOCATION");
//            station_obj.put("fuelType", "Petrol");

            station_obj.put("id", id);
            station_obj.put("username", v_owner_name);
            station_obj.put("address", v_address);
            station_obj.put("fuelType", v_fuelTYpe);
            station_obj.put("vehicleNumber", v_number);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    BASE_URL,
                    station_obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(TAG, response.toString());

                            // Register to local db
                            RegUser();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    }
            ) {
            };

            // Add the request to the RequestQueue
            Volley.newRequestQueue(getApplicationContext()).add(request);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //display the selection in a toast
        Toast.makeText(this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void RegUser() {

//        boolean register = dbHelper.insertData(v_mail,v_pwd, "vehicle");
        boolean register = dbHelper.insertData("ts@mail.com","ss", "vehicle");

        if(register){
            Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
            //redirect to Login
            startActivity(new Intent(RegisterVehicle.this, Login.class));
        } else {
            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();

        }
    }
}