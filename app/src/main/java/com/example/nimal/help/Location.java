package com.example.nimal.help;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Location extends AppCompatActivity {


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = android.Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    TextView txt;
    EditText edt;
    SharedPreferences sharedPreferences;
    StringBuffer str;
    public static String u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        sharedPreferences = getSharedPreferences("FromUser", Context.MODE_PRIVATE);
        u = sharedPreferences.getString("User",null).toString();

    }

    public void show_location(View view)
    {
        gps = new GPSTracker(Location.this);
        txt = (TextView) findViewById(R.id.LocationShow);
        double latitude;
        double longitude;

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            String x = "Latitude : " + latitude+ "\nLongitude : " + longitude;
            txt.setText(x);

        }
        else
        {
            gps.showSettingsAlert();
        }
    }
    public void onBackPressed()
    {
        if(u.contentEquals("user")) {
            Intent i = new Intent(this, User1.class);
            startActivity(i);
        }
        else if(u.contentEquals("Login")){
            Intent i = new Intent(this, Login1.class);
            startActivity(i);
        }

    }
}
