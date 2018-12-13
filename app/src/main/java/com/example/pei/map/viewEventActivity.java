package com.example.pei.map;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// All event list view page
public class viewEventActivity extends AppCompatActivity {
    //data base
    myDbAdapter helper;

    ListView EventListView;
    String[] event_name;
    String[] event_host;
    String[] event_time;
    String[] event_endtime;
    String[] event_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        helper = new myDbAdapter(this);

        //find the current time and date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-YY HH:mm:ss");
        String time = format.format(calendar.getTime());
        int day = Integer.parseInt(time.substring(3,5));
        int hour = Integer.parseInt(time.substring(9,11));


        EventListView = (ListView) findViewById(R.id.EventListView);
        //pass the string here
        event_name=helper.getAllNamesData(day,hour);
        event_host=helper.getAllHostData(day,hour);
        event_time=helper.getAllStartData(day,hour);
        event_endtime=helper.getAllEndData(day,hour);
        event_id=helper.getAllIDData(day,hour);


        nameAdapter nameAdapter = new nameAdapter(this,event_name,event_host,event_time,event_endtime);
        EventListView .setAdapter(nameAdapter);


        //pass the index and the event to the view detail page
        EventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //find thr i th element in id and pass it
                //int id = Integer.parseInt(event_id[i]);
                //Toast.makeText(viewEventActivity.this, "id is "+i,Toast.LENGTH_LONG).show();
                Intent showDetailEvent = new Intent(getApplicationContext(), clickEventActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("index", i);
                extras.putInt("listtype",1);
                //showDetailEvent.putExtra("PASSIN",i);
                showDetailEvent.putExtras(extras);
                startActivity(showDetailEvent);
            }
        });

    }

    public void LoginPage(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void MapView(View view)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
