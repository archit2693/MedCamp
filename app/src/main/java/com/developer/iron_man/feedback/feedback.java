package com.developer.iron_man.feedback;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.StringTokenizer;

import Interfaces.API_Interface;
import model.result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iron_Man on 04/02/17.
 */

public class feedback extends AppCompatActivity

{
    Button button;
    private Toolbar mToolbar;
    String languageToLoad=null;
    Locale locale=null;
    Configuration config;
    PrefManager pref;
    RadioGroup radioGroupGender;
    RadioGroup radioGroupRegistration;
    RadioGroup radioGroupPick;
    RadioGroup radioGroupTreatment;
    RadioGroup radioGroupAttitude;
    RadioGroup radioGroupManagement;
    RadioGroup radioGroupFood;
    RadioGroup radioGroupHospitality;
    RadioGroup radioGroupOverall;
    AutoCompleteTextView name;
    EditText age;
    EditText contact;
    EditText address;

    EditText remark;
    RadioButton radioButton;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    RadioButton radioButton7;
    RadioButton radioButton8;
    RadioButton radioButton9;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        button=(Button)findViewById(R.id.submitf);
        name=(AutoCompleteTextView)findViewById(R.id.name);
        remark=(EditText)findViewById(R.id.remark);
        age=(EditText)findViewById(R.id.age);
        contact=(EditText)findViewById(R.id.contact);
        address=(EditText)findViewById(R.id.address);

        context=getApplicationContext();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vol=pref.getVol();
                radioGroupGender = (RadioGroup) findViewById(R.id.Gender);
                radioGroupRegistration = (RadioGroup) findViewById(R.id.Registration);
                radioGroupPick = (RadioGroup) findViewById(R.id.pickup);
                radioGroupTreatment = (RadioGroup) findViewById(R.id.Treatment);
                radioGroupAttitude = (RadioGroup) findViewById(R.id.Attitude);
                radioGroupManagement = (RadioGroup) findViewById(R.id.Management);
                radioGroupFood = (RadioGroup) findViewById(R.id.Food);
                radioGroupHospitality = (RadioGroup) findViewById(R.id.Hospitality);
                radioGroupOverall = (RadioGroup) findViewById(R.id.OverallExp);
                String namePatient=name.getText().toString();
                if(namePatient.equals(""))
                {
                    namePatient="defaultName";
                }
                String agePatient=age.getText().toString();
                if(agePatient.equals(""))
                {
                    agePatient="0";
                }
                String cont=contact.getText().toString();
                if(cont.equals(""))
                {
                    cont="0";
                }
                String add=address.getText().toString();
                if(add.equals(""))
                {
                    add="defaultAddress";
                }

                int s1=radioGroupGender.getCheckedRadioButtonId();
                int s2=radioGroupRegistration.getCheckedRadioButtonId();
                int s3=radioGroupPick.getCheckedRadioButtonId();
                int s4=radioGroupTreatment.getCheckedRadioButtonId();
                int s5=radioGroupAttitude.getCheckedRadioButtonId();
                int s6=radioGroupManagement.getCheckedRadioButtonId();
                int s7=radioGroupFood.getCheckedRadioButtonId();
                int s8=radioGroupHospitality.getCheckedRadioButtonId();
                int s9=radioGroupOverall.getCheckedRadioButtonId();

                //Gender
                radioButton=(RadioButton)findViewById(s1);
                String gender=null;
                if(radioButton.getText().toString().equals(getString(R.string.Male)))
                {
                    gender="Male";
                }else if(radioButton.getText().toString().equals(getString(R.string.Female)))
                {
                    gender="Female";
                }
                else if(radioButton.getText().toString().equals(getString(R.string.Other)))
                {
                    gender="Other";
                }



                //Question 1
                radioButton2=(RadioButton)findViewById(s2);
                String answer1=null;
                if(radioButton2.getText().toString().equals(getString(R.string.option1)))
                {
                    answer1="1";
                }else if(radioButton2.getText().toString().equals(getString(R.string.option2)))
                {
                    answer1="2";
                }else if(radioButton2.getText().toString().equals(getString(R.string.option3)))
                {
                    answer1="3";
                }else if(radioButton2.getText().toString().equals(getString(R.string.option4)))
                {
                    answer1="4";
                }else if(radioButton2.getText().toString().equals(getString(R.string.option5)))
                {
                    answer1="5";
                }


                //Question 2
                radioButton3=(RadioButton)findViewById(s3);
                String answer2=null;
                if(radioButton3.getText().toString().equals(getString(R.string.option1)))
                {
                    answer2="1";
                }else if(radioButton3.getText().toString().equals(getString(R.string.option2)))
                {
                    answer2="2";
                }else if(radioButton3.getText().toString().equals(getString(R.string.option3)))
                {
                    answer2="3";
                }else if(radioButton3.getText().toString().equals(getString(R.string.option4)))
                {
                    answer2="4";
                }else if(radioButton3.getText().toString().equals(getString(R.string.option5)))
                {
                    answer2="5";
                }



                //Question 3
                radioButton4=(RadioButton)findViewById(s4);
                String answer3=null;
                if(radioButton4.getText().toString().equals(getString(R.string.option1)))
                {
                    answer3="1";
                }else if(radioButton4.getText().toString().equals(getString(R.string.option2)))
                {
                    answer3="2";
                }else if(radioButton4.getText().toString().equals(getString(R.string.option3)))
                {
                    answer3="3";
                }else if(radioButton4.getText().toString().equals(getString(R.string.option4)))
                {
                    answer3="4";
                }else if(radioButton4.getText().toString().equals(getString(R.string.option5)))
                {
                    answer3="5";
                }



