package com.noqwerty.sqw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class BitmapUtil {
	public static Bitmap getBitmapFromURL(String strUrl) {
		try {
			java.net.URL url = new java.net.URL(strUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.setConnectTimeout(10 * 60 * 1000);
			connection.setReadTimeout(10 * 60 * 1000);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Bitmap getResizedBitmap(Bitmap bitmap, float newWidth,
			float newHeight) {
		Bitmap resizedBitmap;

		try {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;
			// CREATE A MATRIX FOR THE MANIPULATION
			Matrix matrix = new Matrix();
			// RESIZE THE BIT MAP
			matrix.postScale(scaleWidth, scaleHeight);

			// "RECREATE" THE NEW BITMAP
			resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
					matrix, false);
		} catch (Exception e) {
			e.printStackTrace();
			resizedBitmap = null;
		}

		return resizedBitmap;
	}
}
