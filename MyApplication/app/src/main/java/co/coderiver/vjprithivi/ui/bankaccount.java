package co.coderiver.vjprithivi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import co.coderiver.vjprithivi.R;
import co.coderiver.vjprithivi.util.IntentUtil;
import co.coderiver.vjprithivi.util.PrefUtil;

/**
 * Created by vjprithivi on 6/23/2016.
 */
public class bankaccount extends AppCompatActivity implements View.OnClickListener {

    Spinner sp1, sp2;
    EditText t1, t2, t3, t4, a, b, c, d;
    Switch s1;
    Button Register;
    String bankaccountyes, f1, f2;
    private CallbackManager callbackManager;
    private TextView info;
    private ImageView profileImgView;
    private LoginButton loginButton;
    private PrefUtil prefUtil;
    private IntentUtil intentUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.bankaccount);

        prefUtil = new PrefUtil(this);
        intentUtil = new IntentUtil(this);

        sp1 = (Spinner) findViewById(R.id.onchosiesbasicaccount);
        sp2 = (Spinner) findViewById(R.id.whattypeofstudies);

        t1 = (EditText) findViewById(R.id.nameofunviversity);
        t2 = (EditText) findViewById(R.id.towncity);
        t3 = (EditText) findViewById(R.id.postcode);
        t4 = (EditText) findViewById(R.id.Email);
        a = (EditText) findViewById(R.id.Name);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        b = (EditText) findViewById(R.id.dateofbrith);
        c = (EditText) findViewById(R.id.Nationality);
        d = (EditText) findViewById(R.id.mobile);

        s1 = (Switch) findViewById(R.id.switch1);

        Register = (Button) findViewById(R.id.Register);
        Register.setOnClickListener(this);


        a.setVisibility(View.GONE);
        b.setVisibility(View.GONE);
        c.setVisibility(View.GONE);
        d.setVisibility(View.GONE);







        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                f1 = arg0.getItemAtPosition(arg2).toString();
                // Toast.makeText(MainActivity.this, "Selected: " + sex,
                // Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                // Toast.makeText(MainActivity.this, "No Selection",
                // Toast.LENGTH_SHORT).show();
            }
        });



        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                f2 = arg0.getItemAtPosition(arg2).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (s1.isChecked()) {
                    bankaccountyes = s1.getTextOn().toString();

                    a.setVisibility(View.VISIBLE);
                    b.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                    d.setVisibility(View.VISIBLE);

                } else {
                    bankaccountyes = s1.getTextOff().toString();
                    a.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                }
            }


        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               /* Profile profile = Profile.getCurrentProfile();
                info.setText(message(profile));*/


                String userId = loginResult.getAccessToken().getUserId();
                String accessToken = loginResult.getAccessToken().getToken();

                // save accessToken to SharedPreference
                prefUtil.saveAccessToken(accessToken);



            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }


        });

    }


    @Override
    public void onResume() {
        super.onResume();
        deleteAccessToken();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null) {
                    //User logged out
                    prefUtil.clearToken();
                    clearUserArea();


                }
            }
        };
    }

    private void clearUserArea() {
       /* info.setText("");
        profileImgView.setImageDrawable(null);*/
    }


    private void mail() {

        String e1 = t1.getText().toString().trim();
        String e2 = t2.getText().toString().trim();
        String e3 = t3.getText().toString().trim();
        String email = t4.getText().toString().trim();
        String subject = getString(R.string.subject);

        String y1 = a.getText().toString().trim();

        String y2 = b.getText().toString().trim();
        String y3 = c.getText().toString().trim();
        String y4 = d.getText().toString().trim();


        bankmail bm = new bankmail(this, email, subject, f1, f2, e1, e2, e3, bankaccountyes, y1, y2, y3, y4);
        bm.execute();

    }



    @Override
    public void onClick(View view) {
        mail();
    }





}