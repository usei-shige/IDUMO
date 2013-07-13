package jp.idumo.android.parts.provider;

import jp.idumo.android.annotation.IDUMOAndroid;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.annotation.IDUMOProvider;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import jp.idumo.android.manifest.AndroidPermission;
import jp.idumo.android.component.sensor.AndroidBatteryComponent;
import jp.idumo.android.core.AndroidActivityController;
import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.android.core.AndroidController;
import jp.idumo.android.data.AndroidBatteryModel;

@IDUMOAndroid(permissions = { AndroidPermission.BATTERY_STATS })
@IDUMOProvider(send = AndroidBatteryModel.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "Android端末のバッテリー情報の取得", summary = "AndroidのBattery情報を取得")
public class AndroidBatteryProvider implements Sendable, AndroidController, AndroidActivityController {

	private AndroidBatteryComponent battery;
	
	public AndroidBatteryProvider() {
		battery = new AndroidBatteryComponent();
	}
	
	@Override
	public boolean isReady() {
		return battery.isReady();
	}

	@Override
	public void onIdumoStart() {}

	@Override
	public void onIdumoStop() {}

	@Override
	public void registActivity(AndroidActivityResource activity) {
		if(!battery.isInit()) {
			battery.init(activity);
		}
	}

	@Override
	public void onIdumoDestroy() {}

	@Override
	public void onIdumoPause() {
		battery.unregister();
	}

	@Override
	public void onIdumoRestart() {}

	@Override
	public void onIdumoResume() {
		battery.register();
	}

	@Override
	public FlowingData onCall() {
		LogManager.log();
		FlowingData p = new FlowingData();
		AndroidBatteryModel data = battery.getData();
		p.add(data);
		return p;
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(AndroidBatteryModel.class);
	}
	
}
