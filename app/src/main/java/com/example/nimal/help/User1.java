package com.example.nimal.help;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class User1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        GridView gridView = (GridView) findViewById(R.id.GridView2);
        gridView.setAdapter(new User1.ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {//Help
                    Intent j = new Intent(User1.this, Help.class);
                    startActivity(j);
                }
                if (position == 1) {//Location Services
                    Intent j = new Intent(User1.this, Location.class);
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
                R.drawable.image8,
                R.drawable.images8
        };


    }
}
