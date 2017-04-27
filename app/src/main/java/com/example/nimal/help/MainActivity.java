package com.example.nimal.help;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            //Toast.makeText(this, "No User has loged in !!", Toast.LENGTH_SHORT).show();
            count = 1;
        }
        else
        {
            //Toast.makeText(this, firebaseAuth.getCurrentUser().toString(), Toast.LENGTH_SHORT).show();
            count = 0;
        }

        firebaseUser = firebaseAuth.getCurrentUser();

        /*GridView gridView = (GridView) findViewById(R.id.GridView1);
        gridView.setAdapter(new MainActivity.ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent j = new Intent(MainActivity.this, Admin_login.class);
                    startActivity(j);
                }

                if (position == 0) {
                    Intent j = new Intent(MainActivity.this, User1.class);
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

            imageView.setLayoutParams(new GridView.LayoutParams(width, height/2));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageView.setImageResource(mThumbsIda[position]);

            return imageView;
        }

        private Integer[] mThumbsIda = {
                R.drawable.images3,
                R.drawable.images2
        };*/

    }

    public void justing(View view)
    {
        Intent j = new Intent(MainActivity.this, User1.class);
        startActivity(j);
    }
    public void loladmin(View view)
    {
        if(count == 1) {
            Intent j = new Intent(MainActivity.this, Admin_login.class);
            startActivity(j);
        }
        else if(count == 0)
        {
            Intent j = new Intent(MainActivity.this, Login1.class);
            startActivity(j);
        }
    }
}
