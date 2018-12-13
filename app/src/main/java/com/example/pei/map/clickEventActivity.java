package com.example.pei.map;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class clickEventActivity extends AppCompatActivity {

    //Create string for storing information of the clicked event
    myDbAdapter helper;

    String [] event_name;
    String [] event_host;
    String [] event_startTime;
    String [] event_endTime;
    String [] event_des;
    String [] event_loc;
    String [] event_ID;

    private TextView temp;
    String location;

    // open the activity_click_event layout.
    //Take the current date and time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_event);
        helper = new myDbAdapter(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-YY HH:mm:ss");
        String time = format.format(calendar.getTime());
        int day = Integer.parseInt(time.substring(3,5));
        int hour = Integer.parseInt(time.substring(9,11));


        Intent passed = getIntent();
        Bundle extras = passed.getExtras();
        int index = extras.getInt("index");
        int listtype = extras.getInt("listtype");

        //Two ways to pass get the event information
        //Click from the activity_view_event layout
        //Search all events that happen today and the end time is not pass yet.
        if (listtype == 1) {
            event_name=helper.getAllNamesData(day,hour);
            event_host=helper.getAllHostData(day,hour);
            event_startTime=helper.getAllStartData(day,hour);
            event_endTime=helper.getAllEndData(day,hour);
            event_des=helper.getAllDescriptionData(day,hour);
            event_loc=helper.getAllLocationData(day,hour);
            event_ID=helper.getAllIDData(day,hour);
            String Slocation = event_loc[index];
            if (Slocation.equals("1")) {
                location = "GSU";
            }
            else if (Slocation.equals("2")){
                location = "FitRec";
            }
            else if (Slocation.equals("3")) {
                location = "CAS";
            }
            else if (Slocation.equals("4")){
                location = "Questrom";
            }

        }

        //Click from the activity_marker layout.
        //Search all events at that location that happen today and the end time is not pass yet.
        else if (listtype == 2){
            int loc = extras.getInt("location");
            event_name=helper.getLocationNamesData(loc,day,hour);
            event_host=helper.getLocationHostData(loc,day,hour);
            event_startTime=helper.getLocationStartData(loc,day,hour);
            event_endTime=helper.getLocationEndData(loc,day,hour);
            event_des=helper.getLocationDescriptionData(loc,day,hour);
            event_ID=helper.getLocationIDData(loc,day,hour);

            if (loc == 1) {
                location = "GSU";
            }
            else if (loc == 2){
                location = "FitRec";
            }
            else if (loc == 3) {
                location = "CAS";
            }
            else if (loc == 4){
                location = "Questrom";
            }
        }


        //Use the pass in ID to obtain the information of the specific clicked event
        String name = event_name[index];
        String host = event_host[index];
        String description = event_des[index];
        String starttime = event_startTime[index];
        String endtime = event_endTime[index];
        String thisid = event_ID[index];


        //Display all the information on the activity
        TextView nameTextView = (TextView)findViewById(R.id.NameTextView);
        TextView hostTextView = (TextView)findViewById(R.id.HostTextView);
        TextView timeTextView = (TextView)findViewById(R.id.TimeTextView);
        TextView descTextView = (TextView)findViewById(R.id.DescriptionTextView);
        TextView locTextView = (TextView)findViewById(R.id.LocationTextView);
        TextView endTextView = (TextView)findViewById(R.id.EndTimeTextView);
        TextView eventidTextView =(TextView)findViewById(R.id.EventidText);


        nameTextView.setText(name);
        hostTextView.setText(host);
        timeTextView.setText(starttime);
        descTextView.setText(description);
        locTextView.setText(location);
        endTextView.setText(endtime);
        eventidTextView.setText(thisid);



    }

}
