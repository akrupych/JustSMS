package andriy.krupych.justsms;

import android.telephony.SmsMessage;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class SMS {

    public String address;
    public String body;
    public DateTime date;
    public String person;

    public static String toString(SmsMessage smsMessage) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("DisplayMessageBody", smsMessage.getDisplayMessageBody());
            jsonObject.put("DisplayOriginatingAddress", smsMessage.getDisplayOriginatingAddress());
            jsonObject.put("EmailBody", smsMessage.getEmailBody());
            jsonObject.put("EmailFrom", smsMessage.getEmailFrom());
            jsonObject.put("IndexOnIcc", smsMessage.getIndexOnIcc());
            jsonObject.put("MessageBody", smsMessage.getMessageBody());
            jsonObject.put("MessageClass", smsMessage.getMessageClass().toString());
            jsonObject.put("OriginatingAddress", smsMessage.getOriginatingAddress());
            jsonObject.put("Status", smsMessage.getStatus());
            jsonObject.put("Timestamp", new Date(smsMessage.getTimestampMillis()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
