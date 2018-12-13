package com.example.pei.map;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//local database set up
public class myDbAdapter {

    //myDbAdapter Constructor Function
    myDbHelper myhelper;

    //
   public  myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    //Insert Event information into the SQLite Database Table. Each event takes a row of the table.
    public long insertData(String name, String host, String description, int location, int date, int starttime, int endtime)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.HOST, host);
        contentValues.put(myDbHelper.DESCRIPTION, description);
        contentValues.put(myDbHelper.LOCATION, location);
        contentValues.put(myDbHelper.DATE, date);
        contentValues.put(myDbHelper.STARTTIME, starttime);
        contentValues.put(myDbHelper.ENDTIME, endtime);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    //get all the events in the database table. Display everything in Buffer.
    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.HOST};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.HOST));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    //Get all the unfinished events' name that happens today at a specific location and return a String Array containing all the names.
    public String[] getLocationNamesData(int location, int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.NAME};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,myDbHelper.LOCATION +" = " + location + " AND " + myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.STARTTIME + " ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();


        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(name);
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }
    //Get all the unfinished events' host name that happens today at a specific location and return a String Array containing all the host name.
    public String[] getLocationHostData(int location, int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.HOST};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,myDbHelper.LOCATION + " = " + location +" AND " + myDbHelper.DATE + " = " + date + " AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.STARTTIME +" ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();


        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.HOST));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(name);
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' id that happens today at a specific location and return a String Array containing all the id.
    public String[] getLocationIDData(int location, int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,myDbHelper.LOCATION + " = " + location + " AND " + myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.STARTTIME + " ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();


        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            //String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(Integer.toString(cid));

        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' start time that happens today at a specific location and return a String Array containing all the start time.
    public String[] getLocationStartData(int location, int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.STARTTIME};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,myDbHelper.LOCATION + " = " + location +" AND " + myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.STARTTIME + " ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();


        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.STARTTIME));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(Integer.toString(cid));

        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' description that happens today at a specific location and return a String Array containing all the description.
    public String[] getLocationDescriptionData(int location, int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.DESCRIPTION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,myDbHelper.LOCATION +" = " + location + " AND " + myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.STARTTIME + " ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();

        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(name);
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' end time happens today at a specific location and return a String Array containing all the end time.
    public String[] getLocationEndData(int location, int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.ENDTIME};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,myDbHelper.LOCATION + " = " + location +" AND " + myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.STARTTIME + " ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();


        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.ENDTIME));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(Integer.toString(cid));

        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' name that happens today and return a String Array containing all the names.
    public String[] getAllNamesData(int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.NAME};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns, myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.DATE + " ASC, "+ myDbHelper.STARTTIME + " ASC ");
        //StringBuffer buffer= new StringBuffer();
        //int index = 0;
        //String[] nameArray = new String[100];
        List<String> temparray = new ArrayList<>();

        while (cursor.moveToNext())
        {
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            temparray.add(name);
            //nameArray[index] = name;
            //index ++;
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' host name that happens today and return a String Array containing all the host names.
    public String[] getAllHostData(int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.HOST};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns, myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.DATE + " ASC, "+ myDbHelper.STARTTIME + " ASC ");
        List<String> temparray = new ArrayList<>();


        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.HOST));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(name);

        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' description that happens today and return a String Array containing all the description.
    public String[] getAllDescriptionData(int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.DESCRIPTION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns, myDbHelper.DATE + " = " + date + " AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.DATE + " ASC, "+ myDbHelper.STARTTIME + " ASC ");
        List<String> temparray = new ArrayList<>();


        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(name);

        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' id that happens today and return a String Array containing all the id.
    public String[] getAllIDData(int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns, myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.DATE +" ASC, "+ myDbHelper.STARTTIME +" ASC ");
        List<String> temparray = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            //String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(Integer.toString(cid));
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' start time that happens today and return a String Array containing all the start time.
    public String[] getAllStartData(int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.STARTTIME};
        Log.e("In query","ALLSTARTSTUFF");
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns, myDbHelper.DATE + " = " + date +" AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.DATE+" ASC, "+ myDbHelper.STARTTIME+" ASC ");
        List<String> temparray = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.STARTTIME));
            //String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(Integer.toString(cid));
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }

    //Get all the unfinished events' end time that happens today and return a String Array containing all the end time.
    public String[] getAllEndData(int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.ENDTIME};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns, myDbHelper.DATE + " = " + date + " AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.DATE+" ASC, "+ myDbHelper.STARTTIME+" ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.ENDTIME));
            //String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            temparray.add(Integer.toString(cid));
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;
    }


    //Get all the unfinished events' location that happens today and return a String Array containing all the location.
    public String[] getAllLocationData(int date, int hour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.LOCATION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns, myDbHelper.DATE + " = " + date + " AND " + myDbHelper.ENDTIME + " > " + hour,null,null,null,myDbHelper.DATE+" ASC, " + myDbHelper.STARTTIME+" ASC ");
        //StringBuffer buffer= new StringBuffer();
        List<String> temparray = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.LOCATION));
            temparray.add(Integer.toString(cid));
        }
        String [] nameArray = new String[temparray.size()];
        for(int i = 0; i < temparray.size(); i++) nameArray[i] = temparray.get(i);
        return nameArray;    }



    //Delete Event base on the id
    public  int deleteEvent(int ID)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        //Integer[] whereArgs ={ID};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.UID +" = " + ID, null);
        return  count;
    }

    //Delete event base on name
    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME +" = ?",whereArgs);
        return  count;
    }

    //Update event
    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME +" = ?",whereArgs );
        return count;
    }

    //Create a myDbHelper class to Establish the database table
    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String HOST= "Host";    // Column III
        private static final String DESCRIPTION= "Description";    // Column III
        private static final String LOCATION= "Location";    // Column III
        private static final String DATE= "Date";    // Column III
        private static final String STARTTIME= "Start_Time";    // Column III
        private static final String ENDTIME= "End_Time";    // Column III

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +NAME+ " VARCHAR(255)," + HOST+ " VARCHAR(255),"+DESCRIPTION+" VARCHAR(255), "+LOCATION +" INTEGER, " + DATE+" INTEGER, "+STARTTIME+" INTEGER, "+ENDTIME+" INTEGER);";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                com.example.pei.map.Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                com.example.pei.map.Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {

                com.example.pei.map.Message.message(context,""+e);
            }
        }
    }
}
