package andriy.krupych.justsms;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<SMS> mSmsHistory;
    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = (EditText) findViewById(R.id.message);
        loadSmsHistory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                sendSms();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadSmsHistory() {
        mSmsHistory = new ArrayList<>();
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        int addressColumnIndex = cursor.getColumnIndex("address");
        int bodyColumnIndex = cursor.getColumnIndex("body");
        int dateColumnIndex = cursor.getColumnIndex("date");
        int personColumnIndex = cursor.getColumnIndex("person");
        if (cursor.moveToFirst()) {
            for (int i = 0; i < 10; i++) {
                SMS sms = new SMS();
                sms.address = cursor.getString(addressColumnIndex);
                sms.body = cursor.getString(bodyColumnIndex);
                sms.date = new DateTime(cursor.getLong(dateColumnIndex));
                sms.person = cursor.getString(personColumnIndex);
                Log.d(sms.address, sms.person + " " + sms.body);
                mSmsHistory.add(sms);
                cursor.moveToNext();
            }
        }
        cursor.close();
    }

    private void sendSms() {
        String message = mMessageEditText.getText().toString();
        SmsManager.getDefault().sendTextMessage("+380637467485", null, message, null, null);
    }
}
