package com.example.kbpark.frontbeaconmonitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by KBPark on 2017. 1. 15..
 */

public class SignUp extends AppCompatActivity
{
    String email;
    String pw;
    String birth;
    String address;
    String phone;

    EditText email_input;
    EditText pw_input;
    EditText birth_input;
    EditText address_input;
    EditText phone_input;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        init();
    }

    public void onResisterClicked(View v)
    {
        email = email_input.getText().toString();
        pw = pw_input.getText().toString();
        birth = birth_input.getText().toString();
        address = address_input.getText().toString();
        phone = phone_input.getText().toString();

        /*
        // for String test
        Log.d("signup", email);
        Log.d("signup", pw);
        Log.d("signup", birth);
        Log.d("signup", address);
        Log.d("signup", phone);
        */

        // check the empty values!
    }

    private void init()
    {
        email_input = (EditText) findViewById(R.id.email_input);
        pw_input = (EditText) findViewById(R.id.password_input);
        birth_input = (EditText) findViewById(R.id.birth_input);
        address_input = (EditText) findViewById(R.id.address_input);
        phone_input = (EditText) findViewById(R.id.phone_input);

    }

}
