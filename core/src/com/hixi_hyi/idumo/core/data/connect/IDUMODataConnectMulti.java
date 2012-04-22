package com.hixi_hyi.idumo.core.data.connect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hixi_hyi.idumo.core.data.IDUMOData;

public class IDUMODataConnectMulti implements IDUMODataConnect {

	List<Class<? extends IDUMOData>> data = new ArrayList<Class<? extends IDUMOData>>();
	
	public IDUMODataConnectMulti(Class<? extends IDUMOData>... ds){
		for(Class<? extends IDUMOData> d:ds){
			data.add(d);
		}
	}

	@Override
	public Iterator<Class<? extends IDUMOData>> iterator() {
		return data.iterator();
	}

	@Override
	public boolean equals(IDUMODataConnect connect) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
}