package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class StringHandler implements IDUMOSender, IDUMOReceiver {

	private IDUMOSender				sender;
	private String				word;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	private ReceiveValidator vType = new ReceiveValidatorType(1,Boolean.class);

	public StringHandler(String word) {
		this.word = word;
	}

	@Override
	public PipeData getData() {
		if((Boolean)sender.getData().get(0)){
			return PipeData.generatePipeData(word);
		}else{
//			return PipeData.generatePipeData("");
			return null;
		}
	}

	@Override
	public List<Class<?>> getDataType() throws IDUMOException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		type.add(String.class);
		return type;
	}

	@Override
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
	}


	@Override
	public boolean isReady() {
		return sender.isReady();
	}

}
