package com.example.nimal.help;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
}
