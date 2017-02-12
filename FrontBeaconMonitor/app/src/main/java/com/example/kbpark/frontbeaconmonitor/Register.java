package com.example.kbpark.frontbeaconmonitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by KBPark on 2017. 1. 15..
 */

public class Register extends AppCompatActivity
{
    @Bind(R.id.email_input) EditText email_input;
    @Bind(R.id.password_input)EditText pw_input;
    @Bind(R.id.birth_input)EditText birth_input;
    @Bind(R.id.address_input)EditText address_input;
    @Bind(R.id.phone_input)EditText phone_input;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.register_btn)
    void onRegisterClicked()
    {
        String email = email_input.getText().toString();
        String pw = pw_input.getText().toString();
        String birth = birth_input.getText().toString();
        String address = address_input.getText().toString();
        String phone = phone_input.getText().toString();


        if(email.isEmpty() || pw.isEmpty() || birth.isEmpty() || address.isEmpty() || phone.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "모든 항목을 기입하십시오.", Toast.LENGTH_SHORT).show();
        } else
        {
            User user = new User(email, pw, birth, address, phone);
            String registerRes = user.register(); // 실제 register가 일어나는 부분


        }
        // check the empty values!
    }

}
