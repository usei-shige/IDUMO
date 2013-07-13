package jp.idumo.core.doclet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import jp.idumo.core.doclet.JSONBuilder;
import jp.idumo.core.doclet.perser.Model;

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
	
	public static boolean start(RootDoc root) throws IOException {
		
		File idumomodel = new File(System.getProperty("user.die") + "/" + MODEL_JSON_NAME);
		System.out.println(idumomodel.getPath());
		PrintWriter pwModel   = new PrintWriter(new OutputStreamWriter(new FileOutputStream(idumomodel), ENCODING));
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		ClassDoc[] classes = root.classes();
		for (ClassDoc classDoc : classes) {
			String classname = classDoc.toString();
			Model mdl = new Model(classDoc);
			if (mdl.isContainDataElement()) {
				JSONBuilder json = new JSONBuilder();
				json.add(mdl);
				builder.append(String.format("  \"%s\":%s, \n", classname, json));
			}
		}
		builder.setLength(builder.length() -2);
		builder.append("\n}");
		pwModel.println(builder);
		pwModel.close();
		return true;
	}
}
