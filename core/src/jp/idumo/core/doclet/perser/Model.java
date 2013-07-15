package jp.idumo.core.doclet.perser;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
//import java.util.Set;

import jp.idumo.core.doclet.json.AnnotationArrayValue;
import jp.idumo.core.doclet.json.IJSONValue;
import jp.idumo.core.doclet.json.StringArrayValue;
//import jp.idumo.core.doclet.json.StringKVListValue;
import jp.idumo.core.doclet.json.StringValue;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.AnnotationTypeElementDoc;
import com.sun.javadoc.AnnotationValue;
//import com.sun.javadoc.ClassDoc;

/**
 * @author Yusei SHIGENOBU
 */
public class Model implements IAnnotation {

	private static final String TAG = "model";
	private List<String> modelList;
	
//	private Set<String> classList =new HashSet<String>();
	private Map<String, IJSONValue> items = new HashMap<String, IJSONValue>();
	
	public Model(String clazzName, AnnotationDesc annotation) {

		items.put("package", new StringValue(clazzName));
		List<String> annotationList = new ArrayList<String>();
		for (ElementValuePair elementValuePair : annotation.elementValues()) {
			AnnotationTypeElementDoc elementDoc = elementValuePair.element();
			AnnotationValue aValue = elementValuePair.value();
			String elementName = elementDoc.name();
			if (elementName.equals(TAG)) {
				AnnotationArrayValue array = new AnnotationArrayValue(aValue);
				annotationList.addAll(array.toStringList());
//				items.put(TAG, new AnnotationArrayValue(aValue));
			}
		}
		modelList = annotationList;
	}
		
	@Override
	public Map<String, IJSONValue> getKVMap() {
		Map<String, IJSONValue> items = new HashMap<String, IJSONValue>();
//		classList.remove(Object.class.getName());
		items.put(TAG, new StringArrayValue(new ArrayList<String>(modelList)));
//		items.put(TAG, new StringKVListValue(keys, modelList));
		return items;
	}
}
