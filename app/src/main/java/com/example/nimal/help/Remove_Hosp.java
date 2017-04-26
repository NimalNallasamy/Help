package com.example.nimal.help;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Remove_Hosp extends AppCompatActivity {

    TextView tv;
    EditText key;

    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    public static String srt,str;
    public static int count;
    public static StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__hosp);

        tv = (TextView)findViewById(R.id.ShowDetails1);
        key = (EditText)findViewById(R.id.Key);

        //sharedPreferences = getSharedPreferences("Remove_Data", Context.MODE_PRIVATE);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);


        //String str = sharedPreferences.getString("String",null);

        //tv.setText(str);
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
    public void remove(View view)
    {
        String keying = key.getText().toString().trim();
        databaseReference.child("Hospital").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    if(key.getText().toString().equalsIgnoreCase(data.getKey()))
                    {
                        data.getRef().removeValue();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Server is Busy..", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, "Sucessfully Removed", Toast.LENGTH_SHORT).show();
    }

    public void read(View view)
    {
        sb = new StringBuffer();
        for (int i = 0; i <= count; i++) {

            databaseReference.child("Hospital").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        sb.append(data.getKey() + " : " + data.getValue().toString() + "\n");
                        str = sb.toString();
                    }
                    sb.append("\n");
                   // Toast.makeText(Remove_Hosp.this, sb, Toast.LENGTH_SHORT).show();
                    //view1.setText(sb.toString());
                    str = sb.toString();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Server is Busy..", Toast.LENGTH_SHORT).show();
                }
            });
           // Toast.makeText(this, sb, Toast.LENGTH_SHORT).show();
        }


    }

    public void refresh(View view)
    {
        tv.setText(str);
    }
}
