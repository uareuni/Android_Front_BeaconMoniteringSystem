package com.example.kbpark.frontbeaconmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kbpark.frontbeaconmonitor.retrofit.GithubServiceSample;
import com.example.kbpark.frontbeaconmonitor.retrofit.Repo;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

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
             *  < retrofit2 : GET > - server로부터 json 받아오기
             */

            // 1. retrofit obj
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // 2. service
            GithubServiceSample service = retrofit.create(GithubServiceSample.class);
            Call<List<Repo>> repos = service.listRepos("uareuni");

            // 3. enqueue()
            repos.enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    List<Repo> test = response.body(); // 이렇게 받아오면 됩니다.
                    Log.d("git", "호출 성공!");


                    Iterator<Repo> itr = test.iterator();
                    while(itr.hasNext())
                    {
                        Log.d("git", "name : " + itr.next().getName());
                    }
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {
                    Log.d("git", "호출 실패ㅠㅠ");
                }
            });

            /**
             * < retrofit2 : POST > - server로 json 보내고, return 받기
             */
//
//            User user = new User("아이디와", "비밀번호"); // 임의의 user obj
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://echo.jsontest.com")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            Login login = retrofit.create(Login.class);
//            final Call<LoginResult> res = login.login(user.getId(), user.getPw());
//            //Log.d("git", "현재 res값 : " + res.request());
//
//            res.enqueue(new Callback<LoginResult>() {
//                @Override
//                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
//
//                    LoginResult loginResult = response.body();
//                    toServer.append(loginResult.getKey());
//
//                }
//
//                @Override
//                public void onFailure(Call<LoginResult> call, Throwable t) {
//
//                }
//            });



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
