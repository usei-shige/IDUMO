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
package jp.idumo.core.doclet;

import java.util.Map;
import java.util.TreeMap;

import jp.idumo.core.doclet.json.IJSONValue;
import jp.idumo.core.doclet.perser.IAnnotation;

/**
 * @author Hiroyoshi HOUCHI
 */
public class JSONBuilder {
	
	private Map<String, IJSONValue>	items	= new TreeMap<String, IJSONValue>();
	
	public void add(IAnnotation annotation) {
		items.putAll(annotation.getKVMap());
	}
	
	public void add(String key, IJSONValue value) {
		items.put(key, value);
	}
	
	public void add(JSONBuilder builder){
		items.putAll(builder.items);
	}
	
	@Override
	public String toString() {
		StringBuilder json = new StringBuilder();
		json.append("{");
		boolean isExec = false;
		for (Map.Entry<String, IJSONValue> e : items.entrySet()) {
			isExec = true;
			json.append(String.format("\"%s\":%s,", e.getKey().toString(), e.getValue().toString()));
		}
		if (isExec) {
			json.setLength(json.length() - 1);
		}
		json.append("}");
		return json.toString();
	}
	
}
