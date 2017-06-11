package co.coderiver.vjprithivi.ui;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import co.coderiver.vjprithivi.R;



public class welcome extends AppCompatActivity {


    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView t1 = (TextView) findViewById(R.id.welcome);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        t1.setTypeface(face);
        TextView t2 = (TextView) findViewById(R.id.appname);

        Typeface faces = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        t2.setTypeface(faces);



        progressBar = (ProgressBar) findViewById(R.id.progressBar);
       progressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);


        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1.0;
                    // Update the progress bar and display the current value in
                    // the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);


                        }
                    });
                    try {

                        // Sleep for 200 milliseconds. Just to display the
                        // progress slowly
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.
                Intent mInHome = new Intent(welcome.this,MainActivity.class);
               welcome.this.startActivity(mInHome);
            welcome.this.finish();
            }
        }, 5000);

    }


}
