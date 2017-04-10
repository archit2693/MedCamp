package com.developer.iron_man.feedback;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import Interfaces.API_Interface;
import model.result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iron_Man on 04/02/17.
 */

public class LoginActivity extends AppCompatActivity  {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Toolbar mToolbar;
    String languageToLoad=null;
    Locale locale=null;
    Configuration config;
    PrefManager pref;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref=new PrefManager(getApplicationContext());
        if(pref.getLang()==null)
        {
            languageToLoad="en";
        }
        else
        {
            languageToLoad=pref.getLang();
        }
        locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_login);

        context=getApplicationContext();
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    //attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testData(mEmailView.getText().toString(),mPasswordView.getText().toString(),context);
                //attemptLogin();
            }
        });

        //Toolbar
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Log In");
        mToolbar.showOverflowMenu();
        setSupportActionBar(mToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.english) {
            languageToLoad = "en"; // your language
            Intent i=new Intent(LoginActivity.this,LoginActivity.class);
            pref.setLang(languageToLoad);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.hindi) {
            languageToLoad = "hi"; // your language
            Intent i=new Intent(LoginActivity.this,LoginActivity.class);
            pref.setLang(languageToLoad);
            startActivity(i);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void testData(final String name, String password, final Context context)
    {
        final String[] result = {null};
        API_Interface.Factory.getInstance().getCommitsByName(name,password).enqueue(new Callback<result>() {
            @Override
            public void onResponse(Call<result> call, Response<result> response) {
                result[0] =response.body().status;
                Log.d("TAG",response.body().status);
                if(result[0].equals("success")) {
                    pref.setVol(name);
                    Toast t=Toast.makeText(context,"Login Successful !",Toast.LENGTH_SHORT);
                    t.show();
                    Intent intent = new Intent(LoginActivity.this, feedback.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast t=Toast.makeText(context,"Incorrect Credentials. Please try again !",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
            @Override
            public void onFailure(Call<result> call, Throwable t) {

            }
        });
    }

}


