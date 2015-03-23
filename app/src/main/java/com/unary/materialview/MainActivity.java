package com.unary.materialview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView)findViewById(R.id.imageView);
                Bitmap bitmap = Helper.getBitmapWithBorder(findViewById(R.id.textView), 3);
                image.setImageBitmap(bitmap);
            }
        });



    }

    @Override
    protected void onResume() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MaterialView materialView  = new MaterialView(MainActivity.this);
                ((ViewGroup) getWindow().getDecorView()).addView(materialView);
                View targetView = findViewById(R.id.ratingBar);
                Bitmap bitmap = Helper.getBitmapFromView(targetView);
                int[] location = new int[2];
                targetView.getLocationOnScreen(location);
                materialView.addBitmap(bitmap, location[0], location[1]);
            }
        }, 1000);

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = Helper.getBitmapWithBorder(findViewById(R.id.textView), 3);
        image.setImageBitmap(bitmap);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
