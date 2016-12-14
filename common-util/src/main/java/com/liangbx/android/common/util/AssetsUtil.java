package com.liangbx.android.common.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * <p>Title: Assets工具<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/13
 */
public class AssetsUtil {

	public static String getStringFromFile(Context context, String fileName) {
		StringBuilder content = new StringBuilder();
		BufferedReader bufferedReader = null;
		InputStream inputStream = null;
		AssetManager assetManager = context.getAssets();
		try {
			inputStream = assetManager.open(fileName);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line);
			}
			bufferedReader.close();
			inputStream.close();
			return content.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
				if(inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static File copyToStorage(Context context, String assetPath, String storagePath) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = context.getAssets().open(assetPath);
			File destFile = new File(storagePath);
			outputStream = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
			return destFile;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if(outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
