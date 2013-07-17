package jp.idumo.core.doclet.perser;

import java.util.HashMap;
import java.util.Map;

import jp.idumo.core.doclet.json.AnnotationArrayValue;
import jp.idumo.core.doclet.json.IJSONValue;
import jp.idumo.core.doclet.json.StringValue;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.AnnotationTypeElementDoc;
import com.sun.javadoc.AnnotationValue;

/**
 * @author Yusei SHIGENOBU
 */
public class Model implements IAnnotation {

	private static final String TAG = "model";
	
	private Map<String, IJSONValue> items = new HashMap<String, IJSONValue>();
	
	public Model(String clazzName, AnnotationDesc annotation) {

//		System.out.println(clazzName);
//		System.out.println(annotation);
		items.put("package", new StringValue(clazzName));
		for (ElementValuePair elementValuePair : annotation.elementValues()) {
			AnnotationTypeElementDoc elementDoc = elementValuePair.element();
			AnnotationValue aValue = elementValuePair.value();
			String elementName = elementDoc.name();
			if (elementName.equals(TAG)) {
//				System.out.println(aValue);
				items.put(TAG, new AnnotationArrayValue(aValue));
			}
		}
	}
		
	@Override
	public Map<String, IJSONValue> getKVMap() {
		return items;
	}
}
