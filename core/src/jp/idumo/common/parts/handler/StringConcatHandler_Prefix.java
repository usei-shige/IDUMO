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
package jp.idumo.common.parts.handler;

import jp.idumo.common.annotation.IDUMOCommon;
import jp.idumo.core.annotation.IDUMOConstructor;
import jp.idumo.core.annotation.IDUMOHandler;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.data.DataElement;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.data.primitive.StringPrimitiveElement;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.validator.ReceiveValidator;
import jp.idumo.core.validator.ReceiveValidatorSize;

/**
 * @author Hiroyoshi HOUCHI
 * 
 */
@IDUMOCommon
@IDUMOHandler(receive = DataElement.class, send = StringPrimitiveElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "文頭に文字列を追加", summary = "文字列の前に指定された文字を埋め込む")
public class StringConcatHandler_Prefix implements Sendable, Receivable {
	
	private Sendable			provider;
	private String				fixWord;
	private ReceiveValidator	vSize	= new ReceiveValidatorSize(1);
	
	@IDUMOConstructor({ "接頭語" })
	public StringConcatHandler_Prefix(String fixWord) {
		this.fixWord = fixWord;
	}
	
	@Override
	public boolean isReady() {
		return provider.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		String s = ((StringPrimitiveElement) provider.onCall().next()).getString();
		return new FlowingData(new StringPrimitiveElement.StringPrimitiveData(fixWord + s));
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(DataElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(StringPrimitiveElement.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		provider = senders[0];
	}
	
}
