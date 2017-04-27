package com.example.nimal.help;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Help extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 2;
    public static double latitude;
    public static double longitude;
    String mPermission = android.Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    EditText area,city;
    ProgressDialog progressDialog;
    TextView show;
    static StringBuffer sb,sb1;
    static String str,srt,phone,ar;
    static int count,fun,nuf;
    static double dist=1000000000,rand;
    static double hi[] = new double[2] ;
    static int round = 0;


    DatabaseReference databaseReference;

    Snackbar s;

    CoordinatorLayout coordinatorLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        area = (EditText)findViewById(R.id.Area);
        show = (TextView)findViewById(R.id.ShowSugg);
        coordinatorLayout1 = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        sb = new StringBuffer();
        sb1 = new StringBuffer();

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

    }

    public void retreival(View view)
    {

        gps = new GPSTracker(Help.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude =  gps.getLatitude();
            longitude = gps.getLongitude();
             s = Snackbar.make(coordinatorLayout1,"Latitude : "+latitude+"\nLongitude : "+longitude,Snackbar.LENGTH_LONG)
                    .setAction("HIDE", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.dismiss();
                }
            });
            //Toast.makeText(this, latitude+" , "+longitude, Toast.LENGTH_SHORT).show();
            s.show();

        }
        else
        {
            gps.showSettingsAlert();
        }

        for (int i = 0; i <= count; i++) {


            databaseReference.child("Hospital").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        sb.append(data.getKey()+" : "+data.getValue().toString() + "\n");
                        round = 1;
                    }
                    if(round == 1) {
                        sb.append("\n");
                        round = 0;
                    }
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

    public void suggestion(View view)
    {
        String areas = area.getText().toString().trim();
        String[] ss = str.split("\n\n");
        int r=0;

        for(int i=0;i<count;i++)
        {
            String ss1[] = ss[i].split("\n");
            if(ss[i].contains(areas))
            {
                //sb1.append(ss[i]);
                double r1 = lol(ss1[1]);
                double r2 = lol(ss1[2]);
                rand =  Math.hypot(Math.abs(r1-latitude),Math.abs(r2-longitude));
                if((rand < dist && (lol(ss1[5]))>4))
                {
                    dist = rand;
                    hi[0] = dist;
                    fun = i;
                    ar = ss[1];
                    //show.setText(ss[i]);
                }
                else if(rand < dist && (lol(ss1[5]))<4)
                {
                    dist = rand;
                    hi[1] = dist;
                    nuf = i;
                    ar = ss[1];
                    //show.setText(ss[i]);
                }
                else if(!ss[i].contains(ar))
                {
                    dist = rand;
                    hi[0] = dist;
                    hi[1] = dist;
                    fun = i;
                }

            }
            else
            {
                r = r+1;
            }

        }
       // r=r-1;
        if(r==count)
        {
            show.setText("Speciality Not found");
        }
        else {
            if (Math.abs(hi[0] - hi[1]) < 1) {
                phone = ss[fun];
                show.setText(ss[fun]);
            } else {
                phone = ss[fun];
                show.setText(ss[nuf]);
            }
        }
    }
    public double lol(String s)
    {
        String [] r = s.split(" : ");
        double f = Double.parseDouble(r[1]);
        return f;
    }

    public void newing(View view)
    {
        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);

        fab1.setVisibility(view.VISIBLE);
        fab2.setVisibility(view.VISIBLE);
    }
    public void call(View view)
    {
        String[] number1 = phone.split("\n");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number1[4]));
        startActivity(intent);
    }
    public void messaging(View view)
    {
        String[] number1 = phone.split("\n");
        String[] number2 = number1[4].split(" : ");
        Intent intentsms = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + number2[1] ) );
        intentsms.putExtra( "Emergeny", "Bringing a patient" );
        startActivity( intentsms );

        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number1[4], "hello")));

    }


}
