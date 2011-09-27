package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.android.sensor.OrientationSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の傾きの情報を取得できるProvider 地磁気センサと加速度センサにより傾きを算出
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class OrientationProvider implements SenderWithOption,AndroidController {

	public enum Type implements OptionMethodType {
		PITCH("Get ORIENTATION"), AZMUTH("Get ORIENTATION"), ROLL("Get ORIENTATION");
		private final String	description;

		Type(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return description;
		}
	}

	private Type				methodType;
	private OrientationSensor	sensor;

	public OrientationProvider(Activity activity) {
		SensorManager sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		AccelerometerSensor accelerometerSensor = AccelerometerSensor.INSTANCE;
		accelerometerSensor.init(sensorManager);
		MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
		magneticFieldSensor.init(sensorManager);
		OrientationSensor orientationSensor = OrientationSensor.INSTANCE;
		orientationSensor.init(accelerometerSensor, magneticFieldSensor);

		sensor = orientationSensor;
	}

	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Float.class);
		return type;
	}

	@Override
	public PipeData getData() {
		LogManager.log();
		PipeData p = new PipeData();
		switch (methodType) {
			case PITCH:
				p.add(sensor.getPitch());
				break;
			case ROLL:
				p.add(sensor.getRoll());
				break;
			case AZMUTH:
				p.add(sensor.getAzmuth());
				break;
			default:
				throw new IdumoRuntimeException();
		}
		return p;
	}

	@Override
	public void setOption(OptionMethodType type) throws IdumoException {
		if (type instanceof Type) {
			methodType = (Type) type;
		} else {
			throw new IdumoException();
		}
	}

	@Override
	public boolean isReady() {
		return sensor.isReady();
	}

	@Override
	public Map<String, String> getOptions() {
		Map<String, String> map = new HashMap<String, String>();
		for (OptionMethodType t : Type.values()) {
			map.put(t.toString(), t.getDescription());
		}
		return map;
	}

	@Override
	public void onIdumoStart() {
	}

	@Override
	public void onIdumoStop() {
	}

	@Override
	public void onIdumoRestart() {
	}

	@Override
	public void onIdumoResume() {
		sensor.register();
	}

	@Override
	public void onIdumoPause() {
		sensor.unregister();
	}

	@Override
	public void onIdumoDestroy() {
	}
}
