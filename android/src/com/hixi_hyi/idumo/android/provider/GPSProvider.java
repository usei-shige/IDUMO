package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.IDUMOAndroidGPSData;
import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * GPS情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class GPSProvider implements IDUMOSender, AndroidController {
	
	private GPSSensor	gps;
	
	public GPSProvider(Activity activity) {
		GPSSensor gpsSensor = GPSSensor.INSTANCE;
		if (!gpsSensor.isInit()) {
			LocationManager location = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
			gpsSensor.init(location);
		}
		this.gps = gpsSensor;
	}
	
	@Override
	public IDUMOFlowingData onCall() {
		IDUMOLogManager.log();
		if (!isReady()) {
			return null;
		}
		
		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(new IDUMOAndroidGPSData(gps.getLatitude(), gps.getLongitude(), gps.getAltitude(), gps.getTime(), gps.getBearing(), gps.getSpeed()));
		return p;
	}
	
	@Override
	public boolean isReady() {
		return gps.isReady();
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		gps.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		gps.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOAndroidGPSData.class;
	}
	
}
