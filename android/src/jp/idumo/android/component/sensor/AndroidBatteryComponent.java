package jp.idumo.android.component.sensor;

import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.android.data.AndroidBatteryModel;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class AndroidBatteryComponent extends BroadcastReceiver {
	
	private AndroidBatteryModel model;
	private boolean isReady;
	private boolean isRegister;
	private boolean isInit;
	private Activity activity;
	
	public AndroidBatteryComponent() {
		isReady = false;
		isRegister = false;
		isInit = false;
	}
	
	public void register() {
		if(!isRegister) {
			isRegister = true;
			IntentFilter filter = new IntentFilter();
			filter.addAction(Intent.ACTION_BATTERY_CHANGED);
			activity.registerReceiver(this,  filter);
		}
	}
	
	public void init(AndroidActivityResource activityResource) {
		isInit = true;
		this.activity = activityResource.getActivity();
	}
	
	public void unregister() {
		if(isRegister) {
			isRegister = false;
			activity.unregisterReceiver(this);
		}
	}
	
	public AndroidBatteryModel getData() {
		return model;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public boolean isInit() {
		return isInit;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
			String status = "";
			int statusStr = intent.getIntExtra("status", 0);
			if(statusStr == BatteryManager.BATTERY_STATUS_CHARGING) {
				status = "充電中";
			} else if(statusStr == BatteryManager.BATTERY_STATUS_DISCHARGING) {
				status = "充電してません";
			} else if(statusStr == BatteryManager.BATTERY_STATUS_FULL){
				status = "充電満タン";
			} else if(statusStr == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
				status = "充電してません";
			} else if(statusStr == BatteryManager.BATTERY_STATUS_UNKNOWN){
				status = "充電状態不明";
			}

			String plug = "";
			int plugged = intent.getIntExtra("plugged", 0);
			if(plugged == BatteryManager.BATTERY_PLUGGED_AC) {
				plug = "AC";
			} else if(plugged == BatteryManager.BATTERY_PLUGGED_USB){
				plug = "USB";
			}
			int level = intent.getIntExtra("level", 0);
			int scale = intent.getIntExtra("scale", 0);
			int temperature = intent.getIntExtra("temperature", 0);
			model = new AndroidBatteryModel(status, plug, level, scale, temperature);
		}
	}
	
}
