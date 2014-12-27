package com.noqwerty.sqw.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class RawUtil {

	public static String readFileInHTMLFormat(Context context, int rawResourceId) {
		InputStream is = context.getResources().openRawResource(rawResourceId);

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if (i != 0) {
					sb.append("<br>");
				}
				sb.append(line + "\n");

				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
