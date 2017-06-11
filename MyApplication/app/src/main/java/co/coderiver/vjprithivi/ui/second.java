package co.coderiver.vjprithivi.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.coderiver.vjprithivi.R;

import static co.coderiver.vjprithivi.R.id.abankaccount;


/**
 * Created by vjprithivi on 6/28/2016.
 */
public class second extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        TextView t1 = (TextView) findViewById(R.id.looking);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        t1.setTypeface(face);
      /*  TextView t2 = (TextView) findViewById(R.id.or);
        Typeface fac = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Balck.ttf");
        t2.setTypeface(fac);*/
        Button b1 = (Button) findViewById(R.id.studentloan);
        Typeface facedfd = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        b1.setTypeface(facedfd);
        Button b2 = (Button) findViewById(abankaccount);
        Typeface facedfse = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        b2.setTypeface(facedfse);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studentloan: {


                Intent i = new Intent(second.this, next.class);
                startActivity(i);
                finish();

                break;
            }
            case R.id.abankaccount: {
                Intent ir = new Intent(second.this, bankaccount.class);
                startActivity(ir);
                finish();
                break;
            }
        }
    } }