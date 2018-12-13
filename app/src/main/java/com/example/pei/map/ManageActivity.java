package com.example.pei.map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageActivity extends AppCompatActivity {
    // Open activity_manage layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
    }

    // Clicking on add button connects to the addEventActivity1 page
    public void add(View view)
    {
        Intent intent = new Intent(this, addEventActivity1.class);
        startActivity(intent);
    }

    // Clicking on remove button connects to the removeEventActivity1 page
    public void remove(View view)
    {
        Intent intent = new Intent(this, removeEventActivity.class);
        startActivity(intent);
    }
}
