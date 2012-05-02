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
package com.hixi_hyi.idumo.android.data;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawNumber;

/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 */
public class AndroidMagneticFieldData extends IDUMOData {
	private static final String X="x";
	private static final String Y="y";
	private static final String Z="z";

	public AndroidMagneticFieldData(float x, float y, float z) {
		add(new IDUMODataTypeRawNumber(X, x, "Android MagneticField X"));
		add(new IDUMODataTypeRawNumber(Y, y, "Android MagneticField Y"));
		add(new IDUMODataTypeRawNumber(Z, z, "Android MagneticField Z"));
	}

	public float getX() {
		return (Float) getValue(X);
	}

	public float getY() {
		return (Float) getValue(Y);
	}

	public float getZ() {
		return (Float) getValue(Z);
	}

	@Override
	public String toString(){
		return String.format("%s:%f\n%s:%f\n%s:%f",X,getX(),Y,getY(),Z,getZ());
	}
}