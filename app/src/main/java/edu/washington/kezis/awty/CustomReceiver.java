package edu.washington.kezis.awty;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by iguest on 2/22/17.
 */

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("debug", "message sent to phone number: " + MainActivity.msgTxt);
//        String message = MainActivity.phoneNumber + ": " + MainActivity.msgTxt;
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(MainActivity.phoneNumber, null, MainActivity.msgTxt, null, null);
    }
}
