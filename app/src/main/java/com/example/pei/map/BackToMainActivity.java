package com.example.pei.map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BackToMainActivity extends AppCompatActivity {

    //Open the activity_back_to_main layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_to_main);
    }

    //If the buttom is clicked go to the MapsActivity Java class
    public void MainPage(View view)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
