package com.example.pei.map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Remove event page
public class removeEventActivity extends AppCompatActivity {
    myDbAdapter helper;
    EditText DeleteIDText;
    //private TextView temp;
    int DeleteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_event);
        helper = new myDbAdapter(this);
        DeleteIDText = (EditText)findViewById(R.id.DeleteIDText);
        /*DeleteID = Integer.parseInt(DeleteIDText.getText().toString());
        helper.deleteEvent(DeleteID);*/
    }

    // delete function that deletes events with Event ID input
    public void delete(View view)
    {
        DeleteID = Integer.parseInt(DeleteIDText.getText().toString());
        int count = helper.deleteEvent(DeleteID);
        if (count > 0){
        Intent intent = new Intent(this, removeBackToMainActivity.class);
        startActivity(intent);}
        else{
            Toast.makeText(removeEventActivity.this, "Remove Event Fail",Toast.LENGTH_LONG).show();
        }
    }
}
