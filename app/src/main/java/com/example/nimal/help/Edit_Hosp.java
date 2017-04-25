package com.example.nimal.help;

import android.app.ProgressDialog;
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

public class Edit_Hosp extends AppCompatActivity {

    TextView tv;
    EditText key,value,parent;

    DatabaseReference databaseReference,db;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    public static String srt,str;
    public static int count;
    public static StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__hosp);

        tv = (TextView)findViewById(R.id.ShowDetails1);
        key = (EditText)findViewById(R.id.Key);
        value = (EditText)findViewById(R.id.Value);
        parent = (EditText)findViewById(R.id.Parent);

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

    public void update(View view)
    {
        db = FirebaseDatabase.getInstance().getReference("https://help-1c955.firebaseio.com/Hospital");

        final String parenting = key.getText().toString().trim();
        final String keying = key.getText().toString().trim();
        final String valueing = value.getText().toString().trim();

        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
         db.child(parenting).getRef().child(keying).setValue(valueing);

       /* databaseReference.child("Hospital").child(parenting).child(keying).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    data.getRef().child(parenting).child(keying).setValue(valueing);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Server is Busy..", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void read(View view)
    {
        for (int i = 0; i <= count; i++) {

            sb = new StringBuffer();
            databaseReference.child("Hospital").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        sb.append(data.getKey() + " : " + data.getValue().toString() + "\n");
                    }
                    sb.append("\n");
                    Toast.makeText(Edit_Hosp.this, sb, Toast.LENGTH_SHORT).show();
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

    public void refresh(View view)
    {
        tv.setText(str);
    }
}
