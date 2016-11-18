package com.example.administrator.http_analysis;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    final private int TEXT_VIEW = 0X11;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TEXT_VIEW:
                    String text = msg.obj.toString();
                    tv.setText(text);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String path = "https://www.baidu.com";
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(path);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.setReadTimeout(5000);
                            httpURLConnection.connect();

                            if(httpURLConnection.getResponseCode() == 200){
                                InputStream inputStream = httpURLConnection.getInputStream();
                                String text = Utils.getTextFromStream(inputStream);
                                Message message = handler.obtainMessage();
                                message.obj = text;
                                message.what = TEXT_VIEW;
                                handler.sendMessage(message);

                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
