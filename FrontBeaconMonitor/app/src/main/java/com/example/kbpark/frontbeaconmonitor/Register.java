package com.example.kbpark.frontbeaconmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    @Bind(R.id.name_input) EditText name_input;

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
        String name =  name_input.getText().toString();
        String pw = pw_input.getText().toString();
        String birth = birth_input.getText().toString();
        String address = address_input.getText().toString();
        String phone = phone_input.getText().toString();


        if(email.isEmpty() || name.isEmpty() || pw.isEmpty() || birth.isEmpty() || address.isEmpty() || phone.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "모든 항목을 기입하십시오.", Toast.LENGTH_SHORT).show();
        } else
        {
            // 1. register
            User user = User.getInstance();
            user.setName(name);
            user.setPw(pw);
            user.setAddress(address);
            user.setBirth(birth);
            user.setPhone(phone);

            String registerRes = user.register(); // 실제 register가 일어나는 부분

            // 2. send token to server
            Log.i("TOKEN", "token to server : " + user.getToken());
            user.pushTokenToServer();

            Intent intent = new Intent(getApplicationContext(), LoginMain.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        // check the empty values!
    }

}
