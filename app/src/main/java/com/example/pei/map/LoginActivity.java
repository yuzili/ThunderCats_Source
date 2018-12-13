package com.example.pei.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText TextUser, TextPass;
    //Login: only when the user name and password match "admin@bu.edu" and "12345" can manage event

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextUser = (EditText)findViewById(R.id.TextUser);
        TextPass = (EditText)findViewById(R.id.TextPass);
    }

    public void SubmitLogin(View view)
    {
        Intent intent = new Intent(this, ManageActivity.class);
        if (TextUser.getText().toString().matches("admin@bu.edu") && (TextPass.getText().toString().matches("12345"))) {
            startActivity(intent);
        }
        else {
           Toast.makeText(LoginActivity.this, "Username/Password is in correct", Toast.LENGTH_LONG).show();
        }
    }

}
