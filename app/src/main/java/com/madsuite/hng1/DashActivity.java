package com.madsuite.hng1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.madsuite.hng1.UserSession.USERS;

public class DashActivity extends AppCompatActivity {


    private static final String USERS = "RegisterActivity";

    private SharedPreferences sharedpreferences;
    // User Session Manager Class
    UserSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);


        // User Session Manager
        session = new UserSession(getApplicationContext());
        final TextView nameText = findViewById(R.id.nameTextView);
        final TextView emailText = findViewById(R.id.emailTextView);
        final TextView phoneText = findViewById(R.id.phoneTextView);
        final TextView dobText = findViewById(R.id.dobTextView);

        final Button signOutButton = findViewById(R.id.signOut);
        // Get the app's shared preferences
        sharedpreferences = getApplicationContext().getSharedPreferences("RegisterActivity", 0);


        nameText.setText(sharedpreferences.getString("Name", ""));
        emailText.setText(sharedpreferences.getString("Email", ""));
        phoneText.setText(sharedpreferences.getString("Phone", ""));
        dobText.setText(sharedpreferences.getString("DOB", ""));

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Intent intent = new Intent(DashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
