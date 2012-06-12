package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.common.data.GPSData;
import com.hixi_hyi.idumo.common.data.element.LatLngDataElement;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.MultiConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.NumberPrimitiveData;
import com.hixi_hyi.idumo.core.data.primitive.StringPrimitiveData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * 逆ジオコーディング(lat,lon->住所)のハンドラです． senderにはlat,lonの順番で設定してください．
 *
 * @author Hiroyoshi
 *
 */
public class ReversedGeocordingHandler implements Sendable, Receivable {

	private Sendable sender;
	private ReceiveValidatorSize		vSize	= new ReceiveValidatorSize(1);
	private ReceiveValidatorType		v1Type	= new ReceiveValidatorType(1, GPSData.class);

	@Override
	public FlowingData onCall() {
		LatLngDataElement gd = (LatLngDataElement) sender.onCall().next();

		ReversedGeocording rg = new ReversedGeocording(gd.getLatitude(), gd.getLongitude());

		FlowingData p = new FlowingData();
		p.add(new StringPrimitiveData(rg.getLocation()));

		return p;
	}

	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		v1Type.validate(senders);
		sender = senders[0];
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(GPSData.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(StringPrimitiveData.class);
	}

}
