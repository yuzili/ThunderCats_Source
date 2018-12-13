package com.example.pei.map;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// This is the location list view page. Contents of the location list view varies with locations selected.
public class MarkerActivity extends AppCompatActivity {
    myDbAdapter helper;

    private TextView temp;
    ListView MarkerEventList;
    String[] event_name;
    String[] event_host;
    String[] event_time;
    String[] event_endtime;
    String[] event_id;

    int location_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);

        helper = new myDbAdapter(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-YY HH:mm:ss");
        String time = format.format(calendar.getTime());
        int day = Integer.parseInt(time.substring(3,5));
        int hour = Integer.parseInt(time.substring(9,11));

        temp = (TextView) findViewById(R.id.NameTextView);
        temp.setText(getIntent().getExtras().getString("PASS_NAME"));

       // CharSequence iloc = temp.getText().toString();

        ImageView image;
        image = (ImageView) findViewById(R.id.imageView);
        String locationName = temp.getText().toString();


        //setting the image and getting the location ID according to the maker clicked
        if (new String("GSU").equals(locationName)) {
            image.setImageResource(R.drawable.gsu);
            location_int = 1;
        } else if (new String("CAS").equals(locationName)) {
            image.setImageResource(R.drawable.cas);
            location_int = 3;
        } else if (new String("FitRec").equals(locationName)) {
            image.setImageResource(R.drawable.fitrec);
            location_int = 2;
        } else if (new String("Questrom").equals(locationName)) {
            image.setImageResource(R.drawable.qus);
            location_int = 4;
        }



        //get today's events' information from the database with the current location ID
        MarkerEventList = (ListView) findViewById(R.id.MarkerEventList);
        event_name=helper.getLocationNamesData(location_int,day,hour);
        event_host=helper.getLocationHostData(location_int,day,hour);
        //helper.getAllDateData(day,hour);
        event_time=helper.getLocationStartData(location_int,day,hour);
        event_endtime=helper.getLocationEndData(location_int,day,hour);
        event_id=helper.getAllIDData(day,hour);



        nameAdapter nameAdapter2 = new nameAdapter(this,event_name,event_host,event_time,event_endtime);
        MarkerEventList.setAdapter(nameAdapter2);

        //passing the information of the event when it is clicked in list view
        MarkerEventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent showDetailEvent = new Intent (getApplicationContext(),clickEventActivity.class);
                //int id=Integer.parseInt(event_id[i]);
                Bundle extras = new Bundle();
                extras.putInt("index", i);
                extras.putInt("listtype",2);
                extras.putInt("location",location_int);
                //showDetailEvent.putExtra("PASSIN",i);
                showDetailEvent.putExtras(extras);
                startActivity(showDetailEvent);
            }
        });



    }

    public void BackToMapView(View view)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}
