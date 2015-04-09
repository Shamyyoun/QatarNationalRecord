package com.noqwerty.sqw.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;

public class FileUtil {
	/*
	 * used to save image in HD in app data folder
	 */
	public static void saveImage(Context context, Bitmap bitmap,
			String folderName, String imageName) {
		File imagesDir = new File(context.getFilesDir() + "/" + folderName);
		if (!imagesDir.exists()) {
			imagesDir.mkdir();
		}

		File image = new File(imagesDir, imageName);

		try {
			FileOutputStream outStream = new FileOutputStream(image);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);

			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
