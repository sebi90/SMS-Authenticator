package de.smsAuthenticator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private TextView textView;
    private ProgressBar spinner;

    private SMSBroadcastReceiver smsReceiver = new SMSBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            SmsMessage[] message = getMessagesFromIntent(intent);
            Toast.makeText(context, "Nachricht: " + message[0].getMessageBody() + "\n Von: " + message[0].getOriginatingAddress(), Toast.LENGTH_LONG).show();
            textView = (TextView) findViewById(R.id.resultTextView);
            spinner = (ProgressBar) findViewById(R.id.progress);

            showMessage(message);
        }
    };


    private void showMessage(SmsMessage[] message) {
        if (message[0].getOriginatingAddress().equals(getResources().getString(R.string.number))) {
            spinner.setVisibility(View.GONE);
            if (message[0].getMessageBody().equals(getResources().getString(R.string.code))) {
                textView.setText(getResources().getString(R.string.okay));
            } else {
                textView.setText(getResources().getString(R.string.error));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter(SMS_RECEIVED);
        registerReceiver(smsReceiver, intentFilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
