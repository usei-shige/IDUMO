package jp.idumo.common.data;

import jp.idumo.core.annotation.IDUMOModel;
import jp.idumo.core.data.DataElement.AbstractData;
import jp.idumo.core.data.raw.StringRawDataType;

@IDUMOModel(model = { "zipnumber", "prefecture", "city", "town" })
public class AddressModel  extends AbstractData {
	//prefecture
	public static final String ZIP = "zipnumber";
	public static final String PREFECTURE = "prefecture";
	public static final String CITY = "city";
	public static final String TOWN = "town";
	
	public AddressModel(String zip, String ken, String shi, String cho) {
		add(new StringRawDataType(ZIP, zip, "ZipNumber"));
		add(new StringRawDataType(PREFECTURE, ken, "Prefecture"));
		add(new StringRawDataType(CITY, shi, "City"));
		add(new StringRawDataType(TOWN, cho, "Town"));
	}
	
	public String getZipNumber() {
		return(String) getValue(ZIP);
	}
	
	public String getPrefecture() {
		return(String) getValue(PREFECTURE);
	}
	
	public String getCity() {
		return(String) getValue(CITY);
	}
	
	public String getTwon() {
		return(String) getValue(TOWN);
	}
	
	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append(ZIP);
		sb.append(":");
		sb.append(getZipNumber());
		sb.append("\n");
		sb.append(PREFECTURE);
		sb.append(":");
		sb.append(getPrefecture());
		sb.append("\n");
		sb.append(CITY);
		sb.append(":");
		sb.append(getCity());
		sb.append("\n");
		sb.append(TOWN);
		sb.append(":");
		sb.append(getTwon());
		sb.append("\n");
		return sb.toString();
	}
}
