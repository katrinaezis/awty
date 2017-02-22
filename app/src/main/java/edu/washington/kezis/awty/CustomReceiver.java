package edu.washington.kezis.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by iguest on 2/22/17.
 */

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = MainActivity.phoneNumber + ": " + MainActivity.msgTxt;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
