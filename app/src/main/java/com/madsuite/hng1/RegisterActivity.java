package com.madsuite.hng1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences.Editor;

public class RegisterActivity extends AppCompatActivity {



    SharedPreferences sharedpreferences;
    Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button registerButton = findViewById(R.id.registerbtn);
        final EditText nameEditText = findViewById(R.id.nameEditText);
        final EditText emailEditText = findViewById(R.id.emailEditText);
        final EditText phoneEditText = findViewById(R.id.phoneEditText);
        final EditText dobEditText = findViewById(R.id.dobEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        final EditText cPasswordEditText = findViewById(R.id.cPasswordEditText);
        sharedpreferences = getApplicationContext().getSharedPreferences("RegisterActivity", 0);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save details using sharedpref
                String n  = nameEditText.getText().toString();
                String e  = emailEditText.getText().toString();
                String ph  = phoneEditText.getText().toString();
                String dob  = dobEditText.getText().toString();
                String pw  = passwordEditText.getText().toString();
                String cPwd  = cPasswordEditText.getText().toString();


                if(n == null || nameEditText.getText().length()<= 4){
                    Toast.makeText(RegisterActivity.this, "Name must be more than 4 characters", Toast.LENGTH_SHORT).show();
                }
                else if( e.length()<=8 || !e.contains("@")){
                    Toast.makeText(RegisterActivity.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                }
                else if( ph.length()<=0 || !e.contains("@")){
                    Toast.makeText(RegisterActivity.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
                }
                else if( dob.length()<=0){
                    Toast.makeText(RegisterActivity.this, "Enter DOB", Toast.LENGTH_SHORT).show();
                }
                else if( pw.length()<=7){
                    Toast.makeText(RegisterActivity.this, "Password must exceed 8 characters", Toast.LENGTH_SHORT).show();
                }
                else if( !pw.contentEquals(cPwd)){
                    Toast.makeText(RegisterActivity.this, "Password must match!", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Name", n);
                    editor.putString("Email", e);
                    editor.putString("Phone", ph);
                    editor.putString("DOB", dob);
                    editor.putString("Password", pw);
                    editor.commit();
                    Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_LONG).show();

                }
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

            }
        });
    }
}
