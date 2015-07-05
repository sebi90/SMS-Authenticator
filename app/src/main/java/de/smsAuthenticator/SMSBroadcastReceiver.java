package de.smsAuthenticator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

/**
 * Created by Sebi on 03.07.15.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {


    public static SmsMessage[] getMessagesFromIntent(final Intent intent) {
        final Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        final int pduCount = messages.length;
        final SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            final byte[] pdu = (byte[]) messages[i];
            msgs[i] = SmsMessage.createFromPdu(pdu);
        }
        return msgs;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

}
