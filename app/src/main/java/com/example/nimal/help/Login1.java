package com.example.nimal.help;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login1 extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    SharedPreferences sharedPreferences;

    TextView contact,add,remove,view,update,location;
    ImageView contact1,add1,remove1,view1,update1,location1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        sharedPreferences = getSharedPreferences("User_Name", Context.MODE_PRIVATE);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(this, "No User has loged in !!", Toast.LENGTH_SHORT).show();
        }

        firebaseUser = firebaseAuth.getCurrentUser();

        TextView txt = (TextView) findViewById(R.id.TextView1);
        txt.setText("Welcome "+sharedPreferences.getString("Email",null).toString()+" !!!");



        contact = (TextView)findViewById(R.id.textView);
        add = (TextView)findViewById(R.id.textView1);
        remove = (TextView)findViewById(R.id.textView2);
        view = (TextView)findViewById(R.id.textView3);
        //update = (TextView)findViewById(R.id.textView4);
        location = (TextView)findViewById(R.id.textView5);

        contact1 = (ImageView)findViewById(R.id.imageView);
        add1 = (ImageView)findViewById(R.id.imageView5);
        remove1 = (ImageView)findViewById(R.id.imageView4);
        view1 = (ImageView)findViewById(R.id.imageView2);
        //update1 = (ImageView)findViewById(R.id.imageView5);
        location1 = (ImageView)findViewById(R.id.imageView6);


        sharedPreferences = getSharedPreferences("FromUser", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("User","Login");
        editor.commit();

        /*
        GridView gridView = (GridView) findViewById(R.id.GridView2);
        gridView.setAdapter(new Login1.ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {//Add User
                    Intent j = new Intent(Login1.this, AddUser.class);
                    startActivity(j);
                }
                if (position == 1) {//Add Hospital
                    Intent j = new Intent(Login1.this, Add_Hosp.class);
                    startActivity(j);
                }
                if (position == 2) {//Delete Hospital
                    Intent j = new Intent(Login1.this, Remove_Hosp.class);
                    startActivity(j);
                }
                if (position == 3) {//View Hospital
                    Intent j = new Intent(Login1.this, View_Hosp.class);
                    startActivity(j);
                }

                if(position == 4) {//Edit Hospital
                    Intent j = new Intent(Login1.this,Edit_Hosp.class);
                    startActivity(j);
                }
                if(position == 5) {//Location Services
                    Intent j = new Intent(Login1.this,Location.class);
                    startActivity(j);
                }

            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {

            mContext = c;
        }

        public int getCount() {

            return mThumbsIda.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {

            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;

            imageView.setLayoutParams(new GridView.LayoutParams(width/2, height/3));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageView.setImageResource(mThumbsIda[position]);

            return imageView;
        }

        private Integer[] mThumbsIda = {
                R.drawable.images4,
                R.drawable.images5,
                R.drawable.image6,
                R.drawable.images7,
                R.drawable.images9,
                R.drawable.images8
        };*/
    }
/*
    @Override
    public void onClick(View v) {

        if (v == contact||v == contact1)
        {
            Intent j = new Intent(Login1.this, AddUser.class);
            startActivity(j);
        }
        else if(v == add || v== add1)
        {
            Intent j = new Intent(Login1.this, Add_Hosp.class);
            startActivity(j);
        }
        else if(v == remove || v== remove1)
        {
            Intent j = new Intent(Login1.this, Remove_Hosp.class);
            startActivity(j);
        }
        else if(v == view || v== view1)
        {
            Intent j = new Intent(Login1.this, View_Hosp.class);
            startActivity(j);
        }
        else if(v == update || v== update)
        {
            Intent j = new Intent(Login1.this, Edit_Hosp.class);
            startActivity(j);
        }
        else if(v == location || v== location1)
        {
            Intent j = new Intent(Login1.this, Location.class);
            startActivity(j);
        }
        else
        {
            Toast.makeText(this, "Please click either on the image or button", Toast.LENGTH_SHORT).show();
        }

    }
    */

    public void addc(View view)
    {
        Intent j = new Intent(Login1.this, AddUser.class);
        startActivity(j);
    }
    public void addh(View view)
    {
        Intent j = new Intent(Login1.this, Add_Hosp.class);
        startActivity(j);
    }
    public void removeh(View view)
    {
        Intent j = new Intent(Login1.this, Remove_Hosp.class);
        startActivity(j);
    }
    public void viewh(View view)
    {
        Intent j = new Intent(Login1.this, View_Hosp.class);
        startActivity(j);
    }
   /* public void edith(View view)
    {
        Intent j = new Intent(Login1.this, Edit_Hosp.class);
        startActivity(j);
    }
    */
    public void locationh(View view)
    {
        Intent j = new Intent(Login1.this, Location.class);
        startActivity(j);
    }
    public void signout(View view)
    {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Willing to log out?");
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.signOut();
                Intent i = new Intent(Login1.this,MainActivity.class);
                startActivity(i);
            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });
        ab.show();
    }
    public void onBackPressed()
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
