package edu.washington.kezis.awty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int interval;
    String min;
    static String msgTxt;
    static String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText message = (EditText) findViewById(R.id.message);
        final EditText number = (EditText) findViewById(R.id.phone);
        final EditText inputTime = (EditText) findViewById(R.id.min);
        final Button btn = (Button) findViewById(R.id.start);
        final Intent intent = new Intent(this, CustomReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.getText().toString().toLowerCase().equals("start")) {
                    msgTxt = message.getText().toString();
                    phoneNumber = number.getText().toString();
                    min = inputTime.getText().toString();
                    if (msgTxt.equals("") | phoneNumber.equals("") | min.equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Must fill out all fields", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Integer.parseInt(min) < 1) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Time must be more than 1 minute", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        btn.setText("Stop");
                        interval = Integer.parseInt(min) * 60 * 1000;
                        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), interval, pendingIntent);
                    }
                } else {
                    btn.setText("Start");
                    if (alarmManager != null) {
                        alarmManager.cancel(pendingIntent);
                    }
                }
            }
        });
    }
}
