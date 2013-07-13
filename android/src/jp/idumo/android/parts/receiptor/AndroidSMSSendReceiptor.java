package jp.idumo.android.parts.receiptor;

import jp.idumo.android.annotation.IDUMOAndroid;
import jp.idumo.android.component.sensor.AndroidSMSSendComponent;
import jp.idumo.android.core.AndroidActivityController;
import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.android.manifest.AndroidPermission;
import jp.idumo.core.annotation.IDUMOConstructor;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.annotation.IDUMOReceiptor;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.data.primitive.BoolPrimitiveElement;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Executable;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import jp.idumo.core.validator.ReceiveValidatorSize;


@IDUMOAndroid(permissions = { AndroidPermission.SEND_SMS })
@IDUMOReceiptor(receive= BoolPrimitiveElement.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "SMSの送信", summary = "AndroidからSMSを送信します")
public class AndroidSMSSendReceiptor implements Receivable, Executable, AndroidActivityController {

	private AndroidSMSSendComponent sms;
	private Sendable                sender;
	private ReceiveValidatorSize    vSize   = new ReceiveValidatorSize(1);
	public String address;
	public String text;
	public boolean isBool;
	
	@IDUMOConstructor({"送信先番号", "送信するメッセージ"})
	public AndroidSMSSendReceiptor(String sendAddress, String sendText) {
		address = sendAddress;
		text    = sendText;
	}
	
	@Override
	public boolean isReady() {
		return sms.isReady();
	}

	@Override
	public void run() {
		// boolean判定によってsmsを送信するかどうかを判断
		LogManager.log();
		FlowingData idf = sender.onCall();
		BoolPrimitiveElement boolPrimitive = (BoolPrimitiveElement) idf.next();
		isBool = boolPrimitive.getBool();
		if(isBool) {
			sms.send(address, text);
		}
	}

	@Override
	public void registActivity(AndroidActivityResource activity) {
		if(!sms.isInit()) {
			sms.init(activity);
		}	
	}

	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(BoolPrimitiveElement.class);
	}

	@Override
	public void setSender(Sendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
}