                //Question 4
                radioButton5=(RadioButton)findViewById(s5);
                String answer4=null;
                if(radioButton5.getText().toString().equals(getString(R.string.option1)))
                {
                    answer4="1";
                }else if(radioButton5.getText().toString().equals(getString(R.string.option2)))
                {
                    answer4="2";
                }else if(radioButton5.getText().toString().equals(getString(R.string.option3)))
                {
                    answer4="3";
                }else if(radioButton5.getText().toString().equals(getString(R.string.option4)))
                {
                    answer4="4";
                }else if(radioButton5.getText().toString().equals(getString(R.string.option5)))
                {
                    answer4="5";
                }



                //Question 5
                radioButton6=(RadioButton)findViewById(s6);
                String answer5=null;
                if(radioButton6.getText().toString().equals(getString(R.string.option1)))
                {
                    answer5="1";
                }else if(radioButton6.getText().toString().equals(getString(R.string.option2)))
                {
                    answer5="2";
                }else if(radioButton6.getText().toString().equals(getString(R.string.option3)))
                {
                    answer5="3";
                }else if(radioButton6.getText().toString().equals(getString(R.string.option4)))
                {
                    answer5="4";
                }else if(radioButton6.getText().toString().equals(getString(R.string.option5)))
                {
                    answer5="5";
                }



                //Question 6
                radioButton7=(RadioButton)findViewById(s7);
                String answer6=null;
                if(radioButton7.getText().toString().equals(getString(R.string.option1)))
                {
                    answer6="1";
                }else if(radioButton7.getText().toString().equals(getString(R.string.option2)))
                {
                    answer6="2";
                }else if(radioButton7.getText().toString().equals(getString(R.string.option3)))
                {
                    answer6="3";
                }else if(radioButton7.getText().toString().equals(getString(R.string.option4)))
                {
                    answer6="4";
                }else if(radioButton7.getText().toString().equals(getString(R.string.option5)))
                {
                    answer6="5";
                }



                //Question 7
                radioButton8=(RadioButton)findViewById(s8);
                String answer7=null;
                if(radioButton8.getText().toString().equals(getString(R.string.option1)))
                {
                    answer7="1";
                }else if(radioButton8.getText().toString().equals(getString(R.string.option2)))
                {
                    answer7="2";
                }else if(radioButton8.getText().toString().equals(getString(R.string.option3)))
                {
                    answer7="3";
                }else if(radioButton8.getText().toString().equals(getString(R.string.option4)))
                {
                    answer7="4";
                }else if(radioButton8.getText().toString().equals(getString(R.string.option5)))
                {
                    answer7="5";
                }



                //Question 8
                radioButton9=(RadioButton)findViewById(s9);
                String answer8=null;
                if(radioButton9.getText().toString().equals(getString(R.string.option1)))
                {
                    answer8="1";
                }else if(radioButton9.getText().toString().equals(getString(R.string.option2)))
                {
                    answer8="2";
                }else if(radioButton9.getText().toString().equals(getString(R.string.option3)))
                {
                    answer8="3";
                }else if(radioButton9.getText().toString().equals(getString(R.string.option4)))
                {
                    answer8="4";
                }else if(radioButton9.getText().toString().equals(getString(R.string.option5)))
                {
                    answer8="5";
                }

                String answer9=remark.getText().toString();
                try {
                    namePatient=URLEncoder.encode(namePatient, "UTF-8");
                    answer9 = URLEncoder.encode(answer9, "UTF-8");
                    add= URLEncoder.encode(add,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("Loggg",namePatient+agePatient+gender+answer1+answer2+answer3+answer4+answer5+answer6+answer7+answer8+answer9+vol);
                testData(namePatient,gender,agePatient,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8,answer9,vol,cont,add);
//                Intent i=new Intent(feedback.this,feedback.class);
//                startActivity(i);
//                finish();
            }
        });
        //Toolbar
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Feedback");
        // mToolbar.setPadding(0,mToolbar.getPaddingTop(),0,0);
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
        String languageToLoad=null;
        Locale locale=null;
        Configuration config;
        //noinspection SimplifiableIfStatement
        if (id == R.id.english) {
            languageToLoad = "en"; // your language
            Intent i=new Intent(feedback.this,feedback.class);
            pref.setLang(languageToLoad);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.hindi) {
            languageToLoad = "hi"; // your language
            Intent i=new Intent(feedback.this,feedback.class);
            pref.setLang(languageToLoad);
            startActivity(i);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void testData(String name, String gender,String age,String q1,String q2,String q3,String q4,String q5,String q6,String q7,String q8,String q9,String vol,String contact,String address)
    {

        final String[] result = {null};
        API_Interface.Factory.getInstance().getFeedback(name,gender,age,q1,q2,q3,q4,q5,q6,q7,q8,q9,vol,contact,address).enqueue(new Callback<model.result>() {
            @Override
            public void onResponse(Call<result> call, Response<result> response) {

                result[0] =response.body().status;
                Log.d("TAG",response.body().status);
                if(result[0].equals("success"))
                {
                    Toast t=Toast.makeText(context,"Submission Successful !",Toast.LENGTH_SHORT);
                    t.show();
                    Log.d(" Submit  ? ","TRUE");
                    Intent intent = new Intent(feedback.this, feedback.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast t=Toast.makeText(context,"Submission Failed.Some error occurred !",Toast.LENGTH_SHORT);
                    t.show();
                    Log.d(" Submit  ? ","Failed");
                }


            }

            @Override
            public void onFailure(Call<result> call, Throwable t) {

            }
        });
    }

}
