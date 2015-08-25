package andriy.krupych.justsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_EXTRA_NAME = "pdus";

    @Override
    public void onReceive( Context context, Intent intent ) {
        // Get the SMS map from Intent
        Bundle extras = intent.getExtras();
        String messages = "";
        if ( extras != null ) {
            // Get received SMS array
            Object[] rawData = (Object[]) extras.get( SMS_EXTRA_NAME );
            if (rawData == null) return;
            for (Object smsObject : rawData) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsObject);
                Log.d("qwerty", SMS.toString(sms));
                String body = sms.getMessageBody();
                String address = sms.getOriginatingAddress();
                messages += "SMS from " + address + " :\n";
                messages += body + "\n";
            }
            // Display SMS message
            Toast.makeText( context, messages, Toast.LENGTH_SHORT ).show();
        }
    }

}
