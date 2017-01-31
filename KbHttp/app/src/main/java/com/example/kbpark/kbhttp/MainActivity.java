package com.example.kbpark.kbhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://10.0.2.2/HttpTest/api.php";

    private String requestStr;
    private String responseStr;

    @Bind(R.id.toServer_text) TextView toServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.send_btn)
    void onSendClick()
    {
        NetworkService mService = new NetworkService();
        mService.execute();
    }


    // NetworkService
    /*
        Generic은 순서대로,
            1. doInBackground param
            2. onProgressUpdate param
            3. doInBackground의 return type이자, onPostExecute param
     */

    private class NetworkService extends AsyncTask<String, String, String>
    {
        // < 실행순서 >
        @Override
        protected void onPreExecute() { // 1번 : ui기본 작업, thread 기본작업 등
            super.onPreExecute();

            requestStr = "request data~";
        }

        @Override
        protected String doInBackground(String... strings) { // 2번 - 1 : 얘만 'back'에서 실행되는 친구!
            return request(requestStr);
        }

        @Override
        protected void onProgressUpdate(String... values) { // 2번 - 2 : 퍼센테이지를 업데이트 해주는 등 계속 ui를 update해주는 경우 등.
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) { // 3번 : 보통 여기서 ui를 최종 update 해준다!
            toServer.setText(s);
        }


        public String request(String request)
        {
            InputStream is;
            OutputStream os;

            StringBuilder output = new StringBuilder();
            try
            {
                java.net.URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if(conn != null)
                {
                    // conn setting
                    conn.setConnectTimeout(10000); // 10초
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true); // input set
                    conn.setDoOutput(true);// output set

                    // input stream 확보
                    is = conn.getInputStream();
                    // output stream 확보
                    os = conn.getOutputStream();

                    // making json data
                    JSONObject json = new JSONObject();
                    json.put("name","kbPark");
                    json.put("id", "uareuni");
                    String jsonStr = json.toString();
                        Log.i("test",jsonStr);

                    StringBuilder sb = new StringBuilder(jsonStr);

                    os.write();


                    // server로부터 data 받기
                    int resCode = conn.getResponseCode();
                    if(resCode == HttpURLConnection.HTTP_OK)
                    {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
                        String line = null;
                        while(true) {
                            line = reader.readLine(); // 여기가 실질적으로 통신하는 부분!
                            if (line == null) {
                                break;
                            }
                            output.append(line + "\n");
                        }

                        reader.close();
                        conn.disconnect();
                    }
                }

            } catch(Exception e)
            {
                e.printStackTrace();
            }

            return output.toString(); // 이렇게 될까?
        }

    }

}
