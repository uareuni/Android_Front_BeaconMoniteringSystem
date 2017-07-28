package com.example.kbpark.frontbeaconmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.kbpark.frontbeaconmonitor.Cons.MIN_PASSWORD;

/**
 * Created by KBPark on 2017. 1. 15..
 */

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.login_email) EditText login_email;
    @Bind(R.id.login_password) EditText login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    void onRegisterClicked()
    {
        Intent intent = new Intent(getApplicationContext(), Register.class); // to Register
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    void onSignUpClicked()
    {
        final String email = login_email.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            login_email.setError("Invalid Email");
        }

        final String pass = login_password.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            login_password.setError("Password cannot be empty");
        }

        if(isValidEmail(email) && isValidPassword(pass))
        {
            // Validation Completed
            /** layout test를 위해 우선 주석처리 **/
                    User user = new User(email, pass);
                    String loginRes = user.login(); // 실제로 login이 일어나는 부분 ///////////////////////////////////////////

            Intent intent = new Intent(getApplicationContext(), LoginMain.class);
            // intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

    }


    /**
     * -----------------  < optional part >  ------------------------
     */

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= MIN_PASSWORD) {
            return true;
        }
        return false;
    }
}
