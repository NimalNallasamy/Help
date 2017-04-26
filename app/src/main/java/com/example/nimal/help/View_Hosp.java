package com.example.nimal.help;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.nimal.help.Add_Hosp.COUNT;
import static com.google.android.gms.ads.identifier.AdvertisingIdClient.*;

public class View_Hosp extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    public static int count ;
    public static String srt,str;
    public static StringBuffer sb;
    SharedPreferences sharedPreferences;

    private ProgressDialog progressDialog;

    TextView name1, city1, lat1, lon1, spec1, phno1, rate1,view1;
    String name,city,lat,lon,spec,phno,rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__hosp);

        view1 = (TextView) findViewById(R.id.View1);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Oh Crap !", Toast.LENGTH_SHORT).show();
        }

        String uid = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        sb = new StringBuffer();



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
                    srt = data.getKey();
                    //Toast.makeText(Add_Hosp.this, str, Toast.LENGTH_SHORT).show();
                    count = Integer.parseInt(srt);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(), "Server is Busy..", Toast.LENGTH_SHORT).show();
            }
        });

        //Toast.makeText(this, count, Toast.LENGTH_SHORT).show();

    }

    public void readDatabase(View v)
    {
       // Toast.makeText(this, count, Toast.LENGTH_SHORT).show();

        for (int i = 0; i <= count; i++) {


            databaseReference.child("Hospital").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        sb.append(data.getKey() + " : " + data.getValue().toString() + "\n");
                    }
                    sb.append("\n");
                    //Toast.makeText(View_Hosp.this, sb, Toast.LENGTH_SHORT).show();
                    //view1.setText(sb.toString());
                    str = sb.toString();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Server is Busy..", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void setting(View view)
    {
       /* sharedPreferences = getSharedPreferences("Remove_Data", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("String",str);

        editor.commit();*/

        view1.setText(str);
    }
    public void onBackPressed()
    {
        Intent i = new Intent(this,Login1.class);
        startActivity(i);
    }
}