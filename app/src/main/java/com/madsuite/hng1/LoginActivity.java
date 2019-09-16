package com.madsuite.hng1;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.madsuite.hng1.R;



public class LoginActivity extends AppCompatActivity {

   // public static android.content.SharedPreferences sharedpreferences = null;

    private static final String USERS = "RegisterActivity";

    // User Session Manager Class
    UserSession session;

    private SharedPreferences sharedpreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button signUpButton = findViewById(R.id.signUp);
        final ProgressBar loadingProgressBar = findViewById(R.id.progressBar);

        // User Session Manager
        session = new UserSession(getApplicationContext());

        if(session.isUserLoggedIn())
        {
            Intent main = new Intent(LoginActivity.this, DashActivity.class);
            startActivity(main);
        }


        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();

        sharedpreferences = getSharedPreferences(USERS, Context.MODE_PRIVATE);

        // Login button click event
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                loadingProgressBar.setVisibility(View.VISIBLE);

                // Get email, password from EditText
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validate if email, password is filled
                if(email.trim().length() > 0 && password.trim().length() > 0){
                    String uEmail = null;
                    String uPassword =null;


                    if (sharedpreferences.contains("Email"))
                    {
                        uEmail = sharedpreferences.getString("Email", "");

                    }

                    if (sharedpreferences.contains("Password"))
                    {
                        uPassword = sharedpreferences.getString("Password", "");

                    }

                    // Object uName = null;
                    // Object uEmail = null;
                    if(email.equals(uEmail) && password.equals(uPassword)){

                        session.createUserLoginSession(uEmail, uPassword);

                        // Starting DashActivity
                        Intent i = new  Intent(getApplicationContext(),DashActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        finish();

                    }else{

                        // username / password doesn't match&
                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();

                    }
                }else{

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();

                }

            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
