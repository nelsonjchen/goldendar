package com.mindflakes.goldendar;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/15/11
 * Time: 12:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Base {

    protected StringBuffer loadContent(String filename) throws IOException {
		StringBuffer content = new StringBuffer();
		File contentFile = new File(ClassLoader.getSystemClassLoader().getResource(filename).getFile());
		Reader freader = new FileReader(contentFile);
		BufferedReader in = new BufferedReader(freader);
		String str;
		while ((str = in.readLine()) != null) {
			content.append(str);
			content.append("\n");
		}
		in.close();
		freader.close();
		return content;
	}
}
