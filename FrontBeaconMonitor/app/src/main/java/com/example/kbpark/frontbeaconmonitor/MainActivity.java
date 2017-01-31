package com.example.kbpark.frontbeaconmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kbpark.frontbeaconmonitor.retrofit.Login;
import com.example.kbpark.frontbeaconmonitor.retrofit.LoginResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.kbpark.frontbeaconmonitor.retrofit.Login.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passEditText;
    private Button resister_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init()
    {
        // Address the email and password field
        emailEditText = (EditText) findViewById(R.id.username);
        passEditText = (EditText) findViewById(R.id.password);

        resister_btn = (Button) findViewById(R.id.resister_btn);
        resister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class); // to SignUp
                startActivity(intent);
            }
        });

    }

    public void checkLogin(View arg0)
    {

        final String email = emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            emailEditText.setError("Invalid Email");
        }

        final String pass = passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            passEditText.setError("Password cannot be empty");
        }

        if(isValidEmail(email) && isValidPassword(pass))
        {
            // Validation Completed

            /**
             * < retrofit2 : POST >
             */

            // 1. for loggin (okhttp log)
                //Here a logging interceptor is created
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                //The logging interceptor will be added to the http client
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            // 2. retrofit setting
            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClient.build()) // okhttp client for logging
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Login login = retrofit.create(Login.class);
            final Call<LoginResult> res = login.login("pkb", "1234"); // 실제 통신이 이루어지는 곳

            // 3. 받아온거 뽑아내기
            res.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    Log.d("test", "받아오기 성공!");
                    LoginResult loginResult = response.body();

                    Log.d("test", "from server : " + loginResult.getLoginResult());
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {
                    Log.d("test", "받아오기 실패..");
                }
            });

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
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }
}
