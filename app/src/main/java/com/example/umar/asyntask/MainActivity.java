package com.example.umar.asyntask;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    Integer count = 1;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //progressBar.setVisibility(View.VISIBLE);
        //progressBar.setEnabled(false);
        progressBar.setMax(5);
        btn = (Button) findViewById(R.id.button);
        btn.setText("Start");
        txt = (TextView) findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 1;
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                switch (v.getId()) {

                    case R.id.button:
                        new MyTask().execute(10);
                        break;
                }
            }
        });

    }


    class MyTask extends AsyncTask<Integer, Integer, String> {


        @Override
        protected String doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed";
        }

        @Override
        protected void onPreExecute() {
            txt.setText("Task Starting....");
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            txt.setText(result);
            btn.setText("Restart");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            txt.setText("Running...." + values[0]);
            progressBar.setProgress(values[0]);
        }
    }


}
