package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    // Constant for Logcat identification
    private final String TAG = "LOGIN: ";

    // Variables
    private EditText email, password;
    private Button loginBtn;

    private String mail, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize
        email = findViewById(R.id.loginUser);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

    }

    // Validate user entered data
    private boolean isValid() {
        mail = email.getText().toString().trim();
        pwd = password.getText().toString().trim();

        if (TextUtils.isEmpty(mail)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(pwd)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
            return false;
        } else { return true; }

    }

    private void LoginUser() {
        if (isValid()) {

            // Verify credentials


            //redirect to Login
            startActivity(new Intent(Login.this, StationOwnerMain.class));
        }
    }


}