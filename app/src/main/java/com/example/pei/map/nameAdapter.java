package com.example.pei.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//put the information of each event in the list view
public class nameAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] event_name;
    String[] event_host;
    String[] event_starttime;
    String[] event_endtime;


    public nameAdapter(Context c, String[] n, String[] h, String[] t, String[] e) {
        event_name = n;
        event_host = h;
        event_starttime = t;
        event_endtime = e;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return event_name.length;
    }

    @Override
    public Object getItem(int i) {
        return event_name[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override

    //set the correct information for that particular event in the assigned text boxes
    public View getView(int i, View view, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.view_event_detail,null);
        TextView nameTextView = (TextView) v.findViewById(R.id.NameTextView);
        TextView hostTextView = (TextView) v.findViewById(R.id.HostTextView);
        TextView timeTextView = (TextView) v.findViewById(R.id.TimeTextView);
        TextView endtimeTextView = (TextView) v.findViewById(R.id.EndTimeTextView);


        String name = event_name[i];
        String host = event_host[i];
        String time = event_starttime[i];
        String endtime = event_endtime[i];


        nameTextView.setText(name);
        hostTextView.setText(host);
        timeTextView.setText(time);
        endtimeTextView.setText(endtime);

        return v;
    }
}
