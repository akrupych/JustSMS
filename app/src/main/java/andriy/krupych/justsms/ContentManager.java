package andriy.krupych.justsms;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentManager {

    private static final String TAG = ContentManager.class.getSimpleName();
    private static final int OUTPUT_LIMIT = 10;

    public static void logCursor(Cursor cursor) {
        if (cursor.getCount() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < OUTPUT_LIMIT; i++) {
                cursor.moveToNext();
                JSONObject jsonObject = new JSONObject();
                for (int j = 0; j < cursor.getColumnCount(); j++) {
                    try {
                        jsonObject.put(cursor.getColumnName(j), cursor.getString(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                jsonArray.put(jsonObject);
            }
            Log.d(TAG, jsonArray.toString());
        }
    }

    public static void logSmsTable(boolean allFields, String address) {
        Uri uri = Uri.parse("content://sms");
        String[] projection = allFields ? null : new String[]{"address", "body", "type", "date_sent", "date"};
        String selection = address == null ? null : "address=\'" + address + "\'";
        Cursor cursor = App.getInstance().getContentResolver().query(uri, projection, selection, null, null);
        logCursor(cursor);
        cursor.close();
    }

}
