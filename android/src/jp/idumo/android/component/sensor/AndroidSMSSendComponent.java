package jp.idumo.android.component.sensor;

import jp.idumo.android.core.AndroidActivityResource;
import android.telephony.gsm.SmsManager;

public class AndroidSMSSendComponent {
	private boolean isReady;
	private boolean isRegister;
	private boolean isInit;

	public boolean isReady() {
		return isReady;
	}

	public boolean isInit() {
		return isInit;
	}

	public void send(String sendAddress, String sendText) {
		if(!isRegister) {
			isRegister = true;
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(sendAddress, null, sendText, null, null);
		}
	}

	public void init(AndroidActivityResource activityResource) {
		if(!isInit) {
			isInit = true;
		}
	}
}
