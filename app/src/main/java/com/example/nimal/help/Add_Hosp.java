package com.example.nimal.help;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Hosp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    public static int COUNT ;
    public static String str;

    EditText hname, hcity, latitude, longitude, spec, phno;
    EditText rb;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__hosp);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(this, "No User has loged in !!", Toast.LENGTH_SHORT).show();
        }

        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        hname = (EditText) findViewById(R.id.hname);
        hcity = (EditText) findViewById(R.id.hcity);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        spec = (EditText)findViewById(R.id.speciality);
        phno = (EditText)findViewById(R.id.phno);
        rb = (EditText)findViewById(R.id.Rating);
        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        databaseReference.child("Hospital").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot data  : dataSnapshot.getChildren())
                {
                    str = data.getKey();
                    //Toast.makeText(Add_Hosp.this, str, Toast.LENGTH_SHORT).show();
                    COUNT = Integer.parseInt(str);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(), "Server is Busy..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == add) {

            String name, city, lat, lon, sp, ph,rating;

            name = hname.getText().toString().trim();
            city = hcity.getText().toString().trim();
            lat = latitude.getText().toString().trim();
            lon = longitude.getText().toString().trim();
            sp = spec.getText().toString().trim();
            ph = phno.getText().toString().trim();
            rating = rb.getText().toString().trim();

            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, city, Toast.LENGTH_SHORT).show();

            //databaseReference.child("Hospital").child("Hospital Name").setValue(name);
            COUNT = COUNT+1;

            databaseReference.child("Hospital").child(String.valueOf(COUNT)).child("Name").setValue(name);
            databaseReference.child("Hospital").child(String.valueOf(COUNT)).child("City").setValue(city);
            databaseReference.child("Hospital").child(String.valueOf(COUNT)).child("Latitude").setValue(lat);
            databaseReference.child("Hospital").child(String.valueOf(COUNT)).child("Longitude").setValue(lon);
            databaseReference.child("Hospital").child(String.valueOf(COUNT)).child("Speciality").setValue(sp);
            databaseReference.child("Hospital").child(String.valueOf(COUNT)).child("Phone").setValue(ph);
            databaseReference.child("Hospital").child(String.valueOf(COUNT)).child("Rating").setValue(rating);

            Toast.makeText(this, "Data Saved !!", Toast.LENGTH_SHORT).show();

        }
    }
    public void onBackPressed()
    {
        Intent i = new Intent(Add_Hosp.this,Login1.class);
        startActivity(i);
    }
}
