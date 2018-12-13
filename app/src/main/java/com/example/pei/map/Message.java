package com.example.pei.map;

import android.content.Context;
import android.widget.Toast;

//print message for data base
public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}