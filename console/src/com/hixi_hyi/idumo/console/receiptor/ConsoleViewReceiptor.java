/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.hixi_hyi.idumo.console.receiptor;

import java.util.Iterator;

import com.hixi_hyi.idumo.core.data.Data;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.PrimitiveData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeSingle;
import com.hixi_hyi.idumo.core.data.element.TextElement;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;

/**
 * Systemoutに出力するReceiptor
 *
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 *
 */
public class ConsoleViewReceiptor implements IDUMOReceivable, IDUMORunnable {

	private IDUMOSendable			sender;
	private IDUMOReceiveValidatorSize	vSize	= new IDUMOReceiveValidatorSize(1);

	@Override
	public void run() {
		FlowingData flowdata = sender.onCall();
//		IDUMODataPrimitive data = (IDUMODataPrimitive) flowdata.next();
//		System.out.println(data.getValue());
		Iterator<Data> it = flowdata.iterator();
		for (Data idumoData : flowdata) {
			System.out.println(((TextElement)idumoData).getText());
		}
//		IDUMOData data = (IDUMOData) flowdata.next();
//		System.out.println(data);
	}

	@Override
	public void setSender(IDUMOSendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public ConnectDataType receivableType() {
		return new ConnectDataTypeSingle(Data.class);
	}

}
