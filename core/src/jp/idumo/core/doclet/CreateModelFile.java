package jp.idumo.core.doclet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import jp.idumo.core.doclet.JSONBuilder;
import jp.idumo.core.doclet.perser.Model;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

/**
 * idumoサーバにmodel.jsonを作成する
 * @author Yusei SHIGENOBU
 *
 */
public class CreateModelFile {
	private static final String ENCODING = "UTF-8";
	private static final String MODEL_JSON_NAME = "model.json";
	private static final String I_MODEL = "IDUMOModel";

	public static boolean start(RootDoc root) throws IOException, FileNotFoundException {
		
		boolean isItem;
		File idumomodel = new File(System.getProperty("user.dir") + "/" + MODEL_JSON_NAME);
//		System.out.println(idumomodel.getPath());
		PrintWriter pwModel   = new PrintWriter(new OutputStreamWriter(new FileOutputStream(idumomodel), ENCODING));
		JSONArrayTemplate template = new JSONArrayTemplate();
		ClassDoc[] classes = root.classes();
		for (ClassDoc classDoc : classes) {
			String classname = classDoc.toString();
			isItem = false;
			AnnotationDesc[] annotations = classDoc.annotations();
			JSONBuilder json = new JSONBuilder();
			
			for (AnnotationDesc annotation : annotations) {
				String typename = annotation.annotationType().name();
				if(typename.equals(I_MODEL)) {
					isItem = true;
					json.add(new Model(classname, annotation));
				}
			}
			if(isItem){
				template.add(json);
			}
		}
		pwModel.println(template.toJsonString());
		pwModel.close();
		return true;
	}
}
